package inputs;

import gamestates.GameState;
import main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputs implements KeyListener {
    protected GamePanel gamePanel;
    public KeyboardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent event) {
        switch (GameState.state) {
            case GameState.MENU -> gamePanel.getGame().getMenu().keyPressed(event);
            case GameState.PLAYING -> gamePanel.getGame().getPlaying().keyPressed(event);
        }
    }

    @Override
    public void keyReleased(KeyEvent event) {
        switch (GameState.state) {
            case GameState.MENU -> gamePanel.getGame().getMenu().keyReleased(event);
            case GameState.PLAYING -> gamePanel.getGame().getPlaying().keyReleased(event);
        }
    }
}
