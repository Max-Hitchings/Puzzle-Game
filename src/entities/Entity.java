package entities;

import utils.LoadStuff;

import java.awt.*;
import java.awt.image.BufferedImage;

import static main.Game.TILE_SIZE;

public class Entity {
    float x, y;
    BufferedImage sprite;
    public Entity(int x, int y, Point spriteAtlasPos) {
        this.x = x;
        this.y = y;
        sprite = LoadStuff.Sprite(spriteAtlasPos);
    }
}
