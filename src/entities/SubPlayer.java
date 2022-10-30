package entities;

import utils.Constants;

import java.awt.*;

import static main.Game.TILE_SIZE;


public class SubPlayer extends Entity{
    private int yTilesDelta, xTilesDelta;
    private Player player;
    public SubPlayer(Player player, int xTilesDelta, int yTilesDelta) {
        super((int) player.x, (int) player.y, Constants.SpriteAtlas.PLAYER);
        this.yTilesDelta = yTilesDelta;
        this.xTilesDelta = xTilesDelta;
        this.player = player;
    }

    public void render(Graphics g) {
        g.drawImage(sprite, (int) player.x + (xTilesDelta * TILE_SIZE), (int) player.y + (yTilesDelta * TILE_SIZE), TILE_SIZE, TILE_SIZE, null);

    }

    public void move(int deltaX, int deltaY) {
        x += deltaX;
        y += deltaY;
    }
}
