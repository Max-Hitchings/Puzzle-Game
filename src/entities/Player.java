package entities;

import main.Game;
import utils.Constants;

import java.awt.*;
import java.util.ArrayList;

import static main.Game.TILE_SIZE;

public class Player extends Entity{
    private ArrayList<SubPlayer> subPlayers = new ArrayList<>();
    public Player(Game game, int tilePosX, int tilePosY) {
        super(game, tilePosX * TILE_SIZE, tilePosY * TILE_SIZE);
    }

    public void render(Graphics g) {
        g.drawImage(sprite, (int) x, (int) y, TILE_SIZE, TILE_SIZE, null);
        for (SubPlayer subplayer : subPlayers) {
            subplayer.render(g);
        }
    }

    public void reset() {
        Point spawn = game.getLevelManager().getCurrentLevel().playerSpawn;
        subPlayers.clear();
        sprite = sprites.get(Constants.PlayerSprites.NORMAL);
        x = spawn.x * TILE_SIZE;
        y = spawn.y * TILE_SIZE;
    }

    public void addSubPlayer(Point deltas) {
        if (deltas != null){
            subPlayers.add(new SubPlayer(this, deltas.x, deltas.y));
        }
    }

    public void move(Point delta) {
        if (okayToMove(delta.x, delta.y)) {
            x += delta.x * TILE_SIZE;
            y += delta.y * TILE_SIZE;
            moveSubPlayers(delta.x * TILE_SIZE, delta.y * TILE_SIZE);
            checkForNewSubPlayers();
            checkForWin();
        }
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

    private void checkForWin() {
        if (game.getGameGrid().checkWin((int) x, (int) y)) {
            sprite = sprites.get(Constants.PlayerSprites.CORRECT);

            boolean win = true;
            for (SubPlayer subPlayer : subPlayers) {
                if (!subPlayer.checkForWin()) win = false;
            }
            if (win && subPlayers.size() == game.getGameGrid().finishTiles - 1)  {
                triggerWin();
            }
        } else {
            for (SubPlayer subPlayer : subPlayers) {
                subPlayer.checkForWin();
            }
            sprite = sprites.get(Constants.PlayerSprites.NORMAL);
        }
    }

    private void triggerWin() {
        System.out.println("win");
    }
}
