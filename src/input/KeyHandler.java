package input;

import entity.Player;
import main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    GamePanel gamePanel;

    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public boolean rPressed, controlPressed, zPressed;

    public KeyHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        // TITLE STATE
        if(gamePanel.gameState==gamePanel.titleState){
            switch (code) {
                case KeyEvent.VK_UP:
                    gamePanel.ui.commandNumber--;
                    System.out.println(gamePanel.ui.commandNumber);
                    if(gamePanel.ui.commandNumber<0){
                        gamePanel.ui.commandNumber=1;
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    gamePanel.ui.commandNumber++;
                    System.out.println(gamePanel.ui.commandNumber);
                    if(gamePanel.ui.commandNumber>1){
                        gamePanel.ui.commandNumber=0;
                    }
                    break;
                case KeyEvent.VK_ENTER:
                    if(gamePanel.ui.commandNumber==0){
                        gamePanel.gameState=gamePanel.playState;
                        gamePanel.playMusic(0, -20.0f);
                    }
                    if(gamePanel.ui.commandNumber==1){
                        System.exit(0);
                    }
                    break;
            }
        }
        // PLAY STATE
        if(gamePanel.gameState==gamePanel.playState) {
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
                case KeyEvent.VK_P:
                    if (gamePanel.gameState == gamePanel.playState) {
                        gamePanel.gameState = gamePanel.pauseState;
                    } else if (gamePanel.gameState == gamePanel.pauseState) {
                        gamePanel.gameState = gamePanel.playState;
                    }
                    break;

            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    // Method to reset all flags after processing
    public void resetKeys() {
        if(gamePanel.gameState==gamePanel.playState) {
            upPressed = false;
            downPressed = false;
            leftPressed = false;
            rightPressed = false;
            rPressed = false;
            controlPressed = false;
            zPressed = false;
        }
    }
}