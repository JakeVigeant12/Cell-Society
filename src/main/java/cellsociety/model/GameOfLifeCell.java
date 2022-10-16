package cellsociety.model;

import java.util.List;

public class GameOfLifeCell extends Cell {

    /**
    * Constructor for GameOfLifeCell class
    * @param state is the state of the cell
    * @param row is the row of the cell
    * @param col is the column of the cell
    */
    public GameOfLifeCell(int state, int row, int col) {
        super(state, row, col);
    }

    /**
    * Method that returns the next state of the cell
    * @param neighbors is the list of neighbors of the cell
    * @return next state of the cell
    */
    @Override
    public void setFutureState(List<Cell> neighbors) {
        int aliveNeighbors = 0;
        for (Cell neighbor : neighbors) { // Count each neighbor that is alive
            if (neighbor.getCurrentState() == 1) {
                aliveNeighbors++;
            }
        }
        if (getCurrentState() == 1) { // If current cell is alive
            if (aliveNeighbors < 2 || aliveNeighbors > 3) { // If alive cell has less than 2 or more than 3 neighbors
                setFutureStateValue(0); // Set current cell to dead
            }
            else {
                setFutureStateValue(1); // Set current cell to alive
            }
        }
        else if (getCurrentState() == 0) { // If current cell is dead
            if (aliveNeighbors == 3) { // If current cell has 3 alive neighbors
                setFutureStateValue(1); // Set current cell to alive
            }
            else {
                setFutureStateValue(0); // Set current cell to dead
            }
        }
    }
}