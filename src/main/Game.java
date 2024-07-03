package main;

import gamestates.GameState;
import gamestates.Menu;
import gamestates.Playing;

import java.awt.*;
import java.text.MessageFormat;

public class Game implements Runnable {
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 60;
    private final int UPS_SET = 200;
    private Menu menu;
    private Playing playing;

    public Game() {
        initClasses();
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        startGameLoop();
    }

    public void update() {
        switch (GameState.state) {
            case MENU -> menu.update();
            case PLAYING -> playing.update();
        }
    }

    public void render(Graphics graphics) {
        switch (GameState.state) {
            case MENU -> menu.draw(graphics);
            case PLAYING -> playing.draw(graphics);
        }
    }

    /**
     * <h4>
     *     Run the frame rate and the update of the game
     * <h4>
     */
    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;
        double deltaU = 0;
        double deltaF = 0;
        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();
        long previousTime = System.nanoTime();

        while (true) {
            long currentTime = System.nanoTime();

            // deltaU will be 1.0 or more when the duration, since last update is equal or more than timePerUpdate
            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if(deltaF >= 1) {
                gamePanel.repaint();
                frames++;
                deltaF--;
            }

            if(deltaU >= 1) {
                update();
                updates++;
                deltaU--;
            }

            if(System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println(MessageFormat.format("FPS: {0} | UPS: {1}", frames, updates));
                frames = 0;
                updates = 0;
            }
        }
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    private void initClasses() {
        menu = new Menu(this);
        playing = new Playing(this);
    }

    public void windowFocusLost() {
        if(GameState.state == GameState.PLAYING) {
            playing.getPlayer().resetDirBooleans();
        }
    }

    public Menu getMenu() {
        return menu;
    }

    public Playing getPlaying() {
        return playing;
    }
}
