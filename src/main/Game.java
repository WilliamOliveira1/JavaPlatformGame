package main;

import entities.Player;
import levels.LevelManager;

import java.awt.*;
import java.text.MessageFormat;

import static utils.Constants.GameValues.SCALE;

public class Game implements Runnable {
    private GameWindow gameWindow;
    private LevelManager levelManager;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 60;
    private final int UPS_SET = 200;
    protected final int playerInitPositionX = 200, playerInitPositionY = 200;
    private Player player;

    public Game() {
        initClasses();
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        startGameLoop();
    }

    public void update() {
        player.update();
        levelManager.update();
    }

    public void render(Graphics graphics) {
        levelManager.draw(graphics);
        player.render(graphics);

    }

    public Player getPlayer() {
        return player;
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
        player = new Player(playerInitPositionX, playerInitPositionY, (int) (64 * SCALE), (int) (40 * SCALE));
        levelManager = new LevelManager(this);
        player.loadLvlData(levelManager.getCurrentLevel().getLvlData());
    }

    public void windowFocusLost() {
        player.resetDirBooleans();
    }
}
