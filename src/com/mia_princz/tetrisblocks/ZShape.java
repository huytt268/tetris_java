package com.mia_princz.tetrisblocks;

import com.mia_princz.tetris.GameFieldPanel;
import com.mia_princz.tetris.TetrisBlock;

import java.awt.*;

/**
 * Represents the Z-shape Tetris block.
 * Extends the TetrisBlock class.
 */
public class ZShape extends TetrisBlock {

    /**
     * Constructs a Z-shape block with the specified game panel.
     *
     * @param gamePanel the game field panel the block belongs to.
     */
    public ZShape(GameFieldPanel gamePanel) {
        super(new int[][]{{1, 1, 0},
                {0, 1, 1}}, Color.RED, gamePanel);
    }

    /**
     * Constructs a Z-shape block without a game panel.
     */
    public ZShape() {
        super(new int[][]{{1, 1, 0},
                {0, 1, 1}}, Color.RED);
    }
}
