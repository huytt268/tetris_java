package com.mia_princz.tetrisblocks;

import com.mia_princz.tetris.GameFieldPanel;
import com.mia_princz.tetris.TetrisBlock;

import java.awt.*;

public class ZShape extends TetrisBlock {

    public ZShape(GameFieldPanel gamePanel) {
        super(new int[][]{{1, 1, 0},
                {0, 1, 1}}, Color.RED, gamePanel);
    }

    public ZShape() {
        super(new int[][]{{1, 1, 0},
                {0, 1, 1}}, Color.RED);
    }
}
