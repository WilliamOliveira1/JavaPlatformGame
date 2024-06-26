package inputs;

import main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static utils.Constants.Directions.*;

public class KeyboardInputs implements KeyListener {
    private GamePanel gamePanel;
    public KeyboardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP, KeyEvent.VK_W -> gamePanel.setDirectionPlayer(UP);
            case KeyEvent.VK_S, KeyEvent.VK_DOWN -> gamePanel.setDirectionPlayer(DOWN);
            case KeyEvent.VK_A, KeyEvent.VK_LEFT -> gamePanel.setDirectionPlayer(LEFT);
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> gamePanel.setDirectionPlayer(RIGHT);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_DOWN,
                 KeyEvent.VK_A, KeyEvent.VK_LEFT, KeyEvent.VK_D, KeyEvent.VK_RIGHT ->
                    gamePanel.setMovingPlayer(false);
        }
    }
}
