package cellsociety.model.cells;

import java.util.List;
import java.util.Random;

public class FallingSandCell extends Cell {
    private static final int EMPTY = 0;
    private static final int SAND = 1;
    private static final int WATER = 2;
    private static final int BOUNDARY = 3;

    private static final int UPPERLEFT = 0;
    private static final int UPPER = 1;
    private static final int UPPERRIGHT = 2;
    private static final int LEFT = 3;
    private static final int RIGHT = 4;
    private static final int LOWERLEFT = 5;
    private static final int LOWER = 6;
    private static final int LOWERRIGHT = 7;

    // Key States
    // 0 = Empty
    // 1 = Sand
    // 2 = Water
    // 3 = Boundary

    // Directions
    // 0 1 2
    // 3 C 4
    // 5 6 7

    private boolean wantsToSwap;
    private Cell cellToSwap;

    public FallingSandCell(int state, int id){
        super(state, id);
    }

    public Cell getNeighborToSwap(){
        return cellToSwap;
    }

    public boolean wantsToSwap(){
        return wantsToSwap;
    }

    @Override
    public void setFutureState(List<Cell> neighbors) {
        emptyCellMovement(neighbors);

        sandMovement(neighbors);

        waterMovement(neighbors);

        boundaryMovement();
    }
    private void emptyCellMovement(List<Cell> neighbors) {
        wantsToSwap = false;
        if (getCurrentState() == EMPTY){
            if (neighbors.get(UPPER).getCurrentState() == SAND){ // if the cell is empty and above is sand
                setFutureStateValue(SAND); // Turn into sand
            }
            else {
                setFutureStateValue(EMPTY);
            }
            if (neighbors.get(UPPER).getCurrentState() == WATER){ // if the cell is empty and above is water
                setFutureStateValue(WATER); // Turn into water
            }
            else {
                setFutureStateValue(EMPTY);
            }
        }
    }

    private void waterMovement(List<Cell> neighbors) {
        if (getCurrentState() == WATER){
            if (neighbors.get(LOWER).getCurrentState() == EMPTY){ // if the cell is water and below is empty
                setFutureStateValue(EMPTY); // Turn into empty
            }
            else {
                if (neighbors.get(LOWERLEFT).getCurrentState() == EMPTY && neighbors.get(LEFT).getCurrentState() == EMPTY){
                    setFutureStateValue(WATER);
                    wantsToSwap = true;
                    cellToSwap = neighbors.get(LOWERLEFT);
                }
                else if (neighbors.get(LOWERRIGHT).getCurrentState() == EMPTY && neighbors.get(RIGHT).getCurrentState() == EMPTY){
                    setFutureStateValue(WATER);
                    wantsToSwap = true;
                    cellToSwap = neighbors.get(LOWERRIGHT);
                }
                else if (neighbors.get(LEFT).getCurrentState() == EMPTY && neighbors.get(RIGHT).getCurrentState() == EMPTY){ // If the cell is water and the left and right are empty, but lower left and lower right are not empty
                    setFutureStateValue(WATER);
                    wantsToSwap = true;
                    Random rand = new Random();
                    if (rand.nextBoolean()) {
                        cellToSwap = neighbors.get(LEFT);
                    } else {
                        cellToSwap = neighbors.get(RIGHT);
                    }
                }
                else if (neighbors.get(LEFT).getCurrentState() == EMPTY){
                    setFutureStateValue(WATER);
                    wantsToSwap = true;
                    cellToSwap = neighbors.get(LEFT);
                }
                else if (neighbors.get(RIGHT).getCurrentState() == EMPTY){
                    setFutureStateValue(WATER);
                    wantsToSwap = true;
                    cellToSwap = neighbors.get(RIGHT);
                }
                else {
                    setFutureStateValue(WATER); // Stay the same
                }
            }
        }
    }



    private void sandMovement(List<Cell> neighbors) {
        if (getCurrentState() == SAND){
            if (neighbors.get(LOWER).getCurrentState() == EMPTY){ // if the cell is sand and below is empty
                setFutureStateValue(EMPTY); // Turn into empty
            }
            else {
                setFutureStateValue(SAND);
            }
        }
    }

    private void boundaryMovement(){
        if (getCurrentState() == BOUNDARY){
            setFutureStateValue(BOUNDARY);
        }
    }


}
