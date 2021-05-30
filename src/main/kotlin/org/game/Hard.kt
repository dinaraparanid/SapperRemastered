package org.game

import javax.swing.JOptionPane

/**
 * 32 x 32, 256 mines
 */


internal class Hard : GameType {
    companion object {

        /**
         * 32 x 32, 256 mines
         * @param name name of player
         */


        fun start(name: String, password: String) {
            JOptionPane.showMessageDialog(
                null,
                "Good luck, $name!",
                "Greetings",
                JOptionPane.INFORMATION_MESSAGE
            )

            Game(32, 32, 256, name, password, Hard()).start()
        }
    }
}