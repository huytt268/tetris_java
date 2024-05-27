package com.mia_princz.tetrisblocks;

import com.mia_princz.tetris.GameFieldPanel;
import com.mia_princz.tetris.TetrisBlock;

import java.awt.*;

/**
 * Represents the O-shape Tetris block.
 * Extends the TetrisBlock class.
 */
public class OShape extends TetrisBlock {

    /**
     * Constructs an O-shape block with the specified game panel.
     *
     * @param gamePanel the game field panel the block belongs to.
     */
    public OShape(GameFieldPanel gamePanel) {
        super(new int[][]{{1, 1},
                {1, 1}}, Color.YELLOW, gamePanel);
    }

    /**
     * Constructs an O-shape block without a game panel.
     */
    public OShape() {
        super(new int[][]{{1, 1},
                {1, 1}}, Color.YELLOW);
    }
}