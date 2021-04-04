package org.game

import java.awt.*
import java.awt.event.*
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import javax.sound.sampled.AudioSystem
import javax.swing.*
import javax.swing.SwingUtilities.*
import org.player.PlayerBase

/**
 * Gets image of required scale
 * @param srcImg image itself
 * @param w width
 * @param h height
 * @return scaled image
 */

private fun getScaledImage(srcImg: Image, w: Int, h: Int) = BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB).also {
    it.createGraphics().apply {
        setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR)
        drawImage(srcImg, 0, 0, w, h, null)
        dispose()
    }
}

/**
 * Game itself
 * @param x amount of rows
 * @param y amount of columns
 * @param name name of player
 */

internal fun game(x: Int, y: Int, mines: Int, name: String, gameType: GameType) {
    val player = PlayerBase[name]
    val frame = JFrame("Game").apply { bounds = Rectangle(500, 200, 800, 800) }
    var minesLeft = mines
    var cellsLeft = x * y - mines

    player.startPlaying(gameType)

    val mineTable = Array(x) { BooleanArray(y) { false } }.also {
        (0 until x * y).shuffled().take(mines).forEach { cell -> it[cell / y][cell % y] = true }
    }

    val numberTable = Array(x) { IntArray(y) { 0 } }.also {
        mineTable.forEachIndexed { xc, row ->
            row.forEachIndexed { yc, cell ->
                if (cell) {
                    if (xc > 0)                     it[xc - 1][yc]++
                    if (xc < x - 1)                 it[xc + 1][yc]++
                    if (yc > 0)                     it[xc][yc - 1]++
                    if (yc < y - 1)                 it[xc][yc + 1]++
                    if (xc > 0 && yc > 0)           it[xc - 1][yc - 1]++
                    if (xc > 0 && yc < y - 1)       it[xc - 1][yc + 1]++
                    if (xc < x - 1 && yc > 0)       it[xc + 1][yc - 1]++
                    if (xc < x - 1 && yc < y - 1)   it[xc + 1][yc + 1]++
                }
            }
        }
    }

    val status = JLabel("Mines left: $minesLeft")

    val grid = JPanel(
        GridLayout().also { layout ->
            layout.rows = y
            layout.columns = x
        }
    ).also { panel ->
        Array(x) { Array(y) { arrayOf(JButton(), false, false) } /* mutable triple */ }.let { bts ->
            bts.forEachIndexed { xc, row ->
                row.forEachIndexed { yc, triple ->
                    panel.add(
                        triple.let { (bt, _, _) ->
                            (bt as JButton).addMouseListener(
                                object : MouseListener {
                                    override fun mouseReleased(e: MouseEvent?) {
                                        val mineImage = getScaledImage(
                                            ImageIO.read(
                                                File("src/main/resources/utils/mine.png")
                                            ),
                                            bt.width,
                                            bt.height
                                        )

                                        val flagImage = getScaledImage(
                                            ImageIO.read(
                                                File("src/main/resources/utils/flag.png")
                                            ),
                                            bt.width,
                                            bt.height
                                        )

                                        val numImages = Array<BufferedImage?>(9) { null }.also {
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

                                        if (e?.source === bt) {
                                            when {
                                                isLeftMouseButton(e) -> {
                                                    when {
                                                        mineTable[xc][yc] -> {
                                                            bts.forEachIndexed { xc2, row2 ->
                                                                row2.forEachIndexed { yc2, (cell, _) ->
                                                                    if (mineTable[xc2][yc2]) (cell as JButton).icon =
                                                                        ImageIcon(mineImage)
                                                                }
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

                                                            JOptionPane.showMessageDialog(
                                                                frame,
                                                                "You've exploded :(",
                                                                "BOOM",
                                                                JOptionPane.INFORMATION_MESSAGE
                                                            )

                                                            frame.isVisible = false
                                                            PlayerBase.save()
                                                        }

                                                        else -> {
                                                            bt.icon = ImageIcon(numImages[numberTable[xc][yc]])
                                                            println(numberTable[xc][yc])

                                                            if (bts[xc][yc][1] is Boolean)
                                                                bts[xc][yc][1] = true

                                                            cellsLeft--

                                                            if (numberTable[xc][yc] == 0) {

                                                                // Breadth First Search

                                                                ArrayDeque<Pair<Int, Int>>().let { zeroes ->
                                                                    zeroes.addLast(xc to yc)

                                                                    val open = { xcc: Int, ycc: Int ->
                                                                        if (!(bts[xcc][ycc][1] as Boolean)) {
                                                                            (bts[xcc][ycc][0] as JButton).icon =
                                                                                ImageIcon(numImages[numberTable[xcc][ycc]])

                                                                            bts[xcc][ycc][1] = true
                                                                            panel.updateUI()
                                                                            cellsLeft--

                                                                            if (numberTable[xcc][ycc] == 0)
                                                                                zeroes.addLast(xcc to ycc)
                                                                        }
                                                                    }

                                                                    while (zeroes.isNotEmpty()) {
                                                                        zeroes.first().let { (xc2, yc2) ->
                                                                            if (xc2 > 0)                    open(xc2 - 1, yc2)
                                                                            if (xc2 < x - 1)                open(xc2 + 1, yc2)
                                                                            if (yc2 > 0)                    open(xc2, yc2 - 1)
                                                                            if (yc2 < y - 1)                open(xc2, yc2 + 1)
                                                                            if (xc2 > 0 && yc2 > 0)         open(xc2 - 1, yc2 - 1)
                                                                            if (xc2 > 0 && yc2 < y - 1)     open(xc2 - 1, yc2 + 1)
                                                                            if (xc2 < x - 1 && yc2 > 0)     open(xc2 + 1, yc2 - 1)
                                                                            if (xc2 < x - 1 && yc2 < y - 1) open(xc2 + 1, yc2 + 1)
                                                                        }

                                                                        zeroes.removeFirst()
                                                                    }
                                                                }
                                                            }

                                                            if (cellsLeft == 0) {
                                                                JOptionPane.showMessageDialog(
                                                                    frame,
                                                                    "You've survived!",
                                                                    "Congratulations!",
                                                                    JOptionPane.INFORMATION_MESSAGE
                                                                )

                                                                player.won(gameType)
                                                                PlayerBase.save()
                                                                frame.isVisible = false
                                                            }
                                                        }
                                                    }
                                                }

                                                isMiddleMouseButton(e) -> {
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

                                                    status.text = "Mines left: $minesLeft"
                                                }
                                            }
                                        }
                                    }

                                    override fun mouseClicked(e: MouseEvent?) {}
                                    override fun mousePressed(e: MouseEvent?) {}
                                    override fun mouseEntered(e: MouseEvent?) {}
                                    override fun mouseExited(e: MouseEvent?) {}
                                }
                            )

                            bt
                        }
                    )
                }
            }
        }
    }

    frame.apply {
        contentPane.let {
            it.layout = BorderLayout()
            it.add(BorderLayout.NORTH, status)
            it.add(grid)
        }

        isVisible = true
    }
}
