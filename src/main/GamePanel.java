package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static utils.Constants.Directions.*;
import static utils.Constants.PlayerConstants.*;

public  class GamePanel extends JPanel {
    private float xDelta = 100, yDelta = 100;
    private int width = 1280, height = 800;
    private int imgWidth = 64, imgHeight = 40;
    private BufferedImage img;
    private BufferedImage[][] animations;
    private int animationTick, animationIndex, animationSpeed = 10;
    private int playerAction = IDLE;
    private int playerDirection = -1;
    private boolean isPlayerMoving = false;

    public GamePanel() {
        setPanelSize();
        importImg();
        loadAnimation();
        MouseInputs mouseInputs = new MouseInputs(this);
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void loadAnimation() {
        animations = new BufferedImage[9][6];
        for (int j = 0; j < animations.length; j++) {
            for (int i = 0; i < animations[j].length; i++) {
                animations[j][i] = img.getSubimage(i * imgWidth, j*imgHeight, imgWidth, imgHeight);
            }
        }
    }

    public void setDirectionPlayer(int direction) {
        this.playerDirection = direction;
        isPlayerMoving = true;
    }

    private void updatePosition() {
        if(isPlayerMoving) {
            switch (playerDirection) {
                case LEFT -> xDelta -= 5;
                case UP -> yDelta -= 5;
                case RIGHT -> xDelta += 5;
                case DOWN -> yDelta += 5;
            }
        }
    }


    public void setMovingPlayer(boolean direction) {
        this.isPlayerMoving = direction;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        updateAnimationTick();
        setAnimation();
        updatePosition();
        g.drawImage(animations[playerAction][animationIndex], (int)xDelta, (int)yDelta, imgWidth*3, imgHeight*3, null);
    }

    private void setAnimation() {
        if(isPlayerMoving) {
            playerAction = RUNNING;
        } else {
            playerAction = IDLE;
        }
    }

    private void updateAnimationTick() {
        animationTick++;
        if(animationTick >= animationSpeed) {
            animationTick = 0;
            // will increment the animationIndex and wrap it back to zero when it reaches the return of GetSpriteAmount method.
            animationIndex = (animationIndex + 1) % GetSpriteAmount(playerAction);
        }
    }

    public void setPanelSize() {
        Dimension size = new Dimension(width, height);
        setMinimumSize(size);
        setPreferredSize(size);
    }

    /**
     * Import the image
     */
    private void importImg() {
        InputStream inputStream = getClass().getResourceAsStream("/images/player_sprites.png");
        try {
            if(inputStream != null) {
                img = ImageIO.read(inputStream);
                inputStream.close();
            }
        } catch (Exception ex) {
            System.out.println("Error loading image: " + ex.getMessage());
        } finally {
            try {
                if(inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ex) {
                System.out.println("Error closing input stream: " + ex.getMessage());
            }
        }
    }
}
