package com.paranid5.data.player

import com.paranid5.presentation.game.types.GameTypes
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.id.EntityID

class Player(id: EntityID<Int>) : IntEntity(id), Comparable<Player> {
    var name by Players.name
    var password by Players.password
    var games by Players.games
    var victories by Players.victories
    var easyGames by Players.easyGames
    var easyVictories by Players.easyVictories
    var mediumGames by Players.mediumGames
    var mediumVictories by Players.mediumVictories
    var hardGames by Players.hardGames
    var hardVictories by Players.hardVictories
    var openedCells by Players.openedCells

    companion object : EntityClass<Int, Player>(Players)

    fun incrementPlayedGames(gt: GameTypes) {
        ++games

        when (gt) {
            GameTypes.Easy -> ++easyGames
            GameTypes.Medium -> ++mediumGames
            GameTypes.Hard -> ++hardGames
            else -> Unit
        }
    }

    fun incrementVictories(gt: GameTypes) {
        ++victories

        when (gt) {
            GameTypes.Easy -> ++easyVictories
            GameTypes.Medium -> ++mediumVictories
            GameTypes.Hard -> ++hardVictories
            else -> Unit
        }
    }

    /**
     *  Calculates percent of victory on easy level
     *  @return percent of victory on easy level
     */

    inline val easyVictoryPercent
        get() = if (easyGames == 0) 0.0 else 100.0 * easyVictories / easyGames

    /**
     *  Calculates percent of victory on medium level
     *  @return percent of victory on medium level
     */

    inline val mediumVictoryPercent
        get() = if (mediumGames == 0) 0.0 else 100.0 * mediumVictories / mediumGames

    /**
     *  Calculates percent of victory on hard level
     *  @return percent of victory on hard level
     */

    inline val hardVictoryPercent
        get() = if (hardGames == 0) 0.0 else 100.0 * hardVictories / hardGames

    /**
     *  Calculates percent of victory for all games
     *  @return percent of victory for all games
     */

    inline val victoryPercent
        get() = if (games == 0) 0.0 else 100.0 * victories / games

    override operator fun compareTo(other: Player) = victoryPercent.compareTo(other.victoryPercent)
}