package com.mia_princz.tetrisblocks;

import com.mia_princz.tetris.GameFieldPanel;
import com.mia_princz.tetris.TetrisBlock;

import java.awt.*;

public class LShape extends TetrisBlock {

    public LShape(GameFieldPanel gamePanel) {
        super(new int[][]{{1, 0},
                {1, 0},
                {1, 1}}, Color.ORANGE, gamePanel);
    }

    public LShape() {
        super(new int[][]{{1, 0},
                {1, 0},
                {1, 1}}, Color.ORANGE);
    }
}