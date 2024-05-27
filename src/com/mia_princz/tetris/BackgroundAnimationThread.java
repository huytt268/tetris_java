package com.mia_princz.tetris;

/**
 * The BackgroundAnimationThread class is responsible for running the background animation
 * in a separate thread. It continuously spawns blocks and moves them down until a stop signal is received.
 */
public class BackgroundAnimationThread extends Thread {

    // A flag to indicate whether the thread should stop.
    private volatile boolean stopSignal;

    // The background panel on which the animation is displayed.
    private final MenuBackgroundPanel BACKGROUND_PANEL;

    /**
     * Constructs a new BackgroundAnimationThread with the specified background panel.
     * @param backgroundPanel The MenuBackgroundPanel on which the animation will be displayed.
     */
    public BackgroundAnimationThread(MenuBackgroundPanel backgroundPanel) {
        // Initialization of instance variables
        stopSignal = false;
        BACKGROUND_PANEL = backgroundPanel;
        setName("BackgroundAnimation-Thread");
    }

    /**
     * Signals the thread to stop by setting the stopSignal flag to true.
     */
    public void stopThread() {
        stopSignal = true;
    }


    /**
     * The main logic of the background animation thread.
     * It continuously spawns blocks and moves them down until a stop signal is received.
     * If the thread is interrupted, it throws a RuntimeException.
     */
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
