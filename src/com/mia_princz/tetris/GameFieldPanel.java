package com.mia_princz.tetris;

import com.mia_princz.tetrisblocks.*;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GameFieldPanel extends JPanel {

    private final int GRID_ROWS;

    private final int GRID_COLUMNS;

    private final int GRID_CELL_SIZE;

    private TetrisBlock block;

    private final TetrisBlock[] BLOCKS;

    private final Color[][] BACKGROUND;

    private volatile boolean paused = false;

    private final Object syncObject = new Object();

    public GameFieldPanel(int paramColumns) {
        setBorder(BorderFactory.createLineBorder(new Color(0,0,0,225), 3));
        setBounds(360,120,400,600);
        setOpaque(false);

        GRID_COLUMNS = paramColumns;
        GRID_CELL_SIZE = getBounds().width / GRID_COLUMNS;
        GRID_ROWS = getBounds().height / GRID_CELL_SIZE;

        BACKGROUND = new Color[GRID_ROWS][GRID_COLUMNS];

        BLOCKS = new TetrisBlock[] { new IShape(this),
                                     new JShape(this),
                                     new LShape(this),
                                     new OShape(this),
                                     new SShape(this),
                                     new TShape(this),
                                     new ZShape(this)
        };
    }

    protected void spawnBlock(int nextBlock, boolean isFirstBlockSpawned) {
        if (!isFirstBlockSpawned) {
            Random random = new Random();
            block = BLOCKS[random.nextInt(BLOCKS.length)];
        } else {
            block = BLOCKS[nextBlock];
        }
        block.spawn(this.GRID_COLUMNS);
    }

    protected boolean isBlockOutOfBounds() {
        if (block.getCoordinateY() < 0) {
            block = null;
            return true;
        }

        return false;
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

                    drawGridSquare(g, shapeColor, x, y);
                }
            }
        }
    }

    private void drawBackground(Graphics g) {
        Color color = null;

        for (int row = 0; row < GRID_ROWS; row++) {
            for (int column = 0; column < GRID_COLUMNS; column++) {
                color = BACKGROUND[row][column];

                if (color != null) {
                    int x = column * GRID_CELL_SIZE;
                    int y = row * GRID_CELL_SIZE;

                    drawGridSquare(g, color, x, y);
                }
            }
        }
    }

    private void drawGridSquare(Graphics g, Color shapeColor, int x, int y) {
        g.setColor(shapeColor);
        g.fillRect(x, y, GRID_CELL_SIZE, GRID_CELL_SIZE);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, GRID_CELL_SIZE, GRID_CELL_SIZE);
    }

    protected boolean checkBottom() {
        if (block.getBottomEdge() == GRID_ROWS) {
            return false;
        }

        int[][] shape = block.getShape();
        int width = block.getWidth();
        int height = block.getHeight();

        for (int column = 0; column < width; column++) {
            for (int row = height - 1; row >= 0; row--) {
                if (shape[row][column] != 0) {
                    int x = column + block.getCoordinateX();
                    int y = row + block.getCoordinateY() + 1;
                    if (y < 0) break;
                    if (BACKGROUND[y][x] != null) {
                        return false;
                    }
                    break;
                }
            }
        }

        return true;
    }

    private boolean checkLeftCorner() {
        if (block.getLeftEdge() == 0) {
            return false;
        }

        int[][] shape = block.getShape();
        int width = block.getWidth();
        int height = block.getHeight();

        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                if (shape[row][column] != 0) {
                    int x = column + block.getCoordinateX() - 1;
                    int y = row + block.getCoordinateY();
                    if (y < 0) break;
                    if (BACKGROUND[y][x] != null) {
                        return false;
                    }
                    break;
                }
            }
        }

        return true;
    }

    private boolean checkRightCorner() {
        if (block.getRightEdge() == GRID_COLUMNS) {
            return false;
        }

        int[][] shape = block.getShape();
        int width = block.getWidth();
        int height = block.getHeight();

        for (int row = 0; row < height; row++) {
            for (int column =  width - 1; column >= 0; column--) {
                if (shape[row][column] != 0) {
                    int x = column + block.getCoordinateX() + 1;
                    int y = row + block.getCoordinateY();
                    if (y < 0) break;
                    if (BACKGROUND[y][x] != null) {
                        return false;
                    }
                    break;
                }
            }
        }

        return true;
    }

    protected boolean moveBlockDown() {
        synchronized (syncObject) {
            if (paused) {
                try {
                    syncObject.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            if (!paused) {
                if (!checkBottom()) {
                    return false;
                }

                block.moveDown();
                repaint();
            }
        }
        return true;
    }

    protected void moveBlockRight() {
        if (block == null) {
            return;
        }
        if (!checkRightCorner()) {
            return;
        }

        AudioPlayer.playMoveBlockSound();
        block.moveRight();
        repaint();
    }

    protected void moveBlockLeft() {
        if (block == null) {
            return;
        }
        if (!checkLeftCorner()) {
            return;
        }
        AudioPlayer.playMoveBlockSound();
        block.moveLeft();
        repaint();
    }

    protected void dropBlock() {
        if (block == null) {
            return;
        }

        while (checkBottom()) {
            block.moveDown();
        }

        AudioPlayer.playBlockHardDropSound();
        repaint();
    }

    protected void rotateBlock() {
        if (block == null) {
            return;
        }
        block.rotate();

        if(block.getLeftEdge() < 0) {
            block.setCoordinateX(0);
        }
        if(block.getRightEdge() >= GRID_COLUMNS) {
            block.setCoordinateX(GRID_COLUMNS - block.getWidth());
        }
        if(block.getBottomEdge() >= GRID_ROWS) {
            block.setCoordinateY(GRID_ROWS - block.getHeight());
        }

        AudioPlayer.playRotateBlockSound();

        repaint();
    }

    protected int clearLines() {
        boolean lineFilled;
        int linesCleared = 0;

        for (int row = GRID_ROWS - 1; row >= 0; row--) {
            lineFilled = true;
            for (int column = 0; column < GRID_COLUMNS; column++) {
                if (BACKGROUND[row][column] == null) {
                    lineFilled = false;
                    break;
                }
            }

            if (lineFilled) {
                linesCleared++;
                clearLine(row);
                shiftDown(row);
                clearLine(0);
                row++;
                repaint();
            }
        }

        return linesCleared;
    }

    private void clearLine(int row) {
        for (int i = 0; i < GRID_COLUMNS; i++) {
            BACKGROUND[row][i] = null;
        }
    }

    private void shiftDown(int row) {
        for (; row > 0; row--) {
            for (int column = 0; column < GRID_COLUMNS; column++) {
                BACKGROUND[row][column] = BACKGROUND[row - 1][column];
            }
        }
    }

    protected boolean isItOverlaps() {
        int height = block.getHeight();
        int width = block.getWidth();

        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                if (block.getShape()[row][column] != 0) {
                    int x = column + block.getCoordinateX();
                    int y = row + block.getCoordinateY();

                    if (y < 0) {
                        break;
                    }

                    if (x > 0 && x < GRID_COLUMNS) {
                        if (BACKGROUND[y][x] != null) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    protected void moveBlockToBackground() {
        int[][] shape = block.getShape();
        int height = block.getHeight();
        int width = block.getWidth();

        int coordinateX = block.getCoordinateX();
        int coordinateY = block.getCoordinateY();

        Color color = block.getColor();

        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                if (shape[row][column] == 1) {
                    BACKGROUND[row + coordinateY][column + coordinateX] = color;
                }
            }
        }
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public Object getSyncObject() {
        return syncObject;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int row = 0; row < GRID_ROWS; row++) {
            for (int column = 0; column < GRID_COLUMNS; column++) {
                g.setColor(new Color(255,255,255,100));
                g.fillRect(column * GRID_CELL_SIZE, row * GRID_CELL_SIZE, GRID_CELL_SIZE, GRID_CELL_SIZE);

                g.setColor(new Color(0,0,0,225));
                Graphics2D g2 = (Graphics2D) g;
                g2.setStroke(new BasicStroke(2));
                g.drawRect(column * GRID_CELL_SIZE, row * GRID_CELL_SIZE, GRID_CELL_SIZE, GRID_CELL_SIZE);
            }
        }

        drawBackground(g);
        drawBlock(g);
    }
}
