package main;

import utils.Vector2D;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class InputHandler {
    public static class MouseHandler extends MouseMotionAdapter {
        Vector2D mousePos = new Vector2D(0, 0);

        @Override
        public void mouseMoved(MouseEvent e) {
            mousePos.x = e.getX();
            mousePos.y = e.getY();
        }
    }

    public KeyHandler keyHandler = new KeyHandler();
    public MouseHandler mouseHandler = new MouseHandler();

    public InputHandler() {
    }

    public Vector2D getMousePos() {
        return mouseHandler.mousePos;
    }
}
