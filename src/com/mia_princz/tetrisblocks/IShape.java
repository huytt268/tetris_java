package com.mia_princz.tetrisblocks;

import com.mia_princz.tetris.GameFieldPanel;
import com.mia_princz.tetris.TetrisBlock;

import java.awt.*;

/**
 * Represents the I-shape Tetris block.
 * Extends the TetrisBlock class.
 */
public class IShape extends TetrisBlock {

    /**
     * Constructs an I-shape block with the specified game panel.
     *
     * @param gamePanel the game field panel the block belongs to.
     */
    public IShape(GameFieldPanel gamePanel) {
        super(new int[][]{{1, 1, 1, 1}}, Color.CYAN, gamePanel);
    }

    /**
     * Constructs an I-shape block without a game panel.
     */
    public IShape() {
        super(new int[][]{{1, 1, 1, 1}}, Color.CYAN);
    }

    /**
     * {@inheritDoc}
     * Overrides the rotate method of the TetrisBlock class.
     * Adjusts the coordinates after rotation based on the block's width.
     */
    @Override
    public void rotate() {
        super.rotate();
        if (getWidth() == 1) {
            setCoordinateX(getCoordinateX() + 1);
            setCoordinateY(getCoordinateY() - 1);
        } else {
            setCoordinateX(getCoordinateX() - 1);
            setCoordinateY(getCoordinateY() + 1);
        }
    }
}