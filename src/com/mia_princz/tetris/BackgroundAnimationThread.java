package com.mia_princz.tetris;

public class BackgroundAnimationThread extends Thread {

    private volatile boolean stopSignal;

    private final MenuBackgroundPanel BACKGROUND_PANEL;

    public BackgroundAnimationThread(MenuBackgroundPanel backgroundPanel) {
        stopSignal = false;
        BACKGROUND_PANEL = backgroundPanel;
        setName("BackgroundAnimation-Thread");
    }

    public void stopThread() {
        stopSignal = true;
    }

    @Override
    public void run() {
        // Main animation loop
        while (!stopSignal) {
            // Loop until a stop signal is received
            BACKGROUND_PANEL.spawnBlock();
            while (BACKGROUND_PANEL.checkBottom()) {
                // Move the block down until it reaches the bottom
                try {
                    BACKGROUND_PANEL.block.moveDown();
                    BACKGROUND_PANEL.repaint();
                    // Pause for a short duration to control the speed of the animation
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
