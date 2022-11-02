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
    public Player(Game game, int tilePosX, int tilePosY) {
        super(game, tilePosX * TILE_SIZE, tilePosY * TILE_SIZE, SpriteAtlas.PLAYER);
//        subPlayers.add(new SubPlayer(this, 1, 0));
//        subPlayers.add(new SubPlayer(this, 5, 0));
    }

    public void render(Graphics g) {
        g.drawImage(sprite, (int) x, (int) y, TILE_SIZE, TILE_SIZE, null);
        for (SubPlayer subplayer : subPlayers) {
            subplayer.render(g);
        }
    }

    public void addSubPlayer(Point deltas) {
        if (deltas != null){
            subPlayers.add(new SubPlayer(this, deltas.x, deltas.y));
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

    private boolean okayToMove(int xDelta, int yDelta) {
        boolean move = true;
        boolean playerMove = game.getGameGrid().isOkayToMove((int) x + (xDelta * TILE_SIZE), (int) y + (yDelta * TILE_SIZE));
        if (playerMove) {
            for (SubPlayer subPlayer : subPlayers) {
                if (!subPlayer.checkCollision(xDelta, yDelta)) {
                    move = false;
                }
            }
        } else {
            move = false;
        }
        return move;
    }

    private void checkForNewSubPlayers() {
        addSubPlayer(game.getGameGrid().checkForNewSubPlayers((int) x, (int) y, new Point(0, 0)));
        for (SubPlayer subPlayer : subPlayers) {
            addSubPlayer(subPlayer.checkForNewSubPlayers());
        }
    }

    public void moveLeft() {
        if (okayToMove(-1, 0)) {
            x -= TILE_SIZE;
            moveSubPlayers(-TILE_SIZE, 0);
            checkForNewSubPlayers();
        }
    }
    public void moveRight() {
        if (okayToMove(1, 0)) {
            x += TILE_SIZE;
            moveSubPlayers(TILE_SIZE, 0);
            checkForNewSubPlayers();
        }
    }

    public void moveUp() {
        if (okayToMove(0, -1)) {
            y -= TILE_SIZE;
            moveSubPlayers(0, -TILE_SIZE);
            checkForNewSubPlayers();
        }
    }
    public void moveDown() {
        if (okayToMove(0, 1)) {
            y += TILE_SIZE;
            moveSubPlayers(0, TILE_SIZE);
            checkForNewSubPlayers();
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
