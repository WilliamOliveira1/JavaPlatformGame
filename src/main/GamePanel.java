package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;

import static utils.Constants.GameValues.*;

public  class GamePanel extends JPanel {
    protected Game game;
    public GamePanel(Game game) {
        this.game = game;

        setPanelSize();
        MouseInputs mouseInputs = new MouseInputs(this);
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        game.render(graphics);
    }

    public Game getGame() {
        return game;
    }

    /**
     * <h4>
     *     Set the panel size and background color
     * <h4>
     */
    public void setPanelSize() {
        Dimension size = new Dimension(PANEL_WIDTH, PANEL_HEIGHT);
        setMinimumSize(size);
        setPreferredSize(size);
        setBackground(Color.BLACK);
    }

    public void updateGame() {

    }
}
