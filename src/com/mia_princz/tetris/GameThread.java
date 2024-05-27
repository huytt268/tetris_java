package com.mia_princz.tetris;

/**
 * Represents a thread that controls the Tetris game logic and gameplay.
 */
public class GameThread extends Thread {

    // The game field panel where the Tetris blocks are placed.
    private final GameFieldPanel GAME_PANEL;

    // The side panel that displays game information.
    private final GameSidePanel SIDE_PANEL;

    // The speed increment per level.
    private final int SPEED_PER_LEVEL = 100;

    // The delay between each block movement.
    private int delay;

    // The current score.
    private int score;

    // The current level.
    private int level;

    // The total number of lines cleared.
    private int linesCleared;

    // The index of the next Tetris block.
    private int nextBlock;

    // Flag indicating if the first block has been spawned.
    private boolean isFirstBlockSpawned = false;

    // Flag indicating if the game is paused.
    private volatile boolean paused = false;

    // Flag indicating if the thread should stop.
    private volatile boolean running = true;

    // Synchronized object
    private final Object syncObject = new Object();

    /**
     * Constructs a new GameThread instance.
     *
     * @param gamePanel The game field panel.
     * @param sidePanel The side panel.
     */
    public GameThread(GameFieldPanel gamePanel, GameSidePanel sidePanel) {
        delay = 1000;
        score = 8240;
        level = 0;
        linesCleared = 0;

        GAME_PANEL = gamePanel;
        SIDE_PANEL = sidePanel;

        setName("Game-Thread");
    }

    /**
     * Signals the thread to stop.
     */
    public void stopThread() {
        running = false;
    }

    /**
     * Runs the game logic in the thread.
     * Spawns a new block, moves it down, clears lines, updates score and level, and handles game over.
     */
    @Override
    public void run() {
        while (running) {
            GAME_PANEL.spawnBlock(nextBlock, isFirstBlockSpawned);
            isFirstBlockSpawned = true;

            nextBlock = SIDE_PANEL.spawnBlock();
            SIDE_PANEL.repaint();

            while (GAME_PANEL.moveBlockDown()) {
                synchronized (syncObject) {
                    if (paused) {
                        try {
                            syncObject.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    if (!paused) {
                    }
                    try {
                        AudioPlayer.playMoveBlockSound();
                        Thread.sleep(delay);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            if (GAME_PANEL.isBlockOutOfBounds()) {
                AudioPlayer.playGameOverSound();

                Tetris.gameOver(score);
                stopThread();

                break;
            }

            GAME_PANEL.moveBlockToBackground();

            int currentLinesCleared = GAME_PANEL.clearLines();
            linesCleared += currentLinesCleared;
            int points;

            switch (currentLinesCleared) {
                case 1 -> {
                    points = 40;
                    AudioPlayer.playLineClearedSound();
                    levelCheck();
                }
                case 2 -> {
                    points = 100;
                    AudioPlayer.playLineClearedSound();
                    levelCheck();
                }
                case 3 -> {
                    points = 300;
                    AudioPlayer.playLineClearedSound();
                    levelCheck();
                }
                case 4 -> {
                    points = 1200;
                    AudioPlayer.playFourLineClearedSound();
                    levelCheck();
                }
                default -> {
                    points = 0;
                    AudioPlayer.playBlockLandedSound();
                }
            }

            score += points * (level + 1);
            SIDE_PANEL.updateScore(score);
            SIDE_PANEL.updateClearedLines(linesCleared);
        }
    }

    /**
     * Checks the current level and updates it if necessary.
     * Also adjusts the pause time between block movements based on the level.
     */
    private void levelCheck() {
        if (linesCleared / 10 >= (level + 1) ) {
            AudioPlayer.playLevelUpSound();
            level++;
            SIDE_PANEL.updateLevel(level);
            if (delay > 100) {
                delay -= SPEED_PER_LEVEL;
            }
        }
    }

    /**
     * Pauses the game.
     */
    public void pauseGame() {
        paused = true;
        GAME_PANEL.setPaused(true);
    }

    /**
     * Resumes the game from pause.
     */
    public void continueGame() {
        synchronized (syncObject) {
            paused = false;

            synchronized (GAME_PANEL.getSyncObject()) {
                GAME_PANEL.setPaused(false);
                GAME_PANEL.getSyncObject().notify();
            }

            syncObject.notify();
        }
    }
}
