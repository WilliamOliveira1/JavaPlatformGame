package utils;

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
        int value = lvlData[yTile][xTile];

        return switch (value) {
            case 11, 48, 49 -> false;
            default -> true;
        };
    }
}
