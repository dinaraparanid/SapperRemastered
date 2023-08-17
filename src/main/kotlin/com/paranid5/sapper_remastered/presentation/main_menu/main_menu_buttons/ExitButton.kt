package com.paranid5.sapper_remastered.presentation.main_menu.main_menu_buttons

import java.awt.Color
import java.awt.Font
import javax.swing.JButton
import kotlin.system.exitProcess

fun ExitButton(isPlaying: Boolean) = JButton("EXIT").apply {
    background = Color.DARK_GRAY
    font = Font(Font.SANS_SERIF, Font.BOLD, 25)
    foreground = Color.YELLOW
    addActionListener { if (!isPlaying) exitProcess(0) }
}