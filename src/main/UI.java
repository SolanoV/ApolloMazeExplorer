package main;

import maze.Grid;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class UI {
    GamePanel gp;
    Graphics g;
    Font vcr_osd;
    BufferedImage image1;

    // Title Screen
    public int commandNumber=0;

    public UI(GamePanel gp){
        this.gp=gp;
        importImage();
        try (InputStream is = getClass().getResourceAsStream("/fonts/VCR_OSD_MONO_1.001.ttf")) {
            this.vcr_osd = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics g) {
        this.g=g;


        if(gp.gameState==gp.titleState){
            drawTitleScreen();
        }
        if(gp.gameState== gp.playState){
            mazeBackground();
            drawMoves();
            drawStage();
        }
        if(gp.gameState==gp.pauseState){
            drawPauseScreen();
            if(gp.previousGameState==gp.playState) {
                mazeBackground();
                drawMoves();
                drawStage();
            }
        }
        if(gp.gameState==gp.creditState){

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
        g.setColor(Color.WHITE);
        g.drawString(text, 600, 450);
    }
    public void drawMoves(){
        g.setFont(vcr_osd.deriveFont(70F));
        g.setColor(Color.white);
        if(gp.player.getMoves()<10){
            g.drawString("" + gp.player.getMoves(), 165, 660);
        } else {
            g.drawString("" + gp.player.getMoves(), 140, 660);
        }
    }
    public void drawStage(){
        g.setFont(vcr_osd.deriveFont(70F));
        g.setColor(Color.white);
        g.drawString(""+gp.player.getStage(), 1330, 310);
    }
    public void mazeBackground(){
        g.drawImage(image1, 0,0,1530,860,null);
    }
    private void importImage(){
        InputStream mazebackground = getClass().getResourceAsStream("/background/mazeBackground.png");

        try{
            image1 = ImageIO.read(mazebackground);
        } catch (IOException e){
            e.printStackTrace();
        }
    }


}
