package cellsociety.model.cells;

import java.util.List;

public class SegregationCell extends Cell {
    private double myThreshold;
    private double totalNeighbors;
    private double sameNeighborsAgent1;
    private double sameNeighborsAgent2;
    private boolean wantsToMove;

    // Key States
    // 0 = empty
    // 1 = Agent 1
    // 2 = Agent 2

    /**
     * Constructor for SchellingCell class
     * @param state is the state of the cell
     * @param id is the id of the cell
     */
    public SegregationCell(int state, int id, String parameter){
        super(state, id);
        sameNeighborsAgent1 = 0;
        sameNeighborsAgent2 = 0;
        totalNeighbors = 0;
        myThreshold = Double.parseDouble(parameter);
        wantsToMove = false;
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
                    setFutureStateValue(1);
                    wantsToMove = true;
                }
                else {
                    setFutureStateValue(1);
                    wantsToMove = false;
                }
            }

            if (getCurrentState() == 2){
                if (sameNeighborsAgent2/totalNeighbors < myThreshold){
                    setFutureStateValue(2);
                    wantsToMove = true;
                }
                else {
                    setFutureStateValue(2);
                    wantsToMove = false;
                }
            }
        }
    }

    /**
     * Method that returns whether the cell wants to move
     * @return true if the cell wants to move, false otherwise
     */
    public boolean getWantsToMove(){
        return wantsToMove;
    }

}
