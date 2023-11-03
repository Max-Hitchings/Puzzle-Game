package Levels;

import main.Game;
import utils.LoadStuff;

import java.awt.*;
import java.awt.image.BufferedImage;

import static main.Game.TILES_IN_HEIGHT;
import static main.Game.TILES_IN_WIDTH;
import static utils.Constants.SpriteAtlas.ATLAS_DIMENSIONS;
import static utils.Constants.SpriteAtlas.GetAtlasPos;
import static utils.Constants.LEVEL_FILES;

public class LevelManager {
    private Game game;
    private int currentLevelIndex = 0;
    private static final Level[] levels = new Level[LEVEL_FILES.length];
    private BufferedImage[][] mapBlocks;

    public LevelManager(Game game) {
        this.game = game;
        loadMapBlocks();
//        levels[currentLevelIndex] = LoadStuff.LoadJSONLevel("level_1.json");
        loadLevels();
        game.getGameGrid().loadLevel(levels[currentLevelIndex]);
    }

    private void loadLevels() {
        for (int i = 0; i < LEVEL_FILES.length; i++) {
            levels[i] = LoadStuff.LoadJSONLevel(LEVEL_FILES[i]);
        }
    }

    private void loadMapBlocks() {
        mapBlocks = new BufferedImage[ATLAS_DIMENSIONS.y][ATLAS_DIMENSIONS.x];
        BufferedImage spriteSheet = LoadStuff.Image("sprites.png");
        for (int i = 0; i < ATLAS_DIMENSIONS.y; i++) {
            for (int j = 0; j < ATLAS_DIMENSIONS.x; j++)  {
                mapBlocks[i][j] = spriteSheet.getSubimage(j*32, i*32, 32, 32);
            }
        }
    }

    public void restartLevel() {
        game.getPlayer().reset();
        game.getGameGrid().loadLevel(levels[currentLevelIndex]);
    }

    public void nextLevel() {
        if (currentLevelIndex + 1 < levels.length) {
            currentLevelIndex++;
            restartLevel();
        } else {
            game.quit();
        }

    }

    public void previousLevel() {
        if (currentLevelIndex - 1 >= 0) {
            currentLevelIndex--;
            restartLevel();
        }
    }

    public void render(Graphics g) {
        for (int i = 0; i < TILES_IN_HEIGHT; i++) {
            for (int j = 0; j < TILES_IN_WIDTH; j++) {
                g.drawImage(mapBlocks[GetAtlasPos(game.getGameGrid().getGrid()[i][j]).y][GetAtlasPos(game.getGameGrid().getGrid()[i][j]).x], Game.TILE_SIZE * j, Game.TILE_SIZE * i, Game.TILE_SIZE, Game.TILE_SIZE, null);
            }
        }
    }

    public void update() {

    }

    public Level getCurrentLevel() {
        return levels[currentLevelIndex];
    }
}
