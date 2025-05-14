package main;

import maze.MazeGenerator;

public class test {
    public static void main(String[] args) {
        MazeGenerator mazeGenerator=new MazeGenerator(21,21,0,0,19,19);
        mazeGenerator.generateMaze();
        mazeGenerator.printMazeAsNumbers();
    }
}
