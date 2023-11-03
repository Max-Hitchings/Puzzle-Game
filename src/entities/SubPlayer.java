package entities;

import utils.Constants;

import java.awt.*;

import static main.Game.TILE_SIZE;


public class SubPlayer extends Entity{
    public int yTilesDelta, xTilesDelta;
    private Player player;
    public SubPlayer(Player player, int xTilesDelta, int yTilesDelta) {
        super(player.game, (int) player.x, (int) player.y);
        this.yTilesDelta = yTilesDelta;
        this.xTilesDelta = xTilesDelta;
        this.player = player;
        player.addSubPlayer(checkForNewSubPlayers());
    }
    public void render(Graphics g) {
        g.drawImage(sprite, (int) player.x + (xTilesDelta * TILE_SIZE), (int) player.y + (yTilesDelta * TILE_SIZE), TILE_SIZE, TILE_SIZE, null);
    }

    public boolean checkCollision(int xDelta, int yDelta) {
        return game.getGameGrid().isOkayToMove((int) x + (xTilesDelta * TILE_SIZE) + (xDelta * TILE_SIZE), (int) y + (yTilesDelta * TILE_SIZE) + (yDelta * TILE_SIZE));
    }
    public Point checkForNewSubPlayers() {
        return game.getGameGrid().checkForNewSubPlayers((int) x + (xTilesDelta * TILE_SIZE), (int) y + (yTilesDelta * TILE_SIZE), new Point(xTilesDelta, yTilesDelta));
    }

    public void move(int deltaX, int deltaY) {
        x += deltaX;
        y += deltaY;
    }

//    TODO optimise this
    public boolean checkForWin() {
        if (game.getGameGrid().checkWin((int) x + (xTilesDelta * TILE_SIZE), (int) y + (yTilesDelta * TILE_SIZE))) {
            sprite = sprites.get(Constants.PlayerSprites.CORRECT);
            return true;
        } else {
            sprite = sprites.get(Constants.PlayerSprites.NORMAL);
            return false;
        }
    }
}
