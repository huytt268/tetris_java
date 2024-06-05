package com.mia_princz.tetrisblocks;

import com.mia_princz.tetris.GameFieldPanel;
import com.mia_princz.tetris.TetrisBlock;

import java.awt.*;

public class TShape extends TetrisBlock {

    public TShape(GameFieldPanel gamePanel) {
        super(new int[][]{{0, 1, 0},
                {1, 1, 1}}, Color.PINK, gamePanel);
    }

    public TShape() {
        super(new int[][]{{0, 1, 0},
                {1, 1, 1}}, Color.PINK);
    }
}