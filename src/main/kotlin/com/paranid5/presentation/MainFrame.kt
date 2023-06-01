package com.paranid5.presentation

import com.paranid5.presentation.main_menu.MainMenuPanel
import com.paranid5.presentation.utils.getScaledImage
import kotlinx.coroutines.flow.MutableStateFlow
import java.awt.BorderLayout
import java.awt.Rectangle
import java.io.File
import javax.imageio.ImageIO
import javax.swing.ImageIcon
import javax.swing.JFrame

fun MainFrame(): JFrame {
    val isPlayingState = MutableStateFlow(false)

    return JFrame("SAPPER Remastered").also { frame ->
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.bounds = Rectangle(0, 0, 1920, 1080)

        frame.iconImage = ImageIcon(
            getScaledImage(
                ImageIO.read(File("src/main/resources/utils/mine.png")),
                200,
                200
            )
        ).image

        frame.contentPane.add(MainMenuPanel(isPlayingState), BorderLayout.CENTER)
    }
}