package tile;

import main.GamePanel;
import maze.Grid;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class TileManager {
    GamePanel gamePanel;
    Grid grid;
    Tile[] tiles;

    public TileManager(GamePanel gamePanel, Grid grid) {
        this.gamePanel = gamePanel;
        this.grid = grid;
        tiles=new Tile[10];
        getTitleImage();
    }

    public void getTitleImage(){
        try{
            tiles[0]=new Tile();
            tiles[0].image= ImageIO.read(getClass().getResourceAsStream("/tile/floor.png"));

            tiles[1]=new Tile();
            tiles[1].image= ImageIO.read(getClass().getResourceAsStream("/tile/wall.png"));

            tiles[2]=new Tile();
            tiles[2].image= ImageIO.read(getClass().getResourceAsStream("/tile/start.png"));

            tiles[3]=new Tile();
            tiles[3].image= ImageIO.read(getClass().getResourceAsStream("/tile/end.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void update(){

    }

    public void draw(Graphics g){
        // Render grid
        for (int y = 0; y < grid.getHeight(); y++) {
            for (int x = 0; x < grid.getWidth(); x++) {
                int worldX = x * gamePanel.tileSize + gamePanel.offsetX;
                int worldY = y * gamePanel.tileSize + gamePanel.offsetY;
                if (grid.getCell(x, y) == Grid.FLOOR) {
                    g.drawImage(tiles[0].image,worldX,worldY, gamePanel.tileSize, gamePanel.tileSize, null);
                }
                else if (grid.getCell(x, y) == Grid.WALL) {
                    g.drawImage(tiles[1].image,worldX,worldY, gamePanel.tileSize, gamePanel.tileSize, null);
                }
                else if(grid.getCell(x, y) == Grid.START) {
                    g.drawImage(tiles[2].image,worldX,worldY, gamePanel.tileSize, gamePanel.tileSize, null);
                }
                else if (grid.getCell(x, y) == Grid.END) {
                    g.drawImage(tiles[3].image,worldX,worldY, gamePanel.tileSize, gamePanel.tileSize, null);
                }
                else if(grid.getCell(x,y)==Grid.PATH){
                    g.drawImage(tiles[0].image,worldX,worldY, gamePanel.tileSize, gamePanel.tileSize, null);
                }
            }
        }
    }
    public void reset(Grid grid){
        this.grid=grid;
    }
}
