package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;

public  class GamePanel extends JPanel {
    private int xDelta = 100;
    private int yDelta = 100;
    public Rectangle rect;
    public GamePanel() {
        MouseInputs mouseInputs = new MouseInputs(this);
        addKeyListener(new KeyboardInputs());
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
        rect = new Rectangle(xDelta, yDelta, 200, 200);
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
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.fill3DRect(xDelta, yDelta,200,200,true);
    }
}
