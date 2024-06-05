package com.mia_princz.tetris;

import java.awt.Color;

public class TetrisBlock {

    private int[][] shape;

    private Color color;

    private int coordinateX;

    private int coordinateY;

    private int[][][] shapes;

    private int currentRotation;

    private GameFieldPanel gamePanel;

    public TetrisBlock(int[][] shape, Color color, GameFieldPanel gamePanel) {
        this.shape = shape;
        this.color = color;
        this.gamePanel = gamePanel;

        initShapes();
    }

    public TetrisBlock(int[][] shape, Color color) {
        this.shape = shape;
        this.color = color;

        initShapes();
    }

    public void spawn(int gridWidth) {
        currentRotation = 0;
        shape = shapes[currentRotation];

        coordinateY = -getHeight();
        coordinateX = (gridWidth - getWidth()) / 2;
    }

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

    public int[][] getShape() {
        return shape;
    }

    public Color getColor() {
        return color;
    }

    public int getHeight() {
        return shape.length;
    }

    public int getWidth() {
        return shape[0].length;
    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }

    public void moveDown() {
        coordinateY++;
    }

    public void moveRight() {
        coordinateX++;
    }

    public void moveLeft() {
        coordinateX--;
    }

    public int getBottomEdge() {
        return coordinateY + getHeight();
    }

    public int getLeftEdge() {
        return coordinateX;
    }

    public int getRightEdge() {
        return coordinateX + getWidth();
    }

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

    public void setCoordinateX(int x) {
        coordinateX = x;
    }

    public void setCoordinateY(int y) {
        coordinateY = y;
    }
}