package infoDisplay;

import java.awt.*;

public class Display {
    private int FPS, TPS;
    private int x, y;

    public Display(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void draw_display(Graphics g) {
        g.setColor(new Color(158, 5, 7));
        g.drawString("FPS: " + FPS, x, y);
        g.drawString("TPS: " + TPS, x, y + 15);
    }

    public void updateFPS(int fps) {
        FPS = fps;
    }
    public void updateTPS(int tps) {
        TPS = tps;
    }
}
