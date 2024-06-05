package com.mia_princz.tetris;

import com.mia_princz.tetrisblocks.*;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GameSidePanel extends JPanel {

    private JLabel lblScore;

    private JLabel lblLevel;

    private JLabel lblClearedLines;

    private final int GRID_CELL_SIZE = 40;

    protected TetrisBlock block;

    private final TetrisBlock[] BLOCKS;

    public GameSidePanel() {
        setBounds(780,120,300,800);
        setLayout(null);
        setOpaque(false);

        initLabels();

        BLOCKS = new TetrisBlock[] { new IShape(),
                                     new JShape(),
                                     new LShape(),
                                     new OShape(),
                                     new SShape(),
                                     new TShape(),
                                     new ZShape()
        };
    }

    private void initLabels() {
        lblScore = new JLabel("Score: " + 0);
        lblLevel = new JLabel("Level: " + 0);
        lblClearedLines = new JLabel("Cleared Lines: " + 0);
        JLabel nextBlockLabel = new JLabel("Next Block:");

        lblScore.setVisible(true);
        lblScore.setBounds(20, 20, 200, 80);
        lblScore.setFont(new Font("Arial", Font.PLAIN, 25));
        lblScore.setForeground(new Color(255,255,255));

        lblLevel.setVisible(true);
        lblLevel.setBounds(20, 70, 200, 80);
        lblLevel.setFont(new Font("Arial", Font.PLAIN, 25));
        lblLevel.setForeground(new Color(255,255,255));

        lblClearedLines.setVisible(true);
        lblClearedLines.setBounds(20, 120, 200, 80);
        lblClearedLines.setFont(new Font("Arial", Font.PLAIN, 25));
        lblClearedLines.setForeground(new Color(255,255,255));

        JLabel textPanelBackground = new JLabel();
        textPanelBackground.setIcon(new ImageIcon("game_assets\\panel.png"));
        textPanelBackground.setBounds(0, 0, 255, 285);

        nextBlockLabel.setVisible(true);
        nextBlockLabel.setBounds(20, 200, 200, 80);
        nextBlockLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        nextBlockLabel.setForeground(new Color(255,255,255));

        add(lblScore);
        add(lblLevel);
        add(lblClearedLines);
        add(nextBlockLabel);
        add(textPanelBackground);
    }

    protected void updateScore(int score) {
        lblScore.setText("Score: " + score);
    }

    protected void updateLevel(int level) {
        lblLevel.setText("Level: " + level);
    }

    protected void updateClearedLines(int clearedLines) {
        lblClearedLines.setText("Cleared Lines: " + clearedLines);
    }

    protected int spawnBlock() {
        Random random = new Random();

        int nextBlock = random.nextInt(BLOCKS.length);

        block = BLOCKS[nextBlock];
        block.spawn(2);
        block.setCoordinateX(2);
        block.setCoordinateY(8);

        return nextBlock;
    }

    private void drawBlock(Graphics g) {
        int height = block.getHeight();
        int width = block.getWidth();
        int[][] shape = block.getShape();
        Color shapeColor = block.getColor();

        // Nested loop iterating through the 2D Array to visualize a tetris block
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                if (shape[row][column] == 1) {
                    int x = (block.getCoordinateX() + column) * GRID_CELL_SIZE + 25;
                    int y = (block.getCoordinateY() + row) * GRID_CELL_SIZE + 20;

                    g.setColor(shapeColor);
                    g.fillRect(x, y, GRID_CELL_SIZE, GRID_CELL_SIZE);
                    g.setColor(Color.BLACK);
                    g.drawRect(x, y, GRID_CELL_SIZE, GRID_CELL_SIZE);
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int GRID_ROWS = 5;
        for (int row = 0; row < GRID_ROWS; row++) {
            int GRID_COLUMNS = 5;
            for (int column = 0; column < GRID_COLUMNS; column++) {
                g.setColor(new Color(255,255,255,100));
                g.fillRect(column * GRID_CELL_SIZE + 25, row * GRID_CELL_SIZE + 300, GRID_CELL_SIZE, GRID_CELL_SIZE);

                g.setColor(new Color(0,0,0,225));
                Graphics2D g2 = (Graphics2D) g;
                g2.setStroke(new BasicStroke(2));
                g.drawRect(column * GRID_CELL_SIZE + 25, row * GRID_CELL_SIZE + 300, GRID_CELL_SIZE, GRID_CELL_SIZE);
            }
        }

        drawBlock(g);
    }
}
