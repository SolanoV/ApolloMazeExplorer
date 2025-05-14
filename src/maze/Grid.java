package maze;

import java.util.Random;

public class Grid{
    public static final int FLOOR = 0;
    public static final int WALL = 1;
    public static final int START = 2;
    public static final int END = 3;
    public static final int PATH = 5;

    private int width; // Matches maxScreenCol
    private int height; // Matches maxScreenRow
    private final int[][] grid;

    private Random rand = new Random();
    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private int characterStartX;
    private int characterStartY;

    public MazeGenerator mazeGenerator;

    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
        grid = new int[height][width];
        startX = (rand.nextInt((width - 1) / 2) * 2 + 1);
        startY = (rand.nextInt((height - 1) / 2) * 2 + 1);

        final int minDistance = 30;
        int attempts = 0;
        final int maxAttempts = 100;

        // Generate end position, ensuring it's far enough from start
        do {
            endX = (rand.nextInt((width - 1) / 2) * 2 + 1); // Odd x in [1, width-1]
            endY = (rand.nextInt((height - 1) / 2) * 2 + 1); // Odd y in [1, height-1]
            attempts++;
        } while (calculateManhattanDistance(startX, startY, endX, endY) < minDistance && attempts < maxAttempts);
        if (attempts >= maxAttempts) {
            // Fallback: Place end in opposite corner or far region
            endX = (startX < width / 2) ? (width - 2) : 1;
            endY = (startY < height / 2) ? (height - 2) : 1;
        }
        initializeGrid();

    }
    private int calculateManhattanDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x2 - x1) + Math.abs(y2 - y1);
    }

    private void initializeGrid() {
        // Sample level: 0 = FLOOR, 1 = WALL
        mazeGenerator= new MazeGenerator(width, height, startX, startY, endX, endY);
        mazeGenerator.generateMaze();
        mazeGenerator.printMazeAsNumbers();
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
                if(grid[y][x]==START) {
                    characterStartX=x;
                    characterStartY=y;
                }
            }
        }
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
        else if(grid[y][x] == START) {
            return grid[y][x] == START;
        }
        else if(grid[y][x] == END) {
            return grid[y][x] == END;
        }
        else if(grid[y][x] == PATH) {
            return grid[y][x] == PATH;
        }
        return grid[y][x] == FLOOR;
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

}