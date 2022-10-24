package cellsociety.model.cells;

import java.util.List;

public class RockPaperScissorCell extends Cell {
    private double numNeighbors;
    private double numRockNeighbors;
    private double numPaperNeighbors;
    private double numScissorsNeighbors;

    // Key States
    // 0 = Rock
    // 1 = Paper
    // 2 = Scissors

    /**
     * Constructor for RockPaperScissorsCell class
     * @param state is the state of the cell
     * @param id is the id of the cell
     */
    public RockPaperScissorCell(int state, int id){
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
        numNeighbors = 0;
        numRockNeighbors = 0;
        numPaperNeighbors = 0;
        numScissorsNeighbors = 0;
        for (Cell neighbor : neighbors){
            if (neighbor.getCurrentState() == 0){
                numRockNeighbors++;
            }
            else if (neighbor.getCurrentState() == 1){
                numPaperNeighbors++;
            }
            else if (neighbor.getCurrentState() == 2){
                numScissorsNeighbors++;
            }
            numNeighbors++;
        } // gets number of neighbors of each type
        if (getCurrentState() == 0){ // if cell is a rock
            if (numRockNeighbors/numNeighbors > 0.5){ // if more than 50% of neighbors are rock, then rock stays
                setFutureStateValue(0);
            }
            else if (numPaperNeighbors/numNeighbors > 0.5){ // if more than 50% of neighbors are paper, then paper wins
                setFutureStateValue(1);
            }
            else if (numScissorsNeighbors/numNeighbors > 0.5){ // if more than 50% of neighbors are scissors, then rock wins
                setFutureStateValue(0);
            }
        }
        else if (getCurrentState() == 1){ // if cell is paper
            if (numRockNeighbors/numNeighbors > 0.5){ // if more than 50% of neighbors are rock, then paper wins
                setFutureStateValue(1);
            }
            else if (numPaperNeighbors/numNeighbors > 0.5){ // if more than 50% of neighbors are paper, then paper stays
                setFutureStateValue(1);
            }
            else if (numScissorsNeighbors/numNeighbors > 0.5){ // if more than 50% of neighbors are scissors, then scissors wins
                setFutureStateValue(2);
            }
        }
        else if (getCurrentState() == 2){ // if cell is scissors
            if (numRockNeighbors/numNeighbors > 0.5){ // if more than 50% of neighbors are rock, then rock wins
                setFutureStateValue(0);
            }
            else if (numPaperNeighbors/numNeighbors > 0.5){ // if more than 50% of neighbors are paper, then scissors wins
                setFutureStateValue(2);
            }
            else if (numScissorsNeighbors/numNeighbors > 0.5){ // if more than 50% of neighbors are scissors, then scissors stays
                setFutureStateValue(2);
            }
        }
    }
}
