package com.mia_princz.tetris;

public class GameThread extends Thread {

    private final GameFieldPanel GAME_PANEL;

    private final GameSidePanel SIDE_PANEL;

    private final int SPEED_PER_LEVEL = 100;

    private int delay;

    private int score;

    private int level;

    private int linesCleared;

    private int nextBlock;

    private boolean isFirstBlockSpawned = false;

    private volatile boolean paused = false;

    private volatile boolean running = true;

    private final Object syncObject = new Object();

    public GameThread(GameFieldPanel gamePanel, GameSidePanel sidePanel) {
        delay = 1000;
        score = 0;
        level = 0;
        linesCleared = 0;

        GAME_PANEL = gamePanel;
        SIDE_PANEL = sidePanel;

        setName("Game-Thread");
    }

    public void stopThread() {
        running = false;
    }

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

    public void pauseGame() {
        paused = true;
        GAME_PANEL.setPaused(true);
    }

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
