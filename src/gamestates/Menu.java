package gamestates;

import main.Game;
import ui.MenuButton;
import utils.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static utils.Constants.GameValues.GAME_WIDTH;
import static utils.Constants.GameValues.SCALE;
import static utils.Constants.ImageFiles.MENU_BACKGROUND;

public class Menu extends State implements StateMethods{
    private MenuButton[] buttons = new MenuButton[3];
    private BufferedImage backgroundImage;
    private int menuX, menuY, menuWidth, menuHeight;
    public Menu(Game game) {
        super(game);
        loadButtons();
        loadBackground();
    }

    private void loadBackground() {
        backgroundImage = LoadSave.GetSpriteAtlas(MENU_BACKGROUND);
        menuWidth = (int) (backgroundImage.getWidth() * SCALE);
        menuHeight = (int) (backgroundImage.getHeight() * SCALE);
        menuX = GAME_WIDTH / 2 - menuWidth / 2;
        menuY = (int) (45 * SCALE);
    }

    private void loadButtons() {
        buttons[0] = new MenuButton(GAME_WIDTH/2, (int)(150 * SCALE), 0, GameState.PLAYING);
        buttons[1] = new MenuButton(GAME_WIDTH/2, (int)(220 * SCALE), 1, GameState.OPTIONS);
        buttons[2] = new MenuButton(GAME_WIDTH/2, (int)(290 * SCALE), 2, GameState.QUIT);
    }

    @Override
    public void update() {
        for(MenuButton mb : buttons) {
            mb.update();
        }
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.drawImage(backgroundImage, menuX, menuY, menuWidth, menuHeight, null);
        for(MenuButton mb : buttons) {
            mb.draw(graphics);
        }
    }

    @Override
    public void keyPressed(KeyEvent event) {
        if(event.getKeyCode() == KeyEvent.VK_ENTER) {
            GameState.state = GameState.PLAYING;
        }
    }

    @Override
    public void keyReleased(KeyEvent event) {

    }

    @Override
    public void mouseMoved(MouseEvent event) {
        for(MenuButton mb : buttons) {
            mb.setMouseOver(false);
        }

        for(MenuButton mb : buttons) {
            if(isInArea(event, mb)) {
                mb.setMouseOver(true);
                break;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        for(MenuButton mb : buttons) {
            if(isInArea(event, mb) && mb.isMousePressed()) {
                mb.applyGameState();
                break;
            }
        }
        resetButtons();
    }

    @Override
    public void mousePressed(MouseEvent event) {
        for(MenuButton mb : buttons) {
            if(isInArea(event, mb)) {
                mb.setMousePressed(true);
            }
        }
    }

    private void resetButtons() {
        for(MenuButton mb : buttons) {
            mb.resetBooleans();
        }
    }

    @Override
    public void mouseClicked(MouseEvent event) {

    }
}
