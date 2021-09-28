package org.game;

import java.lang.System;

/**
 * 32 x 32, 256 mines
 */
@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, xi = 2, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0004"}, d2 = {"Lorg/game/Hard;", "Lorg/game/GameType;", "()V", "Companion", "Sapper_PC"})
public final class Hard implements org.game.GameType {
    @org.jetbrains.annotations.NotNull
    public static final org.game.Hard.Companion Companion = null;
    
    public Hard() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, xi = 2, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006\u00a8\u0006\b"}, d2 = {"Lorg/game/Hard$Companion;", "", "()V", "start", "", "name", "", "password", "Sapper_PC"})
    public static final class Companion {
        
        /**
         * 32 x 32, 256 mines
         * @param name name of player
         */
        public final void start(@org.jetbrains.annotations.NotNull
        java.lang.String name, @org.jetbrains.annotations.NotNull
        java.lang.String password) {
        }
        
        private Companion() {
            super();
        }
    }
}