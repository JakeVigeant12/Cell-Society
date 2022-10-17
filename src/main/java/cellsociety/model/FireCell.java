package cellsociety.model;

import java.util.List;

public class FireCell extends Cell {
    private static final int BURNING_TIME = 3;
    private int turns;
    private double myProbCatch;

    /**
     * Constructor for FireCell class
     * @param state is the state of the cell
     * @param id is the id of the cell
     */
    public FireCell(int state, int id, double probCatch){
        super(state, id);
        turns = 0;

        if (probCatch > 1){
            throw new IllegalArgumentException("Probability of catching fire must be between 0 and 1");
        } else {
            myProbCatch = probCatch;
        }
    }

    /**
     * Method that returns the future state of the cell
     * @param neighbors is the list of neighbors of the cell
     * @return next state of the cell
     */
    @Override
    public void setFutureState(List<Cell> neighbors) {
        List<Integer> states = getNeighborStates(neighbors);
        if (getCurrentState() == 1 || states.contains(2)) { // If current cell is a tree or has a burning neighbor
            if (Math.random() < myProbCatch) { // If random number is less than probability of catching fire
                setFutureStateValue(2); // Set current cell to burning
            }
            else {
                setFutureStateValue(1); // Set current cell to tree
            }
        }
        else if (getCurrentState() == 2) { // If current cell is burning
            if (turns == BURNING_TIME) { // If current cell has been burning for BURNING_TIME turns
                setFutureStateValue(0); // Set current cell to empty
            }
            else {
                turns++;
                setFutureStateValue(2); // Keep current cell burning
            }
        }
    }
}
