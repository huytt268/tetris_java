package com.mia_princz.tetris;

import java.awt.Color;

/**
 * Represents a Tetris block.
 */
public class TetrisBlock {

    // The shape of the block
    private int[][] shape;

    // The color of the block
    private Color color;

    // The X coordinate of the block
    private int coordinateX;

    // The Y coordinate of the block
    private int coordinateY;

    // Array of all possible rotations of the block
    private int[][][] shapes;

    // The current rotation of the block
    private int currentRotation;

    // The game field panel the block belongs to
    private GameFieldPanel gamePanel;

    /**
     * Constructs a Tetris block with the specified shape, color, and game panel.
     *
     * @param shape     the shape of the block.
     * @param color     the color of the block.
     * @param gamePanel the game field panel the block belongs to.
     */
    public TetrisBlock(int[][] shape, Color color, GameFieldPanel gamePanel) {
        this.shape = shape;
        this.color = color;
        this.gamePanel = gamePanel;

        initShapes();
    }

    /**
     * Constructs a Tetris block with the specified shape and color.
     *
     * @param shape the shape of the block.
     * @param color the color of the block.
     */
    public TetrisBlock(int[][] shape, Color color) {
        this.shape = shape;
        this.color = color;

        initShapes();
    }

    /**
     * Spawns the block on the game field.
     *
     * @param gridWidth the width of the game field grid.
     */
    public void spawn(int gridWidth) {
        currentRotation = 0;
        shape = shapes[currentRotation];

        coordinateY = -getHeight();
        coordinateX = (gridWidth - getWidth()) / 2;
    }

    /**
     * Initializes the array of all possible rotations of the block.
     */
    public void initShapes() {
        shapes = new int[4][][];

        for (int i = 0; i < 4; i++) {
            int rows = shape[0].length;
            int columns = shape.length;

            shapes[i] = new int[rows][columns];

            for (int y = 0; y < rows; y++) {
                for (int x = 0; x < columns; x++) {
                    shapes[i][y][x] = shape[columns - x - 1][y];
                }
            }

            shape = shapes[i];
        }
    }

    /**
     * Returns the shape of the block.
     *
     * @return the shape of the block.
     */
    public int[][] getShape() {
        return shape;
    }

    /**
     * Returns the color of the block.
     *
     * @return the color of the block.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Returns the height of the block.
     *
     * @return the height of the block.
     */
    public int getHeight() {
        return shape.length;
    }

    /**
     * Returns the width of the block.
     *
     * @return the width of the block.
     */
    public int getWidth() {
        return shape[0].length;
    }

    /**
     * Returns the X coordinate of the block.
     *
     * @return the X coordinate of the block.
     */
    public int getCoordinateX() {
        return coordinateX;
    }

    /**
     * Returns the Y coordinate of the block.
     *
     * @return the Y coordinate of the block.
     */
    public int getCoordinateY() {
        return coordinateY;
    }

    /**
     * Moves the block down by one unit.
     */
    public void moveDown() {
        coordinateY++;
    }

    /**
     * Moves the block to the right by one unit.
     */
    public void moveRight() {
        coordinateX++;
    }

    /**
     * Moves the block to the left by one unit.
     */
    public void moveLeft() {
        coordinateX--;
    }

    /**
     * Returns the bottom edge coordinate of the block.
     *
     * @return the bottom edge coordinate of the block.
     */
    public int getBottomEdge() {
        return coordinateY + getHeight();
    }

    /**
     * Returns the left edge coordinate of the block.
     *
     * @return the left edge coordinate of the block.
     */
    public int getLeftEdge() {
        return coordinateX;
    }

    /**
     * Returns the right edge coordinate of the block.
     *
     * @return the right edge coordinate of the block.
     */
    public int getRightEdge() {
        return coordinateX + getWidth();
    }

    /**
     * Rotates the block clockwise.
     * If the rotation causes overlap with other blocks on the game field, the rotation is reverted.
     */
    public void rotate() {
        currentRotation++;
        if (currentRotation > 3) {
            currentRotation = 0;
        }
        shape = shapes[currentRotation];

        if (gamePanel.isItOverlaps()) {
            currentRotation--;
            if (currentRotation < 0) {
                currentRotation = 3;
            }
            shape = shapes[currentRotation];
        }
    }

    /**
     * Sets the X coordinate of the block.
     *
     * @param x the X coordinate to set.
     */
    public void setCoordinateX(int x) {
        coordinateX = x;
    }

    /**
     * Sets the Y coordinate of the block.
     *
     * @param y the Y coordinate to set.
     */
    public void setCoordinateY(int y) {
        coordinateY = y;
    }
}