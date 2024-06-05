package com.mia_princz.tetrisblocks;

import com.mia_princz.tetris.GameFieldPanel;
import com.mia_princz.tetris.TetrisBlock;

import java.awt.*;

public class JShape extends TetrisBlock {

    public JShape(GameFieldPanel gamePanel) {
        super(new int[][]{{0, 1},
                {0, 1},
                {1, 1}}, Color.BLUE, gamePanel);
    }

    public JShape() {
        super(new int[][]{{0, 1},
                {0, 1},
                {1, 1}}, Color.BLUE);
    }
}