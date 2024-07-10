package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Objects;

import gamestates.GameState;
import main.GamePanel;

public class MouseInputs implements MouseListener, MouseMotionListener {
    protected GamePanel gamePanel;
    public MouseInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void mouseMoved(MouseEvent event) {
        switch (GameState.state) {
            case MENU -> gamePanel.getGame().getMenu().mouseMoved(event);
            case PLAYING -> gamePanel.getGame().getPlaying().mouseMoved(event);
        }
    }

    public void mouseClicked(MouseEvent event) {
        switch (GameState.state) {
            case GameState.PLAYING -> gamePanel.getGame().getMenu().mouseClicked(event);
            case GameState.MENU -> gamePanel.getGame().getPlaying().mouseClicked(event);
        }
    }

    @Override
    public void mousePressed(MouseEvent event) {
        switch (GameState.state) {
            case MENU -> gamePanel.getGame().getMenu().mousePressed(event);
            case PLAYING -> gamePanel.getGame().getPlaying().mousePressed(event);
        }
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        switch (GameState.state) {
            case MENU -> gamePanel.getGame().getMenu().mouseReleased(event);
            case PLAYING -> gamePanel.getGame().getPlaying().mouseReleased(event);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent event) {
        if (Objects.requireNonNull(GameState.state) == GameState.PLAYING) {
            gamePanel.getGame().getPlaying().mouseDragged(event);
        }
    }
}
