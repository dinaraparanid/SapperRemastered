package org.player

import arrow.core.None
import arrow.core.Some
import org.sqlite.SQLiteDataSource
import java.io.File
import java.sql.Connection
import java.sql.PreparedStatement

/**
 * Manipulate with players' database
 */

internal object PlayerRepository {
    private const val allSQL = "SELECT * FROM Players ORDER BY name"

    private const val getByNameSQL = "SELECT * FROM Players " +
            "WHERE name = ?"

    private const val updateSQL = "UPDATE Players " +
            "SET name = ?, password = ?, " +
            "easy_games = ?, easy_vic = ?, " +
            "medium_games = ?, medium_vic = ?, " +
            "hard_games = ?, hard_vic = ?, " +
            "opened_cells = ?" +
            "WHERE name = ?"

    private const val addSQL = "INSERT INTO Players " +
            "(name, password, easy_games, easy_vic, medium_games, medium_vic, hard_games, hard_vic, opened_cells) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"

    private val connection: Connection = SQLiteDataSource()
        .apply {
            url = "jdbc:sqlite:${
                File("").absolutePath.replace(
                    "\\",
                    "/"
                )
            }/players.db"
        }
        .connection

    /** @return all players */

    val all: List<Player>
        get() = connection.createStatement().use { stm ->
            stm.executeQuery(allSQL).use { res ->
                mutableListOf<Player>().apply {
                    while (res.next()) {
                        add(
                            Player(
                                res.getString("name"),
                                res.getString("password"),
                                res.getInt("easy_games"),
                                res.getInt("easy_vic"),
                                res.getInt("medium_games"),
                                res.getInt("medium_vic"),
                                res.getInt("hard_games"),
                                res.getInt("hard_vic"),
                                res.getLong("opened_cells")
                            )
                        )
                    }
                }
            }
        }

    /**
     * Gets player by name (primary key)
     * @param name name of player
     * @return player with such name
     */

    fun getByName(name: String) = connection
        .prepareStatement(getByNameSQL)
        .apply { setString(1, name) }
        .use { stm ->
            stm.executeQuery().use { res ->
                when {
                    res.next() -> Some(
                        Player(
                            res.getString("name"),
                            res.getString("password"),
                            res.getInt("easy_games"),
                            res.getInt("easy_vic"),
                            res.getInt("medium_games"),
                            res.getInt("medium_vic"),
                            res.getInt("hard_games"),
                            res.getInt("hard_vic"),
                            res.getLong("opened_cells")
                        )
                    )

                    else -> None
                }
            }
        }

    /**
     * Updates player
     * @param player player which data needed to update
     * @param oldName old name of player (by default it's current)
     * @return true if the first result is a ResultSet object;
     *         false if the first result is an update count or there is no result
     */

    fun update(player: Player, oldName: String = player.Data().name) = connection
        .prepareStatement(updateSQL)
        .apply {
            player.Data().let { data ->
                setString(1, data.name)
                setString(2, data.password)
                setInt(3, data.easyGames)
                setInt(4, data.easyVictories)
                setInt(5, data.mediumGames)
                setInt(6, data.mediumVictories)
                setInt(7, data.hardGames)
                setInt(8, data.hardVictories)
                setLong(9, data.openedCells)
                setString(10, oldName)
            }
        }
        .use(PreparedStatement::execute)

    /**
     * Adds new player
     * @param name player's name
     * @param password player's password
     * @return created player
     */

    fun add(name: String, password: String) = connection
        .prepareStatement(addSQL)
        .apply {
            setString(1, name)
            setString(2, password)
            setInt(3, 0)
            setInt(4, 0)
            setInt(5, 0)
            setInt(6, 0)
            setInt(7, 0)
            setInt(8, 0)
            setLong(9, 0)
        }
        .use(PreparedStatement::execute)
        .run { Player(name, password) }
}