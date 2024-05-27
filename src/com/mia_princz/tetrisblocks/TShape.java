package com.mia_princz.tetrisblocks;

import com.mia_princz.tetris.GameFieldPanel;
import com.mia_princz.tetris.TetrisBlock;

import java.awt.*;

/**
 * Represents the T-shape Tetris block.
 * Extends the TetrisBlock class.
 */
public class TShape extends TetrisBlock {

    /**
     * Constructs a T-shape block with the specified game panel.
     *
     * @param gamePanel the game field panel the block belongs to.
     */
    public TShape(GameFieldPanel gamePanel) {
        super(new int[][]{{0, 1, 0},
                {1, 1, 1}}, Color.PINK, gamePanel);
    }

    /**
     * Constructs a T-shape block without a game panel.
     */
    public TShape() {
        super(new int[][]{{0, 1, 0},
                {1, 1, 1}}, Color.PINK);
    }
}