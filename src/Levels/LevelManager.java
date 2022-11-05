package Levels;

import main.Game;
import utils.LoadStuff;

import java.awt.*;
import java.awt.image.BufferedImage;

import static main.Game.TILES_IN_HEIGHT;
import static main.Game.TILES_IN_WIDTH;
import static utils.Constants.SpriteAtlas.ATLAS_DIMENSIONS;
import static utils.Constants.SpriteAtlas.GetAtlasPos;

public class LevelManager {
    private Game game;
    private Level currentLevel;
    private Level level1;
    private BufferedImage[][] mapBlocks;

    public LevelManager(Game game) {
        this.game = game;
        loadMapBlocks();
        level1 = LoadStuff.LoadJSONLevel("level_1.json");
        currentLevel = level1;

        game.getGameGrid().loadLevel(currentLevel);
        System.out.println(game.getGameGrid().getGrid()[2][9]);
        System.out.println(game.getGameGrid().getGrid()[2][9]);

    }

    private void loadMapBlocks() {
        mapBlocks = new BufferedImage[ATLAS_DIMENSIONS.y][ATLAS_DIMENSIONS.x];
        BufferedImage spriteSheet = LoadStuff.Image("sprites.png");
        int k = 0;
        for (int i = 0; i < ATLAS_DIMENSIONS.y; i++) {
            for (int j = 0; j < ATLAS_DIMENSIONS.x; j++)  {
                mapBlocks[i][j] = spriteSheet.getSubimage(j*32, i*32, 32, 32);
                k++;
            }
        }
    }

    public void restartLevel() {
        System.out.println(game.getGameGrid().getGrid()[2][9]);
        game.getGameGrid().loadLevel(LoadStuff.LoadJSONLevel("level_1.json"));
        System.out.println(game.getGameGrid().getGrid()[2][9]);
    }

    public void render(Graphics g) {
        for (int i = 0; i < TILES_IN_HEIGHT; i++) {
            for (int j = 0; j < TILES_IN_WIDTH; j++) {
                g.drawImage(mapBlocks[GetAtlasPos(game.getGameGrid().getGrid()[i][j]).y][GetAtlasPos(currentLevel.layout[i][j]).x], Game.TILE_SIZE * j, Game.TILE_SIZE * i, Game.TILE_SIZE, Game.TILE_SIZE, null);
            }
        }
    }

    public void update() {

    }

    public Level getCurrentLevel() {
        return currentLevel;
    }
}
