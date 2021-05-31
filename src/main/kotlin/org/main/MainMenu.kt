package org.main

import org.game.Game.Companion.chooseGameType
import org.player.Player
import org.player.PlayerRepository
import java.awt.*
import javax.swing.*
import kotlin.system.exitProcess

internal class MainMenu {
    private val gameBt = JButton("GAME").apply {
        horizontalAlignment = SwingConstants.CENTER
        verticalAlignment = SwingConstants.CENTER
        verticalTextPosition = SwingConstants.CENTER
        add(Box.createVerticalStrut(30))
        background = Color.DARK_GRAY
        font = Font(Font.SANS_SERIF, Font.BOLD, 25)
        foreground = Color.YELLOW

        addActionListener {
            if (!Program.playing) {
                val name = JTextField(15)
                val password = JPasswordField(15)
                if (JOptionPane.showConfirmDialog(
                        null, arrayOf<Any>(
                            JLabel("Name"),
                            name,
                            JLabel("Login"),
                            password
                        ),
                        "Sing In",
                        JOptionPane.OK_CANCEL_OPTION
                    ) == JOptionPane.OK_OPTION
                ) chooseGameType(name.text, password.password.toString())
            }
        }
    }

    private val resultsBt = JButton("RESULTS").apply {
        background = Color.DARK_GRAY
        font = Font(Font.SANS_SERIF, Font.BOLD, 25)
        foreground = Color.YELLOW

        addActionListener {
            if (!Program.playing) {
                JFrame().apply {
                    contentPane.background = Color.YELLOW
                    bounds = Rectangle(300, 300, 900, 500)
                    title = "Results"

                    add(
                        JScrollPane(
                            JTable(
                                PlayerRepository.all.map { player: Player ->
                                    player.Data().let { data ->
                                        arrayOf(
                                            data.name,
                                            data.easyVictories,
                                            data.easyGames,
                                            String.format("%.2f", data.easyPercent),
                                            data.mediumVictories,
                                            data.mediumGames,
                                            String.format("%.2f", data.mediumPercent),
                                            data.hardVictories,
                                            data.hardGames,
                                            String.format("%.2f", data.hardPercent),
                                            data.victories,
                                            data.games,
                                            String.format("%.2f", data.percent)
                                        )
                                    }
                                }.toTypedArray(),
                                arrayOf(
                                    "Name",
                                    "Vic on Easy",
                                    "Games on Easy",
                                    "Easy vic %",
                                    "Vic on Medium",
                                    "Games on Medium",
                                    "Medium vic %",
                                    "Vic on Hard",
                                    "Games on Hard",
                                    "Hard vic %",
                                    "Victories",
                                    "Games",
                                    "Victory %"
                                )
                            ).apply {
                                rowHeight = 50
                                tableHeader.background = Color(0, 162, 232)
                                tableHeader.font = Font(Font.SANS_SERIF, Font.BOLD, 12)
                                background = Color.DARK_GRAY
                                font = Font(Font.SANS_SERIF, Font.BOLD, 15)
                                foreground = Color.YELLOW
                            }
                        )
                    )

                    isVisible = true
                }
            }
        }
    }

    private val helpBt = JButton("HELP").apply {
        background = Color.DARK_GRAY
        font = Font(Font.SANS_SERIF, Font.BOLD, 25)
        foreground = Color.YELLOW
        addActionListener {
            if (!Program.playing) {
                JFrame("Help").also { frame ->
                    frame.bounds = Rectangle(300, 300, 600, 400)

                    frame.add(
                        JTextArea(
                            """
                            Author: dinaraparanid (Follow on GitHub: https://github.com/dinaraparanid)
                            
                            About project:
                                This project is a remaster of my old Sapper game (console version on C language)
                                You can check it here: https://github.com/dinaraparanid/Sapper
                                
                                Game has 4 types of levels:
                                    Easy (8 x 8 and 16 mines)
                                    Medium (16 x 16 and 64 mines)
                                    Hard (32 x 32 and 256 mines)
                                    Custom with your game settings (width, height and mines > 0)
                                
                            Gameplay:
                                As in classic sapper game from Microsoft you are trying to open as many cells as you can.
                                Numbers shows amount of mines in near cells. For example, it opened cell's number is 1,
                                than there is one mine in neighbour cells. You can put flags to show that there is a mine.
                            
                            ENJOY!
                        """.trimIndent()
                        ).also { label ->
                            label.isEditable = false
                            label.background = Color.DARK_GRAY
                            label.font = Font(Font.SANS_SERIF, Font.BOLD, 15)
                            label.foreground = Color.YELLOW
                            label.bounds = Rectangle(0, 0, 600, 400)
                        }
                    )

                    frame.pack()
                }.isVisible = true
            }
        }
    }

    private val exitBt = JButton("EXIT").apply {
        background = Color.DARK_GRAY
        font = Font(Font.SANS_SERIF, Font.BOLD, 25)
        foreground = Color.YELLOW
        addActionListener { if (!Program.playing) exitProcess(0) }
    }

    private val constraint = GridBagConstraints().apply { ipadx = 200 }

    val panel = JPanel(GridLayout(12, 1)).apply {
        isOpaque = false
        add(Box.createHorizontalStrut(300))
        add(Box.createHorizontalStrut(300))
        add(Box.createHorizontalStrut(300))
        add(Box.createHorizontalStrut(300))
        add(Box.createHorizontalStrut(300))
        add(gameBt, constraint)
        add(Box.createHorizontalStrut(300))
        add(resultsBt, constraint)
        add(Box.createHorizontalStrut(300))
        add(helpBt, constraint)
        add(Box.createHorizontalStrut(300))
        add(exitBt, constraint)
    }
}