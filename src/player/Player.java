package player;

import java.awt.Graphics;

public class Player extends movmentKeyTracker {

    public void setYVelocity(int velocity) { yVelocity = velocity; }
    public void setXVelocity(int velocity) {
        xVelocity = velocity;
    }

    public void adjustYVelocity(int deltaV) {
        yVelocity += deltaV;
        if (yVelocity > 2 ) {
            yVelocity = 2;
        } else if (yVelocity < -2) {
            yVelocity = -2;
        }
    }
    public void adjustXVelocity(int deltaV) { xVelocity += deltaV; }

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
