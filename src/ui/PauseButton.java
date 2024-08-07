package ui;

import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class PauseButton {

    protected int x, y, width, height;
    protected Rectangle bounds;

    public PauseButton(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        createBounds();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    private void createBounds() {
        bounds = new Rectangle(x, y, width, height);
    }

    public abstract void setMouseOver(boolean isMouseOver);
    public abstract void setMousePressed(boolean isMouseOver);
    public abstract void draw(Graphics graphics);
    public abstract void update();
    public boolean isIn(MouseEvent event) {
        return this.getBounds().contains(event.getX(), event.getY());
    }
}
