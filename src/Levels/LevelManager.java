package Levels;

import main.Game;
import utils.LoadStuff;

import java.awt.*;
import java.awt.image.BufferedImage;

import static main.Game.TILES_IN_HEIGHT;
import static main.Game.TILES_IN_WIDTH;
import static utils.Constants.SpriteAtlas.ATLAS_DIMENTIONS;
import static utils.Constants.SpriteAtlas.GetAtlasPos;

public class LevelManager {
    private Game game;
    private Level level1;
    private BufferedImage[][] mapBlocks;

    public LevelManager(Game game) {
        this.game = game;
        loadMapBlocks();
        level1 = LoadStuff.LoadLevel("level_1.txt", TILES_IN_WIDTH, TILES_IN_HEIGHT);
        game.getGameGrid().loadLevel(level1);
    }

    private void loadMapBlocks() {
        mapBlocks = new BufferedImage[ATLAS_DIMENTIONS.y][ATLAS_DIMENTIONS.x];
        BufferedImage spriteSheet = LoadStuff.Image("sprites.png");
        int k = 0;
        for (int i = 0; i < ATLAS_DIMENTIONS.y; i++) {
            for (int j = 0; j < ATLAS_DIMENTIONS.x; j++)  {
                mapBlocks[i][j] = spriteSheet.getSubimage(j*32, i*32, 32, 32);
                k++;
            }
        }
    }

    public void render(Graphics g) {
        for (int i = 0; i < TILES_IN_HEIGHT; i++) {
            for (int j = 0; j < TILES_IN_WIDTH; j++) {
                g.drawImage(mapBlocks[GetAtlasPos(level1.layout[i][j]).y][GetAtlasPos(level1.layout[i][j]).x], Game.TILE_SIZE * j, Game.TILE_SIZE * i, Game.TILE_SIZE, Game.TILE_SIZE, null);
            }
        }
    }

    public void update() {

    }

    public Level getLevel1() {
        return level1;
    }
}
