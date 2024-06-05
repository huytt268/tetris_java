package com.mia_princz.tetris;

import com.mia_princz.tetrisblocks.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class MenuBackgroundPanel extends JPanel {

    private final int GRID_ROWS;

    private final int GRID_COLUMNS;

    private final int GRID_CELL_SIZE;

    protected TetrisBlock block;

    private final TetrisBlock[] BLOCKS;

    private final Image scaledImage;

    private final int BACKGROUND_WIDTH;

    private final int BACKGROUND_HEIGHT;

    public MenuBackgroundPanel(int backgroundWidth, int backgroundHeight) {
        setOpaque(false);
        setBounds(0,0,1080,1080);

        BufferedImage buffImage;
        try {
            buffImage = ImageIO.read(new File("game_assets\\Sky-Background.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        scaledImage = buffImage.getScaledInstance(backgroundWidth, backgroundHeight, Image.SCALE_SMOOTH);

        BACKGROUND_WIDTH = backgroundWidth;
        BACKGROUND_HEIGHT = backgroundHeight;

        GRID_COLUMNS = 30;
        GRID_CELL_SIZE = getBounds().width / GRID_COLUMNS;
        GRID_ROWS = getBounds().height / GRID_CELL_SIZE;

        BLOCKS = new TetrisBlock[] { new IShape(),
                                     new JShape(),
                                     new LShape(),
                                     new OShape(),
                                     new SShape(),
                                     new TShape(),
                                     new ZShape()
        };
    }

    protected void spawnBlock() {
        Random random = new Random();

        block = BLOCKS[random.nextInt(BLOCKS.length)];
        block.spawn(random.nextInt(5,30));
    }

    private void drawBlock(Graphics g) {
        int height = block.getHeight();
        int width = block.getWidth();
        int[][] shape = block.getShape();
        Color shapeColor = block.getColor();

        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                if (shape[row][column] == 1) {
                    int x = (block.getCoordinateX() + column) * GRID_CELL_SIZE;
                    int y = (block.getCoordinateY() + row) * GRID_CELL_SIZE;

                    g.setColor(shapeColor);
                    g.fillRect(x, y, GRID_CELL_SIZE, GRID_CELL_SIZE);
                    g.setColor(Color.BLACK);
                    g.drawRect(x, y, GRID_CELL_SIZE, GRID_CELL_SIZE);
                }
            }
        }
    }

    public boolean checkBottom() {
        if (block.getBottomEdge() == this.GRID_ROWS) {
            return false;
        }

        int[][] shape = block.getShape();
        int width = block.getWidth();
        int height = block.getHeight();

        for (int column = 0; column < width; column++) {
            for (int row = height - 1; row >= 0; row--) {
                if (shape[row][column] != 0) {
                    int y = row + block.getCoordinateY() + 1;
                    if (y < 0) break;
                    break;
                }
            }
        }

        return true;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(scaledImage,0,0, BACKGROUND_WIDTH, BACKGROUND_HEIGHT, null);
        drawBlock(g);
    }
}
