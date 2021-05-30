package org.game

import arrow.core.None
import arrow.core.Some
import javax.swing.JLabel
import javax.swing.JOptionPane
import javax.swing.JTextField

/**
 * custom level
 */

class Custom private constructor() : GameType {
    val width = JTextField(15)
    val height = JTextField(15)
    val mines = JTextField(15)

    companion object {
        private fun parse(textField: JTextField) = textField.text.let { text ->
            when {
                text != null -> {
                    try {
                        Some(text.toInt())
                    } catch (e: Exception) {
                        JOptionPane.showMessageDialog(
                            null,
                            "Not an integer",
                            "Parse Error",
                            JOptionPane.INFORMATION_MESSAGE
                        )
                        None
                    }
                }

                else -> {
                    JOptionPane.showMessageDialog(
                        null,
                        "You haven't entered anything",
                        "Input Error",
                        JOptionPane.INFORMATION_MESSAGE
                    )
                    None
                }
            }
        }

        private fun check(xc: Int, yc: Int, m: Int, name: String, password: String) = when {
            xc <= 0 || yc <= 0 -> JOptionPane.showMessageDialog(
                null,
                "Incorrect table",
                "Input Error",
                JOptionPane.INFORMATION_MESSAGE
            )

            m <= 0 -> JOptionPane.showMessageDialog(
                null,
                "Mines amount must be > 0",
                "Input Error",
                JOptionPane.INFORMATION_MESSAGE
            )

            m >= xc * yc -> JOptionPane.showMessageDialog(
                null,
                "Mines' amount must be not bigger than scale of table",
                "Input Error",
                JOptionPane.INFORMATION_MESSAGE
            )

            else -> {
                JOptionPane.showMessageDialog(
                    null,
                    "Good luck, $name!",
                    "Greetings",
                    JOptionPane.INFORMATION_MESSAGE
                )

                Game(xc, yc, m, name, password, Custom()).start()
            }
        }

        /**
         * custom level
         * @param name name of player
         */

        fun start(name: String, password: String) {
            val game = Custom()

            if (
                JOptionPane.showConfirmDialog(
                    null,
                    arrayOf(
                        JLabel("Width"),
                        game.width,
                        JLabel("Height"),
                        game.height,
                        JLabel("Mines amount"),
                        game.mines
                    ),
                    "Enter game table parameters",
                    JOptionPane.OK_CANCEL_OPTION
                ) == JOptionPane.OK_OPTION
            ) check(
                parse(game.width).orNull() ?: 0,
                parse(game.height).orNull() ?: 0,
                parse(game.mines).orNull() ?: 0,
                name,
                password
            )
        }
    }
}