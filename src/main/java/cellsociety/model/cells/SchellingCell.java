package cellsociety.model.cells;

import cellsociety.model.cells.Cell;

import java.util.List;

public class SchellingCell extends Cell {
    private double myThreshold;
    private double totalNeighbors;
    private double sameNeighborsAgent1;
    private double sameNeighborsAgent2;

    // Key States
    // 0 = empty
    // 1 = Agent 1
    // 2 = Agent 2
    // 3 = Agent 1 unhappy and wants to be moved to open spot
    // 4 = Agent 2 unhappy and wants to be moved to open spot

    /**
     * Constructor for SchellingCell class
     * @param state is the state of the cell
     * @param id is the id of the cell
     */
    public SchellingCell(int state, int id, double threshold){
        super(state, id);
        sameNeighborsAgent1 = 0;
        sameNeighborsAgent2 = 0;
        totalNeighbors = 0;
        myThreshold = threshold;
    }

    /**
     * Method that sets the next state of the cell
     * @param neighbors is the list of neighbors of the cell
     * @return next state of the cell
     */
    @Override
    public void setFutureState(List<Cell> neighbors) {
        if (getCurrentState() == 0){
            setFutureStateValue(0);
        }
        else {
            for (Cell neighbor : neighbors){
                if (neighbor.getCurrentState() == 1){
                    sameNeighborsAgent1++;
                }
                if (neighbor.getCurrentState() == 2){
                    sameNeighborsAgent2++;
                }
                totalNeighbors++;
            }

            if (getCurrentState() == 1){
                if (sameNeighborsAgent1/totalNeighbors < myThreshold){ // if the percentage of same neighbors is less than the threshold, then the agent will move
                    setFutureStateValue(3);
                }
                else {
                    setFutureStateValue(1);
                }
            }

            if (getCurrentState() == 2){
                if (sameNeighborsAgent2/totalNeighbors < myThreshold){
                    setFutureStateValue(4);
                }
                else {
                    setFutureStateValue(2);
                }
            }
        }
    }

}
