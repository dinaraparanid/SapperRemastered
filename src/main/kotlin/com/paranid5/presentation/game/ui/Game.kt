package com.paranid5.presentation.game.ui

import com.paranid5.data.player.Player
import com.paranid5.data.player.PlayerRepository
import com.paranid5.presentation.game.types.GameTypes
import com.paranid5.presentation.utils.getScaledImage
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.swing.Swing
import java.awt.event.ComponentAdapter
import java.awt.event.ComponentEvent
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import java.io.File
import javax.imageio.ImageIO
import javax.swing.SwingUtilities.isLeftMouseButton
import javax.swing.SwingUtilities.isRightMouseButton

fun startGame(
    rows: Int,
    columns: Int,
    mines: Int,
    player: Player,
    gameType: GameTypes,
    isPlayingState: MutableStateFlow<Boolean>,
) {
    val coroutineScope = CoroutineScope(Dispatchers.Swing)

    isPlayingState.update { true }

    PlayerRepository.updateAsync(player) { incrementPlayedGames(gameType) }

    val minesLeftState = MutableStateFlow(mines)
    val cellsLeftState = MutableStateFlow(rows * columns - mines)

    val mineTable = MineTable(rows, columns, mines)
    val zeroes = ArrayDeque<Pair<Int, Int>>()

    val buttons = (0 until rows).map { row ->
        (0 until columns).map { column ->
            CellButton(row, column)
        }
    }

    val frame = GameFrame(rows, columns, buttons, isPlayingState, minesLeftState).apply {
        addComponentListener(object : ComponentAdapter() {
            override fun componentHidden(e: ComponentEvent?) {
                super.componentHidden(e)
                coroutineScope.cancel()
            }
        })
    }

    val numberTable = NumberTable(rows, columns, mineTable)

    buttons.forEachIndexed { row, r ->
        r.forEachIndexed { column, bt ->
            bt.addMouseListener(
                object : MouseListener {
                    override fun mouseReleased(e: MouseEvent?) {
                        if (e?.source === bt) {
                            when {
                                isLeftMouseButton(e) -> {
                                    when {
                                        mineTable[row][column] -> {
                                            boom(rows, columns, mineTable, buttons, isPlayingState)
                                            showGameOverMessage(frame)
                                            frame.isVisible = false
                                        }

                                        else -> startCellOpening(
                                            rows = rows,
                                            columns = columns,
                                            player = player,
                                            button = bt,
                                            numberTable = numberTable,
                                            numberImages = NumberImages(bt.width, bt.height),
                                            zeroes = zeroes,
                                            buttons = buttons,
                                            cellsLeftState = cellsLeftState
                                        )
                                    }
                                }

                                isRightMouseButton(e) -> setFlag(
                                    button = bt,
                                    flagImage = FlagImage(bt.width, bt.height),
                                    minesLeftState = minesLeftState
                                )
                            }
                        }
                    }

                    override fun mouseClicked(e: MouseEvent?) = Unit
                    override fun mousePressed(e: MouseEvent?) = Unit
                    override fun mouseEntered(e: MouseEvent?) = Unit
                    override fun mouseExited(e: MouseEvent?) = Unit
                }
            )
        }
    }

    coroutineScope.launch(Dispatchers.Swing) {
        cellsLeftState.collectLatest { cellsLeft ->
            println("Cells left: ${cellsLeftState.value}")

            if (cellsLeft == 0) {
                isPlayingState.update { false }
                PlayerRepository.updateAsync(player) { incrementVictories(gameType) }
                showVictoryMessage(frame)
                frame.isVisible = false
            }
        }
    }

    frame.run {
        isVisible = true
        isAlwaysOnTop = true
        isFocusable = true
    }
}

private fun MineTable(rows: Int, columns: Int, mines: Int) =
    Array(rows) { BooleanArray(columns) }.also { mineTable ->
        (0 until rows * columns)
            .shuffled()
            .take(mines)
            .forEach { cell ->
                mineTable[cell / columns][cell % columns] = true
            }
    }

private fun NumberTable(
    rows: Int,
    columns: Int,
    mineTable: Array<BooleanArray>
): Array<IntArray> {
    val coroutineScope = CoroutineScope(Dispatchers.Default)

    return Array(rows) { IntArray(columns) }.also { numTable ->
        // 8 threads for initializing number table

        val div = rows * columns / 8
        val rem = rows * columns % 8

        val numsInThreads = Array(8) { div }.also { numsInThreads ->
            (0 until rem).forEach { i -> numsInThreads[i]++ }
        }

        numsInThreads.fold(0) { initedCells, curCells ->
            coroutineScope.launch {
                (initedCells until initedCells + curCells).forEach { t ->
                    val row = t / columns
                    val column = t % columns

                    if (mineTable[row][column]) {
                        if (row > 0) numTable[row - 1][column]++
                        if (row < columns - 1) numTable[row + 1][column]++
                        if (column > 0) numTable[row][column - 1]++
                        if (column < columns - 1) numTable[row][column + 1]++
                        if (row > 0 && column > 0) numTable[row - 1][column - 1]++
                        if (row > 0 && column < rows - 1) numTable[row - 1][column + 1]++
                        if (row < columns - 1 && column > 0) numTable[row + 1][column - 1]++
                        if (row < columns - 1 && column < rows - 1) numTable[row + 1][column + 1]++
                    }
                }
            }

            initedCells + curCells
        }
    }
}

private fun NumberImages(buttonWidth: Int, buttonHeight: Int) =
    (0..8).map { i ->
        getScaledImage(
            ImageIO.read(File("src/main/resources/utils/$i.png")),
            buttonWidth,
            buttonHeight
        )
    }.toTypedArray()