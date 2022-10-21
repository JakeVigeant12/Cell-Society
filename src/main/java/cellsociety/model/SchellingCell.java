package cellsociety.model;

import java.util.List;

public class SchellingCell extends Cell{
    private double myThreshold;
    private int totalNeighbors;
    private int sameNeighborsAgent1;
    private int sameNeighborsAgent2;

    /**
     * Constructor for SchellingCell class
     * @param state is the state of the cell
     * @param id is the id of the cell
     */
    public SchellingCell(int state, int id, double threshold){
        super(state, id);
        sameNeighborsAgent1 = 0;
        sameNeighborsAgent2 = 0;
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
                if (sameNeighborsAgent1/totalNeighbors < myThreshold){
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
