package com.mia_princz.tetris;

import javax.swing.*;
import java.io.IOException;

/**
 * The main class of the Tetris game.
 */
public class Tetris {

    // Game window
    private static GameWindow GAME_WINDOW;

    // Menu window
    private static MenuWindow MENU_WINDOW;

    // Leaderboard window
    private static LeaderboardWindow LEADERBOARD_WINDOW;

    // Menu background panel
    private static MenuBackgroundPanel MENU_BACKGROUND_PANEL;

    // Settings window
    private static SettingsWindow SETTINGS_WINDOW;

    /**
     * Starts the Tetris game.
     * Creates a new game window and starts the game.
     */
    public static void start() {
        try {
            GAME_WINDOW = new GameWindow();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        GAME_WINDOW.setVisible(true);
        GAME_WINDOW.startGame();
    }

    /**
     * Shows the settings window.
     * Creates a menu background panel and a background animation thread.
     * Displays the settings window and starts the background animation.
     */
    public static void showSettings() {
        MENU_BACKGROUND_PANEL = new MenuBackgroundPanel(720, 900);

        BackgroundAnimationThread backgroundAnimationThread = new BackgroundAnimationThread(MENU_BACKGROUND_PANEL);
        backgroundAnimationThread.start();

        SETTINGS_WINDOW = new SettingsWindow(MENU_BACKGROUND_PANEL, backgroundAnimationThread);
        SETTINGS_WINDOW.setVisible(true);
    }

    /**
     * Shows the leaderboard window.
     * Creates a menu background panel and a background animation thread.
     * Displays the leaderboard window, starts the background animation, and loads the leaderboard data.
     *
     * @throws IOException            if an I/O error occurs.
     * @throws ClassNotFoundException if the class of a serialized object cannot be found.
     */
    public static void showLeaderboard() throws IOException, ClassNotFoundException {
        MENU_BACKGROUND_PANEL = new MenuBackgroundPanel(720, 1080);

        BackgroundAnimationThread backgroundAnimationThread = new BackgroundAnimationThread(MENU_BACKGROUND_PANEL);
        backgroundAnimationThread.start();

        LEADERBOARD_WINDOW = new LeaderboardWindow(MENU_BACKGROUND_PANEL, backgroundAnimationThread);
        LEADERBOARD_WINDOW.setVisible(true);

        LEADERBOARD_WINDOW.getDATA().loadLeaderboard();
    }

    /**
     * Shows the main menu window.
     * Creates a menu background panel and a background animation thread.
     * Displays the main menu window and starts the background animation.
     *
     * @throws IOException if an I/O error occurs.
     */
    public static void showMainMenu() throws IOException {
        MENU_BACKGROUND_PANEL = new MenuBackgroundPanel(720, 900);

        BackgroundAnimationThread backgroundAnimationThread = new BackgroundAnimationThread(MENU_BACKGROUND_PANEL);
        backgroundAnimationThread.start();

        MENU_WINDOW = new MenuWindow(MENU_BACKGROUND_PANEL, backgroundAnimationThread);
        MENU_WINDOW.setVisible(true);
    }

    /**
     * Handles the game over event.
     * Prompts the player to enter their name.
     * Displays the leaderboard window, adds the player to the leaderboard, and saves the leaderboard data.
     *
     * @param playerScore the score achieved by the player.
     */
    public static void gameOver(int playerScore) {
        String playerName = JOptionPane.showInputDialog("Game Over!\nPlease enter your name:");
        GAME_WINDOW.setVisible(false);

        try {
            showLeaderboard();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        if (playerName != null && !playerName.isEmpty()) {
            LEADERBOARD_WINDOW.addPlayer(playerName, playerScore);
            LEADERBOARD_WINDOW.getDATA().saveLeaderboard();
        }
    }

    /**
     * The main method of the Tetris game.
     * Initializes the audio player, plays the game theme song, and shows the main menu.
     *
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        AudioPlayer.initSounds();
        AudioPlayer.playGameThemeSong();

        try {
            showMainMenu();
        } catch (IOException e) {
            System.out.println("Exception. . .");
        }
    }
}