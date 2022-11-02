package entities;

import main.Game;
import utils.LoadStuff;

import java.awt.*;
import java.awt.image.BufferedImage;

import static main.Game.TILE_SIZE;

public class Entity {
    float x, y;
    Game game;
    BufferedImage sprite;
    public Entity(Game game, int x, int y, Point spriteAtlasPos) {
        this.game = game;
        this.x = x;
        this.y = y;
        sprite = LoadStuff.Sprite(spriteAtlasPos);
    }


}
