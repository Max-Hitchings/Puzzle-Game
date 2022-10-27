package main;

import infoDisplay.Display;
import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.JPanel;
import java.awt.*;


public class GamePanel extends JPanel {
    int panelSizeX, panelSizeY;
    private MouseInputs mouseInputs;
    private Game game;
    public GamePanel(Game game, int size_x, int size_y) {
        mouseInputs = new MouseInputs(this);
        this.game = game;
        panelSizeX = size_x;
        panelSizeY = size_y;
        setPanelSize();

        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void setPanelSize() {
        Dimension size = new Dimension(panelSizeX, panelSizeY);
        setMinimumSize(size);
        setMaximumSize(size);
        setPreferredSize(size);
    }

    private Display infoDisplay = new Display(panelSizeX - 60, 15);

//    public void update_fps(int fps) {
//        infoDisplay.updateFPS(fps);
//    }
//    public void update_tps(int tps) {
//        infoDisplay.updateTPS(tps);
//    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);



        game.render(g);
        infoDisplay.draw_display(g);
    }
    public Game getGame() {
        return game;
    }
}
