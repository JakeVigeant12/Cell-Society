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

    // Grid Loops Required: 3

    private boolean wantsToSwap;
    private Cell cellToSwap;

    private List<Cell> neighborHood;

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
        neighborHood = neighbors;
        wantsToSwap = false;

        if (getCurrentState() == EMPTY){
            emptyCellMovement();
        }

        if (getCurrentState() == SAND){
            sandMovement();
        }

        if (getCurrentState() == WATER){
            waterMovement();
        }

        if (getCurrentState() == BOUNDARY){
            boundaryMovement();
        }
    }

    private void emptyCellMovement() {
        int newState = neighborHood.get(UPPER).getCurrentState();
        if (newState == SAND || newState == WATER){
            setFutureStateValue(newState); // Turn into sand or water
        }
    }

    private void waterMovement() {
        if (neighborHood.get(LOWER).getCurrentState() == EMPTY){ // if the cell is water and below is empty
            setFutureStateValue(EMPTY); // Turn into empty
        }
        else {
            if (neighborHood.get(UPPER).getCurrentState() == SAND){ // if the cell is water and above is sand
                setFutureStateValue(SAND); // Turn into sand
            }
            else if (neighborHood.get(LOWERLEFT).getCurrentState() == EMPTY && neighborHood.get(LEFT).getCurrentState() == EMPTY){
                setFutureStateValue(WATER);
                wantsToSwap = true;
                cellToSwap = neighborHood.get(LOWERLEFT);
            }
            else if (neighborHood.get(LOWERRIGHT).getCurrentState() == EMPTY && neighborHood.get(RIGHT).getCurrentState() == EMPTY){
                setFutureStateValue(WATER);
                wantsToSwap = true;
                cellToSwap = neighborHood.get(LOWERRIGHT);
            }
            else if (neighborHood.get(LEFT).getCurrentState() == EMPTY && neighborHood.get(RIGHT).getCurrentState() == EMPTY){ // If the cell is water and the left and right are empty, but lower left and lower right are not empty
                setFutureStateValue(WATER);
                wantsToSwap = true;
                Random rand = new Random();
                if (rand.nextBoolean()) {
                    cellToSwap = neighborHood.get(LEFT);
                } else {
                    cellToSwap = neighborHood.get(RIGHT);
                }
            }
            else if (neighborHood.get(LEFT).getCurrentState() == EMPTY){
                setFutureStateValue(WATER);
                wantsToSwap = true;
                cellToSwap = neighborHood.get(LEFT);
            }
            else if (neighborHood.get(RIGHT).getCurrentState() == EMPTY){
                setFutureStateValue(WATER);
                wantsToSwap = true;
                cellToSwap = neighborHood.get(RIGHT);
            }
            else {
                setFutureStateValue(WATER); // Stay the same
            }
        }
    }

    private void sandMovement() {
        int newState = neighborHood.get(LOWER).getCurrentState();
        if (newState == EMPTY || newState == WATER){
            setFutureStateValue(newState); // Turn into empty
        }
    }

    private void boundaryMovement(){
        setFutureStateValue(BOUNDARY);
    }

}
