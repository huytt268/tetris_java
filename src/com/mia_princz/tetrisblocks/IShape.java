package com.mia_princz.tetrisblocks;

import com.mia_princz.tetris.GameFieldPanel;
import com.mia_princz.tetris.TetrisBlock;

import java.awt.*;

public class IShape extends TetrisBlock {

    public IShape(GameFieldPanel gamePanel) {
        super(new int[][]{{1, 1, 1, 1}}, Color.CYAN, gamePanel);
    }

    public IShape() {
        super(new int[][]{{1, 1, 1, 1}}, Color.CYAN);
    }

    @Override
    public void rotate() {
        super.rotate();
        if (getWidth() == 1) {
            setCoordinateX(getCoordinateX() + 1);
            setCoordinateY(getCoordinateY() - 1);
        } else {
            setCoordinateX(getCoordinateX() - 1);
            setCoordinateY(getCoordinateY() + 1);
        }
    }
}