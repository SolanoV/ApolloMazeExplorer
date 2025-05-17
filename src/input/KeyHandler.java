package input;

import entity.Player;
import main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    GamePanel gamePanel;

    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public boolean rPressed, controlPressed;

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
                        gamePanel.playMusic(0,-20.0f);
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

            }
        }
        // PAUSE STATE
        if(gamePanel.gameState==gamePanel.pauseState){
            switch (code) {
                case KeyEvent.VK_UP:
                    gamePanel.ui.pauseNumber--;
                    System.out.println(gamePanel.ui.pauseNumber);
                    if(gamePanel.ui.pauseNumber<0){
                        gamePanel.ui.pauseNumber=1;
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    gamePanel.ui.pauseNumber++;
                    System.out.println(gamePanel.ui.pauseNumber);
                    if(gamePanel.ui.pauseNumber>1) {
                        gamePanel.ui.pauseNumber = 0;
                    }
                    break;
                case KeyEvent.VK_ENTER:
                    if(gamePanel.ui.pauseNumber==0){
                        gamePanel.gameState=gamePanel.playState;
                        gamePanel.playMusic(0,-20.0f);
                    }
                    if(gamePanel.ui.pauseNumber==1){
                        gamePanel.gameState=gamePanel.titleState    ;
                    }
                    break;
            }
        }

        // CREDIT STATE
        if(gamePanel.gameState==gamePanel.creditState) {
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

            }
        }

        // COMPLETE STATE
        if(gamePanel.gameState==gamePanel.completeState) {
            switch (code) {
                case KeyEvent.VK_ENTER:
                    gamePanel.gameState=gamePanel.titleState;
                    break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_P) {
            if (gamePanel.gameState == gamePanel.playState) {
                gamePanel.previousGameState = gamePanel.playState;
                gamePanel.gameState = gamePanel.pauseState;
                gamePanel.stopMusic();
                System.out.println("Stopped music in pauseState from playState");
            } else if (gamePanel.gameState == gamePanel.creditState) {
                gamePanel.previousGameState = gamePanel.creditState;
                gamePanel.gameState = gamePanel.pauseState;
                gamePanel.stopMusic();
                System.out.println("Stopped music in pauseState from creditState");
            } else if (gamePanel.gameState == gamePanel.pauseState) {
                gamePanel.gameState = gamePanel.previousGameState;
                if (gamePanel.previousGameState == gamePanel.playState) {
                    gamePanel.playMusic(0, -20.0f); // backgroundMusic.wav
                    System.out.println("Resumed playState music");
                } else if (gamePanel.previousGameState == gamePanel.creditState) {
                    gamePanel.playMusic(2, -20.0f); // creditMusic.wav
                    System.out.println("Resumed creditState music");
                }
            }
        }

    }

    // Method to reset all flags after processing
    public void resetKeys() {
            upPressed = false;
            downPressed = false;
            leftPressed = false;
            rightPressed = false;
            rPressed = false;
            controlPressed = false;

    }
}