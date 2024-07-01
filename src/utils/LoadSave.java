package utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static utils.Constants.GameValues.TILES_IN_HEIGHT;
import static utils.Constants.GameValues.TILES_IN_WIDTH;
import static utils.Constants.ImageFiles.LEVEL_ONE_DATA;

public class LoadSave {
    public static BufferedImage GetSpriteAtlas(String path) {
        BufferedImage img = null;
        InputStream inputStream = LoadSave.class.getResourceAsStream("/images/" + path);
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
        return img;
    }

    public static int[][] GetLevelData() {
        int[][] lvlData = new int[TILES_IN_HEIGHT][TILES_IN_WIDTH];
        BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);
        for(int j = 0; j < img.getHeight(); j++) {
            for(int i = 0; i < img.getWidth(); i++) {
                Color color = new Color(img.getRGB(i, j));
                int value = color.getRed();
                if(value >= 48) {
                    value = 0;
                }
                lvlData[j][i] = value;
            }
        }
        return lvlData;
    }
}
