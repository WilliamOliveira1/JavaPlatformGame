package gamestates;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface StateMethods {
    public void update();

    public void draw(Graphics graphics);

    public void keyPressed(KeyEvent event);

    public void keyReleased(KeyEvent event);

    public void mouseMoved(MouseEvent event);

    public void mouseReleased(MouseEvent event);

    public void mousePressed(MouseEvent event);

    public void mouseClicked(MouseEvent event);
}
