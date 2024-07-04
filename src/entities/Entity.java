package entities;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public abstract class Entity {
    protected float x, y;
    protected int width, height;
    protected Rectangle2D.Float hitbox;
    public Entity(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    protected void initHitBox(float x, float y, int width, int height) {
        hitbox = new Rectangle2D.Float((int) x, (int) y, width, height);
    }

    public Rectangle2D.Float getHitbox() {
        return hitbox;
    }

    protected void drawHitBox(Graphics graphics) {
        graphics.setColor(Color.PINK);
        graphics.drawRect((int) hitbox.x, (int) hitbox.y, (int)hitbox.width, (int)hitbox.height);
    }
}
