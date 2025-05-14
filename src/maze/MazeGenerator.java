package maze;

import java.util.*;

public class MazeGenerator {
    private final int width, height;
    private final int[][] maze;
    private final boolean[][] visited;
    private final int startX, startY, endX, endY;
    private final Random rand = new Random();
    private int moves;

    // Directions: N, S, E, W
    private static final int[][] DIRECTIONS = {
            {-2, 0}, {2, 0}, {0, 2}, {0, -2}
    };

    // Reduced probability to keep a wall to create more connections
    private static final double WALL_KEEP_CHANCE = 0.05;

    public MazeGenerator(int width, int height, int startX, int startY, int endX, int endY) {
        this.width = (width % 2 == 0) ? width + 1 : width;
        this.height = (height % 2 == 0) ? height + 1 : height;
        this.startX = Math.max(0, Math.min(this.height - 1, startX | 1));
        this.startY = Math.max(0, Math.min(this.width - 1, startY | 1));
        this.endX = Math.max(0, Math.min(this.height - 1, endX | 1));
        this.endY = Math.max(0, Math.min(this.width - 1, endY | 1));
        this.maze = new int[this.height][this.width];
        this.visited = new boolean[this.height][this.width];

        for (int[] row : maze) Arrays.fill(row, 1); // 1 = wall
    }

    public void generateMaze() {
        dfs(startX, startY);
        postProcessMaze(); // Add post-processing to create alternative paths
        maze[startX][startY] = 2; // 2 = start
        maze[endX][endY] = 3;     // 3 = end
        markShortestPath();       // Mark shortest path with 5 and count moves
    }

    private void dfs(int x, int y) {
        visited[x][y] = true;
        maze[x][y] = 0; // 0 = floor

        List<int[]> dirs = new ArrayList<>(Arrays.asList(DIRECTIONS));
        // Sort directions by number of unvisited neighbors (more unvisited = higher priority)
        dirs.sort((d1, d2) -> {
            int nx1 = x + d1[0], ny1 = y + d1[1];
            int nx2 = x + d2[0], ny2 = y + d2[1];
            int count1 = countUnvisitedNeighbors(nx1, ny1);
            int count2 = countUnvisitedNeighbors(nx2, ny2);
            return Integer.compare(count2, count1); // Descending order
        });
        Collections.shuffle(dirs, rand); // Randomize after sorting

        for (int[] dir : dirs) {
            int nx = x + dir[0];
            int ny = y + dir[1];

            if (isInBounds(nx, ny) && !visited[nx][ny]) {
                if (isValidFloor(x, y) && isValidFloor(nx, ny)) {
                    // Lower WALL_KEEP_CHANCE means more walls are removed
                    if (rand.nextDouble() >= WALL_KEEP_CHANCE) {
                        int wallX = x + dir[0] / 2;
                        int wallY = y + dir[1] / 2;
                        if (isInBounds(wallX, wallY)) {
                            maze[wallX][wallY] = 0;
                        }
                        dfs(nx, ny);
                    }
                }
            }
        }
    }

    // Post-process the maze to add alternative paths by removing walls
    private void postProcessMaze() {
        // Iterate over all cells
        for (int x = 1; x < height - 1; x += 2) {
            for (int y = 1; y < width - 1; y += 2) {
                // For each floor cell, consider removing adjacent walls to create new paths
                for (int[] dir : DIRECTIONS) {
                    int wallX = x + dir[0] / 2;
                    int wallY = y + dir[1] / 2;
                    int nx = x + dir[0];
                    int ny = y + dir[1];

                    // Check if the wall exists and the adjacent cell is a floor
                    if (isInBounds(wallX, wallY) && isInBounds(nx, ny) &&
                            maze[wallX][wallY] == 1 && maze[nx][ny] == 0 &&
                            rand.nextDouble() < 0.1) { // 10% chance to remove wall
                        maze[wallX][wallY] = 0; // Remove wall to create new path
                    }
                }
            }
        }
    }

    // Count unvisited neighbors for a cell (for direction prioritization)
    private int countUnvisitedNeighbors(int x, int y) {
        int count = 0;
        for (int[] dir : DIRECTIONS) {
            int nx = x + dir[0];
            int ny = y + dir[1];
            if (isInBounds(nx, ny) && !visited[nx][ny] && isValidFloor(nx, ny)) {
                count++;
            }
        }
        return count;
    }

    private boolean isInBounds(int x, int y) {
        return x >= 0 && x < height && y >= 0 && y < width;
    }

    private boolean isValidFloor(int x, int y) {
        return x % 2 == 1 && y % 2 == 1;
    }

    // Mark the shortest path from start to end with value 5 and count moves
    private void markShortestPath() {
        boolean[][] visitedDFS = new boolean[height][width];
        Map<String, String> parent = new HashMap<>();
        Map<String, Integer> distance = new HashMap<>();
        List<String> shortestPath = new ArrayList<>();
        int[] minDistance = {Integer.MAX_VALUE};

        dfsShortestPath(startX, startY, 0, visitedDFS, parent, distance, shortestPath, minDistance);

        if (!shortestPath.isEmpty()) {
            moves = minDistance[0];
            System.out.println("Number of moves from start to end: " + moves);

            for (String pos : shortestPath) {
                String[] coords = pos.split(",");
                int x = Integer.parseInt(coords[0]);
                int y = Integer.parseInt(coords[1]);
                if (maze[x][y] != 2 && maze[x][y] != 3) {
                    maze[x][y] = 5;
                }
            }
        } else {
            System.out.println("No path found from start to end.");
        }
    }

    private void dfsShortestPath(int x, int y, int dist, boolean[][] visited, Map<String, String> parent,
                                 Map<String, Integer> distance, List<String> shortestPath, int[] minDistance) {
        visited[x][y] = true;
        distance.put(x + "," + y, dist);

        if (x == endX && y == endY) {
            if (dist < minDistance[0]) {
                minDistance[0] = dist;
                shortestPath.clear();
                String curr = x + "," + y;
                while (curr != null) {
                    shortestPath.add(0, curr);
                    curr = parent.get(curr);
                }
            }
        } else {
            for (int[] dir : new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}}) {
                int nx = x + dir[0];
                int ny = y + dir[1];

                if (isInBounds(nx, ny) && !visited[nx][ny] && (maze[nx][ny] == 0 || maze[nx][ny] == 3)) {
                    parent.put(nx + "," + ny, x + "," + y);
                    dfsShortestPath(nx, ny, dist + 1, visited, parent, distance, shortestPath, minDistance);
                }
            }
        }

        visited[x][y] = false;
    }

    public void printMaze() {
        for (int[] row : maze) {
            for (int cell : row) {
                char c = switch (cell) {
                    case 0 -> ' ';
                    case 1 -> '#';
                    case 2 -> 'S';
                    case 3 -> 'E';
                    case 5 -> '*';
                    default -> '?';
                };
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public int[][] getMazeArray() {
        return maze;
    }

    public void printMazeAsNumbers() {
        for (int[] row : maze) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    public int getMovesCount() {
        return moves;
    }
}