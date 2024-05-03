package io;

import utils.Vector2D;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class MousePositionHandler extends MouseMotionAdapter {
    Vector2D mousePos = new Vector2D();
    @Override
    public void mouseMoved(MouseEvent e) {
        mousePos.x = e.getX();
        mousePos.y = e.getY();
    }
}

