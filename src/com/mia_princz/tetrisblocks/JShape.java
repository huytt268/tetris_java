package com.mia_princz.tetrisblocks;

import com.mia_princz.tetris.GameFieldPanel;
import com.mia_princz.tetris.TetrisBlock;

import java.awt.*;

/**
 * Represents the J-shape Tetris block.
 * Extends the TetrisBlock class.
 */
public class JShape extends TetrisBlock {

    /**
     * Constructs a J-shape block with the specified game panel.
     *
     * @param gamePanel the game field panel the block belongs to.
     */
    public JShape(GameFieldPanel gamePanel) {
        super(new int[][]{{0, 1},
                {0, 1},
                {1, 1}}, Color.BLUE, gamePanel);
    }

    /**
     * Constructs a J-shape block without a game panel.
     */
    public JShape() {
        super(new int[][]{{0, 1},
                {0, 1},
                {1, 1}}, Color.BLUE);
    }
}