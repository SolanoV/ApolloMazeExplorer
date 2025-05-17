package maze;

import main.GamePanel;

import java.util.Random;

public class Grid {
    public static final int FLOOR = 0;
    public static final int WALL = 1;
    public static final int START = 2;
    public static final int END = 3;
    public static final int PATH = 5;

    private int width; // Matches maxScreenCol
    private int height; // Matches maxScreenRow
    private int[][] grid;

    private Random rand = new Random();
    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private int characterStartX;
    private int characterStartY;
    private int characterEndX;
    private int characterEndY;

    public MazeGenerator mazeGenerator;
    GamePanel gamePanel;

    public Grid(int width, int height, GamePanel gamePanel) {
        // Ensure odd dimensions for proper maze generation
//        this.width = 17;
//        this.height = 17;
        this.gamePanel=gamePanel;
        this.width = (width % 2 == 0) ? width + 1 : width;
        this.height = (height % 2 == 0) ? height + 1 : height;
        // Place start and end near opposite edges, mirrored
        if(gamePanel.gameState==gamePanel.creditState){
            this.grid=getCreditGrid();
            getCharacterStartFromMaze();
            getCharacterEndFromMaze();
        }
        else{
            this.grid = new int[this.height][this.width];
            placeStartAndEnd();
            initializeGrid();
            mazeGenerator.printMazeAsNumbers();
        }

    }
    private int[][] getCreditGrid(){
        int[][] newGrid=new int[][]{
                {14, 14, 14, 14, 14, 14, 14, 14, 3, 14, 14, 14, 14, 14, 14, 14, 14},
                {14, 0, 0, 0, 0, 0, 0, 0, 13, 0, 0, 0, 0, 0, 0, 0, 14},
                {14, 0, 0, 0, 0, 0, 0, 0, 11, 0, 0, 0, 0, 0, 0, 0, 14},
                {14, 0, 0, 0, 0, 0, 0, 0, 11, 0, 0, 0, 0, 0, 0, 0, 14},
                {14, 0, 0, 0, 0, 0, 0, 0, 11, 0, 0, 0, 0, 0, 0, 0, 14},
                {14, 0, 14, 0, 0, 0, 0, 10, 10, 10, 0, 0, 0, 0, 0, 0, 14},
                {14, 14, 14, 0, 0, 0, 0, 10, 10, 10, 0, 0, 0, 0, 0, 0, 14},
                {14, 0, 14, 0, 0, 0, 0, 10, 10, 10, 0, 0, 0, 0, 0, 0, 14},
                {14, 0, 14, 0, 0, 0, 0, 10, 10, 10, 0, 0, 0, 0, 0, 0, 14},
                {14, 0, 0, 0, 0, 0, 0, 10, 10, 10, 0, 0, 0, 0, 0, 0, 14},
                {14, 14, 0, 0, 0, 0, 0, 10, 10, 10, 0, 0, 14, 14, 14, 0, 14},
                {14, 0, 0, 0, 0, 0, 0, 10, 10, 10, 0, 0, 0, 0, 14, 14, 14},
                {14, 14, 0, 0, 0, 0, 0, 0, 11, 0, 0, 0, 14, 0, 14, 0, 14},
                {14, 14, 0, 0, 0, 0, 0, 0, 11, 0, 0, 0, 0, 0, 0, 0, 14},
                {14, 14, 0, 0, 0, 0, 0, 0, 11, 0, 0, 0, 0, 0, 0, 0, 14},
                {14, 14, 0, 0, 0, 0, 0, 0, 12, 0, 0, 0, 0, 0, 0, 0, 14},
                {14, 14, 14, 14, 14, 14, 14, 14, 2, 14, 14, 14, 14, 14, 14, 14, 14}
        };
        return newGrid;
    }

    private void placeStartAndEnd() {
        // Randomly choose one of two mirrored configurations:
        // 1. Start in top-left, end in bottom-right
        // 2. Start in bottom-right, end in top-left
        boolean topLeftStart = rand.nextBoolean();

        if (topLeftStart) {
            // Start near top-left (odd coordinates, close to edge)
            startX = 1; // First odd column
            startY = 1; // First odd row
            // End near bottom-right
            endX = width - 2; // Last odd column
            endY = height - 2; // Last odd row
        } else {
            // Start near bottom-right
            startX = width - 2;
            startY = height - 2;
            // End near top-left
            endX = 1;
            endY = 1;
        }

        // Ensure start and end are on valid floor cells (odd coordinates)
        if (startX % 2 == 0) startX--;
        if (startY % 2 == 0) startY--;
        if (endX % 2 == 0) endX--;
        if (endY % 2 == 0) endY--;
    }

    private void initializeGrid() {
        // Initialize maze with MazeGenerator
        mazeGenerator = new MazeGenerator(width, height, startX, startY, endX, endY);
        mazeGenerator.generateMaze();
        // mazeGenerator.printMazeAsNumbers(); // Optional: for debugging
        int[][] initialGrid = mazeGenerator.getMazeArray();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grid[y][x] = initialGrid[y][x];
            }
        }
        getCharacterStartFromMaze();
    }

    public void getCharacterStartFromMaze() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (grid[y][x] == START) {
                    characterStartX = x;
                    characterStartY = y;
                }
            }
        }
    }
    public void getCharacterEndFromMaze() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (grid[y][x] == END) {
                    characterEndX = x;
                    characterEndY = y;
                }
            }
        }
    }
    public int getCharacterEndX() {
        return characterEndX;
    }
    public int getCharacterEndY() {
        return characterEndY;
    }

    public int getCharacterStartX() {
        return characterStartX;
    }

    public int getCharacterStartY() {
        return characterStartY;
    }

    public boolean isValidMove(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return false;
        }
        return grid[y][x] == FLOOR || grid[y][x] == START || grid[y][x] == END || grid[y][x] == PATH || grid[y][x]==10||grid[y][x]==11 ||grid[y][x]==12||grid[y][x]==13;
    }

    public int getCell(int x, int y) {
        return grid[y][x];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    public int getStartX(){
        return startX;
    }
    public int getStartY(){
        return startY;
    }
    public int getEndX(){
        return endX;
    }
    public int getEndY(){
        return endY;
    }
}