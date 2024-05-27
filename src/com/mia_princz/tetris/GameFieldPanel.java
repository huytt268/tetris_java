package com.mia_princz.tetris;

import com.mia_princz.tetrisblocks.*;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * The GameFieldPanel class represents the panel where the Tetris game field is displayed.
 * It manages the drawing of blocks, background, and handles various game-related operations.
 */
public class GameFieldPanel extends JPanel {

    // The number of rows in the game field grid.
    private final int GRID_ROWS;

    // The number of columns in the game field grid.
    private final int GRID_COLUMNS;

    // The size of each cell in the grid.
    private final int GRID_CELL_SIZE;

    // The currently active Tetris block.
    private TetrisBlock block;

    // An array of available Tetris blocks.
    private final TetrisBlock[] BLOCKS;

    // The background grid that holds the placed blocks.
    private final Color[][] BACKGROUND;

    private volatile boolean paused = false;

    private final Object syncObject = new Object();

    /**
     * Constructs a new GameFieldPanel with the specified number of columns.
     * @param paramColumns The number of columns in the game field grid.
     */
    public GameFieldPanel(int paramColumns) {
        // Initialization of instance variables and setup of the panel
        setBorder(BorderFactory.createLineBorder(new Color(0,0,0,225), 3));
        setBounds(360,120,400,800);
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

    /**
     * Spawns a new Tetris block on the game field.
     * If isFirstBlockSpawned is false, a random block is selected.
     * If isFirstBlockSpawned is true, the block at the specified index is selected from the BLOCKS array.
     * @param nextBlock The index of the next block to spawn from the BLOCKS array.
     * @param isFirstBlockSpawned Indicates whether this is the first block being spawned.
     */
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

    /**
     * Draws the currently active Tetris block on the game field.
     * @param g The Graphics object used for drawing.
     */
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

    /**
     * Draws the background grid and the placed blocks on the game field.
     * @param g The Graphics object used for drawing.
     */
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

    /**
     * Checks if the active block has reached the bottom of the game field.
     * @return true if the block can move down, false otherwise.
     */
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

    /**
     * Checks if the active block has reached the left corner of the game field.
     * @return true if the block can move to the left, false otherwise.
     */
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

    /**
     * Checks if the active block has reached the right corner of the game field.
     * @return true if the block can move to the right, false otherwise.
     */
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

    /**
     * Moves the active block one cell down on the game field.
     * @return true if the block was moved down, false if it reached the bottom or couldn't move.
     */
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

    /**
     * Moves the active block one cell to the right on the game field.
     */
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

    /**
     * Moves the active block one cell to the left on the game field.
     */
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

    /**
     * Drops the active block to the bottom of the game field.
     */
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

    /**
     * Rotates the active block clockwise on the game field.
     */
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

    /**
     * Checks if there are any completed lines in the game field and clears them.
     * @return The number of lines cleared.
     */
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

    /**
     * Checks if the active block overlaps with any placed blocks on the game field.
     * @return true if the block overlaps, false otherwise.
     */
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

    /**
     * Moves the active block to the background grid, marking its cells as placed blocks.
     */
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

    // Logic to paint the game field panel, including the grid, background, and blocks
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
