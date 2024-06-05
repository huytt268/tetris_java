package com.mia_princz.tetris;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class GameWindow extends JFrame implements ActionListener {
    private GameFieldPanel gameField;
    private GameSidePanel scorePanel;
    private GameThread gameThread;
    private final Image BACKGROUND_IMG = ImageIO.read(new File("game_assets\\space.jpg"));
    private final Image TITLE_BAR_ICON = ImageIO.read(new File("game_assets\\tetris-icon3.png"));
    private final ImageIcon RETURN_TO_MENU_ICON_1 = new ImageIcon("game_assets\\button_return-to-menu.png");
    private final ImageIcon RETURN_TO_MENU_ICON_2 = new ImageIcon("game_assets\\button_return-to-menu (1).png");
    private final ImageIcon PAUSE_BUTTON_ICON_1 = new ImageIcon("game_assets\\button_pause.png");
    private final ImageIcon PAUSE_BUTTON_ICON_2 = new ImageIcon("game_assets\\button_pause (1).png");
    private final ImageIcon CONTINUE_BUTTON_ICON_1 = new ImageIcon("game_assets\\button_continue.png");
    private final ImageIcon CONTINUE_BUTTON_ICON_2 = new ImageIcon("game_assets\\button_continue (1).png");
    private final ImageIcon MUTE_BUTTON_ICON_1 = new ImageIcon("game_assets\\button_mute.png");
    private final ImageIcon MUTE_BUTTON_ICON_2 = new ImageIcon("game_assets\\button_mute (1).png");
    private final ImageIcon UNMUTE_BUTTON_ICON_1 = new ImageIcon("game_assets\\button_unmute.png");
    private final ImageIcon UNMUTE_BUTTON_ICON_2 = new ImageIcon("game_assets\\button_unmute (1).png");
    private final ImageIcon RESTART_BUTTON_ICON_1 = new ImageIcon("game_assets\\button_restart.png");
    private final ImageIcon RESTART_BUTTON_ICON_2 = new ImageIcon("game_assets\\button_restart (1).png");
    private JButton btnReturnToMenu;
    private JButton btnPause;
    private JButton btnContinue;
    private JButton btnMute;
    private JButton btnUnmute;
    private JButton btnRestart;
    private boolean isPaused = false;

    public GameWindow() throws IOException {
        initWindow();
        initGameField();
        initSidePanel();
        initButtons();
        initControls();
    }

    private void initWindow() {
        setTitle("Tetris ~ Game");
        setLayout(null);
        setSize(1080, 800);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setIconImage(TITLE_BAR_ICON);
        setContentPane(new JLabel(new ImageIcon(BACKGROUND_IMG)));
    }

    private void initGameField() {
        gameField = new GameFieldPanel(10);
        add(gameField);
    }

    private void initSidePanel() {
        scorePanel = new GameSidePanel();
        add(scorePanel);
    }

    private void initButtons() {
        btnReturnToMenu = new JButton();
        btnReturnToMenu.setBounds(25, 120, 300, 80);
        btnReturnToMenu.setActionCommand("back");
        btnReturnToMenu.addActionListener(this);
        btnReturnToMenu.setOpaque(false);
        btnReturnToMenu.setContentAreaFilled(false);
        btnReturnToMenu.setBorderPainted(false);
        btnReturnToMenu.setFocusPainted(false);
        btnReturnToMenu.setIcon(new ImageIcon(RETURN_TO_MENU_ICON_1.getImage().getScaledInstance(188, 80, Image.SCALE_SMOOTH)));
        btnReturnToMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnReturnToMenu.setIcon(new ImageIcon(RETURN_TO_MENU_ICON_2.getImage().getScaledInstance(188, 80, Image.SCALE_SMOOTH)));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnReturnToMenu.setIcon(new ImageIcon(RETURN_TO_MENU_ICON_1.getImage().getScaledInstance(188, 80, Image.SCALE_SMOOTH)));
            }
        });

        btnPause = new JButton();
        btnPause.setBounds(25, 225, 300, 80);
        btnPause.setActionCommand("pause");
        btnPause.addActionListener(this);
        btnPause.setOpaque(false);
        btnPause.setContentAreaFilled(false);
        btnPause.setBorderPainted(false);
        btnPause.setFocusPainted(false);
        btnPause.setIcon(new ImageIcon(PAUSE_BUTTON_ICON_1.getImage().getScaledInstance(188, 80, Image.SCALE_SMOOTH)));
        btnPause.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnPause.setIcon(new ImageIcon(PAUSE_BUTTON_ICON_2.getImage().getScaledInstance(188, 80, Image.SCALE_SMOOTH)));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnPause.setIcon(new ImageIcon(PAUSE_BUTTON_ICON_1.getImage().getScaledInstance(188, 80, Image.SCALE_SMOOTH)));
            }
        });

        btnContinue = new JButton();
        btnContinue.setBounds(25, 225, 300, 80);
        btnContinue.setActionCommand("continue");
        btnContinue.addActionListener(this);
        btnContinue.setOpaque(false);
        btnContinue.setContentAreaFilled(false);
        btnContinue.setBorderPainted(false);
        btnContinue.setFocusPainted(false);
        btnContinue.setVisible(false);
        btnContinue.setIcon(new ImageIcon(CONTINUE_BUTTON_ICON_1.getImage().getScaledInstance(188, 80, Image.SCALE_SMOOTH)));
        btnContinue.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnContinue.setIcon(new ImageIcon(CONTINUE_BUTTON_ICON_2.getImage().getScaledInstance(188, 80, Image.SCALE_SMOOTH)));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnContinue.setIcon(new ImageIcon(CONTINUE_BUTTON_ICON_1.getImage().getScaledInstance(188, 80, Image.SCALE_SMOOTH)));
            }
        });

        btnMute = new JButton();
        btnMute.setBounds(25, 330, 300, 80);
        btnMute.setActionCommand("mute");
        btnMute.addActionListener(this);
        btnMute.setOpaque(false);
        btnMute.setContentAreaFilled(false);
        btnMute.setBorderPainted(false);
        btnMute.setFocusPainted(false);
        btnMute.setIcon(new ImageIcon(MUTE_BUTTON_ICON_1.getImage().getScaledInstance(188, 80, Image.SCALE_SMOOTH)));
        btnMute.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMute.setIcon(new ImageIcon(MUTE_BUTTON_ICON_2.getImage().getScaledInstance(188, 80, Image.SCALE_SMOOTH)));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMute.setIcon(new ImageIcon(MUTE_BUTTON_ICON_1.getImage().getScaledInstance(188, 80, Image.SCALE_SMOOTH)));
            }
        });

        btnUnmute = new JButton();
        btnUnmute.setBounds(25, 330, 300, 80);
        btnUnmute.setActionCommand("unmute");
        btnUnmute.addActionListener(this);
        btnUnmute.setOpaque(false);
        btnUnmute.setContentAreaFilled(false);
        btnUnmute.setBorderPainted(false);
        btnUnmute.setFocusPainted(false);
        btnUnmute.setIcon(new ImageIcon(UNMUTE_BUTTON_ICON_1.getImage().getScaledInstance(188, 80, Image.SCALE_SMOOTH)));
        btnUnmute.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnUnmute.setIcon(new ImageIcon(UNMUTE_BUTTON_ICON_2.getImage().getScaledInstance(188, 80, Image.SCALE_SMOOTH)));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnUnmute.setIcon(new ImageIcon(UNMUTE_BUTTON_ICON_1.getImage().getScaledInstance(188, 80, Image.SCALE_SMOOTH)));
            }
        });

        btnRestart = new JButton();
        btnRestart.setBounds(25, 435, 300, 80);
        btnRestart.setActionCommand("restart");
        btnRestart.addActionListener(this);
        btnRestart.setOpaque(false);
        btnRestart.setContentAreaFilled(false);
        btnRestart.setBorderPainted(false);
        btnRestart.setFocusPainted(false);
        btnRestart.setIcon(new ImageIcon(RESTART_BUTTON_ICON_1.getImage().getScaledInstance(188, 80, Image.SCALE_SMOOTH)));
        btnRestart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRestart.setIcon(new ImageIcon(RESTART_BUTTON_ICON_2.getImage().getScaledInstance(188, 80, Image.SCALE_SMOOTH)));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRestart.setIcon(new ImageIcon(RESTART_BUTTON_ICON_1.getImage().getScaledInstance(188, 80, Image.SCALE_SMOOTH)));
            }
        });

        if (AudioPlayer.getIsMuted()) {
            btnMute.setVisible(false);
        } else {
            btnUnmute.setVisible(false);
        }

        add(btnReturnToMenu);
        add(btnPause);
        add(btnContinue);
        add(btnMute);
        add(btnUnmute);
        add(btnRestart);
    }

    private void initControls() {
        InputMap inputMap = getRootPane().getInputMap();
        ActionMap actionMap = getRootPane().getActionMap();

        inputMap.put(KeyStroke.getKeyStroke("RIGHT"), "right");
        inputMap.put(KeyStroke.getKeyStroke("LEFT"), "left");
        inputMap.put(KeyStroke.getKeyStroke("UP"), "up");
        inputMap.put(KeyStroke.getKeyStroke("DOWN"), "down");
        inputMap.put(KeyStroke.getKeyStroke("SPACE"), "space");

        actionMap.put("right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isPaused) {
                    gameField.moveBlockRight();
                }
            }
        });
        actionMap.put("left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isPaused) {
                    gameField.moveBlockLeft();
                }
            }
        });
        actionMap.put("up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isPaused) {
                    gameField.rotateBlock();
                }
            }
        });
        actionMap.put("down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isPaused) {
                    gameField.moveBlockDown();
                }
            }
        });
        actionMap.put("space", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isPaused) {
                    gameField.dropBlock();
                }
            }
        });
    }

    public void startGame() {
        gameThread = new GameThread(gameField, scorePanel);
        gameThread.start();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("pause")) {
            AudioPlayer.playButtonSound();
            btnPause.setVisible(false);
            btnContinue.setVisible(true);
            isPaused = true;
            gameThread.pauseGame();

        } else if (ae.getActionCommand().equals("continue")) {
            AudioPlayer.playButtonSound();
            btnPause.setVisible(true);
            btnContinue.setVisible(false);
            isPaused = false;
            gameThread.continueGame();

        } else if (ae.getActionCommand().equals("mute")) {
            AudioPlayer.playButtonSound();
            btnMute.setVisible(false);
            btnUnmute.setVisible(true);
            AudioPlayer.muteOrUnmuteGame();

        } else if (ae.getActionCommand().equals("unmute")) {
            AudioPlayer.playButtonSound();
            btnMute.setVisible(true);
            btnUnmute.setVisible(false);
            AudioPlayer.muteOrUnmuteGame();

        } else if (ae.getActionCommand().equals("restart")) {
            AudioPlayer.playButtonSound();
            gameThread.stopThread();
            Tetris.start();
            dispose();

        } else if (ae.getActionCommand().equals("back")) {
            AudioPlayer.playButtonSound();

            gameThread.stopThread();
            setVisible(false);

            try {
                Tetris.showMainMenu();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}