package com.paranid5.presentation.game.types

sealed interface GameTypes {
    object Easy : GameTypes
    object Medium : GameTypes
    object Hard : GameTypes
    object Custom : GameTypes
}