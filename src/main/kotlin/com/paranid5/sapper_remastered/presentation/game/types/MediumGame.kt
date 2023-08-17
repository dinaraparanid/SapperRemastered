package com.paranid5.sapper_remastered.presentation.game.types

import com.paranid5.sapper_remastered.data.player.Player
import com.paranid5.sapper_remastered.presentation.game.showGreetingsDialog
import com.paranid5.sapper_remastered.presentation.game.ui.startGame
import kotlinx.coroutines.flow.MutableStateFlow

/** 16 x 16, 64 mines */

fun startMediumGame(player: Player, isPlayingState: MutableStateFlow<Boolean>) {
    showGreetingsDialog(player.name)
    startGame(16, 16, 64, player, GameTypes.Medium, isPlayingState)
}