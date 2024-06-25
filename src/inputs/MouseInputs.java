package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import main.GamePanel;

public class MouseInputs implements MouseListener, MouseMotionListener {
    private GamePanel gamePanel;
    private boolean isMouseInRectArea = false;
    public MouseInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(gamePanel.rect.contains(e.getPoint())) {
            isMouseInRectArea = true;
        }
    }

    public void mouseClicked(MouseEvent e) {
        System.out.println("Mouse Clicked");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("Mouse Pressed");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("Mouse Released");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("Mouse Entered");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("Mouse Exited");
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(isMouseInRectArea) {
            gamePanel.setRectPos(e.getX(), e.getY());
        }
    }
}
