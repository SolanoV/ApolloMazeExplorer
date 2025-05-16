package main;

import maze.Grid;

import java.awt.*;

public class UI {
    GamePanel gp;
    Graphics g;
    Font arial_40;

    // Title Screen
    public int commandNumber=0;

    public UI(GamePanel gp) {
        this.gp=gp;
        arial_40 = new Font("Arial", Font.BOLD, 40);
    }

    public void draw(Graphics g) {
        this.g=g;


        if(gp.gameState==gp.titleState){
            drawTitleScreen();
        }
        if(gp.gameState== gp.playState){
            g.setFont(arial_40);
            g.setColor(Color.white);
            g.drawString(""+gp.player.getMoves(), 100, 100);
        }
        if(gp.gameState==gp.pauseState){
            drawPauseScreen();
        }

    }
    public void drawTitleScreen() {
        //TITLE NAME
        g.setFont(g.getFont().deriveFont(Font.BOLD,76F));
        String text="Placeholder*";
        g.setColor(Color.WHITE);
        g.drawString(text, 570, 150);

        g.setFont(g.getFont().deriveFont(Font.BOLD,46F));
        text="PLAY";
        g.drawString(text, 710, 400);
        if(commandNumber==0){
            g.drawString(">", 680, 400);
        }


        g.setFont(g.getFont().deriveFont(Font.BOLD,46F));
        text="QUIT";
        g.drawString(text, 710, 500);
        if(commandNumber==1){
            g.drawString(">", 680, 500);
        }

    }

    public void drawPauseScreen() {
        g.setFont(g.getFont().deriveFont(Font.PLAIN,80F));
        String text="PAUSED";
        g.drawString(text, 600, 450);
    }


}
