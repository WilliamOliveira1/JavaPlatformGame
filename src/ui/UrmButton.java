package ui;

import utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utils.Constants.ImageFiles.URM_BUTTONS;
import static utils.Constants.UI.UrmButtons.URM_DEFAULT_SIZE;
import static utils.Constants.UI.UrmButtons.URM_SIZE;

public class UrmButton extends PauseButton {
    private int rowIndex, index;
    private BufferedImage[] images;
    private boolean mouseOver, mousePressed;
    public UrmButton(int x, int y, int width, int height, int rowIndex) {
        super(x, y, width, height);
        this.rowIndex = rowIndex;
        loadImages();
    }

    private void loadImages() {
        BufferedImage temp = LoadSave.GetSpriteAtlas(URM_BUTTONS);
        images = new BufferedImage[3];
        for(int i = 0; i < images.length; i++) {
            images[i] = temp.getSubimage(i * URM_DEFAULT_SIZE, rowIndex * URM_DEFAULT_SIZE, URM_DEFAULT_SIZE, URM_DEFAULT_SIZE);
        }
    }

    public void update() {
        index = 0;
        if(mouseOver) {
            index = 1;
        }
        if(mousePressed) {
            index = 2;
        }
    }

    public void draw(Graphics graphics) {
        graphics.drawImage(images[index], x, y, URM_SIZE, URM_SIZE, null);
    }

    public void resetBooleans() {
        mouseOver = false;
        mousePressed = false;
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
}
