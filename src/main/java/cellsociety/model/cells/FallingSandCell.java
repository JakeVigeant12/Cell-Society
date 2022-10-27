package cellsociety.model.cells;

import java.util.List;
import java.util.Random;

public class FallingSandCell extends Cell {
    // Key States
    // 0 = Empty
    // 1 = Sand
    // 2 = Water
    // 3 = Boundary

    // Directions
    // 1 2 3
    // 4 C 5
    // 6 7 8

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
        wantsToSwap = false;
        if (getCurrentState() == 0){
            if (neighbors.get(2).getCurrentState() == 1){ // if the cell is empty and above is sand
                setFutureStateValue(1); // Turn into sand
            }
            else {
                setFutureStateValue(0);
            }
            if (neighbors.get(2).getCurrentState() == 2){ // if the cell is empty and above is water
                setFutureStateValue(2); // Turn into water
            }
            else {
                setFutureStateValue(0);
            }
        }

        if (getCurrentState() == 1){
            if (neighbors.get(7).getCurrentState() == 0){ // if the cell is sand and below is empty
                setFutureStateValue(0); // Turn into empty
            }
            else {
                setFutureStateValue(1);
            }
        }

        if (getCurrentState() == 2){
            if (neighbors.get(7).getCurrentState() == 0){ // if the cell is sand and below is empty
                setFutureStateValue(0); // Turn into empty
            }
            else {
                if (neighbors.get(6).getCurrentState() == 0 && neighbors.get(4).getCurrentState() == 0){
                    setFutureStateValue(1);
                    wantsToSwap = true;
                    cellToSwap = neighbors.get(6);
                }
                else if (neighbors.get(8).getCurrentState() == 0 && neighbors.get(5).getCurrentState() == 0){
                    setFutureStateValue(1);
                    wantsToSwap = true;
                    cellToSwap = neighbors.get(8);
                }
                else if (neighbors.get(4).getCurrentState() == 0 && neighbors.get(5).getCurrentState() == 0){
                    setFutureStateValue(1);
                    wantsToSwap = true;
                    Random rand = new Random();
                    if (rand.nextBoolean()) {
                        cellToSwap = neighbors.get(4);
                    } else {
                        cellToSwap = neighbors.get(5);
                    }
                }
                else if (neighbors.get(5).getCurrentState() == 0){
                    setFutureStateValue(1);
                    wantsToSwap = true;
                    cellToSwap = neighbors.get(5);
                }
                else if (neighbors.get(4).getCurrentState() == 0){
                    setFutureStateValue(1);
                    wantsToSwap = true;
                    cellToSwap = neighbors.get(4);
                }
                else {
                    setFutureStateValue(2);
                }
            }
        }
    }
}
