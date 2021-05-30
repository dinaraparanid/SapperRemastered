package org.game;

import javax.swing.*;

/**
 * 8 x 8, 16 mines
 */

public final class Easy implements GameType {
    private Easy() { }

    /**
     * 8 x 8, 16 mines
     * @param name name of player
     */

    public static final void start(final String name, final String password) {
        JOptionPane.showMessageDialog(
                null,
                "Good luck, " + name + "!",
                "Greetings",
                JOptionPane.INFORMATION_MESSAGE
        );

        new Game(8, 8, 16, name, password, new Easy()).start();
    }
}