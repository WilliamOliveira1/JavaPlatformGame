package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public  class GamePanel extends JPanel {
    private float xDelta = 100, yDelta = 100;
    private float xDir = 1, yDir = 1;
    private Color color = new Color(100, 0, 200);
    private Random random;

    public Rectangle rect;

    public GamePanel() {
        random = new Random();
        MouseInputs mouseInputs = new MouseInputs(this);
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
        rect = new Rectangle(Math.round(xDelta), Math.round(yDelta), 200, 200);
    }

    public void changeXDelta(int value) {
        this.xDelta += value;
        repaint();
    }

    public void changeYDelta(int value) {
        this.yDelta += value;
        repaint();
    }

    public void setRectPos(int x, int y) {
        this.xDelta = x;
        this.yDelta = y;
        rect.x = x;
        rect.y = y;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        updateRectangle();
        g.setColor(color);
        g.fillRect(Math.round(xDelta), Math.round(yDelta),200,200);
    }

    private void updateRectangle() {
        xDelta += xDir;
        if(xDelta > 600 || xDelta < 0) {
            xDir*=-1;
            color = newColor();
        }

        yDelta += yDir;
        if(yDelta > 400 || yDelta < 0) {
            yDir*=-1;
            color = newColor();
        }
    }

    private Color newColor() {
        return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
    }
}
