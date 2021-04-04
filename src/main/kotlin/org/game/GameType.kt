package org.game

import java.awt.*
import javax.swing.*

/** Game types */

internal interface GameType

/**
 * 8 x 8, 16 mines
 * @param name name of player
 */

internal class Easy(name: String) : GameType {
    init {
        game(8, 8, 16, name, this)
    }
}

/**
 * 16 x 16, 64 mines
 * @param name name of player
 */

internal class Medium(name: String) : GameType {
    init {
        game(16, 16, 64, name, this)
    }
}

/**
 * 32 x 32, 256 mines
 * @param name name of player
 */

internal class Hard(name: String) : GameType {
    init {
        game(32, 32, 256, name, this)
    }
}

/**
 * custom level
 * @param name name of player
 */

internal class Custom(name: String) : GameType {
    init {
        object : JFrame() {
            private val buttonOK: JButton
            private var xCord: JTextField
            private var yCord: JTextField
            private var mines: JTextField

            init {
                bounds = Rectangle(800, 500, 325, 170)
                title = "Game parameters"

                add(
                    JPanel().also { panel ->
                        panel.add(JLabel("Width, "))
                        xCord = JTextField(15)

                        panel.add(JLabel("Height, "))
                        yCord = JTextField(15)

                        panel.add(JLabel("Mines amount"))
                        mines = JTextField(15)

                        panel.add(xCord)
                        panel.add(yCord)
                        panel.add(mines)

                        buttonOK = JButton("OK").also { bt ->
                            bt.addActionListener { e ->
                                if (e?.source === bt) {
                                    val parse = { parseFiled: JTextField ->
                                        parseFiled.text.let { text ->
                                            when {
                                                text != null -> text.toIntOrNull().let { num ->
                                                    when {
                                                        num != null -> num

                                                        else -> {
                                                            JOptionPane.showMessageDialog(
                                                                this,
                                                                "Not integer",
                                                                "Parse Error",
                                                                JOptionPane.INFORMATION_MESSAGE
                                                            )

                                                            null
                                                        }
                                                    }
                                                }

                                                else -> {
                                                    JOptionPane.showMessageDialog(
                                                        this,
                                                        "You haven't entered anything",
                                                        "Input Error",
                                                        JOptionPane.INFORMATION_MESSAGE
                                                    )

                                                    null
                                                }
                                            }
                                        }
                                    }

                                    var xc = 0
                                    var yc = 0
                                    var m = 0

                                    parse(xCord).let { if (it != null) xc = it }
                                    parse(yCord).let { if (it != null) yc = it }
                                    parse(mines).let { if (it != null) m = it }

                                    when {
                                        xc <= 0 || yc <= 0 -> JOptionPane.showMessageDialog(
                                            this,
                                            "Incorrect table",
                                            "Input Error",
                                            JOptionPane.INFORMATION_MESSAGE
                                        )

                                        m <= 0 -> JOptionPane.showMessageDialog(
                                            this,
                                            "Mines amount must be > 0",
                                            "Input Error",
                                            JOptionPane.INFORMATION_MESSAGE
                                        )

                                        m >= xc * yc -> JOptionPane.showMessageDialog(
                                            this,
                                            "Mines amount must be not bigger than scale of table",
                                            "Input Error",
                                            JOptionPane.INFORMATION_MESSAGE
                                        )

                                        else -> {
                                            isVisible = false
                                            game(xc, yc, m, name, this@Custom)
                                        }
                                    }

                                    isVisible = false
                                }
                            }
                        }

                        panel.add(buttonOK)
                    }
                )

                isVisible = true
            }
        }
    }
}

/**
 * Choose game type
 * @param _name name of player
 */

internal class GameTypeChooser(_name: String) {
    private var f = JFrame("Game Type").apply { bounds = Rectangle(800, 500, 325, 100) }
    private val cb = JComboBox(arrayOf("Easy", "Medium", "Hard", "Custom")).apply { bounds = Rectangle(50, 50, 90, 20) }

    init {
        f.add(
            JPanel().also { panel ->
                panel.add(JLabel("Select game type: "))
                panel.add(cb)
                panel.add(JButton("OK").apply {
                    addActionListener { e ->
                        if (e.source === this) {
                            when {
                                (cb.selectedItem as String).isNotEmpty() -> when (cb.selectedItem) {
                                    "Easy" -> {
                                        f.isVisible = false
                                        Easy(_name)
                                    }

                                    "Medium" -> {
                                        f.isVisible = false
                                        Medium(_name)
                                    }

                                    "Hard" -> {
                                        f.isVisible = false
                                        Hard(_name)
                                    }
                                    
                                    else -> {
                                        f.isVisible = false
                                        Custom(_name)
                                    }
                                }

                                else -> JOptionPane.showMessageDialog(
                                    this,
                                    "Incorrect table",
                                    "Input Error",
                                    JOptionPane.INFORMATION_MESSAGE
                                )
                            }
                        }
                    }
                })
            }
        )

        f.bounds = Rectangle(800, 500, 325, 100)
        f.isVisible = true
    }
}