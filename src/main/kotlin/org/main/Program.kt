package org.main

import org.game.Game
import java.awt.*
import java.awt.BorderLayout.*
import java.io.*
import javax.imageio.ImageIO
import javax.sound.sampled.AudioSystem
import javax.swing.*
import kotlin.concurrent.thread

/**
 * Main part of program
 * (all widgets and actions)
 */

object Program {
    internal var isPlaying = false

    private val frame = JFrame("SAPPER Remastered").also { frame ->
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.bounds = Rectangle(0, 0, 1920, 1080)

        frame.iconImage = ImageIcon(
            Game.getScaledImage(
                ImageIO.read(File("src/main/resources/utils/mine.png")),
                200,
                200
            )
        ).image

        frame.contentPane.add(
            object : JPanel() {
                override fun paintComponent(g: Graphics) {
                    super.paintComponent(g)
                    g.drawImage(ImageIO.read(File("src/main/resources/utils/background.png")), 0, 0, null)
                }

                init {
                    layout = GridBagLayout()
                    add(MainMenu().panel)
                }
            },
            CENTER
        )
    }

    /** Start of program */

    fun start() {
        frame.isVisible = true

        thread {
            while (frame.isVisible)
                AudioSystem.getClip().run {
                    open(
                        AudioSystem.getAudioInputStream(
                            File("src/main/resources/utils/Alexei Zakharov - Transcendental (Bonus Track).wav")
                                .absoluteFile
                        )
                    )
                    start()
                    Thread.sleep(334000)
                }
        }
    }
}