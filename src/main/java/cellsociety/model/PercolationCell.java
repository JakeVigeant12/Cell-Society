package cellsociety.model;

import java.util.List;

public class PercolationCell extends Cell {

    // Key States
    // 0 = empty
    // 1 = Percolated
    // 2 = Blocked

    /**
     * Constructor for PercolationCell class
     * @param state is the state of the cell
     * @param id is the id of the cell
     */
    public PercolationCell(int state, int id){
        super(state, id);
    }

    /**
     * Method that sets the next state of the cell
     * @param neighbors is the list of neighbors of the cell
     * @return next state of the cell
     */
    @Override
    public void setFutureState(List<Cell> neighbors) {
        if (getCurrentState() == 0){
            for (Cell neighbor : neighbors) {
                if (neighbor.getCurrentState() == 1){
                    setFutureStateValue(1);
                }
            }
        }
        else if (getCurrentState() == 1){
            setFutureStateValue(1);
        }
        else if (getCurrentState() == 2){
            setFutureStateValue(2);
        }
    }

}
