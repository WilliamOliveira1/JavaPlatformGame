package utils;

public class Constants {

    public static class Directions {
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }

    public static class GameValues {
        public final static int TILES_DEFAULT_SIZE = 32;
        public final static int TILES_IN_WIDTH = 26;
        public final static int TILES_IN_HEIGHT = 14;
        public final static float SCALE = 2f;
        public final static int TILES_SIZE = (int)(TILES_DEFAULT_SIZE * SCALE);
        public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
        public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;
        public static final int CHAR_WIDTH = 64;
        public static final int CHAR_HEIGHT = 40;
    }

    public static class ImageFiles {
        public static final String PLAYER_FILE_PATH = "player_sprites.png";
        public static final String OUTSIDE_FILE_PATH = "outside_sprites.png";
        public static final String LEVEL_ONE_DATA = "level_one_data.png";
    }

    public static class PlayerConstants {
        public static final int IDLE = 0;
        public static final int RUNNING = 1;
        public static final int JUMP = 2;
        public static final int FALLING = 3;
        public static final int GROUND = 4;
        public static final int HIT = 5;
        public static final int ATTACK_1 = 6;
        public static final int ATTACK_JUMP_1 = 7;
        public static final int ATTACK_JUMP_2 = 8;

        public static int GetSpriteAmount(int playerAction) {
            int spriteAmount = 1;
            switch (playerAction) {
                case GROUND -> spriteAmount = 2;
                case JUMP, ATTACK_1, ATTACK_JUMP_1, ATTACK_JUMP_2 -> spriteAmount = 3;
                case HIT -> spriteAmount = 4;
                case IDLE -> spriteAmount = 5;
                case RUNNING -> spriteAmount = 6;
                case FALLING -> spriteAmount = 9;
            }
            return spriteAmount;
        }
    }
}
