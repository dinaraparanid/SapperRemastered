package com.paranid5.sapper_remastered.presentation.game.ui

import com.paranid5.sapper_remastered.data.player.Player
import com.paranid5.sapper_remastered.presentation.game.types.startCustomGame
import com.paranid5.sapper_remastered.presentation.game.types.startEasyGame
import com.paranid5.sapper_remastered.presentation.game.types.startHardGame
import com.paranid5.sapper_remastered.presentation.game.types.startMediumGame
import kotlinx.coroutines.flow.MutableStateFlow
import javax.swing.JOptionPane

private const val EASY_GAME = "Easy"
private const val MEDIUM_GAME = "Medium"
private const val HARD_GAME = "Hard"
private const val CUSTOM_GAME = "Custom"

/** Shows dialog to choose game type */

fun showGameTypeDialog(): String? =
    JOptionPane.showInputDialog(
        null,
        "Choose game type:",
        "Game Type",
        JOptionPane.QUESTION_MESSAGE,
        null,
        arrayOf(EASY_GAME, MEDIUM_GAME, HARD_GAME, CUSTOM_GAME),
        EASY_GAME
    ) as String?

/**
 * Starts game after game type was chosen
 * @param player current player
 * @param gameTypeResult game type itself
 * @param isPlayingState is game running
 */

fun launchSelectedGame(
    player: Player,
    gameTypeResult: String?,
    isPlayingState: MutableStateFlow<Boolean>
) = when (gameTypeResult) {
    EASY_GAME -> startEasyGame(player, isPlayingState)
    MEDIUM_GAME -> startMediumGame(player, isPlayingState)
    HARD_GAME -> startHardGame(player, isPlayingState)
    CUSTOM_GAME -> startCustomGame(player, isPlayingState)
    else -> Unit
}