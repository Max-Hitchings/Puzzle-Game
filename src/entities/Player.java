package entities;

import main.Game;
import utils.LoadStuff;
import utils.Constants.SpriteAtlas;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static main.Game.TILE_SIZE;

public class Player extends Entity{
    private boolean up, down, left, right;
    private boolean moving = false;
    private ArrayList<SubPlayer> subPlayers = new ArrayList<>();
    private float playerSpeed = 2.0f;
    private Game game;

    public Player(Game game, int tilePosX, int tilePosY) {
        super(tilePosX * TILE_SIZE, tilePosY * TILE_SIZE, SpriteAtlas.PLAYER);
        this.game = game;
        subPlayers.add(new SubPlayer(this, 1, 0));
    }

    public void render(Graphics g) {
        g.drawImage(sprite, (int) x, (int) y, TILE_SIZE, TILE_SIZE, null);
        System.out.println("test");
        for (SubPlayer subplayer : subPlayers) {
//            System.out.println(subPlayers.get(j));
            subplayer.render(g);
        }
    }

    public void cancelMovement() {
        right = down = left = up = false;
    }
    public void moveSubPlayers(int deltaX, int deltaY) {
        for (SubPlayer subPlayer : subPlayers) {
            subPlayer.move(deltaX, deltaY);
        }
    }

    public void moveLeft() {
        if (game.getGameGrid().isOkayToMove((int) x - TILE_SIZE, (int) y)) {
            x -= TILE_SIZE;
        }
    }
    public void moveRight() {
        if (game.getGameGrid().isOkayToMove((int) x + TILE_SIZE, (int) y)) {
            x += TILE_SIZE;
        }
    }
    public void moveUp() {
        if (game.getGameGrid().isOkayToMove((int) x, (int) y - TILE_SIZE)) {
            y -= TILE_SIZE;
        }
    }
    public void moveDown() {
        if (game.getGameGrid().isOkayToMove((int) x, (int) y + TILE_SIZE)) {
            y += TILE_SIZE;
        }
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }
}
