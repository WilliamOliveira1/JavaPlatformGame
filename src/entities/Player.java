package entities;

import utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utils.Constants.GameValues.*;
import static utils.Constants.ImageFiles.PLAYER_FILE_PATH;
import static utils.Constants.PlayerConstants.*;
import static utils.HelpMethods.CanMoveHere;

public class Player extends Entity{
    private BufferedImage[][] animations;
    private int animationTick, animationIndex, animationSpeed = 20;
    private int playerAction = IDLE;
    private boolean isPlayerMoving = false, isPlayerAttacking = false;
    private boolean up, down, right, left;
    private float playerSpeed = 2.0f;
    private float xDrawOffset = 21 * SCALE, yDrawOffset = 4 * SCALE;
    private int[][] lvlData;

    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
        loadAnimation();
        initHitBox(x, y, 20*SCALE, 28 * SCALE);
    }

    public void update() {
        updatePosition();
        updateAnimationTick();
        setAnimation();
    }

    public void render(Graphics graphics) {
        graphics.drawImage(animations[playerAction][animationIndex], (int)(hitbox.x - xDrawOffset), (int)(hitbox.y - yDrawOffset), width, height, null);
        drawHitBox(graphics);
    }

    /**
     * <h4>
     *     Set animation of the char running through each image position since is one file with all animations
     * <h4>
     */
    private void loadAnimation() {
        BufferedImage img = LoadSave.GetSpriteAtlas(PLAYER_FILE_PATH);
        if(img == null) {
            return;
        }
        animations = new BufferedImage[9][6];
        for (int j = 0; j < animations.length; j++) {
            for (int i = 0; i < animations[j].length; i++) {
                animations[j][i] = img.getSubimage(i * CHAR_WIDTH, j*CHAR_HEIGHT, CHAR_WIDTH, CHAR_HEIGHT);
            }
        }
    }

    public void loadLvlData(int[][] lvlData) {
        this.lvlData = lvlData;
    }

    /**
     * <h4>
     *     Change the player position
     * <h4>
     */
    private void updatePosition() {
        isPlayerMoving = false;
        if(!left && !right && !up && !down) {
            return;
        }

        float xSpeed = 0, ySpeed = 0;
        if (left ^ right) {
            xSpeed = left ? -playerSpeed : playerSpeed;
        }

        if(up ^ down) {
            ySpeed = up ? -playerSpeed : playerSpeed;
        }

        if(CanMoveHere(hitbox.x + xSpeed, hitbox.y + ySpeed, hitbox.width, hitbox.height, lvlData)) {
            hitbox.x += xSpeed;
            hitbox.y += ySpeed;
            isPlayerMoving = true;
        }
    }

    /**
     * <h4>
     *     Change the animation index value to set player animation
     * <h4>
     */
    private void updateAnimationTick() {
        animationTick++;
        if(animationTick >= animationSpeed) {
            animationTick = 0;
            // will increment the animationIndex and wrap it back to zero when it reaches the return of GetSpriteAmount method.
            animationIndex = (animationIndex + 1) % GetSpriteAmount(playerAction);
            if(animationIndex == 0) {
                isPlayerAttacking = false;
            }
        }
    }

    /**
     * <h4>
     *     Change the player action to set different animation
     * <h4>
     */
    private void setAnimation() {
        int startAnimation = playerAction;

        if(isPlayerMoving) {
            playerAction = RUNNING;
        } else if(isPlayerAttacking) {
            playerAction = ATTACK_1;
        } else {
            playerAction = IDLE;
        }

        if(startAnimation != playerAction) {
            resetAnimationTick();
        }
    }

    private void resetAnimationTick() {
        animationTick = 0;
        animationIndex = 0;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void resetDirBooleans() {
        left = false;
        right = false;
        up = false;
        down = false;
    }

    public void setAttack(boolean isAttacking) {
        this.isPlayerAttacking = isAttacking;
    }
}
