package org.game;

import javax.swing.*;

/**
 * 16 x 16, 64 mines
 */

public final class Medium implements GameType {
    private Medium() { }

    /**
     * 16 x 16, 64 mines
     * @param name name of player
     */

    public static final void start(final String name, final String password) {
        JOptionPane.showMessageDialog(
                null,
                "Good luck, " + name + "!",
                "Greetings",
                JOptionPane.INFORMATION_MESSAGE
        );

        new Game(16, 16, 64, name, password, new Medium()).start();
    }
}