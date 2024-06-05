package com.mia_princz.tetris;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class LeaderboardWindow extends JFrame implements ActionListener {

    private JTable leaderboard;
    private final LeaderboardData DATA;
    private final Image TITLE_BAR_ICON = ImageIO.read(new File("game_assets\\tetris-icon3.png"));
    private final ImageIcon RETURN_TO_MENU_BUTTON_ICON_1 = new ImageIcon("game_assets\\button_return-to-menu.png");
    private final ImageIcon RETURN_TO_MENU_BUTTON_ICON_2 = new ImageIcon("game_assets\\button_return-to-menu (1).png");
    private JButton btnReturnToMenu;
    private final BackgroundAnimationThread BACKGROUND_ANIMATION_THREAD;

    public LeaderboardWindow(MenuBackgroundPanel menuBackgroundPanel, BackgroundAnimationThread backgroundAnimationThread) throws IOException {
        initWindow();

        DATA = new LeaderboardData();
        BACKGROUND_ANIMATION_THREAD = backgroundAnimationThread;

        initTable();
        initScrollPane();
        initButton();

        add(menuBackgroundPanel);
    }

    private void initWindow() {
        setTitle("Tetris ~ Leaderboard");
        setLayout(null);
        setSize(560, 700); // Changed window size
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setIconImage(TITLE_BAR_ICON);
    }

    private void initTable() {
        leaderboard = new JTable(DATA);
        leaderboard.setAutoCreateRowSorter(true);
        leaderboard.setFillsViewportHeight(true);
        leaderboard.getRowSorter().toggleSortOrder(1);
        leaderboard.getRowSorter().toggleSortOrder(1);
        leaderboard.setFont(new Font("Serif", Font.BOLD, 14)); // Adjusted font size
        leaderboard.getTableHeader().setReorderingAllowed(false);
        leaderboard.getTableHeader().setFont(new Font("Serif", Font.BOLD, 14)); // Adjusted font size
        leaderboard.getTableHeader().setOpaque(false);
        leaderboard.getTableHeader().setBackground(new Color(150, 50, 255, 90));
        leaderboard.getTableHeader().setForeground(new Color(255, 255, 255));
        leaderboard.getTableHeader().setMinimumSize(new Dimension(50, 50));
        leaderboard.setBackground(new Color(150, 50, 255, 150));
        leaderboard.setRowHeight(20); // Adjusted row height
        leaderboard.setForeground(new Color(255, 255, 255));
    }

    private void initButton() {
        btnReturnToMenu = new JButton();
        btnReturnToMenu.setBounds((getWidth() - 188) / 2, 570, 188, 80); // Adjusted button position and size
        btnReturnToMenu.setActionCommand("back");
        btnReturnToMenu.addActionListener(this);
        btnReturnToMenu.setOpaque(false);
        btnReturnToMenu.setContentAreaFilled(false);
        btnReturnToMenu.setBorderPainted(false);
        btnReturnToMenu.setFocusPainted(false);
        btnReturnToMenu.setIcon(new ImageIcon(RETURN_TO_MENU_BUTTON_ICON_1.getImage().getScaledInstance(188, 80, Image.SCALE_SMOOTH)));
        btnReturnToMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnReturnToMenu.setIcon(new ImageIcon(RETURN_TO_MENU_BUTTON_ICON_2.getImage().getScaledInstance(188, 80, Image.SCALE_SMOOTH)));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnReturnToMenu.setIcon(new ImageIcon(RETURN_TO_MENU_BUTTON_ICON_1.getImage().getScaledInstance(188, 80, Image.SCALE_SMOOTH)));
            }
        });

        add(btnReturnToMenu);
    }

    private void initScrollPane() {
        JScrollPane pane = new JScrollPane(leaderboard, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pane.setBounds(30, 50, 500, 500); // Adjusted scroll pane position and size
        pane.setBackground(new Color(0, 0, 0, 0));

        add(pane);
    }

    public LeaderboardData getDATA() {
        return DATA;
    }

    public void addPlayer(String playerName, int playerScore) {
        DATA.addPlayerToMap(playerName, playerScore);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("back")) {
            AudioPlayer.playButtonSound();

            BACKGROUND_ANIMATION_THREAD.stopThread();

            setVisible(false);

            try {
                Tetris.showMainMenu();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
