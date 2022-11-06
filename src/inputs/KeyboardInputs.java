package inputs;

import main.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputs implements KeyListener {
    private GamePanel gamePanel;
    public KeyboardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
//            case KeyEvent.VK_W:
//                gamePanel.getGame().getPlayer().setUp(true);
//                break;
//            case KeyEvent.VK_S:
//                gamePanel.getGame().getPlayer().setDown(true);
//                break;
//            case KeyEvent.VK_A:
//                gamePanel.getGame().getPlayer().setLeft(true);
//                break;
//            case KeyEvent.VK_D:
//                gamePanel.getGame().getPlayer().setRight(true);
//                break;

            case KeyEvent.VK_A, KeyEvent.VK_LEFT -> gamePanel.getGame().getPlayer().move(new Point(-1, 0));
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> gamePanel.getGame().getPlayer().move(new Point(1, 0));
            case KeyEvent.VK_W, KeyEvent.VK_UP -> gamePanel.getGame().getPlayer().move(new Point(0, -1));
            case KeyEvent.VK_S, KeyEvent.VK_DOWN -> gamePanel.getGame().getPlayer().move(new Point(0, 1));
            case KeyEvent.VK_R -> gamePanel.getGame().getLevelManager().restartLevel();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
