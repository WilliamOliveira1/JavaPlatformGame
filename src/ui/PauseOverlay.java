package ui;

import gamestates.GameState;
import gamestates.Playing;
import utils.LoadSave;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static utils.Constants.GameValues.GAME_WIDTH;
import static utils.Constants.GameValues.SCALE;
import static utils.Constants.ImageFiles.PAUSE_BACKGROUND;
import static utils.Constants.UI.PauseButtons.SOUND_SIZE;
import static utils.Constants.UI.UrmButtons.URM_SIZE;
import static utils.Constants.UI.VolumeButtons.SLIDER_WIDTH;
import static utils.Constants.UI.VolumeButtons.VOLUME_HEIGHT;

public class PauseOverlay {
    private Playing playing;
    private BufferedImage backgroundImg;
    private int bgX, bgY, bgW, bgH;;
    private SoundButton musicButton, sfxButton;
    private UrmButton menuButton, replayButton, unpauseButton;
    private PauseButton[] pauseButtons;
    private VolumeButton volumeButton;

    public PauseOverlay(Playing playing) {
        this.playing = playing;
        loadBackground();
        createSoundButton();
        createUrmButtons();
        createVolumeButtons();
        pauseButtons = new PauseButton[]{musicButton, sfxButton, menuButton, replayButton, unpauseButton, volumeButton};
    }

    private void createVolumeButtons() {
        int volumeX = dimensionValueWithScale(309);
        int volumeY = dimensionValueWithScale(275);
        volumeButton = new VolumeButton(volumeX, volumeY, SLIDER_WIDTH, VOLUME_HEIGHT);
    }

    private void createUrmButtons() {
        int menuX = dimensionValueWithScale(313);
        int replayX = dimensionValueWithScale(387);
        int unpauseX = dimensionValueWithScale(462);
        int buttonY = dimensionValueWithScale(325);
        menuButton = new UrmButton(menuX, buttonY, URM_SIZE, URM_SIZE, 2);
        replayButton = new UrmButton(replayX, buttonY, URM_SIZE, URM_SIZE, 1);
        unpauseButton = new UrmButton(unpauseX, buttonY, URM_SIZE, URM_SIZE, 0);
    }

    private void createSoundButton() {
        int soundX = dimensionValueWithScale(450);
        int musicY = dimensionValueWithScale(140);
        int sfxY = dimensionValueWithScale(185);
        musicButton = new SoundButton(soundX, musicY, SOUND_SIZE, SOUND_SIZE);
        sfxButton = new SoundButton(soundX, sfxY, SOUND_SIZE, SOUND_SIZE);
    }

    private void loadBackground() {
        backgroundImg = LoadSave.GetSpriteAtlas(PAUSE_BACKGROUND);
        bgW = dimensionValueWithScale(backgroundImg.getWidth());
        bgH = dimensionValueWithScale(backgroundImg.getHeight());
        bgX = (GAME_WIDTH - bgW) / 2;
        bgY = dimensionValueWithScale(25);
    }

    public void update() {
        for (PauseButton button : pauseButtons) {
            button.update();
        }
    }

    public void draw(Graphics graphics) {
        // Background
        graphics.drawImage(backgroundImg, bgX, bgY, bgW, bgH, null);
        for (PauseButton button : pauseButtons) {
            button.draw(graphics);
        }
    }

    public void mouseMoved(MouseEvent event) {
        for (PauseButton button : pauseButtons) {
            button.setMouseOver(false);
        }

        for (PauseButton button : pauseButtons) {
            if (button.isIn(event)) {
                button.setMouseOver(true);
                break;
            }
        }
    }

    public void mouseReleased(MouseEvent event) {
        if(musicButton.isIn(event) && musicButton.isMousePressed()) {
            musicButton.setMuted(!musicButton.isMuted());
        } else if(sfxButton.isIn(event) && sfxButton.isMousePressed()) {
            sfxButton.setMuted(!sfxButton.isMuted());
        }

        if(menuButton.isIn(event) && menuButton.isMousePressed()) {
            GameState.state = GameState.MENU;
            playing.unpauseGame();
        } else if(replayButton.isIn(event) && replayButton.isMousePressed()) {
            System.out.println("replay level");
        } else if(unpauseButton.isIn(event) && unpauseButton.isMousePressed()) {
            playing.unpauseGame();
        }

        musicButton.resetBooleans();
        sfxButton.resetBooleans();
        menuButton.resetBooleans();
        replayButton.resetBooleans();
        unpauseButton.resetBooleans();
        volumeButton.resetBooleans();
    }

    public void mousePressed(MouseEvent event) {
        for (PauseButton button : pauseButtons) {
            if (button.isIn(event)) {
                button.setMousePressed(true);
                break;
            }
        }
    }

    public void mouseClicked(MouseEvent event) {

    }

    public void mouseDragged(MouseEvent event) {
        if(volumeButton.isMousePressed()) {
            volumeButton.changeX(event.getX());
        }
    }

    private int dimensionValueWithScale(float value) {
        return (int)(value * SCALE);
    }
}
