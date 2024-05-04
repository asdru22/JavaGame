package io;

import entity.PlayableEntity;
import utils.Vector2D;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static main.Main.gamePanel;

public class MouseListenerHandler implements MouseListener {
    public boolean leftPressed;
    public boolean rightPressed;

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        updatePressedButtons(e, true);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        updatePressedButtons(e, false);
        Vector2D pos = new Vector2D(e.getX(), e.getY());
        // make any entity clickable
        for (PlayableEntity c : gamePanel.game.playerParty[0].characters) {
            if (c.contains(pos)) {
                if (e.getButton() == MouseEvent.BUTTON1) c.onLeftClick(pos);
            }
        }
        for (PlayableEntity c : gamePanel.game.playerParty[1].characters) {
            if (c.contains(pos)) {
                if (e.getButton() == MouseEvent.BUTTON1) c.onLeftClick(pos);
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    private void updatePressedButtons(MouseEvent e, boolean pressed) {
        int code = e.getButton();
        if (code == MouseEvent.BUTTON1) leftPressed = pressed;
        if (code == MouseEvent.BUTTON3) rightPressed = pressed;

    }
}