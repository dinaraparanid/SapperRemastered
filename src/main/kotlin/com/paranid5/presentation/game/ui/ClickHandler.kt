package com.paranid5.presentation.game.ui

import com.paranid5.data.player.Player
import com.paranid5.data.player.PlayerRepository
import com.paranid5.presentation.utils.getScaledImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.swing.Swing
import org.jetbrains.exposed.sql.transactions.transaction
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import javax.sound.sampled.AudioSystem
import javax.swing.ImageIcon
import javax.swing.JFrame
import javax.swing.JOptionPane

fun startCellOpening(
    rows: Int,
    columns: Int,
    player: Player,
    button: CellButton,
    numberTable: Array<IntArray>,
    numberImages: Array<BufferedImage>,
    zeroes: ArrayDeque<Pair<Int, Int>>,
    buttons: List<List<CellButton>>,
    cellsLeftState: MutableStateFlow<Int>
) {
    if (button.cellState == CellButton.CellState.OPENED)
        return

    val row = button.row
    val column = button.column

    button.icon = ImageIcon(numberImages[numberTable[row][column]])

    if (button.cellState == CellButton.CellState.CLOSED)
        button.cellState = CellButton.CellState.OPENED

    transaction {
        println("Open cell (${button.row}; ${button.column}). Total: ${++player.openedCells}")

        cellsLeftState.update { it - 1 }

        if (numberTable[row][column] == 0) {

            // Breadth First Search

            zeroes.clear()
            zeroes.addLast(row to column)

            fun open(row: Int, column: Int) = openSingleCellAndAddToZeroes(
                buttons[row][column],
                player,
                zeroes,
                numberTable,
                numberImages,
                cellsLeftState
            )

            while (zeroes.isNotEmpty()) {
                zeroes.first().let { (xc2, yc2) ->
                    if (xc2 > 0) open(xc2 - 1, yc2)
                    if (xc2 < rows - 1) open(xc2 + 1, yc2)
                    if (yc2 > 0) open(xc2, yc2 - 1)
                    if (yc2 < columns - 1) open(xc2, yc2 + 1)
                    if (xc2 > 0 && yc2 > 0) open(xc2 - 1, yc2 - 1)
                    if (xc2 > 0 && yc2 < columns - 1) open(xc2 - 1, yc2 + 1)
                    if (xc2 < rows - 1 && yc2 > 0) open(xc2 + 1, yc2 - 1)
                    if (xc2 < rows - 1 && yc2 < columns - 1) open(xc2 + 1, yc2 + 1)
                }

                zeroes.removeFirst()
            }
        }
    }
}

private fun openSingleCellAndAddToZeroes(
    button: CellButton,
    player: Player,
    zeroes: ArrayDeque<Pair<Int, Int>>,
    numberTable: Array<IntArray>,
    numberImages: Array<BufferedImage>,
    cellsLeftState: MutableStateFlow<Int>
) {
    if (button.cellState != CellButton.CellState.CLOSED)
        return

    println("Open cell (${button.row}; ${button.column}). Total: ${++player.openedCells}")

    button.run {
        icon = ImageIcon(numberImages[numberTable[row][column]])
        cellState = CellButton.CellState.OPENED

        if (numberTable[row][column] == 0)
            zeroes.addLast(row to column)
    }

    cellsLeftState.update { it - 1 }
}

fun boom(
    rows: Int,
    columns: Int,
    mineTable: Array<BooleanArray>,
    buttons: List<List<CellButton>>,
    isPlayingState: MutableStateFlow<Boolean>
) {
    // 8 threads for initializing icon
    // of mines when player is lost

    val coroutineScope = CoroutineScope(Dispatchers.Swing)

    val div = rows * columns / 8
    val rem = rows * columns % 8

    val numsInThreads = Array(8) { div }.also { numsInThreads ->
        repeat(rem) { ++numsInThreads[it] }
    }

    val mineImage = buttons[0][0].run { MineImage(width, height) }

    numsInThreads.fold(0) { openedCells, curCells ->
        coroutineScope.launch(Dispatchers.Default) {
            (openedCells until openedCells + curCells).forEach { t ->
                val xc2 = t / rows
                val yc2 = t % rows

                if (mineTable[xc2][yc2])
                    buttons[xc2][yc2].icon = ImageIcon(mineImage)
            }
        }

        openedCells + curCells
    }

    BoomSound().start()
    isPlayingState.update { false }
}

private fun BoomSound() = AudioSystem.getClip().apply {
    open(
        AudioSystem.getAudioInputStream(
            File("src/main/resources/utils/Boom (Big Shaq).wav")
                .absoluteFile
        )
    )
}

fun setFlag(button: CellButton, flagImage: BufferedImage, minesLeftState: MutableStateFlow<Int>) {
    if (minesLeftState.value != 0) {
        when (button.cellState) {
            CellButton.CellState.FLAG -> {
                button.icon = null
                button.cellState = CellButton.CellState.CLOSED
                minesLeftState.update { it + 1 }
            }

            else -> {
                button.icon = ImageIcon(flagImage)
                button.cellState = CellButton.CellState.FLAG
                minesLeftState.update { maxOf(it - 1, 0) }
            }
        }
    }
}

fun showGameOverMessage(gameFrame: JFrame) = JOptionPane.showMessageDialog(
    gameFrame,
    "You've exploded :(",
    "BOOM",
    JOptionPane.INFORMATION_MESSAGE
)

fun showVictoryMessage(gameFrame: JFrame) = JOptionPane.showMessageDialog(
    gameFrame,
    "You've survived!",
    "Congratulations!",
    JOptionPane.INFORMATION_MESSAGE
)

fun FlagImage(width: Int, height: Int) = getScaledImage(
    ImageIO.read(File("src/main/resources/utils/flag.png")),
    width,
    height
)

private fun MineImage(width: Int, height: Int) = getScaledImage(
    ImageIO.read(File("src/main/resources/utils/mine.png")),
    width,
    height
)