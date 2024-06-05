package com.mia_princz.tetris;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MenuWindow extends JFrame implements ActionListener {

    private final ImageIcon START_BUTTON_ICON_1 = new ImageIcon("game_assets\\button_start-game.png");
    private final ImageIcon START_BUTTON_ICON_2 = new ImageIcon("game_assets\\button_start-game (1).png");
    private final ImageIcon LEADERBOARD_BUTTON_ICON_1 = new ImageIcon("game_assets\\button_leaderboard.png");
    private final ImageIcon LEADERBOARD_BUTTON_ICON_2 = new ImageIcon("game_assets\\button_leaderboard (1).png");
    private final ImageIcon SETTINGS_BUTTON_ICON_1 = new ImageIcon("game_assets\\button_settings.png");
    private final ImageIcon SETTINGS_BUTTON_ICON_2 = new ImageIcon("game_assets\\button_settings (1).png");
    private final ImageIcon EXIT_BUTTON_ICON_1 = new ImageIcon("game_assets\\button_exit-game.png");
    private final ImageIcon EXIT_BUTTON_ICON_2 = new ImageIcon("game_assets\\button_exit-game (1).png");

    private JButton btbStart;
    private JButton btnLeaderboard;
    private JButton btnSettings;
    private JButton btnExit;

    private final BackgroundAnimationThread BACKGROUND_ANIMATION_THREAD;

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
        setSize(560, 700); // Resized to 560x700
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
        gameLogo.setBounds(90, 50, 375, 250); // Adjusted position for resizing

        add(gameLogo);
    }

    private void initButtons() {
        btbStart = new JButton();
        btbStart.setActionCommand("start");
        btbStart.addActionListener(this);
        btbStart.setOpaque(false);
        btbStart.setContentAreaFilled(false);
        btbStart.setBorderPainted(false);
        btbStart.setFocusPainted(false);
        btbStart.setIcon(new ImageIcon(START_BUTTON_ICON_1.getImage().getScaledInstance(188, 80, Image.SCALE_SMOOTH)));
        btbStart.setBounds((getWidth() - 188) / 2, 310, 188, 80);
        btbStart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btbStart.setIcon(new ImageIcon(START_BUTTON_ICON_2.getImage().getScaledInstance(188, 80, Image.SCALE_SMOOTH)));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btbStart.setIcon(new ImageIcon(START_BUTTON_ICON_1.getImage().getScaledInstance(188, 80, Image.SCALE_SMOOTH)));
            }
        });

        btnLeaderboard = new JButton();
        btnLeaderboard.setActionCommand("leaderboard");
        btnLeaderboard.addActionListener(this);
        btnLeaderboard.setOpaque(false);
        btnLeaderboard.setContentAreaFilled(false);
        btnLeaderboard.setBorderPainted(false);
        btnLeaderboard.setFocusPainted(false);
        btnLeaderboard.setIcon(new ImageIcon(LEADERBOARD_BUTTON_ICON_1.getImage().getScaledInstance(188, 80, Image.SCALE_SMOOTH)));
        btnLeaderboard.setBounds((getWidth() - 188) / 2, 400, 188, 80);
        btnLeaderboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLeaderboard.setIcon(new ImageIcon(LEADERBOARD_BUTTON_ICON_2.getImage().getScaledInstance(188, 80, Image.SCALE_SMOOTH)));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLeaderboard.setIcon(new ImageIcon(LEADERBOARD_BUTTON_ICON_1.getImage().getScaledInstance(188, 80, Image.SCALE_SMOOTH)));
            }
        });

        btnSettings = new JButton();
        btnSettings.setActionCommand("settings");
        btnSettings.addActionListener(this);
        btnSettings.setOpaque(false);
        btnSettings.setContentAreaFilled(false);
        btnSettings.setBorderPainted(false);
        btnSettings.setFocusPainted(false);
        btnSettings.setIcon(new ImageIcon(SETTINGS_BUTTON_ICON_1.getImage().getScaledInstance(188, 80, Image.SCALE_SMOOTH)));
        btnSettings.setBounds((getWidth() - 188) / 2, 490, 188, 80);
        btnSettings.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSettings.setIcon(new ImageIcon(SETTINGS_BUTTON_ICON_2.getImage().getScaledInstance(188, 80, Image.SCALE_SMOOTH)));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSettings.setIcon(new ImageIcon(SETTINGS_BUTTON_ICON_1.getImage().getScaledInstance(188, 80, Image.SCALE_SMOOTH)));
            }
        });

        btnExit = new JButton();
        btnExit.setActionCommand("exit");
        btnExit.addActionListener(this);
        btnExit.setOpaque(false);
        btnExit.setContentAreaFilled(false);
        btnExit.setBorderPainted(false);
        btnExit.setFocusPainted(false);
        btnExit.setIcon(new ImageIcon(EXIT_BUTTON_ICON_1.getImage().getScaledInstance(188, 80, Image.SCALE_SMOOTH)));
        btnExit.setBounds((getWidth() - 188) / 2, 580, 188, 80);
        btnExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnExit.setIcon(new ImageIcon(EXIT_BUTTON_ICON_2.getImage().getScaledInstance(188, 80, Image.SCALE_SMOOTH)));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnExit.setIcon(new ImageIcon(EXIT_BUTTON_ICON_1.getImage().getScaledInstance(188, 80, Image.SCALE_SMOOTH)));
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
            //AudioPlayer.playButtonSound();

            BACKGROUND_ANIMATION_THREAD.stopThread();
            System.exit(0);
            //dispose();
        }
    }
}
