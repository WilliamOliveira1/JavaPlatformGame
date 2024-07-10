package gamestates;

import entities.Player;
import levels.LevelManager;
import main.Game;
import ui.PauseOverlay;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static utils.Constants.GameValues.SCALE;

public class Playing extends State implements StateMethods{
    private Player player;
    private LevelManager levelManager;
    protected final int playerInitPositionX = 200, playerInitPositionY = 200;
    private boolean paused = false;
    private PauseOverlay pauseOverlay;

    public Playing(Game game) {
        super(game);
        initClasses();
    }

    private void initClasses() {
        player = new Player(playerInitPositionX, playerInitPositionY, (int) (64 * SCALE), (int) (40 * SCALE));
        levelManager = new LevelManager(game);
        player.loadLvlData(levelManager.getCurrentLevel().getLvlData());
        pauseOverlay = new PauseOverlay(this);
    }

    public void windowFocusLost() {
        player.resetDirBooleans();
    }

    @Override
    public void update() {
        if(!paused) {
            levelManager.update();
            player.update();
            return;
        }
        pauseOverlay.update();
    }

    @Override
    public void draw(Graphics graphics) {
        levelManager.draw(graphics);
        player.render(graphics);
        if (paused) {
            pauseOverlay.draw(graphics);
        }
    }

    @Override
    public void mouseMoved(MouseEvent event) {
        if(paused) {
            pauseOverlay.mouseMoved(event);
        }
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        if(paused) {
            pauseOverlay.mouseReleased(event);
        }
    }

    @Override
    public void mousePressed(MouseEvent event) {
        if(paused) {
            pauseOverlay.mousePressed(event);
        }
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        setActionValue(event.getButton(), true);
    }

    @Override
    public void keyPressed(KeyEvent event) {
        setActionValue(event.getKeyCode(), true);
    }

    @Override
    public void keyReleased(KeyEvent event) {
        if(event.getKeyCode() != KeyEvent.VK_BACK_SPACE) {
            setActionValue(event.getKeyCode(), false);
        }
    }

    public Player getPlayer() {
        return player;
    }

    private void setActionValue(int keyCode, boolean isSettingAction) {
        switch (keyCode) {
            case KeyEvent.VK_UP, KeyEvent.VK_W -> player.setUp(isSettingAction);
            case KeyEvent.VK_S, KeyEvent.VK_DOWN -> player.setDown(isSettingAction);
            case KeyEvent.VK_A, KeyEvent.VK_LEFT -> player.setLeft(isSettingAction);
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> player.setRight(isSettingAction);
            case KeyEvent.VK_1, KeyEvent.VK_E, MouseEvent.BUTTON1 -> player.setAttack(isSettingAction);
            case KeyEvent.VK_SPACE -> player.setJump(isSettingAction);
            case KeyEvent.VK_BACK_SPACE -> paused = !paused;
        }
    }

    public void unpauseGame() {
        paused = false;
    }

    public void mouseDragged(MouseEvent event) {
        if(paused) {
            pauseOverlay.mouseDragged(event);
        }
    }
}
