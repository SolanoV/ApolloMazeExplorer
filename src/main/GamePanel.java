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
    final int screenWidth = tileSize * maxScreenCol; // 640
    final int screenHeight = tileSize * maxScreenRow; // 512

    public final int offsetX=375;
    public final int offsetY=25;

    public int FPS = 165;


    Thread gameThread;
    Grid grid;
    Player player;
    KeyHandler keyH = new KeyHandler();
    UI ui;
    TileManager tileManager;


    public GamePanel() {
        this.setPreferredSize(new Dimension(1920, 1080));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        this.grid=new Grid(maxScreenCol, maxScreenRow);
        this.player=new Player(this, keyH, grid);
        this.ui=new UI(this);
        this.tileManager=new TileManager(this, grid);
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
        player.update();
        resetPressedHandler();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        tileManager.draw(g2);
        player.draw(g2);
        ui.draw(g2);
        g2.dispose();
    }

    private void resetPressedHandler(){
        if(keyH.rPressed){
            if(keyH.controlPressed){
                this.grid=new Grid(maxScreenCol, maxScreenRow);
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
}