package com.paranid5.sapper_remastered.presentation.game

import javax.swing.JOptionPane

fun showGreetingsDialog(name: String) = JOptionPane.showMessageDialog(
    null,
    "Good luck, $name!",
    "Greetings",
    JOptionPane.INFORMATION_MESSAGE
)