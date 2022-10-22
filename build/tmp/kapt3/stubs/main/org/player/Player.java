package org.player;

import java.lang.System;

/**
 * Player Class.
 * Implements Comparable, compares by name
 * @param name name of player
 */
@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, xi = 2, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001&B[\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\u0006\u0012\b\b\u0002\u0010\t\u001a\u00020\u0006\u0012\b\b\u0002\u0010\n\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0006\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u00a2\u0006\u0002\u0010\u000eJ\u0011\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\u0000H\u0096\u0002J\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001fH\u0096\u0002J\b\u0010 \u001a\u00020\u0006H\u0016J\u0006\u0010!\u001a\u00020\rJ\u000e\u0010\"\u001a\u00020\u00062\u0006\u0010#\u001a\u00020$J\u000e\u0010%\u001a\u00020\u00062\u0006\u0010#\u001a\u00020$R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0015\u0010\u000f\u001a\u00020\u00108\u00c2\u0002X\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0015\u0010\u0014\u001a\u00020\u00108\u00c2\u0002X\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0015\u0010\u0012R\u000e\u0010\b\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0015\u0010\u0016\u001a\u00020\u00108\u00c2\u0002X\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0017\u0010\u0012R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0015\u0010\u0019\u001a\u00020\u00108\u00c2\u0002X\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u001a\u0010\u0012\u00a8\u0006\'"}, d2 = {"Lorg/player/Player;", "", "name", "", "password", "easyGames", "", "easyVictories", "mediumGames", "mediumVictories", "hardGames", "hardVictories", "openedCells", "", "(Ljava/lang/String;Ljava/lang/String;IIIIIIJ)V", "easyVictoryPercent", "", "getEasyVictoryPercent", "()D", "games", "hardVictoryPercent", "getHardVictoryPercent", "mediumVictoryPercent", "getMediumVictoryPercent", "victories", "victoryPercent", "getVictoryPercent", "compareTo", "other", "equals", "", "", "hashCode", "openCell", "startPlaying", "gt", "Lorg/game/GameType;", "won", "Data", "Sapper_PC"})
public final class Player implements java.lang.Comparable<org.player.Player> {
    private int victories;
    private int games;
    private final java.lang.String name = null;
    private final java.lang.String password = null;
    private int easyGames;
    private int easyVictories;
    private int mediumGames;
    private int mediumVictories;
    private int hardGames;
    private int hardVictories;
    private long openedCells;
    
    /**
     * Updates game count when starting playing
     * @return game amount
     */
    public final int startPlaying(@org.jetbrains.annotations.NotNull
    org.game.GameType gt) {
        return 0;
    }
    
    /**
     * Updates victory count game finishing
     * @return victory amount
     */
    public final int won(@org.jetbrains.annotations.NotNull
    org.game.GameType gt) {
        return 0;
    }
    
    /**
     * Update opened cells count
     * @return updated opened cells amount
     */
    public final long openCell() {
        return 0L;
    }
    
    private final double getEasyVictoryPercent() {
        return 0.0;
    }
    
    private final double getMediumVictoryPercent() {
        return 0.0;
    }
    
    private final double getHardVictoryPercent() {
        return 0.0;
    }
    
    private final double getVictoryPercent() {
        return 0.0;
    }
    
    @java.lang.Override
    public int compareTo(@org.jetbrains.annotations.NotNull
    org.player.Player other) {
        return 0;
    }
    
    @java.lang.Override
    public boolean equals(@org.jetbrains.annotations.Nullable
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override
    public int hashCode() {
        return 0;
    }
    
    public Player(@org.jetbrains.annotations.NotNull
    java.lang.String name, @org.jetbrains.annotations.NotNull
    java.lang.String password, int easyGames, int easyVictories, int mediumGames, int mediumVictories, int hardGames, int hardVictories, long openedCells) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, xi = 2, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\t\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006R\u0011\u0010\u000f\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0006R\u0011\u0010\u0011\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0006R\u0011\u0010\u0013\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\fR\u0011\u0010\u0015\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0006R\u0011\u0010\u0017\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0006R\u0011\u0010\u0019\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\fR\u0011\u0010\u001b\u001a\u00020\u001c\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u001f\u001a\u00020 \u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010#\u001a\u00020\u001c\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001eR\u0011\u0010%\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0006R\u0011\u0010\'\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010\f\u00a8\u0006)"}, d2 = {"Lorg/player/Player$Data;", "", "(Lorg/player/Player;)V", "easyGames", "", "getEasyGames", "()I", "easyVictories", "getEasyVictories", "easyVictoryPercent", "", "getEasyVictoryPercent", "()D", "games", "getGames", "hardGames", "getHardGames", "hardVictories", "getHardVictories", "hardVictoryPercent", "getHardVictoryPercent", "mediumGames", "getMediumGames", "mediumVictories", "getMediumVictories", "mediumVictoryPercent", "getMediumVictoryPercent", "name", "", "getName", "()Ljava/lang/String;", "openedCells", "", "getOpenedCells", "()J", "password", "getPassword", "victories", "getVictories", "victoryPercent", "getVictoryPercent", "Sapper_PC"})
    public final class Data {
        @org.jetbrains.annotations.NotNull
        private final java.lang.String name = null;
        @org.jetbrains.annotations.NotNull
        private final java.lang.String password = null;
        private final int easyVictories = 0;
        private final int easyGames = 0;
        private final double easyVictoryPercent = 0.0;
        private final int mediumVictories = 0;
        private final int mediumGames = 0;
        private final double mediumVictoryPercent = 0.0;
        private final int hardVictories = 0;
        private final int hardGames = 0;
        private final double hardVictoryPercent = 0.0;
        private final int victories = 0;
        private final int games = 0;
        private final double victoryPercent = 0.0;
        private final long openedCells = 0L;
        
        @org.jetbrains.annotations.NotNull
        public final java.lang.String getName() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.lang.String getPassword() {
            return null;
        }
        
        public final int getEasyVictories() {
            return 0;
        }
        
        public final int getEasyGames() {
            return 0;
        }
        
        public final double getEasyVictoryPercent() {
            return 0.0;
        }
        
        public final int getMediumVictories() {
            return 0;
        }
        
        public final int getMediumGames() {
            return 0;
        }
        
        public final double getMediumVictoryPercent() {
            return 0.0;
        }
        
        public final int getHardVictories() {
            return 0;
        }
        
        public final int getHardGames() {
            return 0;
        }
        
        public final double getHardVictoryPercent() {
            return 0.0;
        }
        
        public final int getVictories() {
            return 0;
        }
        
        public final int getGames() {
            return 0;
        }
        
        public final double getVictoryPercent() {
            return 0.0;
        }
        
        public final long getOpenedCells() {
            return 0L;
        }
        
        public Data() {
            super();
        }
    }
}