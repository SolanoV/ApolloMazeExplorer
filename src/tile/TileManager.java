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
        tiles = new Tile[70];
        getTitleImage();
    }

    public void getTitleImage() {
        try {
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/tile/floor.png"));
            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/tile/start.png"));
            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/tile/end.png"));
            tiles[3] = new Tile();
            tiles[3].image = ImageIO.read(getClass().getResourceAsStream("/tile/maze_BottoEnd.png"));
            tiles[4] = new Tile();
            tiles[4].image = ImageIO.read(getClass().getResourceAsStream("/tile/maze_topLeftCorner.png")); // Actual bottom-left sprite
            tiles[5] = new Tile();
            tiles[5].image = ImageIO.read(getClass().getResourceAsStream("/tile/maze_BottomLeftCornerConnector.png"));
            tiles[6] = new Tile();
            tiles[6].image = ImageIO.read(getClass().getResourceAsStream("/tile/maze_BottomLeftCorner.png")); // Actual bottom-right sprite
            System.out.println("Loaded bottomRightCorner (index 6): " + (tiles[6].image != null ? "Success" : "Failed"));
            tiles[7] = new Tile();
            tiles[7].image = ImageIO.read(getClass().getResourceAsStream("/tile/maze_BottomRightCornerConnector.png"));
            tiles[8] = new Tile();
            tiles[8].image = ImageIO.read(getClass().getResourceAsStream("/tile/maze_edgeBottom.png"));
            tiles[9] = new Tile();
            tiles[9].image = ImageIO.read(getClass().getResourceAsStream("/tile/maze_edgeLeft.png"));
            tiles[10] = new Tile();
            tiles[10].image = ImageIO.read(getClass().getResourceAsStream("/tile/maze_edgeRight.png"));
            tiles[11] = new Tile();
            tiles[11].image = ImageIO.read(getClass().getResourceAsStream("/tile/maze_edgeTop.png"));
            tiles[12] = new Tile();
            tiles[12].image = ImageIO.read(getClass().getResourceAsStream("/tile/maze_horizontalConnector.png"));
            tiles[13] = new Tile();
            tiles[13].image = ImageIO.read(getClass().getResourceAsStream("/tile/maze_innerNoConnector.png"));
            tiles[14] = new Tile();
            tiles[14].image = ImageIO.read(getClass().getResourceAsStream("/tile/maze_innerWithConnector.png"));
            tiles[15] = new Tile();
            tiles[15].image = ImageIO.read(getClass().getResourceAsStream("/tile/maze_leftEnd.png"));
            tiles[16] = new Tile();
            tiles[16].image = ImageIO.read(getClass().getResourceAsStream("/tile/maze_rightEnd.png"));
            tiles[17] = new Tile();
            tiles[17].image = ImageIO.read(getClass().getResourceAsStream("/tile/maze_single.png"));
            tiles[18] = new Tile();
            tiles[18].image = ImageIO.read(getClass().getResourceAsStream("/tile/maze_topEnd.png"));
            tiles[19] = new Tile();
            tiles[19].image = ImageIO.read(getClass().getResourceAsStream("/tile/maze_BottomRightCorner.png")); // Actual top-left sprite
            System.out.println("Loaded topLeftCorner (index 19): " + (tiles[19].image != null ? "Success" : "Failed"));
            tiles[20] = new Tile();
            tiles[20].image = ImageIO.read(getClass().getResourceAsStream("/tile/maze_topLeftCornerConnector.png"));
            System.out.println("Loaded topLeftCornerConnector (index 20): " + (tiles[20].image != null ? "Success" : "Failed"));
            tiles[21] = new Tile();
            tiles[21].image = ImageIO.read(getClass().getResourceAsStream("/tile/maze_TopRightCorner.png")); // Hypothesized top-right sprite (capitalized); confirm this file exists and is correct
            System.out.println("Loaded topRightCorner (index 21): " + (tiles[21].image != null ? "Success" : "Failed"));
            tiles[22] = new Tile();
            tiles[22].image = ImageIO.read(getClass().getResourceAsStream("/tile/maze_TopRightCornerConnector.png")); // Hypothesized top-right connector (capitalized); confirm this file exists and is correct
            System.out.println("Loaded topRightCornerConnector (index 22): " + (tiles[22].image != null ? "Success" : "Failed"));
            tiles[23] = new Tile();
            tiles[23].image = ImageIO.read(getClass().getResourceAsStream("/tile/maze_verticalConnector.png"));
            tiles[24] = new Tile();
            tiles[24].image = ImageIO.read(getClass().getResourceAsStream("/tile/maze_TconnectorBottom.png"));
            tiles[25] = new Tile();
            tiles[25].image = ImageIO.read(getClass().getResourceAsStream("/tile/maze_TconnectorLeft.png"));
            tiles[26] = new Tile();
            tiles[26].image = ImageIO.read(getClass().getResourceAsStream("/tile/maze_TconnectorRight.png"));
            tiles[27] = new Tile();
            tiles[27].image = ImageIO.read(getClass().getResourceAsStream("/tile/maze_TconnectorTop.png"));
            tiles[28] = new Tile();
            tiles[28].image= ImageIO.read(getClass().getResourceAsStream("/tile/plains_bottomLeftCorner.png"));
            tiles[29] = new Tile();
            tiles[29].image= ImageIO.read(getClass().getResourceAsStream("/tile/plains_bottomRightCorner.png"));
            tiles[30] = new Tile();
            tiles[30].image= ImageIO.read(getClass().getResourceAsStream("/tile/plains_end.png"));
            tiles[31] = new Tile();
            tiles[31].image= ImageIO.read(getClass().getResourceAsStream("/tile/plains_grass.png"));
            tiles[32] = new Tile();
            tiles[32].image= ImageIO.read(getClass().getResourceAsStream("/tile/plains_grass2.png"));
            tiles[33] = new Tile();
            tiles[33].image= ImageIO.read(getClass().getResourceAsStream("/tile/plains_path.png"));
            tiles[34] = new Tile();
            tiles[34].image= ImageIO.read(getClass().getResourceAsStream("/tile/plains_pathBottom.png"));
            tiles[35] = new Tile();
            tiles[35].image= ImageIO.read(getClass().getResourceAsStream("/tile/plains_pathEndBottom.png"));
            tiles[36] = new Tile();
            tiles[36].image= ImageIO.read(getClass().getResourceAsStream("/tile/plains_pathEndLeft.png"));
            tiles[37] = new Tile();
            tiles[37].image= ImageIO.read(getClass().getResourceAsStream("/tile/plains_pathEndRight.png"));
            tiles[38] = new Tile();
            tiles[38].image= ImageIO.read(getClass().getResourceAsStream("/tile/plains_pathEndSingle.png"));
            tiles[39] = new Tile();
            tiles[39].image= ImageIO.read(getClass().getResourceAsStream("/tile/plains_pathEndTop.png"));
            tiles[40] = new Tile();
            tiles[40].image= ImageIO.read(getClass().getResourceAsStream("/tile/plains_pathLeft.png"));
            tiles[41] = new Tile();
            tiles[41].image= ImageIO.read(getClass().getResourceAsStream("/tile/plains_pathRight.png"));
            tiles[42] = new Tile();
            tiles[42].image= ImageIO.read(getClass().getResourceAsStream("/tile/plains_pathTop.png"));
            tiles[43] = new Tile();
            tiles[43].image= ImageIO.read(getClass().getResourceAsStream("/tile/plains_pathTopLeftCorner.png"));
            tiles[44] = new Tile();
            tiles[44].image= ImageIO.read(getClass().getResourceAsStream("/tile/plains_pathTopRightCorner.png"));
            tiles[45] = new Tile();
            tiles[45].image= ImageIO.read(getClass().getResourceAsStream("/tile/plains_start.png"));
            tiles[46] = new Tile();
            tiles[46].image= ImageIO.read(getClass().getResourceAsStream("/tile/plains_tree.png"));
            tiles[47] = new Tile();
            tiles[47].image= ImageIO.read(getClass().getResourceAsStream("/tile/plains_wallBottomLeftCorner.png"));
            tiles[48] = new Tile();
            tiles[48].image= ImageIO.read(getClass().getResourceAsStream("/tile/plains_wallBottomRightCorner.png"));
            tiles[49] = new Tile();
            tiles[49].image= ImageIO.read(getClass().getResourceAsStream("/tile/plains_wallEdgeBottom.png"));
            tiles[50] = new Tile();
            tiles[50].image= ImageIO.read(getClass().getResourceAsStream("/tile/plains_wallEdgeLeft.png"));
            tiles[51] = new Tile();
            tiles[51].image= ImageIO.read(getClass().getResourceAsStream("/tile/plains_wallEdgeRight.png"));
            tiles[52] = new Tile();
            tiles[52].image= ImageIO.read(getClass().getResourceAsStream("/tile/plains_wallEdgeTop.png"));
            tiles[53] = new Tile();
            tiles[53].image= ImageIO.read(getClass().getResourceAsStream("/tile/plains_wallTopLeftCorner.png"));
            tiles[54] = new Tile();
            tiles[54].image= ImageIO.read(getClass().getResourceAsStream("/tile/plains_wallTopRightCorner.png"));
            tiles[55] = new Tile();
            tiles[55].image= ImageIO.read(getClass().getResourceAsStream("/tile/plains_pathVertical.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int getWallTileIndex(int x, int y) {
        if (grid.getCell(x, y) != Grid.WALL) {
            return -1; // Not a wall
        }

        // Compute bitmask for 8 neighbors
        int bitmask = 0;
        boolean n = y > 0 && grid.getCell(x, y - 1) == Grid.WALL;
        boolean e = x < grid.getWidth() - 1 && grid.getCell(x + 1, y) == Grid.WALL;
        boolean s = y < grid.getHeight() - 1 && grid.getCell(x, y + 1) == Grid.WALL;
        boolean w = x > 0 && grid.getCell(x - 1, y) == Grid.WALL;
        boolean ne = x < grid.getWidth() - 1 && y > 0 && grid.getCell(x + 1, y - 1) == Grid.WALL;
        boolean se = x < grid.getWidth() - 1 && y < grid.getHeight() - 1 && grid.getCell(x + 1, y + 1) == Grid.WALL;
        boolean sw = x > 0 && y < grid.getHeight() - 1 && grid.getCell(x - 1, y + 1) == Grid.WALL;
        boolean nw = x > 0 && y > 0 && grid.getCell(x - 1, y - 1) == Grid.WALL;

        if (n) bitmask |= 1;   // North
        if (e) bitmask |= 2;   // East
        if (s) bitmask |= 4;   // South
        if (w) bitmask |= 8;   // West
        if (ne) bitmask |= 16; // Northeast
        if (se) bitmask |= 32; // Southeast
        if (sw) bitmask |= 64; // Southwest
        if (nw) bitmask |= 128;// Northwest

        // Boundary checks for edge tiles
        boolean isTopEdge = (y == 0);
        boolean isBottomEdge = (y == grid.getHeight() - 1);
        boolean isLeftEdge = (x == 0);
        boolean isRightEdge = (x == grid.getWidth() - 1);

        // Check for corner edges first
        // Top-left corner
        if (isTopEdge && isLeftEdge) {
            if (s && e) {
                return 20; // topLeftCornerConnector
            }
            return 19; // topLeftCorner
        }
        // Top-right corner
        if (isTopEdge && isRightEdge) {
            if (s && w) {
                return 22; // topRightCornerConnector
            }
            return 21; // topRightCorner
        }
        // Bottom-left corner
        if (isBottomEdge && isLeftEdge) {
            if (n && e) {
                return 5; // bottomLeftCornerConnector
            }
            return 4; // bottomLeftCorner
        }
        // Bottom-right corner
        if (isBottomEdge && isRightEdge) {
            if (n && w) {
                return 7; // bottomRightCornerConnector
            }
            return 6; // bottomRightCorner
        }

        // Edge tiles (not at corners)
        if (isTopEdge && !n) {
            if (e && w) return 12; // horizontalConnector
            if (e) return 15;      // leftEnd
            if (w) return 16;      // rightEnd
            return 11;             // edgeTop
        }
        if (isBottomEdge && !s) {
            if (e && w) return 12; // horizontalConnector
            if (e) return 15;      // leftEnd
            if (w) return 16;      // rightEnd
            return 8;              // edgeBottom
        }
        if (isLeftEdge && !w) {
            if (n && s) return 23; // verticalConnector
            if (n) return 3;       // bottomend
            if (s) return 18;      // topEnd
            return 9;              // edgeLeft
        }
        if (isRightEdge && !e) {
            if (n && s) return 23; // verticalConnector
            if (n) return 3;       // bottomend
            if (s) return 18;      // topEnd
            return 10;             // edgeRight
        }

        // Simplified mapping based on cardinal directions
        int cardinalBitmask = bitmask & 15; // Mask to N, E, S, W
        switch (cardinalBitmask) {
            // Single tile (no neighbors)
            case 0:
                return 17; // single

            // Ends (one neighbor)
            case 1: // N
                return 3;  // bottomend
            case 2: // E
                return 15; // leftEnd
            case 4: // S
                return 18; // topEnd
            case 8: // W
                return 16; // rightEnd

            // Connectors (two opposite neighbors)
            case 5: // N+S
                return 23; // verticalConnector
            case 10: // E+W
                return 12; // horizontalConnector

            // Outer corners (two adjacent neighbors)
            case 3: // N+E
                if (!isTopEdge && !isLeftEdge && ne) {
                    return 19; // topLeftCorner (inner, wall to NE)
                }
                return 6; // bottomRightCorner (default for bitmask 3)
            case 6: // E+S
                if (!isTopEdge && !isRightEdge && se) {
                    return 21; // topRightCorner (inner, wall to SE)
                }
                return 4; // bottomLeftCorner (default for bitmask 6)
            case 12: // S+W
                if (sw) {
                    return 21; // topRightCorner (wall to SW)
                }
                return 4; // bottomLeftCorner
            case 9: // N+W
                if (nw) return 6; // bottomRightCorner (wall to NW)
                return 19; // topLeftCorner

            // T-junctions (three neighbors)
            case 7: // N+E+S
                return 25; // TconnectorLeft
            case 11: // N+E+W
                return 27; // TconnectorTop
            case 14: // E+S+W
                return 24; // TconnectorBottom
            case 13: // N+S+W
                return 26; // TconnectorRight

            // Surrounded (four neighbors)
            case 15: // N+E+S+W
                if (ne && se && sw && nw) {
                    return 13; // innerNoConnector
                }
                return 14; // innerWithConnector
        }

        // Default: Fallback for unhandled cases
        System.out.println("Unhandled bitmask " + bitmask + " at (" + x + "," + y + "), using single");
        return 17; // single
    }

    public void draw(Graphics g) {
        // Render tiles for MAZE
        if(gamePanel.gameState==gamePanel.playState||gamePanel.gameState==gamePanel.pauseState) {
            for (int y = 0; y < grid.getHeight(); y++) {
                for (int x = 0; x < grid.getWidth(); x++) {
                    int worldX = x * gamePanel.tileSize + gamePanel.offsetX;
                    int worldY = y * gamePanel.tileSize + gamePanel.offsetY;
                    int cell = grid.getCell(x, y);
                    int tileIndex = -1;

                    if (cell == Grid.FLOOR || cell == Grid.PATH) {
                        tileIndex = 0; // floor.png
                    } else if (cell == Grid.START) {
                        tileIndex = 1; // start.png
                    } else if (cell == Grid.END) {
                        tileIndex = 2; // end.png
                    } else if (cell == Grid.WALL) {
                        tileIndex = getWallTileIndex(x, y); // Autotile walls
                    }

                    if (tileIndex >= 0 && tiles[tileIndex] != null && tiles[tileIndex].image != null) {
                        g.drawImage(tiles[tileIndex].image, worldX, worldY, gamePanel.tileSize, gamePanel.tileSize, null);
                    } else {
                        System.err.println("Invalid tile at (" + x + "," + y + "): index=" + tileIndex);
                    }
                }
            }


            //PLAINS
        } else if(gamePanel.gameState==gamePanel.creditState) {
            for (int y = 0; y < grid.getHeight(); y++) {
                for (int x = 0; x < grid.getWidth(); x++) {
                    int worldX = x * gamePanel.tileSize + gamePanel.offsetX;
                    int worldY = y * gamePanel.tileSize + gamePanel.offsetY;
                    int cell = grid.getCell(x, y);
                    int tileIndex = -1;

                    if (cell == Grid.FLOOR ) {
                        tileIndex = 31; //
                    } else if (cell == Grid.START) {
                        tileIndex = 45; //
                    } else if (cell == Grid.END) {
                        tileIndex = 30; // end.png
                    } else if (cell == 10) {
                        tileIndex = 33;
                    } else if (cell == 11) {
                        tileIndex = 55;
                    } else if (cell == 12) {
                        tileIndex = 35;
                    } else if (cell == 13) {
                        tileIndex = 39;
                    } else if (cell == 14) {
                        tileIndex = 52;
                    } else if (cell == 15) {
                        tileIndex = 50;
                    } else if (cell == 16) {
                        tileIndex = 51;
                    }else if (cell == 17) {
                        tileIndex = 49;
                    }


                    if (tileIndex >= 0 && tiles[tileIndex] != null && tiles[tileIndex].image != null) {
                        g.drawImage(tiles[tileIndex].image, worldX, worldY, gamePanel.tileSize, gamePanel.tileSize, null);
                    } else {
                        System.err.println("Invalid tile at (" + x + "," + y + "): index=" + tileIndex);
                    }
                }
            }

        }
    }

    public void update() {
    }

    public void reset(Grid grid) {
        this.grid = grid;
    }
}