package utils;

public class Constants {

    public static class Directions {
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
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
