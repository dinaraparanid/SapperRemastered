package com.paranid5.data.player

import java.sql.ResultSet

inline val ResultSet.player
    get() = Player(
        getString("name"),
        getString("password"),
        getInt("games"),
        getInt("vic"),
        getInt("easy_games"),
        getInt("easy_vic"),
        getInt("medium_games"),
        getInt("medium_vic"),
        getInt("hard_games"),
        getInt("hard_vic"),
        getLong("opened_cells")
    )

inline val ResultSet.players: List<Player>
    get() = mutableListOf<Player>().apply { while (next()) add(player) }