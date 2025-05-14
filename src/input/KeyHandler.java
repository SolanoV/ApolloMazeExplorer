package input;

import entity.Player;
import main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public boolean rPressed, controlPressed, zPressed;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code) {
            case KeyEvent.VK_UP:
                upPressed = true;
                break;
            case KeyEvent.VK_DOWN:
                downPressed = true;
                break;
            case KeyEvent.VK_LEFT:
                leftPressed = true;
                break;
            case KeyEvent.VK_RIGHT:
                rightPressed = true;
                break;
            case KeyEvent.VK_R:
                rPressed = true;
                break;
            case KeyEvent.VK_CONTROL:
                controlPressed = true;
                break;
            case KeyEvent.VK_Z:
                zPressed = true;
                break;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    // Method to reset all flags after processing
    public void resetKeys() {
        upPressed = false;
        downPressed = false;
        leftPressed = false;
        rightPressed = false;
        rPressed = false;
        controlPressed = false;
        zPressed = false;
    }
}