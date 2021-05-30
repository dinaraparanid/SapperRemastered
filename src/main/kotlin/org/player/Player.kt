package org.player

import org.game.*

/**
 * Player Class.
 * Implements Comparable, compares by name
 * @param name name of player
 */

internal class Player(
    private val name: String,
    private val password: String,
    private var easyGames: Int = 0,
    private var easyVictories: Int = 0,
    private var mediumGames: Int = 0,
    private var mediumVictories: Int = 0,
    private var hardGames: Int = 0,
    private var hardVictories: Int = 0,
) : Comparable<Player> {

    private var victories = easyVictories + mediumVictories + hardVictories
    private var games = easyGames + mediumGames + hardGames

    /**
     *  Updates game count when starting playing
     *  @return game amount
     */

    fun startPlaying(gt: GameType): Int {
        when (gt) {
            is Easy -> easyGames++
            is Medium -> mediumGames++
            is Hard -> hardGames++
        }

        return ++games
    }

    /**
     *  Updates victory count game finishing
     *  @return victory amount
     */

    fun won(gt: GameType): Int {
        when (gt) {
            is Easy -> easyVictories++
            is Medium -> mediumVictories++
            is Hard -> hardVictories++
        }

        return ++victories
    }

    /**
     *  Calculates percent of victory on easy level
     *  @return percent of victory on easy level
     */

    fun easyVictoryPercent() = if (easyGames == 0) 0.0 else 100.0 * easyVictories / easyGames

    /**
     *  Calculates percent of victory on medium level
     *  @return percent of victory on medium level
     */

    fun mediumVictoryPercent() = if (mediumGames == 0) 0.0 else 100.0 * mediumVictories / mediumGames

    /**
     *  Calculates percent of victory on hard level
     *  @return percent of victory on hard level
     */

    fun hardVictoryPercent() = if (hardGames == 0) 0.0 else 100.0 * hardVictories / hardGames

    /**
     *  Calculates percent of victory for all games
     *  @return percent of victory for all games
     */

    fun victoryPercent() = if (games == 0) 0.0 else 100.0 * victories / games

    inner class Data {
        val name = this@Player.name
        val password = this@Player.password
        val easyVictories = this@Player.easyVictories
        val easyGames = this@Player.easyGames
        val easyPercent = easyVictoryPercent()
        val mediumVictories = this@Player.mediumVictories
        val mediumGames = this@Player.mediumGames
        val mediumPercent = mediumVictoryPercent()
        val hardVictories = this@Player.hardVictories
        val hardGames = this@Player.hardGames
        val hardPercent = hardVictoryPercent()
        val victories = this@Player.victories
        val games = this@Player.games
        val percent = victoryPercent()
    }

    override operator fun compareTo(other: Player): Int = name.compareTo(other.name)
    override fun equals(other: Any?): Boolean = name == (other as Player).name
    override fun hashCode(): Int = name.hashCode().let { 31 * it + victories }.let { 31 * it + games }
}