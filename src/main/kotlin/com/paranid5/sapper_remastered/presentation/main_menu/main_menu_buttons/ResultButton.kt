package com.paranid5.sapper_remastered.presentation.main_menu.main_menu_buttons

import com.paranid5.sapper_remastered.data.player.PlayerRepository
import com.paranid5.sapper_remastered.presentation.utils.getScaledImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.swing.Swing
import java.awt.Color
import java.awt.Font
import java.awt.Rectangle
import java.io.File
import javax.imageio.ImageIO
import javax.swing.*

fun ResultButton(isPlaying: Boolean): JButton {
    val coroutineScope = CoroutineScope(Dispatchers.Swing)

    return JButton("RESULTS").apply {
        initView()

        addActionListener {
            if (isPlaying)
                return@addActionListener

            coroutineScope.launch { ResultsFrame().isVisible = true }
        }
    }
}

private fun JButton.initView() {
    background = Color.DARK_GRAY
    font = Font(Font.SANS_SERIF, Font.BOLD, 25)
    foreground = Color.YELLOW
}

private suspend inline fun ResultsFrame() = JFrame().apply {
    contentPane.background = Color.YELLOW
    bounds = Rectangle(300, 300, 900, 500)
    title = "Results"

    iconImage = ImageIcon(
        getScaledImage(
            ImageIO.read(File("src/main/resources/utils/mine.png")),
            200,
            200
        )
    ).image

    add(ResultsTable())
}

private suspend inline fun ResultsTable(): JScrollPane {
    val header = arrayOf(
        "Name",
        "Easy vic",
        "Easy games",
        "Easy vic %",
        "Medium vic",
        "Medium games",
        "Medium vic %",
        "Hard vic",
        "Hard games",
        "Hard vic %",
        "Victories",
        "Games",
        "Victory %",
        "Opened cells"
    )

    val content = PlayerRepository.allAsync
        .await()
        .map { player ->
            arrayOf(
                player.name,
                player.easyVictories,
                player.easyGames,
                String.format("%.2f", player.easyVictoryPercent),
                player.mediumVictories,
                player.mediumGames,
                String.format("%.2f", player.mediumVictoryPercent),
                player.hardVictories,
                player.hardGames,
                String.format("%.2f", player.hardVictoryPercent),
                player.victories,
                player.games,
                String.format("%.2f", player.victoryPercent),
                player.openedCells
            )
        }
        .toTypedArray()

    return JScrollPane(
        JTable(content, header).apply {
            rowHeight = 50
            tableHeader.background = Color(0, 162, 232)
            tableHeader.font = Font(Font.SANS_SERIF, Font.BOLD, 12)
            background = Color.DARK_GRAY
            font = Font(Font.SANS_SERIF, Font.BOLD, 15)
            foreground = Color.YELLOW
        }
    )
}