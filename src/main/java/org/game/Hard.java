package org.game;

import javax.swing.*;

/**
 * 32 x 32, 256 mines
 */

public final class Hard implements GameType {
    private Hard() { }

    /**
     * 32 x 32, 256 mines
     * @param name name of player
     */

    public static final void start(final String name, final String password) {
        JOptionPane.showMessageDialog(
                null,
                "Good luck, " + name + "!",
                "Greetings",
                JOptionPane.INFORMATION_MESSAGE
        );

        new Game(32, 32, 256, name, password, new Hard()).start();
    }
}
