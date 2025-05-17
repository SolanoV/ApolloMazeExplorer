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
    BufferedImage[] image=new BufferedImage[20];

    // Title Screen
    public int commandNumber=0;
    public int pauseNumber=0;

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
        if(gp.gameState==gp.completeState){
            drawCompleteScreen();
        }

    }
    public void drawCompleteScreen(){
        g.setFont(vcr_osd.deriveFont(46F));
        g.setColor(Color.WHITE);
        String text="YOU WON!";
        g.drawString(text, 600, 400);

        g.setFont(vcr_osd.deriveFont(35F));
        g.setColor(Color.WHITE);
        text="Press ENTER to continue";
        g.drawString(text, 500, 500);
    }
    public void drawTitleScreen() {
        //TITLE NAME
        titleBackground();

        g.setFont(vcr_osd.deriveFont(46F));
        g.setColor(Color.WHITE);
        String text="PLAY";
        g.drawString(text, 710, 600);

        if(commandNumber==0){
            g.drawString(">", 680, 600);
        }


        g.setFont(vcr_osd.deriveFont(46F));
        text="QUIT";
        g.drawString(text, 710, 700);
        g.setColor(Color.WHITE);
        if(commandNumber==1){
            g.drawString(">", 680, 700);
        }

    }

    public void drawPauseScreen() {
        g.drawImage(image[2], 0,0,1530,860,null);

        g.setFont(vcr_osd.deriveFont(46F));
        g.setColor(Color.WHITE);
        String text="RESUME";
        g.drawString(text, 650, 450);
        if(pauseNumber==0){
            g.drawString(">", 610, 450);
        }

        g.setFont(vcr_osd.deriveFont(46F));
        text="MAIN MENU";
        g.drawString(text, 650, 550);
        g.setColor(Color.WHITE);
        if(pauseNumber==1){
            g.drawString(">", 610, 550);
        }
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
        g.drawImage(image[0], 0,0,1530,860,null);
    }
    public void titleBackground(){
        g.drawImage(image[1],0,0,1530,860,null);
    }
    private void importImage(){
        InputStream mazebackground = getClass().getResourceAsStream("/background/mazeBackground.png");
        InputStream titleBackground= getClass().getResourceAsStream("/background/titleBackground.png");
        InputStream pauseBackground = getClass().getResourceAsStream("/background/pauseBackground.png");

        try{
            image[0] = ImageIO.read(mazebackground);
            image[1]=ImageIO.read(titleBackground);
            image[2]=ImageIO.read(pauseBackground);
        } catch (IOException e){
            e.printStackTrace();
        }
    }


}