package com.paranid5.data.player

import arrow.core.toOption
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import java.io.File

/** Manipulates with players' database */

object PlayerRepository : CoroutineScope by CoroutineScope(Dispatchers.IO) {
    fun initDatabaseAsync() = launch {
        Database.connect(
            url = "jdbc:sqlite:${
                File("").absolutePath.replace(
                    "\\",
                    "/"
                )
            }/players.db",
            driver = "org.sqlite.JDBC"
        )

        newSuspendedTransaction { SchemaUtils.create(Players) }
    }

    /** @return all players */

    val allAsync
        get() = async { newSuspendedTransaction { Player.all().toList() } }

    /**
     * Gets player by name (primary key)
     * @param name name of player
     * @return player with such name
     */

    fun getByNameAsync(name: String) = async {
        newSuspendedTransaction {
            Player.find { Players.name eq name }
                .singleOrNull()
                .toOption()
        }
    }

    /**
     * Updates player
     * @param player player which data needed to update
     */

    inline fun updateAsync(player: Player, crossinline action: Player.() -> Unit) = launch {
        newSuspendedTransaction { action(player) }
    }

    /**
     * Adds new player
     * @param name player's name
     * @param password player's password
     * @return created player
     */

    fun addAsync(name: String, password: String) = async {
        newSuspendedTransaction {
            Player.new {
                this.name = name
                this.password = password
            }
        }
    }
}