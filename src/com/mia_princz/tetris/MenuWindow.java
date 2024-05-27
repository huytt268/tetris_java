package com.mia_princz.tetris;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The main menu window of the Tetris game.
 * This class extends JFrame and implements ActionListener.
 */
public class MenuWindow extends JFrame implements ActionListener {

    // The first icon for the start button
    private final ImageIcon START_BUTTON_ICON_1 = new ImageIcon("game_assets\\button_start-game.png");

    // The second icon for the start button
    private final ImageIcon START_BUTTON_ICON_2 = new ImageIcon("game_assets\\button_start-game (1).png");

    // The first icon for the leaderboard button
    private final ImageIcon LEADERBOARD_BUTTON_ICON_1 = new ImageIcon("game_assets\\button_leaderboard.png");

    // The second icon for the leaderboard button
    private final ImageIcon LEADERBOARD_BUTTON_ICON_2 = new ImageIcon("game_assets\\button_leaderboard (1).png");

    // The first icon for the settings button
    private final ImageIcon SETTINGS_BUTTON_ICON_1 = new ImageIcon("game_assets\\button_settings.png");

    // The second icon for the settings button
    private final ImageIcon SETTINGS_BUTTON_ICON_2 = new ImageIcon("game_assets\\button_settings (1).png");

    // The first icon for the exit button
    private final ImageIcon EXIT_BUTTON_ICON_1 = new ImageIcon("game_assets\\button_exit-game.png");

    // The second icon for the exit button
    private final ImageIcon EXIT_BUTTON_ICON_2 = new ImageIcon("game_assets\\button_exit-game (1).png");

    // The start button
    private JButton btbStart;

    // The leaderboard button
    private JButton btnLeaderboard;

    // The settings button
    private JButton btnSettings;

    // The exit button
    private JButton btnExit;

    // The background animation thread
    private final BackgroundAnimationThread BACKGROUND_ANIMATION_THREAD;

    /**
     * Constructs a MenuWindow object.
     *
     * @param menuBackgroundPanel       The menu background panel.
     * @param backgroundAnimationThread The background animation thread.
     */
    public MenuWindow(MenuBackgroundPanel menuBackgroundPanel, BackgroundAnimationThread backgroundAnimationThread) {
        initWindow();
        initTitleBarIcon();
        initGameLogo();
        initButtons();

        BACKGROUND_ANIMATION_THREAD = backgroundAnimationThread;
        add(menuBackgroundPanel);
    }

    private void initWindow() {
        setTitle("Tetris ~ Main Menu");
        setLayout(new BorderLayout());
        setSize(720, 900);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void initTitleBarIcon() {
        Image titleBarIcon;
        try {
            titleBarIcon = ImageIO.read(new File("game_assets\\tetris-icon3.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setIconImage(titleBarIcon);
    }

    private void initGameLogo() {
        BufferedImage img;
        try {
            img = ImageIO.read(new File("game_assets\\tetris-logo.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Image scaled = img.getScaledInstance(375, 250, Image.SCALE_SMOOTH);

        JLabel gameLogo = new JLabel();
        gameLogo.setIcon(new ImageIcon(scaled));
        gameLogo.setBounds(160, 75, 375, 250);

        add(gameLogo);
    }

    private void initButtons() {
        btbStart = new JButton();
        btbStart.setBounds(200,380,300,80);
        btbStart.setActionCommand("start");
        btbStart.addActionListener(this);
        btbStart.setOpaque(false);
        btbStart.setContentAreaFilled(false);
        btbStart.setBorderPainted(false);
        btbStart.setFocusPainted(false);
        btbStart.setIcon(START_BUTTON_ICON_1);
        btbStart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btbStart.setIcon(START_BUTTON_ICON_2);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btbStart.setIcon(START_BUTTON_ICON_1);
            }
        });

        btnLeaderboard = new JButton();
        btnLeaderboard.setBounds(200,490,300,80);
        btnLeaderboard.setActionCommand("leaderboard");
        btnLeaderboard.addActionListener(this);
        btnLeaderboard.setOpaque(false);
        btnLeaderboard.setContentAreaFilled(false);
        btnLeaderboard.setBorderPainted(false);
        btnLeaderboard.setFocusPainted(false);
        btnLeaderboard.setIcon(LEADERBOARD_BUTTON_ICON_1);
        btnLeaderboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLeaderboard.setIcon(LEADERBOARD_BUTTON_ICON_2);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLeaderboard.setIcon(LEADERBOARD_BUTTON_ICON_1);
            }
        });

        btnSettings = new JButton();
        btnSettings.setBounds(200,600,300,80);
        btnSettings.setActionCommand("settings");
        btnSettings.addActionListener(this);
        btnSettings.setOpaque(false);
        btnSettings.setContentAreaFilled(false);
        btnSettings.setBorderPainted(false);
        btnSettings.setFocusPainted(false);
        btnSettings.setIcon(SETTINGS_BUTTON_ICON_1);
        btnSettings.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSettings.setIcon(SETTINGS_BUTTON_ICON_2);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSettings.setIcon(SETTINGS_BUTTON_ICON_1);
            }
        });

        btnExit = new JButton();
        btnExit.setBounds(200,710,300,80);
        btnExit.setActionCommand("exit");
        btnExit.addActionListener(this);
        btnExit.setOpaque(false);
        btnExit.setContentAreaFilled(false);
        btnExit.setBorderPainted(false);
        btnExit.setFocusPainted(false);
        btnExit.setIcon(EXIT_BUTTON_ICON_1);
        btnExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnExit.setIcon(EXIT_BUTTON_ICON_2);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnExit.setIcon(EXIT_BUTTON_ICON_1);
            }
        });

        add(btbStart);
        add(btnLeaderboard);
        add(btnExit);
        add(btnSettings);
    }


    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("start")) {
            AudioPlayer.playButtonSound();

            BACKGROUND_ANIMATION_THREAD.stopThread();

            setVisible(false);

            Tetris.start();
            dispose();

        } else if (ae.getActionCommand().equals("leaderboard")) {
            AudioPlayer.playButtonSound();

            BACKGROUND_ANIMATION_THREAD.stopThread();

            setVisible(false);
            try {
                Tetris.showLeaderboard();
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if (ae.getActionCommand().equals("settings")) {
            AudioPlayer.playButtonSound();

            BACKGROUND_ANIMATION_THREAD.stopThread();

            setVisible(false);

            Tetris.showSettings();
        } else if (ae.getActionCommand().equals("exit")) {
            AudioPlayer.playButtonSound();

            BACKGROUND_ANIMATION_THREAD.stopThread();

            dispose();
        }
    }
}
