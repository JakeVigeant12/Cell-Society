package cellsociety.model.cells;

import cellsociety.model.cells.Cell;

import java.util.List;

public class RockPaperScissorsCell extends Cell {
    private int numNeighbors;
    private int numRockNeighbors;
    private int numPaperNeighbors;
    private int numScissorsNeighbors;

    // Key States
    // 0 = Empty
    // 1 = Rock
    // 2 = Paper
    // 3 = Scissors

    /**
     * Constructor for RockPaperScissorsCell class
     * @param state is the state of the cell
     * @param id is the id of the cell
     */
    public RockPaperScissorsCell(int state, int id){
        super(state, id);
        numNeighbors = 0;
        numRockNeighbors = 0;
        numPaperNeighbors = 0;
        numScissorsNeighbors = 0;
    }

    /**
     * Method that sets the next state of the cell
     * @param neighbors is the list of neighbors of the cell
     * @return next state of the cell
     */
    @Override
    public void setFutureState(List<Cell> neighbors) {
        for (Cell neighbor : neighbors){
            if (neighbor.getCurrentState() == 1){
                numRockNeighbors++;
            }
            else if (neighbor.getCurrentState() == 2){
                numPaperNeighbors++;
            }
            else if (neighbor.getCurrentState() == 3){
                numScissorsNeighbors++;
            }
            numNeighbors++;
        }
        if (getCurrentState() == 1){
            if (numRockNeighbors/numNeighbors > 0.5){
                setFutureStateValue(1);
            }
            else if (numPaperNeighbors/numNeighbors > 0.5){
                setFutureStateValue(2);
            }
            else if (numScissorsNeighbors/numNeighbors > 0.5){
                setFutureStateValue(3);
            }
        }
        else if (getCurrentState() == 2){
            if (numRockNeighbors/numNeighbors > 0.5){
                setFutureStateValue(1);
            }
            else if (numPaperNeighbors/numNeighbors > 0.5){
                setFutureStateValue(2);
            }
            else if (numScissorsNeighbors/numNeighbors > 0.5){
                setFutureStateValue(3);
            }
        }
        else if (getCurrentState() == 3){
            if (numRockNeighbors/numNeighbors > 0.5){
                setFutureStateValue(1);
            }
            else if (numPaperNeighbors/numNeighbors > 0.5){
                setFutureStateValue(2);
            }
            else if (numScissorsNeighbors/numNeighbors > 0.5){
                setFutureStateValue(3);
            }
        }
    }
}
