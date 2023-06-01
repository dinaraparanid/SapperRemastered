package com.paranid5.presentation.main_menu.main_menu_buttons

import com.paranid5.presentation.utils.getScaledImage
import java.awt.Color
import java.awt.Font
import java.awt.Rectangle
import java.io.File
import javax.imageio.ImageIO
import javax.swing.ImageIcon
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JTextArea

fun HelpButton(isPlaying: Boolean) = JButton("HELP").apply {
    initView()

    addActionListener {
        if (isPlaying)
            return@addActionListener

        HelpFrame().isVisible = true
    }
}

private fun JButton.initView() {
    background = Color.DARK_GRAY
    font = Font(Font.SANS_SERIF, Font.BOLD, 25)
    foreground = Color.YELLOW
}

private fun HelpFrame() = JFrame("Help").also { frame ->
    frame.bounds = Rectangle(300, 300, 600, 400)

    frame.iconImage = ImageIcon(
        getScaledImage(
            ImageIO.read(File("src/main/resources/utils/mine.png")),
            200,
            200
        )
    ).image

    frame.add(HelpTextField())
    frame.pack()
}

private fun HelpTextField() =
    JTextArea(
        """
Author: dinaraparanid (Follow on GitHub: https://github.com/dinaraparanid)

About project:
    This project is a remaster of my old Sapper game (console version on C language)
    You can check it here: https://github.com/dinaraparanid/Sapper
                                
    Game has 4 types of levels:
        Easy (8 x 8 and 16 mines)
        Medium (16 x 16 and 64 mines)
        Hard (32 x 32 and 256 mines)
        Custom with your game settings (width, height and mines > 0)
                                
    Gameplay:
        As in classic sapper game from Microsoft you are trying to open as many cells as you can.
        Numbers shows amount of mines in near cells. For example, it opened cell's number is 1,
        than there is one mine in neighbour cells. You can put flags to show that there is a mine.
                            
Enjoy!
""".trimIndent()
    ).also { label ->
        label.isEditable = false
        label.background = Color.DARK_GRAY
        label.font = Font(Font.SANS_SERIF, Font.BOLD, 15)
        label.foreground = Color.YELLOW
        label.bounds = Rectangle(0, 0, 600, 400)
    }