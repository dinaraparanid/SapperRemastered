package org.game

import javax.swing.JOptionPane

/**
 * 16 x 16, 64 mines
 */


internal class Medium : GameType {
    companion object {

        /**
         * 16 x 16, 64 mines
         * @param name name of player
         */

        fun start(name: String, password: String) {
            JOptionPane.showMessageDialog(
                null,
                "Good luck, $name!",
                "Greetings",
                JOptionPane.INFORMATION_MESSAGE
            )

            Game(16, 16, 64, name, password, Medium()).start()
        }
    }
}