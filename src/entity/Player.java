package entity;

import main.GamePanel;
import input.KeyHandler;
import maze.Grid;
import tile.TileManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static utilz.Constants.playerConstants.*;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;
    Grid grid;
    BufferedImage img;
    BufferedImage[][] animations;
    TileManager tileManager;

    private int animationTick;
    private int animationIndex;
    private int animationSpeed;
    private int playerAction;

    int width;
    int height;
    int tileSize;
    int FPS;

    // PLAYER POSITION
    int targetGridX;
    int targetGridY;
    float worldX; // Pixel position
    float worldY;
    boolean isMoving;
    float moveTimer;
    float moveDuration; // 0.2 seconds to move one grid cell
    float cooldownTimer; // Cooldown timer
    float cooldownDuration; // 0.25 seconds cooldown

    // STATE DATA
    int stage=1;
    int moves;

    public Player(GamePanel gp, KeyHandler keyH, Grid grid) {
        this.gp=gp;
        this.keyH=keyH;
        this.grid=grid;
        setDefaultValues();
    }
    public void setDefaultValues() {
        this.width=gp.maxScreenCol;
        this.height=gp.maxScreenRow;
        this.tileSize=gp.tileSize;
        this.FPS=gp.FPS;

        xPosition=grid.getCharacterStartX();
        yPosition=grid.getCharacterStartY();
        direction="DOWN";
        this.worldX=xPosition*tileSize;
        this.worldY=yPosition*tileSize;
        this.targetGridX=xPosition;
        this.targetGridY=yPosition;
        this.isMoving = false;
        this.moveTimer = 0.0f;
        this.moveDuration = 0.15f; // 0.2 seconds to move one grid cell
        this.cooldownTimer = 0.0f; // Cooldown timer
        this.cooldownDuration = 0.25f;

        this.moves=grid.mazeGenerator.getMovesCount();
        this.tileManager=gp.tileManager;
        importImage();
        loadAnimations();

        animationSpeed=80;
        playerAction=IDLEFRONT;
    }
    public void update() {
        if(gp.gameState==gp.playState){
            isGameOver();
        }
        updateCooldownTimer();
        inputHandler();
        updateMovement();
    }
    public void draw(Graphics g) {
        updateAnimationTick();
        switch (direction) {
            case "UP":
                playerAction=IDLEBACK;
                break;
            case "DOWN":
                playerAction=IDLEFRONT;
                break;
            case "LEFT":
                playerAction=LEFTDASH;
                break;
            case "RIGHT":
                playerAction=RIGHTDASH;
                break;
            default:
                playerAction=IDLEFRONT;
                break;
        }
        int drawX = (int) worldX + gp.offsetX;
        int drawY = (int) worldY + gp.offsetY;
        g.drawImage(animations[playerAction][animationIndex],drawX,drawY, gp.originalTileSize*gp.scale, gp.originalTileSize*gp.scale,null);

    }
    private void importImage() {
        InputStream is = getClass().getResourceAsStream("/entity/character_sprites.png");

        try{
            img = ImageIO.read(is);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    private void updateAnimationTick(){
        animationTick++;
        if(animationTick>=animationSpeed){
            animationTick=0;
            animationIndex++;
            if (animationIndex == 6 && (playerAction == LEFTDASH || playerAction == RIGHTDASH)) {
                direction="DOWN";
                animationIndex = 0;
            }
            else if(animationIndex>=GetSpriteAmount(playerAction)){
                animationIndex=0;
            }
        }
    }
    private void loadAnimations() {
        animations=new BufferedImage[4][12];
        for(int j=0;j<animations.length;j++) {
            for (int i = 0; i < animations[j].length; i++) {
                animations[j][i] = img.getSubimage(i * 32, j*32, 32, 32);
            }
        }
    }

    private void updateCooldownTimer(){
        if (cooldownTimer < cooldownDuration) {
            cooldownTimer += 1.0f / FPS; // Increment using approximate delta time
        }
    }

    private void inputHandler(){
        // Handle input only if not moving and cooldown is over
        if (!isMoving && cooldownTimer >= cooldownDuration) {
            int dx = 0, dy = 0;
            if(keyH.upPressed) {
                dy=-1;
                direction="UP";
            }
            if(keyH.downPressed) {
                dy = 1;
                direction="DOWN";
            }
            if(keyH.leftPressed) {
                dx = -1;
                direction="LEFT";
            }
            if(keyH.rightPressed) {
                dx = 1;
                direction="RIGHT";
            }


            if (dx != 0 || dy != 0) {
                targetGridX = xPosition + dx;
                targetGridY = yPosition + dy;
                if (grid.isValidMove(targetGridX, targetGridY)) {
                    gp.playSFX(1, 0);
                    moves--;
                    System.out.println("Moves Left:" + moves);
                    isMoving = true;
                    moveTimer = 0.0f;
                    cooldownTimer = 0.0f; // Reset cooldown on move
                } else {
                    targetGridX = xPosition;
                    targetGridY = yPosition;
                    playerAction=IDLEFRONT;
                }
                // Reset key flags to require a new press
                keyH.resetKeys();
            }
        }
    }
    private void updateMovement(){
        // Update movement
        if (isMoving) {
            moveTimer += 1.0f / FPS; // Approximate delta time
            float t = moveTimer / moveDuration;
            if (t > 1.0f) t = 1.0f;

            // Interpolate world position
            float startX = xPosition * tileSize;
            float startY = yPosition * tileSize;
            float endX = targetGridX * tileSize;
            float endY = targetGridY * tileSize;
            worldX = lerp(startX, endX, t);
            worldY = lerp(startY, endY, t);

            // Check if movement is complete
            if (t >= 1.0f) {
                xPosition = targetGridX;
                yPosition = targetGridY;
                worldX = endX;
                worldY = endY;
                isMoving = false;
            }
        }
    }
    private float lerp(float a, float b, float t) {
        return a + (b - a) * t;
    }

    private void isGameOver(){
        if(playerAtEndTile()&&stage<=3){
            reset(new Grid(gp.maxScreenCol,gp.maxScreenRow, gp));
            tileManager.reset(this.grid);
            keyH.resetKeys();
            stage++;
        }
        else if(stage==4){
            gp.stopMusic();
            gp.gameState = gp.creditState;
            Grid newGrid = new Grid(gp.maxScreenCol, gp.maxScreenRow, gp); // Create credit grid
            reset(newGrid);
            tileManager.reset(this.grid);
            keyH.resetKeys();
            gp.playMusic(2, -20.0f);
        }
        else if(resetChecker()){
            reset();
        }

    }
    private boolean playerAtEndTile(){
        if(targetGridX == grid.getEndX() && targetGridY == grid.getEndY()){
            return true;
        }
        return false;
    }

    private boolean resetChecker(){
        if(moves>0) return false;
        return true;
    }
    public void reset(){
        if(resetChecker()){
            if(gp.gameState==gp.playState) {
                this.moves = grid.mazeGenerator.getMovesCount();
            }
            this.xPosition=grid.getCharacterStartX();
            this.yPosition=grid.getCharacterStartY();
            this.worldX=xPosition*tileSize;
            this.worldY=yPosition*tileSize;
            this.targetGridX=xPosition;
            this.targetGridY=yPosition;
            this.direction="DOWN";
        }
    }
    public void reset(Grid grid){
        this.grid=grid;
        if(gp.gameState==gp.playState) {
            this.moves = grid.mazeGenerator.getMovesCount();
        }
        xPosition=grid.getCharacterStartX();
        yPosition=grid.getCharacterStartY();
        this.worldX=xPosition*tileSize;
        this.worldY=yPosition*tileSize;
        this.targetGridX=xPosition;
        this.targetGridY=yPosition;
        this.direction="DOWN";
    }

    public int getMoves() {
        return moves;
    }
    public void setMoves(int moves) {
        this.moves = moves;
    }

    public int getStage(){
        return stage;
    }
    public void setStage(int stage) {
        this.stage = stage;
    }


}
