package inputs;

import main.GamePanel;

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
            case KeyEvent.VK_W:

                gamePanel.player.adjustYVelocity(-2);
                break;
            case KeyEvent.VK_S:
                gamePanel.player.adjustYVelocity(2);
                break;
            case KeyEvent.VK_A:
                gamePanel.player.setXVelocity(-2);
                break;
            case KeyEvent.VK_D:
                gamePanel.player.setXVelocity(2);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                gamePanel.player.adjustYVelocity(2);
            case KeyEvent.VK_S:
                gamePanel.player.adjustYVelocity(-2);
                break;
            case KeyEvent.VK_A:
            case KeyEvent.VK_D:
                gamePanel.player.setXVelocity(0);
                break;
        }
    }
}
