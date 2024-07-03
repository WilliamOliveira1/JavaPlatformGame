package utils;

import java.awt.geom.Rectangle2D;

import static utils.Constants.GameValues.*;

public class HelpMethods {
    public static boolean CanMoveHere(float x, float y, float width, float height, int[][] lvlData) {
        return !IsSolid(x, y, lvlData)
                && !IsSolid(x + width, y + height, lvlData)
                && !IsSolid(x + width, y, lvlData)
                && !IsSolid(x, y + height, lvlData);
    }

    private static boolean IsSolid(float x, float y,int[][] lvlData) {
        if((x < 0 || x >= GAME_WIDTH) || (y < 0 || y >= GAME_HEIGHT)) {
            return true;
        }
        float xIndex = x / TILES_SIZE;
        float yIndex = y / TILES_SIZE;
        return IsTileSolid((int) xIndex, (int) yIndex, lvlData);
    }

    public static boolean IsTileSolid(int xTile, int yTile, int[][] lvlData) {
        return switch (lvlData[yTile][xTile]) {
            case 11, 48, 49 -> false;
            default -> true;
        };
    }

    /**
     * Get the player next to wall or floor due the pixels on collision
     **/
    public static float GetEntityXPosNextToWall(Rectangle2D.Float hitbox, float xSpeed) {
        int currentTile = (int) (hitbox.x / TILES_SIZE);

        if(xSpeed > 0) {
            // right
            int tileXPos = currentTile * TILES_SIZE;
            int xOffSet = (int)(TILES_SIZE - hitbox.width);
            return tileXPos + xOffSet - 1;
        }
        // left
        return currentTile * TILES_SIZE;
    }

    public static float GetEntityYUnderRoofOrAboveFloor(Rectangle2D.Float hitbox, float airSpeed) {
        int currentTile = (int) (hitbox.y / TILES_SIZE);

        if(airSpeed > 0) {
            // falling - touching floor
            int tileYPos = currentTile * TILES_SIZE;
            int yOffSet = (int)(TILES_SIZE - hitbox.height);
            return tileYPos + yOffSet - 1;
        }
        // jumping
        return currentTile * TILES_SIZE;
    }

    public static boolean IsEntityOnFloor(Rectangle2D.Float hitbox, int[][] lvlData) {
        boolean isOnFloor = !IsSolid(hitbox.x, hitbox.y + hitbox.height+1, lvlData) &&
                !IsSolid(hitbox.x + hitbox.width, hitbox.y + hitbox.height+1, lvlData);
        return !isOnFloor;
    }
}
