package inputs;

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
        setDirectionValue(event.getKeyCode(), true);
    }

    @Override
    public void keyReleased(KeyEvent event) {
        setDirectionValue(event.getKeyCode(), false);
    }

    private void setDirectionValue(int keyCode, boolean isSettingDirection) {
        switch (keyCode) {
            case KeyEvent.VK_UP, KeyEvent.VK_W -> gamePanel.getGame().getPlayer().setUp(isSettingDirection);
            case KeyEvent.VK_S, KeyEvent.VK_DOWN -> gamePanel.getGame().getPlayer().setDown(isSettingDirection);
            case KeyEvent.VK_A, KeyEvent.VK_LEFT -> gamePanel.getGame().getPlayer().setLeft(isSettingDirection);
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> gamePanel.getGame().getPlayer().setRight(isSettingDirection);
            case KeyEvent.VK_1 -> gamePanel.getGame().getPlayer().setAttack(isSettingDirection);
            case KeyEvent.VK_SPACE -> gamePanel.getGame().getPlayer().setJump(isSettingDirection);
        }
    }
}
