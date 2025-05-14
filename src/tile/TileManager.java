package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class TileManager {
    GamePanel gamePanel;
    Tile[] tiles;

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tiles=new Tile[10];
        getTitleImage();
    }

    public void getTitleImage(){
        try{
            tiles[0]=new Tile();
            tiles[0].image= ImageIO.read(getClass().getResourceAsStream(""));
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
