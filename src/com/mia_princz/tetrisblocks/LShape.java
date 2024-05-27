package com.mia_princz.tetrisblocks;

import com.mia_princz.tetris.GameFieldPanel;
import com.mia_princz.tetris.TetrisBlock;

import java.awt.*;

/**
 * Represents the L-shape Tetris block.
 * Extends the TetrisBlock class.
 */
public class LShape extends TetrisBlock {

    /**
     * Constructs an L-shape block with the specified game panel.
     *
     * @param gamePanel the game field panel the block belongs to.
     */
    public LShape(GameFieldPanel gamePanel) {
        super(new int[][]{{1, 0},
                {1, 0},
                {1, 1}}, Color.ORANGE, gamePanel);
    }

    /**
     * Constructs an L-shape block without a game panel.
     */
    public LShape() {
        super(new int[][]{{1, 0},
                {1, 0},
                {1, 1}}, Color.ORANGE);
    }
}