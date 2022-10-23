package player;

import java.awt.Graphics;

public class Player {
    public void setYVelocity(int velocity) {
        yVelocity = velocity;
    }
    public void setXVelocity(int velocity) {
        xVelocity = velocity;
    }

    enum state {
        ALIVE,
        DEAD,
        NA
    }

    private int xPos, yPos;
    private int xVelocity, yVelocity;

    public Player(int x, int y) {
        xPos = x;
        yPos = y;
    }

    public void drawPlayer(Graphics g) {
        g.fillRect(xPos, yPos, 100, 100);
    }

    public void updatePlayer() {
        xPos += xVelocity;
        yPos += yVelocity;
    }
}
