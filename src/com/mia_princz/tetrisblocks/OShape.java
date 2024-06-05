package com.mia_princz.tetrisblocks;

import com.mia_princz.tetris.GameFieldPanel;
import com.mia_princz.tetris.TetrisBlock;

import java.awt.*;

public class OShape extends TetrisBlock {

    public OShape(GameFieldPanel gamePanel) {
        super(new int[][]{{1, 1},
                {1, 1}}, Color.YELLOW, gamePanel);
    }

    public OShape() {
        super(new int[][]{{1, 1},
                {1, 1}}, Color.YELLOW);
    }
}