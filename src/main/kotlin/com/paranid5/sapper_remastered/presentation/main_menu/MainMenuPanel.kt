package com.paranid5.sapper_remastered.presentation.main_menu

import kotlinx.coroutines.flow.MutableStateFlow
import java.awt.Graphics
import java.awt.GridBagLayout
import java.io.File
import javax.imageio.ImageIO
import javax.swing.JPanel

fun MainMenuPanel(isPlayingState: MutableStateFlow<Boolean>) = object : JPanel() {
    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        g.drawImage(ImageIO.read(File("src/main/resources/utils/background.png")), 0, 0, null)
    }

    init {
        layout = GridBagLayout()
        add(MainMenu(isPlayingState))
    }
}