package com.paranid5.presentation.game.ui

import com.paranid5.presentation.utils.getScaledImage
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.swing.Swing
import java.awt.*
import java.awt.event.ComponentAdapter
import java.awt.event.ComponentEvent
import java.awt.event.WindowEvent
import java.awt.event.WindowListener
import java.io.File
import javax.imageio.ImageIO
import javax.swing.*

fun GameFrame(
    rows: Int,
    columns: Int,
    buttons: List<List<CellButton>>,
    isPlayingState: MutableStateFlow<Boolean>,
    minesLeftState: MutableStateFlow<Int>,
): JFrame {
    val coroutineScope = CoroutineScope(Dispatchers.Swing)
    val timerState = MutableStateFlow(0)

    val panel = GameFramePanel(minesLeftState.value, timerState.value)

    val frame = JFrame("Game").apply {
        bounds = Rectangle(500, 200, 800, 800)
        background = Color.BLUE

        iconImage = ImageIcon(
            getScaledImage(
                ImageIO.read(File("src/main/resources/utils/mine.png")),
                200,
                200
            )
        ).image

        contentPane.run {
            layout = BorderLayout()
            add(BorderLayout.NORTH, panel)
            add(GameGrid(rows, columns, buttons))
        }

        addWindowListener(WindowListener(isPlayingState, timerState))

        addComponentListener(object : ComponentAdapter() {
            override fun componentHidden(e: ComponentEvent?) {
                super.componentHidden(e)
                coroutineScope.cancel()
            }
        })
    }

    coroutineScope.launch(Dispatchers.Swing) {
        combine(minesLeftState, timerState) { minesLeft, time -> minesLeft to time }
            .collectLatest { (minesLeft, time) -> panel.update(minesLeft, time) }
    }

    return frame
}

private fun GameGrid(rows: Int, columns: Int, buttons: List<List<CellButton>>) = JPanel(
    GridLayout().also { layout ->
        layout.rows = rows
        layout.columns = columns
    }
).also { buttons.flatten().forEach(it::add) }

private fun MinesLeftLabel(minesLeft: Int) = JLabel("$minesLeft").apply {
    font = Font(Font.SANS_SERIF, Font.BOLD, 25)
    foreground = Color.BLACK
}

private fun TimerLabel(time: Int) = JLabel("Time: $time").apply {
    font = Font(Font.SANS_SERIF, Font.BOLD, 25)
    foreground = Color.BLACK
}

private fun GameFramePanel(minesLeft: Int, time: Int) = object : JPanel() {
    private val minesLeftLabel = MinesLeftLabel(minesLeft)
    private val timerLabel = TimerLabel(time)

    fun update(minesLeft: Int, time: Int) {
        minesLeftLabel.text = "$minesLeft"
        timerLabel.text = "Time: $time"
    }

    init {
        add(MineIcon())
        add(minesLeftLabel)
        add(Box.createHorizontalStrut(200))
        add(timerLabel)
    }
}

private fun MineIcon() = JLabel(
    ImageIcon(
        getScaledImage(
            ImageIO.read(File("src/main/resources/utils/mine.png")),
            50,
            50
        )
    )
)

private fun WindowListener(
    isPlayingState: MutableStateFlow<Boolean>,
    timerState: MutableStateFlow<Int>
): WindowListener {
    val coroutineScope = CoroutineScope(Dispatchers.Swing)

    return object : WindowListener {
        override fun windowOpened(e: WindowEvent?) {
            isPlayingState.update { true }

            coroutineScope.launch(Dispatchers.Swing) {
                while (isPlayingState.value) {
                    delay(1000)
                    timerState.update { it + 1 }
                }
            }
        }

        override fun windowClosed(e: WindowEvent?) {
            isPlayingState.value = false
        }

        override fun windowActivated(e: WindowEvent?) = Unit
        override fun windowClosing(e: WindowEvent?) = Unit
        override fun windowIconified(e: WindowEvent?) = Unit
        override fun windowDeiconified(e: WindowEvent?) = Unit
        override fun windowDeactivated(e: WindowEvent?) = Unit
    }
}