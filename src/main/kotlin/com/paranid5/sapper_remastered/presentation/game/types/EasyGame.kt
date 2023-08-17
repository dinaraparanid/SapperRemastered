package com.paranid5.sapper_remastered.presentation.game.types

import com.paranid5.sapper_remastered.data.player.Player
import com.paranid5.sapper_remastered.presentation.game.showGreetingsDialog
import com.paranid5.sapper_remastered.presentation.game.ui.startGame
import kotlinx.coroutines.flow.MutableStateFlow

/** 8 x 8, 16 mines */

fun startEasyGame(player: Player, isPlayingState: MutableStateFlow<Boolean>) {
    showGreetingsDialog(player.name)
    startGame(8, 8, 16, player, GameTypes.Easy, isPlayingState)
}