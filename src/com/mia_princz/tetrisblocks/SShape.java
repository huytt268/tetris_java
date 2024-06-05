package com.mia_princz.tetrisblocks;

import com.mia_princz.tetris.GameFieldPanel;
import com.mia_princz.tetris.TetrisBlock;

import java.awt.*;

public class SShape extends TetrisBlock {

    public SShape(GameFieldPanel gamePanel) {
        super(new int[][]{{0, 1, 1},
                {1, 1, 0}}, Color.GREEN, gamePanel);
    }

    public SShape() {
        super(new int[][]{{0, 1, 1},
                {1, 1, 0}}, Color.GREEN);
    }
}
