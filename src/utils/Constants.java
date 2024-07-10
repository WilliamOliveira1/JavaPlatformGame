package utils;

public class Constants {

    public static class UI {
        public static class Buttons {
            public static final int B_WIDTH_DEFAULT = 140;
            public static final int B_HEIGHT_DEFAULT = 56;
            public static final int B_WIDTH = (int)(B_WIDTH_DEFAULT * GameValues.SCALE);
            public static final int B_HEIGHT = (int)(B_HEIGHT_DEFAULT * GameValues.SCALE);
        }

        public static class PauseButtons {
            public static final int SOUND_SIZE_DEFAULT = 42;
            public static final int SOUND_SIZE = (int)(SOUND_SIZE_DEFAULT * GameValues.SCALE);
        }

        public static class UrmButtons {
            public static final int URM_DEFAULT_SIZE = 56;
            public static final int URM_SIZE = (int)(URM_DEFAULT_SIZE * GameValues.SCALE);
        }

        public static class VolumeButtons {
            public static final int VOLUME_DEFAULT_WIDTH = 28;
            public static final int VOLUME_DEFAULT_HEIGHT = 44;
            public static final int SLIDER_DEFAULT_WIDTH = 215;
            public static final int VOLUME_WIDTH = (int)(VOLUME_DEFAULT_WIDTH * GameValues.SCALE);
            public static final int VOLUME_HEIGHT = (int)(VOLUME_DEFAULT_HEIGHT * GameValues.SCALE);
            public static final int SLIDER_WIDTH = (int)(SLIDER_DEFAULT_WIDTH * GameValues.SCALE);
        }
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
        public static final String MENU_BUTTONS = "button_atlas.png";
        public static final String MENU_BACKGROUND = "menu_background.png";
        public static final String PAUSE_BACKGROUND = "pause_menu.png";
        public static final String SOUND_BUTTONS = "sound_button.png";
        public static final String URM_BUTTONS = "urm_buttons.png";
        public static final String VOLUME_BUTTONS = "volume_buttons.png";
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
            }
            return spriteAmount;
        }
    }
}
