package org.game

import javax.swing.JOptionPane

/**
 * 8 x 8, 16 mines
 */

internal class Easy : GameType {
    companion object {

        /**
         * 8 x 8, 16 mines
         * @param name name of player
         */

        fun start(name: String, password: String) {
            JOptionPane.showMessageDialog(
                null,
                "Good luck, $name!",
                "Greetings",
                JOptionPane.INFORMATION_MESSAGE
            )

            Game(8, 8, 16, name, password, Easy()).start()
        }
    }
}