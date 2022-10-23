package cellsociety.model.cells;

import cellsociety.model.cells.Cell;

import java.util.List;

public class RockPaperScissorsCell extends Cell {
    private double numNeighbors;
    private double numRockNeighbors;
    private double numPaperNeighbors;
    private double numScissorsNeighbors;

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
        } // gets number of neighbors of each type
        if (getCurrentState() == 1){ // if cell is a rock
            if (numRockNeighbors/numNeighbors > 0.5){ // if more than 50% of neighbors are rock, then rock stays
                setFutureStateValue(1);
            }
            else if (numPaperNeighbors/numNeighbors > 0.5){ // if more than 50% of neighbors are paper, then paper wins
                setFutureStateValue(2);
            }
            else if (numScissorsNeighbors/numNeighbors > 0.5){ // if more than 50% of neighbors are scissors, then rock wins
                setFutureStateValue(1);
            }
        }
        else if (getCurrentState() == 2){ // if cell is paper
            if (numRockNeighbors/numNeighbors > 0.5){ // if more than 50% of neighbors are rock, then paper wins
                setFutureStateValue(2);
            }
            else if (numPaperNeighbors/numNeighbors > 0.5){ // if more than 50% of neighbors are paper, then paper stays
                setFutureStateValue(2);
            }
            else if (numScissorsNeighbors/numNeighbors > 0.5){ // if more than 50% of neighbors are scissors, then scissors wins
                setFutureStateValue(3);
            }
        }
        else if (getCurrentState() == 3){ // if cell is scissors
            if (numRockNeighbors/numNeighbors > 0.5){ // if more than 50% of neighbors are rock, then rock wins
                setFutureStateValue(1);
            }
            else if (numPaperNeighbors/numNeighbors > 0.5){ // if more than 50% of neighbors are paper, then scissors wins
                setFutureStateValue(3);
            }
            else if (numScissorsNeighbors/numNeighbors > 0.5){ // if more than 50% of neighbors are scissors, then scissors stays
                setFutureStateValue(3);
            }
        }
    }
}
