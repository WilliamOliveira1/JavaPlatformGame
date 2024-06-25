package inputs;

import main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
            case KeyEvent.VK_UP:
                System.out.println("Up");
                break;
            case KeyEvent.VK_W:
                System.out.println("W");
                break;
            case KeyEvent.VK_S:
                System.out.println("S");
                break;
            case KeyEvent.VK_DOWN:
                System.out.println("Down");
                break;
            case KeyEvent.VK_A:
                System.out.println("A");
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
