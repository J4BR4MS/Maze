/**
 * Solves the given maze using DFS or BFS
 * @author Ms. Namasivayam
 * @version 03/10/2023
 */
import java.util.Stack;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
public class MazeSolver {
    private Maze maze;

    public MazeSolver() {
        this.maze = null;
    }

    public MazeSolver(Maze maze) {
        this.maze = maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    /**
     * Starting from the end cell, backtracks through
     * the parents to determine the solution
     * @return An arraylist of MazeCells to visit in order
     */
    public ArrayList<MazeCell> getSolution() {

        //Since we have to go from the end to start, a stack is appropriate to reverse the order
        Stack<MazeCell> s  = new Stack<MazeCell>();
        ArrayList<MazeCell> arr = new ArrayList<MazeCell>();

        //create a cell to represent where we are on the board
        MazeCell current = maze.getEndCell();
        s.add(current);

        //find our way back to start by looking at parent cells
        while(current.getParent() != null){
            s.add(current.getParent());
            current = current.getParent();
        }

        //reverse order
        int temp = s.size();
        for(int i = 0; i < temp; i++){
            arr.add(s.pop());
        }

        return arr;
    }

    /**
     * Performs a Depth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeDFS() {
        //create a stack to store cells we are going to visit
        Stack<MazeCell> toVisit = new Stack<MazeCell>();
        //create a cell that will represent our current place on the board
        MazeCell currentCell = maze.getStartCell();
        //use this cell as the one we will look at when deciding where to go next
        MazeCell temp;

        //until we reach the end
        while(currentCell != maze.getEndCell()){

            //check NORTH, EAST, SOUTH and WEST
            //make sure the cell we are looking at is valid,
            //then classify it as a parent (for getSolution) and add it to our to visit stack.
            if(maze.isValidCell(currentCell.getRow() - 1, currentCell.getCol())){
                temp = maze.getCell(currentCell.getRow() - 1, currentCell.getCol());
                temp.setParent(currentCell);
                currentCell.setExplored(true);
                toVisit.push(temp);
            }
            if(maze.isValidCell(currentCell.getRow(), currentCell.getCol() + 1)){
                temp = maze.getCell(currentCell.getRow(), currentCell.getCol() + 1);
                temp.setParent(currentCell);
                currentCell.setExplored(true);
                toVisit.push(temp);
            }
            if(maze.isValidCell(currentCell.getRow() + 1, currentCell.getCol())){
                temp = maze.getCell(currentCell.getRow() + 1, currentCell.getCol());
                temp.setParent(currentCell);
                currentCell.setExplored(true);
                toVisit.push(temp);
            }
            if(maze.isValidCell(currentCell.getRow(), currentCell.getCol() - 1)){
                temp = maze.getCell(currentCell.getRow(), currentCell.getCol() - 1);
                temp.setParent(currentCell);
                currentCell.setExplored(true);
                toVisit.push(temp);
            }

            //travel to the most recently explored cell in the to visit stack
            currentCell = toVisit.pop();
        }

        //because we've updated the parent status of every square we've been to, we can simply run getSolution
        return getSolution();
    }

    /**
     * Performs a Breadth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeBFS() {
        //create a QUEUE (BFS looks for all possibilities) to store cells we are going to visit
        Queue<MazeCell> toVisit = new LinkedList<MazeCell>();
        //create a cell that will represent our current place on the board
        MazeCell currentCell = maze.getStartCell();
        //use this cell as the one we will look at when deciding where to go next
        MazeCell temp;

        //until we reach the end
        while(currentCell != maze.getEndCell()){

            //check NORTH, EAST, SOUTH and WEST
            //make sure the cell we are looking at is valid,
            //then classify it as a parent (for getSolution) and add it to our to visit queue
            if(maze.isValidCell(currentCell.getRow() - 1, currentCell.getCol())){
                temp = maze.getCell(currentCell.getRow() - 1, currentCell.getCol());
                temp.setParent(currentCell);
                currentCell.setExplored(true);
                toVisit.add(temp);
            }
            if(maze.isValidCell(currentCell.getRow(), currentCell.getCol() + 1)){
                temp = maze.getCell(currentCell.getRow(), currentCell.getCol() + 1);
                temp.setParent(currentCell);
                currentCell.setExplored(true);
                toVisit.add(temp);
            }
            if(maze.isValidCell(currentCell.getRow() + 1, currentCell.getCol())){
                temp = maze.getCell(currentCell.getRow() + 1, currentCell.getCol());
                temp.setParent(currentCell);
                currentCell.setExplored(true);
                toVisit.add(temp);
            }
            if(maze.isValidCell(currentCell.getRow(), currentCell.getCol() - 1)){
                temp = maze.getCell(currentCell.getRow(), currentCell.getCol() - 1);
                temp.setParent(currentCell);
                currentCell.setExplored(true);
                toVisit.add(temp);
            }

            //travel to the most recently explored cell in the to visit queue
            currentCell = toVisit.remove();
        }

        //because we've updated the parent status of every square we've been to, we can simply run getSolution
        return getSolution();
    }

    public static void main(String[] args) {
        // Create the Maze to be solved
        Maze maze = new Maze("Resources/maze3.txt");

        // Create the MazeSolver object and give it the maze
        MazeSolver ms = new MazeSolver();
        ms.setMaze(maze);

        // Solve the maze using DFS and print the solution
        ArrayList<MazeCell> sol = ms.solveMazeDFS();
        maze.printSolution(sol);

        // Reset the maze
        maze.reset();

        // Solve the maze using BFS and print the solution
        sol = ms.solveMazeBFS();
        maze.printSolution(sol);
    }
}
