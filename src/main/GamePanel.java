package main;

import entity.Player;
import input.KeyHandler;
import maze.Grid;

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

    public int FPS = 165;


    Thread gameThread;
    Grid grid;
    Player player;
    KeyHandler keyH = new KeyHandler();
    UI ui;


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        this.grid=new Grid(maxScreenCol, maxScreenRow);
        this.player=new Player(this, keyH, grid);
        this.ui=new UI(this);
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

        // Render grid
        for (int y = 0; y < grid.getHeight(); y++) {
            for (int x = 0; x < grid.getWidth(); x++) {
                int worldX = x * tileSize;
                int worldY = y * tileSize;
                if (grid.getCell(x, y) == Grid.FLOOR) {
                    g2.setColor(Color.BLACK);
                } else if (grid.getCell(x, y) == Grid.WALL) {
                    g2.setColor(Color.GRAY);
                }
                else if(grid.getCell(x, y) == Grid.START) {
                    g2.setColor(Color.GREEN);
                }
                else if (grid.getCell(x, y) == Grid.END) {
                    g2.setColor(Color.RED);
                }
                else if(grid.getCell(x,y)==Grid.PATH){
                    g2.setColor(Color.BLUE);
                }
                g2.fillRect(worldX, worldY, tileSize, tileSize);
            }
        }
        player.draw(g2);
        ui.draw(g2);
        g2.dispose();
    }

    private void resetPressedHandler(){
        if(keyH.rPressed){
            if(keyH.controlPressed){
                this.grid=new Grid(maxScreenCol, maxScreenRow);
                player.reset(this.grid);
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