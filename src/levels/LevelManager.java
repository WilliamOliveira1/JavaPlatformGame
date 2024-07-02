package levels;

import main.Game;
import utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utils.Constants.GameValues.*;
import static utils.Constants.ImageFiles.OUTSIDE_FILE_PATH;

public class LevelManager {
    protected Game game;
    protected BufferedImage[] levelSprite;
    protected Level levelOne;
    public LevelManager(Game game) {
        this.game = game;
        importOutsideSprites();
        levelOne = new Level(LoadSave.GetLevelData());
    }

    private void importOutsideSprites() {
        BufferedImage img = LoadSave.GetSpriteAtlas(OUTSIDE_FILE_PATH);
        levelSprite = new BufferedImage[48];
        for(int j = 0; j < 4; j++) {
            for(int i = 0; i < 12; i++) {
                int index = j * 12 + i;
                levelSprite[index] = img.getSubimage(i * 32, j * 32, 32, 32);
            }
        }
    }

    public void draw(Graphics graphics) {
        for(int j = 0; j < TILES_IN_HEIGHT; j++) {
            for(int i = 0; i < TILES_IN_WIDTH; i++) {
                int index = levelOne.getSpriteIndex(i, j);
                graphics.drawImage(levelSprite[index], TILES_SIZE * i, TILES_SIZE * j, TILES_SIZE, TILES_SIZE, null);
            }
        }
    }

    public void update() {

    }

    public Level getCurrentLevel() {
        return levelOne;
    }
}
