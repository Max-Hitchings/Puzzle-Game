package utils;

import Levels.Level;
import utils.Constants.MapBlocks;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class LoadStuff {
    public static BufferedImage Image(String fileName) {
        BufferedImage img = null;
        InputStream is = LoadStuff.class.getResourceAsStream("/" + fileName);
        try {
            img = ImageIO.read(is);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return img;
    }

    public static BufferedImage Sprite(Point pos) {
        BufferedImage img, sprite = null;
        InputStream is = LoadStuff.class.getResourceAsStream("/sprites.png");
        try {
            img = ImageIO.read(is);
            sprite = img.getSubimage(pos.x*32, pos.y*32, 32, 32);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sprite;
    }


    public static Level LoadLevel(String fileName, int width, int height) {
        int[][] encodedLvlData = new int[height][width];


        try {
            InputStream is = LoadStuff.class.getResourceAsStream("/" + fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            String line;
            int x = 0;
            while (reader.ready()) {
                line = reader.readLine();

                for (int i = 0; i < line.length(); i++) {
                    encodedLvlData[x][i] = MapBlocks.GetBlockType(line.charAt(i));
                }
                x++;
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new Level(encodedLvlData);
    }
}
