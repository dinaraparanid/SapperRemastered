package org.player

import org.game.*
import kotlinx.serialization.json.*

/**
 * Player Class.
 * Implements Comparable, compares by name
 * @param name name of org.player
 */

internal class Player(private var name: String) : Comparable<Player> {
    private var easyGames = 0
    private var easyVictories = 0
    private var mediumGames = 0
    private var mediumVictories = 0
    private var hardGames = 0
    private var hardVictories = 0
    private var victories = 0
    private var games = 0

    /**
     * Constructs org.player from JSON
     * @param player
     */

    internal constructor(player: JsonObject) : this("") {
        Json.decodeFromJsonElement<Map<String, JsonPrimitive>>(player).let {
            name            = it["Name"]!!.content
            easyVictories   = it["Easy Victories"]!!.int
            easyGames       = it["Easy Games"]!!.int
            mediumVictories = it["Medium Victories"]!!.int
            mediumGames     = it["Medium Games"]!!.int
            hardVictories   = it["Hard Victories"]!!.int
            hardGames       = it["Hard Games"]!!.int
            victories       = it["Victories"]!!.int
            games           = it["Games"]!!.int
        }
    }

    /**
     *  Updates org.org.game.game count when starting playing
     *  @return org.org.game.game amount
     */

    internal fun startPlaying(gt: GameType): Int {
        when (gt) {
            is Easy -> easyGames++
            is Medium -> mediumGames++
            is Hard -> hardGames++
        }

        return ++games
    }

    /**
     *  Updates victory count after org.org.game.game finishing
     *  @return victory amount
     */

    internal fun won(gt: GameType): Int {
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

    internal fun easyVictoryPercent() = if (easyGames == 0) 0.0 else 100.0 * easyVictories / easyGames

    /**
     *  Calculates percent of victory on medium level
     *  @return percent of victory on medium level
     */

    internal fun mediumVictoryPercent() = if (mediumGames == 0) 0.0 else 100.0 * mediumVictories / mediumGames

    /**
     *  Calculates percent of victory on hard level
     *  @return percent of victory on hard level
     */

    internal fun hardVictoryPercent() = if (hardGames == 0) 0.0 else 100.0 * hardVictories / hardGames

    /**
     *  Calculates percent of victory for all games
     *  @return percent of victory for all games
     */

    internal fun victoryPercent() = if (games == 0) 0.0 else 100.0 * victories / games

    /**
     *  Represent Player to JSON format
     *  @return JSON string representation of org.player data
     */

    internal fun toJson() = JsonObject(
        mapOf(
            "Name" to JsonPrimitive(name),
            "Easy Victories" to JsonPrimitive(easyVictories),
            "Easy Games" to JsonPrimitive(easyGames),
            "Medium Victories" to JsonPrimitive(mediumVictories),
            "Medium Games" to JsonPrimitive(mediumGames),
            "Hard Victories" to JsonPrimitive(hardVictories),
            "Hard Games" to JsonPrimitive(hardGames),
            "Victories" to JsonPrimitive(victories),
            "Games" to JsonPrimitive(games)
        )
    )

    inner class PlayerData {
        internal val playerName: String
            get() = name

        internal val playerEasyVictories: Int
            get() = easyVictories

        internal val playerEasyGames: Int
            get() = easyGames

        internal val playerEasyPercent: Double
            get() = easyVictoryPercent()

        internal val playerMediumVictories: Int
            get() = mediumVictories

        internal val playerMediumGames: Int
            get() = mediumGames

        internal val playerMediumPercent: Double
            get() = mediumVictoryPercent()

        internal val playerHardVictories: Int
            get() = hardVictories

        internal val playerHardGames: Int
            get() = hardGames

        internal val playerHardPercent: Double
            get() = hardVictoryPercent()

        internal val playerVictories: Int
            get() = victories

        internal val playerGames: Int
            get() = games

        internal val playerPercent: Double
            get() = victoryPercent()
    }

    override operator fun compareTo(other: Player): Int = name.compareTo(other.name)
    override fun equals(other: Any?): Boolean = name == (other as Player).name
    override fun hashCode(): Int = name.hashCode().let { 31 * it + victories }.let { 31 * it + games }
}