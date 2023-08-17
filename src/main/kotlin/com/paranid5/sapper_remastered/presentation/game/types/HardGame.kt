package com.paranid5.sapper_remastered.presentation.game.types

import com.paranid5.sapper_remastered.data.player.Player
import com.paranid5.sapper_remastered.presentation.game.showGreetingsDialog
import com.paranid5.sapper_remastered.presentation.game.ui.startGame
import kotlinx.coroutines.flow.MutableStateFlow

/** 32 x 32, 256 mines */

fun startHardGame(player: Player, isPlayingState: MutableStateFlow<Boolean>) {
    showGreetingsDialog(player.name)
    startGame(32, 32, 256, player, GameTypes.Hard, isPlayingState)
}