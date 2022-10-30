package Levels;

import main.Game;
import utils.LoadStuff;

import java.awt.*;
import java.awt.image.BufferedImage;

import static main.Game.TILES_IN_HEIGHT;
import static main.Game.TILES_IN_WIDTH;
import static utils.Constants.MapBlocks.BlockToSprite;

public class LevelManager {
    private Game game;
    private Level level1;
    private BufferedImage[] mapBlocks;

    public LevelManager(Game game) {
        this.game = game;
        loadMapBlocks();
        level1 = LoadStuff.LoadLevel("level_1.txt", TILES_IN_WIDTH, TILES_IN_HEIGHT);
        game.getGameGrid().loadLevel(level1);
    }

    private void loadMapBlocks() {
        mapBlocks = new BufferedImage[4];
        BufferedImage spriteSheet = LoadStuff.Image("sprites.png");
        int k = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++)  {
                mapBlocks[k] = spriteSheet.getSubimage(i*32, j*32, 32, 32);
                k++;
            }
        }
    }

    public void render(Graphics g) {
        for (int i = 0; i < TILES_IN_HEIGHT; i++) {
            for (int j = 0; j < TILES_IN_WIDTH; j++) {
                g.drawImage(mapBlocks[BlockToSprite(level1.layout[i][j])], Game.TILE_SIZE * j, Game.TILE_SIZE * i, Game.TILE_SIZE, Game.TILE_SIZE, null);
            }
        }
    }

    public void update() {

    }

    public Level getLevel1() {
        return level1;
    }
}
