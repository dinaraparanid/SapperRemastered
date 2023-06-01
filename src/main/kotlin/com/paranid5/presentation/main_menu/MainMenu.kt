package com.paranid5.presentation.main_menu

import com.paranid5.presentation.main_menu.main_menu_buttons.ExitButton
import com.paranid5.presentation.main_menu.main_menu_buttons.GameButton
import com.paranid5.presentation.main_menu.main_menu_buttons.HelpButton
import com.paranid5.presentation.main_menu.main_menu_buttons.ResultButton
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.awt.GridBagConstraints
import java.awt.GridLayout
import javax.swing.Box
import javax.swing.JPanel

fun MainMenu(isPlayingState: MutableStateFlow<Boolean>) = JPanel(GridLayout(12, 1)).apply {
    isOpaque = false
    addContent(isPlayingState)
}

private fun JPanel.addContent(isPlayingState: MutableStateFlow<Boolean>) {
    val constraint = GridBagConstraints().apply { ipadx = 200 }
    add(Box.createHorizontalStrut(300))
    add(Box.createHorizontalStrut(300))
    add(Box.createHorizontalStrut(300))
    add(Box.createHorizontalStrut(300))
    add(Box.createHorizontalStrut(300))
    add(GameButton(isPlayingState), constraint)
    add(Box.createHorizontalStrut(300))
    add(ResultButton(isPlayingState.value), constraint)
    add(Box.createHorizontalStrut(300))
    add(HelpButton(isPlayingState.value), constraint)
    add(Box.createHorizontalStrut(300))
    add(ExitButton(isPlayingState.value), constraint)
}