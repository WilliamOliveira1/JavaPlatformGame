package ui;

import utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utils.Constants.ImageFiles.SOUND_BUTTONS;
import static utils.Constants.UI.PauseButtons.SOUND_SIZE_DEFAULT;

public class SoundButton extends PauseButton {
    private BufferedImage[][] soundsImgs;
    private boolean mouseOver, mousePressed, muted;
    private int rowIndex, colIndex;
    public SoundButton(int x, int y, int width, int height) {
        super(x, y, width, height);
        loadImgs();
    }

    private void loadImgs() {
        BufferedImage temp = LoadSave.GetSpriteAtlas(SOUND_BUTTONS);
        soundsImgs = new BufferedImage[2][3];
        for (int j = 0; j < soundsImgs.length; j++) {
            for (int i = 0; i < soundsImgs[j].length; i++) {
                soundsImgs[j][i] = temp.getSubimage(i * SOUND_SIZE_DEFAULT, j * SOUND_SIZE_DEFAULT, SOUND_SIZE_DEFAULT, SOUND_SIZE_DEFAULT);
            }
        }
    }

    public void update() {
        colIndex = 0;
        rowIndex = muted ? 1 : 0;
        if (mouseOver)
            colIndex = 1;
        if (mousePressed)
            colIndex = 2;
    }

    public void resetBooleans() {
        mouseOver = false;
        mousePressed = false;
    }

    public void draw(Graphics graphics) {
        graphics.drawImage(soundsImgs[rowIndex][colIndex], x, y, width, height, null);
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public boolean isMuted() {
        return muted;
    }

    public void setMuted(boolean muted) {
        this.muted = muted;
    }
}
