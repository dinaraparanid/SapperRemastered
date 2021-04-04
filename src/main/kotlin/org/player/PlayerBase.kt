package org.player

import java.io.*
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import org.main.Program

/**
 * Object that contains all
 * players in ascending order
 * (compares by their names)
 */

internal object PlayerBase {
    internal var players = sortedMapOf<String, Player>()

    /**
     * Gets org.player, if he's exists.
     * @return Player with searched name or null
     */

    internal operator fun get(name: String): Player = players.getOrPut(name) { Player(name) }

    /** Saves data to JSON file */

    internal fun save() = BufferedWriter(
        FileWriter(
            File("src/main/resources/utils/players.json"),
            false
        )
    ).run {
        write(
            Json.encodeToString(
                JsonObject(
                    players
                        .map { (str, player) -> str!! to player!!.toJson() }
                        .toMap()
                )
            )
        )

        close()
    }

    /** Load players data from JSON file */

    internal fun load() = File("src/main/resources/utils/players.json")
        .bufferedReader()
        .readLines().toList().let { list ->
            if (list.isNotEmpty()) {
                list.first().let {
                    Json.decodeFromString<Map<String, JsonObject>>(it).run {
                        players = map { (name, player) -> name to Player(player) }.toMap().toSortedMap()
                    }
                }
            }
        }
}