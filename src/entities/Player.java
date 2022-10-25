package entities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Player extends Entity{
    private boolean up, down, left, right;
    private float playerSpeed = 2.0f;
    private BufferedImage sprite;

    public Player(float x, float y) {
        super(x, y);
        loadSprite();
    }
    public void update() {
        updatePos();
    }

    private void updatePos() {
        if (left && !right) {
            x -= playerSpeed;
        } else if  (right && !left) {
            x += playerSpeed;
        }

        if (up && !down) {
            y -=playerSpeed;
        } else if (down && !up) {
            y += playerSpeed;
        }
    }

    private void loadSprite() {
        InputStream is = getClass().getResourceAsStream("/res/player.png");
        try {
            sprite = ImageIO.read(is);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void render(Graphics g) {
        g.drawImage(sprite, (int) x, (int) y, 96, 96, null);
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
