package ui;

import utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utils.Constants.ImageFiles.VOLUME_BUTTONS;
import static utils.Constants.UI.VolumeButtons.*;

public class VolumeButton extends PauseButton {
    private int buttonX, index = 0;
    private BufferedImage[] images;
    private BufferedImage slider;
    private boolean mouseOver, mousePressed;
    private int minX, maxX;
    public VolumeButton(int x, int y, int width, int height) {
        super(x + width/2, y, VOLUME_WIDTH, height);
        bounds.x -= VOLUME_WIDTH/2;
        buttonX = x + width/2;
        this.x = x;
        this.width = width;
        minX = x + VOLUME_WIDTH/2;
        maxX = x + width - VOLUME_WIDTH/2;
        loadImages();
    }

    private void loadImages() {
        BufferedImage temp = LoadSave.GetSpriteAtlas(VOLUME_BUTTONS);
        images = new BufferedImage[3];
        for (int i = 0; i < images.length; i++) {
            images[i] = temp.getSubimage(i * VOLUME_DEFAULT_WIDTH, 0,VOLUME_DEFAULT_WIDTH, VOLUME_DEFAULT_HEIGHT);
        }
        slider = temp.getSubimage(3 * VOLUME_DEFAULT_WIDTH, 0, SLIDER_DEFAULT_WIDTH, VOLUME_DEFAULT_HEIGHT);
    }

    public void changeX(int x) {
        if(x < minX) {
            buttonX = minX;
        } else {
            buttonX = Math.min(x, maxX);
        }
        bounds.x = buttonX - VOLUME_WIDTH/2;
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.drawImage(slider, x, y, width, height, null);
        graphics.drawImage(images[index], buttonX - VOLUME_WIDTH/2, y, VOLUME_WIDTH, height, null);
    }

    @Override
    public void update() {
        index = 0;
        if(mouseOver) {
            index = 1;
        }
        if(mousePressed) {
            index = 2;
        }
    }

    @Override
    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    @Override
    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public void resetBooleans() {
        mouseOver = false;
        mousePressed = false;
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }
}
