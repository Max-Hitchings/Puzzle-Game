package entities;

import utils.LoadStuff;
import utils.Constants.SpriteAtlas;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Player extends Entity{
    private boolean up, down, left, right;
    private float playerSpeed = 2.0f;
    private BufferedImage sprite;

    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
        sprite = LoadStuff.Sprite(SpriteAtlas.PLAYER);
    }
    public void update() {
        updatePos();
    }

    private void updatePos() {
        if (left && !right) {
            x -= playerSpeed;
        } else if (right && !left) {
            x += playerSpeed;
        }

        if (up && !down) {
            y -= playerSpeed;
        } else if (down && !up) {
            y += playerSpeed;
        }
    }


    public void render(Graphics g) {
        g.drawImage(sprite, (int) x, (int) y, width, height, null);
    }

    public void cancelMovement() {
        right = down = left = up = false;
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
