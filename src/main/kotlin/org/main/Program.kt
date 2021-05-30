package org.main

import org.game.*
import org.player.PlayerRepository
import java.awt.*
import java.awt.BorderLayout.*
import java.io.*
import javax.imageio.ImageIO
import javax.sound.sampled.AudioSystem
import javax.swing.*
import javax.swing.border.EmptyBorder
import kotlin.concurrent.thread
import kotlin.system.exitProcess

/**
 * Main part of program
 * (all widgets and actions)
 */

object Program {
    internal var playing = false

    private val gameBt = JButton("Game").apply {
        addActionListener {
            val name = JTextField(15)
            val password = JTextField(15)

            if (JOptionPane.showConfirmDialog(
                    null,
                    arrayOf(
                        JLabel("Name"),
                        name,
                        JLabel("Login"),
                        password
                    ),
                    "Sing In",
                    JOptionPane.OK_CANCEL_OPTION
                ) == JOptionPane.OK_OPTION
            ) Game.chooseGameType(name.text, password.text)
        }
    }

    private val resultsBt = JButton("Results").apply {
        addActionListener {
            object : JFrame() {
                init {
                    bounds = Rectangle(300, 300, 700, 700)
                    title = "Results"

                    add(
                        JScrollPane(
                            JTable(
                                PlayerRepository.all.map {
                                    it.Data().let { data ->
                                        arrayOf(
                                            data.name,
                                            data.easyVictories,
                                            data.easyGames,
                                            data.easyPercent,
                                            data.mediumVictories,
                                            data.mediumGames,
                                            data.mediumPercent,
                                            data.hardVictories,
                                            data.hardGames,
                                            data.hardPercent,
                                            data.victories,
                                            data.games,
                                            data.percent
                                        )
                                    }
                                }.toTypedArray(),
                                arrayOf(
                                    "Name",
                                    "Victories on Easy",
                                    "Amount of games on Easy",
                                    "Vitory percent on Easy",
                                    "Victories on Medium",
                                    "Amount of games on Medium",
                                    "Vitory percent on Medium",
                                    "Victories on Hard",
                                    "Amount of games on Hard",
                                    "Vitory percent on Hard",
                                    "Victories on all levels",
                                    "Amount of games on all levels",
                                    "Victories on all level"
                                )
                            )
                        )
                    )

                    isVisible = true
                }
            }
        }
    }

    private val helpBt = JButton("Help").apply {
        addActionListener {
            JFrame("Help").also { frame ->
                frame.bounds = Rectangle(300, 300, 600, 400)

                frame.add(
                    JTextArea(
                        "   Author: dinaraparanid (Follow on GitHub: https://github.com/dinaraparanid)\n\n" +
                                "   About project\n" +
                                "This project is a remaster of my old Sapper game (console version on C language).\n" +
                                "You can check it here: https://github.com/dinaraparanid/Sapper \n" +
                                "Game has 4 types of levels:\n" +
                                "Easy (8 x 8 and 16 mines)\n" +
                                "Medium (16 x 16 and 64 mines)\n" +
                                "Hard (32 x 32 and 256 mines)\n" +
                                "Custom with your game settings (width, height and mines > 0)\n\n" +
                                "   Gameplay\n\n" +
                                "As in classic sapper game from microsoft you are trying to open as many cells as you can.\n" +
                                "Numbers shows amount of mines in near cells. For example, it opened cell's number is 1,\n" +
                                "than there is one mine in neighbour cells. You can put flags to show that there is a mine.\n\n" +
                                "Enjoy!"
                    ).also { label ->
                        label.isEditable = false
                        label.font = Font("Calibri", Font.ITALIC, 15)
                        label.bounds = Rectangle(0, 0, 600, 400)
                    }
                )
            }.isVisible = true
        }
    }

    private val exitBt = JButton("Exit").apply { addActionListener { exitProcess(0) } }

    private val frame = JFrame("SAPPER Remastered").also { frame ->
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.bounds = Rectangle(0, 0, 1920, 1080)

        frame.contentPane.add(
            object : JPanel(BorderLayout()) {
                override fun paint(g: Graphics) {
                    super.paint(g)
                    g.drawImage(ImageIO.read(File("src/main/resources/utils/background.png")), 0, 0, null)
                }

                init {
                    border = EmptyBorder(2, 3, 2, 3)

                    add(
                        JPanel(GridBagLayout()).also { layout ->
                            layout.border = EmptyBorder(5, 5, 5, 5)
                            layout.add(JPanel(GridLayout(4, 1, 10, 5)).also {
                                it.add(gameBt)
                                it.add(resultsBt)
                                it.add(helpBt)
                                it.add(exitBt)
                            })
                        },
                        CENTER
                    )
                }
            }
        )
    }

    /** Start of program */

    fun start() {
        frame.isVisible = true

        thread {
            while (frame.isVisible) {
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
}