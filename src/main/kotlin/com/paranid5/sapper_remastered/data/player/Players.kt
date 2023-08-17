package com.paranid5.sapper_remastered.data.player

import org.jetbrains.exposed.dao.id.IntIdTable

object Players : IntIdTable() {
    val name = varchar("name", 20).uniqueIndex()
    val password = varchar("password", 20).uniqueIndex()
    val easyGames = integer("easy_games").default(0)
    val easyVictories = integer("easy_vic").default(0)
    val mediumGames = integer("medium_games").default(0)
    val mediumVictories = integer("medium_vic").default(0)
    val hardGames = integer("hard_games").default(0)
    val hardVictories = integer("hard_vic").default(0)
    val openedCells = long("opened_cells").default(0)
    val games = integer("games").default(0)
    val victories = integer("vic").default(0)
}