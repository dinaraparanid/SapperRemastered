package org.game

import java.awt.*
import java.awt.event.*
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import javax.sound.sampled.AudioSystem
import javax.swing.*
import javax.swing.SwingUtilities.*
import org.main.Program
import org.player.PlayerRepository
import kotlin.concurrent.thread

/**
 * Game itself
 * @param x amount of rows
 * @param y amount of columns
 * @param mines amount of mines
 * @param name name of player
 * @param gameType type of game
 * (easy, medium, hard or custom)
 */

internal class Game(
    private val x: Int,
    private val y: Int,
    private val mines: Int,
    private val name: String,
    password: String,
    private val gameType: GameType
) {

    companion object {

        /**
         * Gets image of required scale
         * @param srcImg image itself
         * @param w width
         * @param h height
         * @return scaled image
         */

        internal fun getScaledImage(srcImg: Image, w: Int, h: Int) =
            BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB).also {
                it.createGraphics().apply {
                    setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR)
                    drawImage(srcImg, 0, 0, w, h, null)
                    dispose()
                }
            }

        /**
         * Choose game type
         * @param name name of player
         */

        fun chooseGameType(name: String, password: String) = when (JOptionPane.showInputDialog(
            null,
            "Choose game type:",
            "Game Type",
            JOptionPane.QUESTION_MESSAGE,
            null,
            arrayOf("Easy", "Medium", "Hard", "Custom"),
            "Easy"
        )) {
            null -> Unit
            "Easy" -> Easy.start(name, password)
            "Medium" -> Medium.start(name, password)
            "Hard" -> Hard.start(name, password)
            else -> Custom.start(name, password)
        }
    }

    private val frame = JFrame("Game").apply {
        bounds = Rectangle(500, 200, 800, 800)
        isAlwaysOnTop = true
        isFocusable = true

        iconImage = ImageIcon(
            getScaledImage(
                ImageIO.read(File("src/main/resources/utils/mine.png")),
                200,
                200
            )
        ).image
    }

    private val player = PlayerRepository.getByName(name).orNull() ?: PlayerRepository.add(name, password)
    private var minesLeft = mines
    private var cellsLeft = x * y - mines

    private val status = JLabel("$minesLeft").apply {
        font = Font(Font.SANS_SERIF, Font.BOLD, 25)
        foreground = Color.BLACK
    }

    private val timer = JLabel("Time: 0").apply {
        font = Font(Font.SANS_SERIF, Font.BOLD, 25)
        foreground = Color.BLACK
    }

    private val bts = Array(x) { Array(y) { arrayOf(JButton(), false, false) } }

    private val mineTable = Array(x) { BooleanArray(y) }.also {
        (0 until x * y).shuffled().take(mines).forEach { cell -> it[cell / y][cell % y] = true }
    }

    private val grid = JPanel(
        GridLayout().also { layout ->
            layout.rows = y
            layout.columns = x
        }
    )

    private val numberTable = Array(x) { IntArray(y) }.also {

        // 10 threads for initializing number table

        val d = x * y / 10
        val numsInThreads = Array(10) { d }
        val ost = x * y % 10
        (0 until ost).forEach { i -> numsInThreads[i]++ }

        var cur = 0

        (0 until 10).forEach { i ->
            val finalCur = cur

            thread {
                (finalCur until finalCur + numsInThreads[i]).forEach { t ->
                    val xc = t / y
                    val yc = t % y

                    if (mineTable[xc][yc]) {
                        if (xc > 0) it[xc - 1][yc]++
                        if (xc < x - 1) it[xc + 1][yc]++
                        if (yc > 0) it[xc][yc - 1]++
                        if (yc < y - 1) it[xc][yc + 1]++
                        if (xc > 0 && yc > 0) it[xc - 1][yc - 1]++
                        if (xc > 0 && yc < y - 1) it[xc - 1][yc + 1]++
                        if (xc < x - 1 && yc > 0) it[xc + 1][yc - 1]++
                        if (xc < x - 1 && yc < y - 1) it[xc + 1][yc + 1]++
                    }
                }
            }

            cur += numsInThreads[i]
        }
    }

    /**
     * Mouse Click Handler.
     * Opens cells when mouse is released.
     *
     * @param bt cell button
     * @param xc height
     * @param yc width
     */

    private inner class MouseClickListener(
        private val bt: JButton,
        private val xc: Int,
        private val yc: Int
    ) : MouseListener {
        private lateinit var numImages: Array<BufferedImage?>
        private lateinit var mineImage: BufferedImage

        private val zeroes = ArrayDeque<Pair<Int, Int>>()

        private fun open(xcc: Int, ycc: Int) {
            if (!(bts[xcc][ycc][1] as Boolean)) {
                player.openCell()

                (bts[xcc][ycc][0] as JButton).icon =
                    ImageIcon(numImages[numberTable[xcc][ycc]])

                bts[xcc][ycc][1] = true
                grid.updateUI()
                cellsLeft--

                if (numberTable[xcc][ycc] == 0)
                    zeroes.addLast(xcc to ycc)
            }
        }

        override fun mouseReleased(e: MouseEvent?) {
            val flagImage = getScaledImage(
                ImageIO.read(
                    File("src/main/resources/utils/flag.png")
                ),
                bt.width,
                bt.height
            )

            if (e?.source === bt) {
                when {
                    isLeftMouseButton(e) -> {
                        when {
                            mineTable[xc][yc] -> {
                                mineImage = getScaledImage(
                                    ImageIO.read(
                                        File("src/main/resources/utils/mine.png")
                                    ),
                                    bt.width,
                                    bt.height
                                )

                                // 10 threads for initializing icon
                                // of mines when player is lost

                                val d = x * y / 10
                                val numsInThreads = Array(10) { d }
                                val ost = x * y % 10
                                (0 until ost).forEach { numsInThreads[it]++ }

                                var cur = 0

                                (0 until 10).forEach { i ->
                                    val finalCur = cur

                                    thread {
                                        (finalCur until finalCur + numsInThreads[i]).forEach { t ->
                                            val xc2 = t / y
                                            val yc2 = t % y

                                            if (mineTable[xc2][yc2])
                                                (bts[xc2][yc2].first() as JButton).icon =
                                                    ImageIcon(mineImage)
                                        }
                                    }

                                    cur += numsInThreads[i]
                                }

                                AudioSystem.getClip().run {
                                    open(
                                        AudioSystem.getAudioInputStream(
                                            File("src/main/resources/utils/Boom (Big Shaq).wav")
                                                .absoluteFile
                                        )
                                    )
                                    start()
                                }

                                Program.playing = false

                                JOptionPane.showMessageDialog(
                                    frame,
                                    "You've exploded :(",
                                    "BOOM",
                                    JOptionPane.INFORMATION_MESSAGE
                                )

                                frame.isVisible = false
                                thread { PlayerRepository.update(player) }
                            }

                            else -> if (bt.icon == null || bt.icon == flagImage) {
                                numImages = Array<BufferedImage?>(9) { null }.also {
                                    (0..8).forEach { ind ->
                                        it[ind] = getScaledImage(
                                            ImageIO.read(
                                                File("src/main/resources/utils/$ind.png")
                                            ),
                                            bt.width,
                                            bt.height
                                        )
                                    }
                                }

                                player.openCell()
                                bt.icon = ImageIcon(numImages[numberTable[xc][yc]])

                                if (bts[xc][yc][1] is Boolean)
                                    bts[xc][yc][1] = true

                                cellsLeft--

                                if (numberTable[xc][yc] == 0) {

                                    // Breadth First Search

                                    zeroes.clear()
                                    zeroes.addLast(xc to yc)

                                    while (zeroes.isNotEmpty()) {
                                        zeroes.first().let { (xc2, yc2) ->
                                            if (xc2 > 0) open(xc2 - 1, yc2)
                                            if (xc2 < x - 1) open(xc2 + 1, yc2)
                                            if (yc2 > 0) open(xc2, yc2 - 1)
                                            if (yc2 < y - 1) open(xc2, yc2 + 1)
                                            if (xc2 > 0 && yc2 > 0) open(xc2 - 1, yc2 - 1)
                                            if (xc2 > 0 && yc2 < y - 1) open(xc2 - 1, yc2 + 1)
                                            if (xc2 < x - 1 && yc2 > 0) open(xc2 + 1, yc2 - 1)
                                            if (xc2 < x - 1 && yc2 < y - 1) open(xc2 + 1, yc2 + 1)
                                        }

                                        zeroes.removeFirst()
                                    }
                                }

                                if (cellsLeft == 0) {
                                    Program.playing = false

                                    JOptionPane.showMessageDialog(
                                        frame,
                                        "You've survived!",
                                        "Congratulations!",
                                        JOptionPane.INFORMATION_MESSAGE
                                    )

                                    player.won(gameType)
                                    frame.isVisible = false
                                }

                                thread { PlayerRepository.update(player) }
                            }
                        }
                    }

                    isMiddleMouseButton(e) -> {
                        if (minesLeft != 0) {
                            when {
                                bts[xc][yc][2] as Boolean -> {
                                    bt.icon = null
                                    bts[xc][yc][2] = false
                                    minesLeft++
                                }

                                else -> {
                                    bt.icon = ImageIcon(flagImage)
                                    bts[xc][yc][2] = true
                                    minesLeft = maxOf(minesLeft - 1, 0)
                                }
                            }

                            status.text = "$minesLeft"
                        }
                    }
                }
            }
        }

        override fun mouseClicked(e: MouseEvent?) = Unit
        override fun mousePressed(e: MouseEvent?) = Unit
        override fun mouseEntered(e: MouseEvent?) = Unit
        override fun mouseExited(e: MouseEvent?) = Unit
    }

    fun start() {
        Program.playing = true

        player.startPlaying(gameType)
        thread { PlayerRepository.update(player) }

        bts.forEachIndexed { xc, row ->
            row.forEachIndexed { yc, triple ->
                triple.let { (bt) ->
                    grid.add(bt as JButton)
                    bt.addMouseListener(MouseClickListener(bt, xc, yc))
                    bt.background = Color(0, 162, 232)
                }
            }
        }

        frame.apply {
            contentPane.let {
                it.layout = BorderLayout()

                it.add(BorderLayout.NORTH, JPanel().apply {
                    add(
                        JLabel(
                            ImageIcon(
                                getScaledImage(
                                    ImageIO.read(File("src/main/resources/utils/mine.png")),
                                    50,
                                    50
                                )
                            )
                        )
                    )
                    add(status)
                    add(Box.createHorizontalStrut(200))
                    add(timer)
                })

                it.add(grid)
            }

            isVisible = true
            background = Color.BLUE
            isAlwaysOnTop = true
            isFocusable = true

            var start = 0

            thread {
                while (Program.playing) {
                    Thread.sleep(1000)
                    timer.text = "Time: ${++start}"
                }
            }

            thread {
                while (isVisible) print("")
                Program.playing = false
            }
        }
    }
}
