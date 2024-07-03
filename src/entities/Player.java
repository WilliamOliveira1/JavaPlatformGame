package entities;

import utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utils.Constants.GameValues.*;
import static utils.Constants.ImageFiles.PLAYER_FILE_PATH;
import static utils.Constants.PlayerConstants.*;
import static utils.HelpMethods.*;

public class Player extends Entity{
    private BufferedImage[][] animations;
    private int animationTick, animationIndex, animationSpeed = 20;
    private int playerAction = IDLE;
    private boolean isPlayerMoving = false, isPlayerAttacking = false;
    private boolean up, down, right, left, jump;
    private float playerSpeed = 2.0f;
    private float xDrawOffset = 21 * SCALE, yDrawOffset = 4 * SCALE;
    private int[][] lvlData;

    // Jumping and gravity values
    private float airSpeed = 0f;
    private float gravity = 0.04f * SCALE;
    private float jumpSpeed = -2.25f * SCALE;
    private float fallSpeedAfterCollision = 0.5f * SCALE;
    private boolean inAir = false;


    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
        loadAnimation();
        initHitBox(x, y, 20*SCALE, 27 * SCALE);
    }

    public void update() {
        updatePosition();
        updateAnimationTick();
        setAnimation();
    }

    public void render(Graphics graphics) {
        graphics.drawImage(animations[playerAction][animationIndex], (int)(hitbox.x - xDrawOffset), (int)(hitbox.y - yDrawOffset), width, height, null);
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
        // if player init in air set gravity to fall down
        inAir = !IsEntityOnFloor(hitbox, lvlData);
    }

    /**
     * <h4>
     *     Change the player position
     * <h4>
     */
    private void updatePosition() {
        isPlayerMoving = false;
        float xSpeed = 0;

        if(jump) {
            jump();
        }

        if(!left && !right && !inAir) {
            return;
        }

        if (left ^ right) {
            xSpeed = left ? -playerSpeed : playerSpeed;
        }

        if(!inAir) {
            inAir = !IsEntityOnFloor(hitbox, lvlData);
        }

        if(inAir) {
            isMovingInAir();
        }

        updateXpos(xSpeed);
        isPlayerMoving = true;
    }

    private void isMovingInAir() {
        if(CanMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, lvlData)) {
            hitbox.y += airSpeed;
            airSpeed += gravity;
            return;
        }

        hitbox.y = GetEntityYUnderRoofOrAboveFloor(hitbox, airSpeed);
        if(airSpeed < 0) {
            airSpeed = fallSpeedAfterCollision;
            return;
        }

        resetInAir();
    }

    private void jump() {
        if(inAir) {
            return;
        }
        inAir = true;
        airSpeed = jumpSpeed;
    }

    private void resetInAir() {
        inAir = false;
        airSpeed = 0f;
    }

    private void updateXpos(float xSpeed) {
        boolean canMove = CanMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData);
        hitbox.x = canMove ? hitbox.x + xSpeed : GetEntityXPosNextToWall(hitbox, xSpeed);
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
        } else {
            playerAction = IDLE;
        }

        if(inAir) {
            playerAction = airSpeed < 0 ? JUMP : FALLING;
        }

        if(isPlayerAttacking) {
            playerAction = ATTACK_1;
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

    public void setJump(boolean isJumping) {
        this.jump = isJumping;
    }
}
