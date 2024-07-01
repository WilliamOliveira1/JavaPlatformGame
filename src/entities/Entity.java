package entities;

import java.awt.*;

public abstract class Entity {
    protected float x, y;
    protected int width, height;
    protected Rectangle hitbox;
    public Entity(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        initHitBox();
    }

    private void initHitBox() {
        hitbox = new Rectangle((int) x, (int) y, width, height);
    }

    protected void updateHitBox() {
        hitbox.x = (int) x;
        hitbox.y = (int) y;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    protected void drawHitBox(Graphics graphics) {
        graphics.setColor(Color.PINK);
        graphics.drawRect((int) hitbox.x, (int) hitbox.y, hitbox.width, hitbox.height);
    }
}
