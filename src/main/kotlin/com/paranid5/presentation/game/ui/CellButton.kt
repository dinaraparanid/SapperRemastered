package com.paranid5.presentation.game.ui

import java.awt.Color
import java.lang.ref.WeakReference
import javax.swing.JButton

data class CellButton(
    val row: Int,
    val column: Int,
    var cellState: CellState = CellState.CLOSED
) : JButton() {
    enum class CellState { CLOSED, OPENED, FLAG }

    init {
        background = Color(0, 162, 232)
    }
}