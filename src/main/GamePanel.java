package main;

import entity.Player;
import input.KeyHandler;
import maze.Grid;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    public final int originalTileSize = 16;
    public int scale = 3;
    public final int tileSize = originalTileSize * scale; // 32 by 32
    public final int maxScreenCol = 17;
    public final int maxScreenRow = 17;
    final int screenWidth = 1920; // 640
    final int screenHeight = 1080; // 512

    public final int offsetX=375;
    public final int offsetY=25;

    public int FPS = 165;

    //SYSTEM
    Thread gameThread;
    Grid grid;
    KeyHandler keyH= new KeyHandler(this);;
    public UI ui;
    public TileManager tileManager;
    Sound sound; // Background Music
    Sound sfx; //SFX

    // ENTITY AND OBJECTS
    Player player;

    // GAME STATE
    public int gameState;
    public int previousGameState;
    public final int titleState=0;
    public final int playState=1;
    public final int pauseState=2;
    public final int creditState=3;
    public final int animatedDoorState=4;
    public final int debugState=9;
    public final int completeState=5;


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        // SYSTEM
        this.grid=new Grid(maxScreenCol, maxScreenRow, this);
        this.ui=new UI(this);
        this.tileManager=new TileManager(this, grid);
        this.sound=new Sound();
        this.sfx=new Sound();

        // ENTITY AND OBJECTS
        this.player=new Player(this, keyH, grid);
    }
    public void setupGame(){
        gameState=titleState;

    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000.0 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        if (gameState == playState) {
            player.update();
            resetPressedHandler();
        }
        else if (gameState == pauseState) {

        }
        else if(gameState == creditState) {
            player.update();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if(gameState==titleState){
            ui.draw(g2);
        }
        else if(gameState==playState) {
            tileManager.draw(g2);
            player.draw(g2);
            ui.draw(g2);

        }
        else if(gameState==pauseState){
            tileManager.draw(g2);
            player.draw(g2);
            ui.draw(g2);
        }
        else if(gameState==creditState){
            tileManager.draw(g2);
            player.draw(g2);
            ui.draw(g2);
        }
        else if(gameState==completeState){
            ui.draw(g2);
        }
        g2.dispose();
    }

    private void resetPressedHandler(){
        if(keyH.rPressed){
            if(keyH.controlPressed){
                this.grid=new Grid(maxScreenCol, maxScreenRow, this);
                player.reset(this.grid);
                tileManager.reset(this.grid);
                keyH.resetKeys();
                System.out.println("Ctrl R");
            }
            else {
                player.setMoves(0);
                player.reset();
                keyH.resetKeys();
            }
        }
    }

    public void playMusic(int i, float volume){
        sound.setFile(i, volume);
        sound.play();
        sound.loop();
    }

    public void stopMusic(){
        sound.stop();
    }

    public void playSFX(int i, float volume){
        sfx.setFile(i, volume);
        sfx.play();
    }
}