package org.player;

import java.lang.System;

/**
 * Manipulate with players' database
 */
@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, xi = 2, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u00c0\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u0004J\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00070\u00132\u0006\u0010\u0010\u001a\u00020\u0004J\u0018\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00072\b\b\u0002\u0010\u0017\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068F\u00a2\u0006\u0006\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lorg/player/PlayerRepository;", "", "()V", "addSQL", "", "all", "", "Lorg/player/Player;", "getAll", "()Ljava/util/List;", "allSQL", "connection", "Ljava/sql/Connection;", "getByNameSQL", "updateSQL", "add", "name", "password", "getByName", "Larrow/core/Option;", "update", "", "player", "oldName", "Sapper_PC"})
public final class PlayerRepository {
    private static final java.lang.String allSQL = "SELECT * FROM Players ORDER BY name";
    private static final java.lang.String getByNameSQL = "SELECT * FROM Players WHERE name = ?";
    private static final java.lang.String updateSQL = "UPDATE Players SET name = ?, password = ?, easy_games = ?, easy_vic = ?, medium_games = ?, medium_vic = ?, hard_games = ?, hard_vic = ?, opened_cells = ?WHERE name = ?";
    private static final java.lang.String addSQL = "INSERT INTO Players (name, password, easy_games, easy_vic, medium_games, medium_vic, hard_games, hard_vic, opened_cells) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final java.sql.Connection connection = null;
    @org.jetbrains.annotations.NotNull
    public static final org.player.PlayerRepository INSTANCE = null;
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<org.player.Player> getAll() {
        return null;
    }
    
    /**
     * Gets player by name (primary key)
     * @param name name of player
     * @return player with such name
     */
    @org.jetbrains.annotations.NotNull
    public final arrow.core.Option<org.player.Player> getByName(@org.jetbrains.annotations.NotNull
    java.lang.String name) {
        return null;
    }
    
    /**
     * Updates player
     * @param player player which data needed to update
     * @param oldName old name of player (by default it's current)
     * @return true if the first result is a ResultSet object;
     *        false if the first result is an update count or there is no result
     */
    public final boolean update(@org.jetbrains.annotations.NotNull
    org.player.Player player, @org.jetbrains.annotations.NotNull
    java.lang.String oldName) {
        return false;
    }
    
    /**
     * Adds new player
     * @param name player's name
     * @param password player's password
     * @return created player
     */
    @org.jetbrains.annotations.NotNull
    public final org.player.Player add(@org.jetbrains.annotations.NotNull
    java.lang.String name, @org.jetbrains.annotations.NotNull
    java.lang.String password) {
        return null;
    }
    
    private PlayerRepository() {
        super();
    }
}