package com.mia_princz.tetrisblocks;

import com.mia_princz.tetris.GameFieldPanel;
import com.mia_princz.tetris.TetrisBlock;

import java.awt.*;

/**
 * Represents the S-shape Tetris block.
 * Extends the TetrisBlock class.
 */
public class SShape extends TetrisBlock {

    /**
     * Constructs an S-shape block with the specified game panel.
     *
     * @param gamePanel the game field panel the block belongs to.
     */
    public SShape(GameFieldPanel gamePanel) {
        super(new int[][]{{0, 1, 1},
                {1, 1, 0}}, Color.GREEN, gamePanel);
    }

    /**
     * Constructs an S-shape block without a game panel.
     */
    public SShape() {
        super(new int[][]{{0, 1, 1},
                {1, 1, 0}}, Color.GREEN);
    }
}
