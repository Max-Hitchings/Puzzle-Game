package main;

import infoDisplay.Display;
import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.JPanel;
import java.awt.*;


public class GamePanel extends JPanel {
    final int PANEL_SIZE_X = 1280 ;
    final int PANEL_SIZE_Y = 800;
    private MouseInputs mouseInputs;
    private Game game;
    public GamePanel(Game game) {
        mouseInputs = new MouseInputs(this);
        this.game = game;
        setPanelSize();

        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void setPanelSize() {
        // 32px tiles so 40 wide and 25 high
        Dimension size = new Dimension(PANEL_SIZE_X, PANEL_SIZE_Y);
        setMinimumSize(size);
        setMaximumSize(size);
        setPreferredSize(size);
    }

    private Display infoDisplay = new Display(PANEL_SIZE_X - 60, 15);

    public void update_fps(int fps) {
        infoDisplay.updateFPS(fps);
    }
    public void update_tps(int tps) {
        infoDisplay.updateTPS(tps);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        infoDisplay.draw_display(g);

        game.render(g);
    }
    public Game getGame() {
        return game;
    }
}
