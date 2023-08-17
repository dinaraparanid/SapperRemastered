package com.paranid5.sapper_remastered.presentation.game.types

sealed interface GameTypes {
    object Easy : GameTypes
    object Medium : GameTypes
    object Hard : GameTypes
    object Custom : GameTypes
}