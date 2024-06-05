package com.mia_princz.tetris;

import javax.swing.*;
import java.io.IOException;

public class Tetris {

    private static GameWindow GAME_WINDOW;

    private static MenuWindow MENU_WINDOW;

    private static LeaderboardWindow LEADERBOARD_WINDOW;

    private static MenuBackgroundPanel MENU_BACKGROUND_PANEL;

    private static SettingsWindow SETTINGS_WINDOW;

    public static void start() {
        try {
            GAME_WINDOW = new GameWindow();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        GAME_WINDOW.setVisible(true);
        GAME_WINDOW.startGame();
    }

    public static void showSettings() {
        MENU_BACKGROUND_PANEL = new MenuBackgroundPanel(560, 700);

        BackgroundAnimationThread backgroundAnimationThread = new BackgroundAnimationThread(MENU_BACKGROUND_PANEL);
        backgroundAnimationThread.start();

        SETTINGS_WINDOW = new SettingsWindow(MENU_BACKGROUND_PANEL, backgroundAnimationThread);
        SETTINGS_WINDOW.setVisible(true);
    }

    public static void showLeaderboard() throws IOException, ClassNotFoundException {
        MENU_BACKGROUND_PANEL = new MenuBackgroundPanel(560, 700);

        BackgroundAnimationThread backgroundAnimationThread = new BackgroundAnimationThread(MENU_BACKGROUND_PANEL);
        backgroundAnimationThread.start();

        LEADERBOARD_WINDOW = new LeaderboardWindow(MENU_BACKGROUND_PANEL, backgroundAnimationThread);
        LEADERBOARD_WINDOW.setVisible(true);

        LEADERBOARD_WINDOW.getDATA().loadLeaderboard();
    }

    public static void showMainMenu() throws IOException {
        MENU_BACKGROUND_PANEL = new MenuBackgroundPanel(560, 700);

        BackgroundAnimationThread backgroundAnimationThread = new BackgroundAnimationThread(MENU_BACKGROUND_PANEL);
        backgroundAnimationThread.start();

        MENU_WINDOW = new MenuWindow(MENU_BACKGROUND_PANEL, backgroundAnimationThread);
        MENU_WINDOW.setVisible(true);
    }

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