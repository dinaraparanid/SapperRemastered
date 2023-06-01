package com.paranid5.presentation.game.types

import arrow.core.Tuple4
import com.paranid5.data.player.Player
import com.paranid5.presentation.game.showGreetingsDialog
import com.paranid5.presentation.game.ui.startGame
import kotlinx.coroutines.flow.MutableStateFlow
import javax.swing.JLabel
import javax.swing.JOptionPane
import javax.swing.JTextField

/** Customizable level */

fun startCustomGame(player: Player, isPlayingState: MutableStateFlow<Boolean>) {
    val (width, height, mines, gameSettingsResult) = showGameSettingsDialog()

    if (gameSettingsResult == JOptionPane.OK_OPTION) checkGameSettings(
        rows = width.parseNumberOrShowError() ?: 0,
        columns = height.parseNumberOrShowError() ?: 0,
        mines = mines.parseNumberOrShowError() ?: 0,
        player = player,
        isPlayingState = isPlayingState
    )
}

private fun showGameSettingsDialog(): Tuple4<String?, String?, String?, Int> {
    val width = JTextField(15)
    val height = JTextField(15)
    val mines = JTextField(15)

    val gameSettingsResult = JOptionPane.showConfirmDialog(
        null,
        arrayOf(
            JLabel("Width"),
            width,
            JLabel("Height"),
            height,
            JLabel("Mines amount"),
            mines
        ),
        "Enter game table parameters",
        JOptionPane.OK_CANCEL_OPTION
    )

    return Tuple4(width.text, height.text, mines.text, gameSettingsResult)
}

private fun String?.parseNumberOrShowError(): Int? {
    if (isNullOrEmpty()) {
        showEmptyInputErrorMessage()
        return null
    }

    return when (val number = toIntOrNull()) {
        null -> {
            showNotIntegerErrorMessage()
            null
        }

        else -> number
    }
}

private fun checkGameSettings(
    rows: Int,
    columns: Int,
    mines: Int,
    player: Player,
    isPlayingState: MutableStateFlow<Boolean>
) = when {
    rows <= 0 || columns <= 0 -> showWidthHeightInputErrorMessage()

    mines <= 0 -> showMinesLessOneInputErrorMessage()

    mines >= rows * columns -> showTooManyMinesInputErrorMessage()

    else -> {
        showGreetingsDialog(player.name)
        startGame(rows, columns, mines, player, GameTypes.Custom, isPlayingState)
    }
}

private fun showEmptyInputErrorMessage() = JOptionPane.showMessageDialog(
    null,
    "You haven't entered anything",
    "Input Error",
    JOptionPane.INFORMATION_MESSAGE
)

private fun showNotIntegerErrorMessage() = JOptionPane.showMessageDialog(
    null,
    "Not an integer",
    "Parse Error",
    JOptionPane.INFORMATION_MESSAGE
)

private fun showWidthHeightInputErrorMessage() = JOptionPane.showMessageDialog(
    null,
    "Incorrect table",
    "Input Error",
    JOptionPane.INFORMATION_MESSAGE
)

private fun showMinesLessOneInputErrorMessage() = JOptionPane.showMessageDialog(
    null,
    "Mines amount must be > 0",
    "Input Error",
    JOptionPane.INFORMATION_MESSAGE
)

private fun showTooManyMinesInputErrorMessage() = JOptionPane.showMessageDialog(
    null,
    "Mines' amount must be not bigger than scale of table",
    "Input Error",
    JOptionPane.INFORMATION_MESSAGE
)