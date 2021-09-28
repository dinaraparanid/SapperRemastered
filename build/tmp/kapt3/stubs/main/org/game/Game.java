package org.game;

import java.lang.System;

/**
 * Game itself
 * @param x amount of rows
 * @param y amount of columns
 * @param mines amount of mines
 * @param name name of player
 * @param gameType type of game
 * (easy, medium, hard or custom)
 */
@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, xi = 2, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0018\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 &2\u00020\u0001:\u0002&\'B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u00a2\u0006\u0002\u0010\u000bJ\u0006\u0010$\u001a\u00020%R\"\u0010\f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\r0\rX\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0003X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0011\u001a\n \u0013*\u0004\u0018\u00010\u00120\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\rX\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u001aR\u000e\u0010\u0005\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0003X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\rX\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u001eR\u000e\u0010\u001f\u001a\u00020 X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\"X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006("}, d2 = {"Lorg/game/Game;", "", "x", "", "y", "mines", "name", "", "password", "gameType", "Lorg/game/GameType;", "(IIILjava/lang/String;Ljava/lang/String;Lorg/game/GameType;)V", "bts", "", "Ljava/io/Serializable;", "[[[Ljava/io/Serializable;", "cellsLeft", "executor", "Ljava/util/concurrent/ExecutorService;", "kotlin.jvm.PlatformType", "frame", "Ljavax/swing/JFrame;", "grid", "Ljavax/swing/JPanel;", "mineTable", "", "[[Z", "minesLeft", "numberTable", "", "[[I", "player", "Lorg/player/Player;", "status", "Ljavax/swing/JLabel;", "timer", "start", "", "Companion", "MouseClickListener", "Sapper_PC"})
public final class Game {
    private final java.util.concurrent.ExecutorService executor = null;
    private final javax.swing.JFrame frame = null;
    private final org.player.Player player = null;
    private int minesLeft;
    private int cellsLeft;
    private final javax.swing.JLabel status = null;
    private final javax.swing.JLabel timer = null;
    private final java.io.Serializable[][][] bts = null;
    private final boolean[][] mineTable = null;
    private final javax.swing.JPanel grid = null;
    private final int[][] numberTable = null;
    private final int x = 0;
    private final int y = 0;
    private final int mines = 0;
    private final java.lang.String name = null;
    private final org.game.GameType gameType = null;
    @org.jetbrains.annotations.NotNull
    public static final org.game.Game.Companion Companion = null;
    
    public final void start() {
    }
    
    public Game(int x, int y, int mines, @org.jetbrains.annotations.NotNull
    java.lang.String name, @org.jetbrains.annotations.NotNull
    java.lang.String password, @org.jetbrains.annotations.NotNull
    org.game.GameType gameType) {
        super();
    }
    
    /**
     * Gets image of required scale
     * @param srcImg image itself
     * @param w width
     * @param h height
     * @return scaled image
     */
    @org.jetbrains.annotations.NotNull
    public static final java.awt.image.BufferedImage getScaledImage$Sapper_PC(@org.jetbrains.annotations.NotNull
    java.awt.Image srcImg, int w, int h) {
        return null;
    }
    
    /**
     * Choose game type
     * @param name name of player
     */
    public static final void chooseGameType$Sapper_PC(@org.jetbrains.annotations.NotNull
    java.lang.String name, @org.jetbrains.annotations.NotNull
    java.lang.String password) {
    }
    
    /**
     * Mouse Click Handler.
     * Opens cells when mouse is released.
     *
     * @param bt cell button
     * @param xc height
     * @param yc width
     */
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, xi = 2, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0082\u0004\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0007J\u0012\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\u0012\u0010\u0014\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\u0012\u0010\u0015\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\u0012\u0010\u0016\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\u0012\u0010\u0017\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\u0018\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u0005H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u0018\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u000bX\u0082.\u00a2\u0006\u0004\n\u0002\u0010\fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\r\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u000f0\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2 = {"Lorg/game/Game$MouseClickListener;", "Ljava/awt/event/MouseListener;", "bt", "Ljavax/swing/JButton;", "xc", "", "yc", "(Lorg/game/Game;Ljavax/swing/JButton;II)V", "mineImage", "Ljava/awt/image/BufferedImage;", "numImages", "", "[Ljava/awt/image/BufferedImage;", "zeroes", "Lkotlin/collections/ArrayDeque;", "Lkotlin/Pair;", "mouseClicked", "", "e", "Ljava/awt/event/MouseEvent;", "mouseEntered", "mouseExited", "mousePressed", "mouseReleased", "open", "xcc", "ycc", "Sapper_PC"})
    final class MouseClickListener implements java.awt.event.MouseListener {
        private java.awt.image.BufferedImage[] numImages;
        private java.awt.image.BufferedImage mineImage;
        private final kotlin.collections.ArrayDeque<kotlin.Pair<java.lang.Integer, java.lang.Integer>> zeroes = null;
        private final javax.swing.JButton bt = null;
        private final int xc = 0;
        private final int yc = 0;
        
        private final void open(int xcc, int ycc) {
        }
        
        @java.lang.Override
        public void mouseReleased(@org.jetbrains.annotations.Nullable
        java.awt.event.MouseEvent e) {
        }
        
        @java.lang.Override
        public void mouseClicked(@org.jetbrains.annotations.Nullable
        java.awt.event.MouseEvent e) {
        }
        
        @java.lang.Override
        public void mousePressed(@org.jetbrains.annotations.Nullable
        java.awt.event.MouseEvent e) {
        }
        
        @java.lang.Override
        public void mouseEntered(@org.jetbrains.annotations.Nullable
        java.awt.event.MouseEvent e) {
        }
        
        @java.lang.Override
        public void mouseExited(@org.jetbrains.annotations.Nullable
        java.awt.event.MouseEvent e) {
        }
        
        public MouseClickListener(@org.jetbrains.annotations.NotNull
        javax.swing.JButton bt, int xc, int yc) {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, xi = 2, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001d\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0001\u00a2\u0006\u0002\b\bJ%\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH\u0001\u00a2\u0006\u0002\b\u0010\u00a8\u0006\u0011"}, d2 = {"Lorg/game/Game$Companion;", "", "()V", "chooseGameType", "", "name", "", "password", "chooseGameType$Sapper_PC", "getScaledImage", "Ljava/awt/image/BufferedImage;", "srcImg", "Ljava/awt/Image;", "w", "", "h", "getScaledImage$Sapper_PC", "Sapper_PC"})
    public static final class Companion {
        
        /**
         * Gets image of required scale
         * @param srcImg image itself
         * @param w width
         * @param h height
         * @return scaled image
         */
        @org.jetbrains.annotations.NotNull
        public final java.awt.image.BufferedImage getScaledImage$Sapper_PC(@org.jetbrains.annotations.NotNull
        java.awt.Image srcImg, int w, int h) {
            return null;
        }
        
        /**
         * Choose game type
         * @param name name of player
         */
        public final void chooseGameType$Sapper_PC(@org.jetbrains.annotations.NotNull
        java.lang.String name, @org.jetbrains.annotations.NotNull
        java.lang.String password) {
        }
        
        private Companion() {
            super();
        }
    }
}