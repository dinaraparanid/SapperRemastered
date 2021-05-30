package org.game;

import arrow.core.None;
import arrow.core.Option;
import arrow.core.Some;
import javax.swing.*;

/**
 * custom level
 */

public final class Custom implements GameType {

    final JTextField width = new JTextField(15);
    final JTextField height = new JTextField(15);
    final JTextField mines = new JTextField(15);

    private Custom() { }

    private static final Option<Integer> parse(final JTextField textField) {
        final var text = textField.getText();

        if (text != null) {
            try {
                return new Some<>(Integer.parseInt(text));
            } catch (final Exception e) {
                JOptionPane.showMessageDialog(
                        null,
                        "Not an integer",
                        "Parse Error",
                        JOptionPane.INFORMATION_MESSAGE
                );

                return None.INSTANCE;
            }
        } else {
            JOptionPane.showMessageDialog(
                    null,
                    "You haven't entered anything",
                    "Input Error",
                    JOptionPane.INFORMATION_MESSAGE
            );

            return None.INSTANCE;
        }
    }

    private static final void check(final int xc, final int yc, final int m, final String name, final String password) {
        if (xc <= 0 || yc <= 0)
            JOptionPane.showMessageDialog(
                null,
                "Incorrect table",
                "Input Error",
                JOptionPane.INFORMATION_MESSAGE
            );

        else if (m <= 0)
            JOptionPane.showMessageDialog(
                null,
                "Mines amount must be > 0",
                "Input Error",
                JOptionPane.INFORMATION_MESSAGE
            );

        else if (m >= xc * yc)
            JOptionPane.showMessageDialog(
                null,
                "Mines' amount must be not bigger than scale of table",
                "Input Error",
                JOptionPane.INFORMATION_MESSAGE
            );

        else {
            JOptionPane.showMessageDialog(
                    null,
                    "Good luck, " + name + "!",
                    "Greetings",
                    JOptionPane.INFORMATION_MESSAGE
            );

            new Game(xc, yc, m, name,  password, new Custom()).start();
        }
    }

    /**
     * custom level
     * @param name name of player
     */

    public static final void start(final String name, final String password) {
        final var game = new Custom();

        final var result = JOptionPane.showConfirmDialog(
                null,
                new Object[] {
                        new JLabel("Width"),
                        game.width,
                        new JLabel("Height"),
                        game.height,
                        new JLabel("Mines amount"),
                        game.mines
                },
                "Enter game table parameters",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (result == JOptionPane.OK_OPTION) {
            var xc = 0;
            var yc = 0;
            var m = 0;

            final var t1 = parse(game.width);
            final var t2 = parse(game.height);
            final var t3 = parse(game.mines);

            if (!(t1 instanceof None || t2 instanceof None || t3 instanceof None)) {
                xc = t1.orNull();
                yc = t2.orNull();
                m = t3.orNull();
            }

            check(xc, yc, m, name, password);
        }
    }
}
