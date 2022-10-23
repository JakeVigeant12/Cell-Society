package cellsociety.model.cells;

import java.util.List;

public class WatorWorldCell extends Cell {
    private int fishTurns;
    private int sharkTurns;
    private int sharkStarve;
    private boolean wantsToMove;

    // Cell Key
    // 0 = water
    // 1 = fish
    // 2 = shark that eats fish
    // 3 = shark that doesn't eat fish

    /**
     * Constructor for WaTorWorldCell class
     * @param state is the state of the cell
     * @param id is the id of the cell
     */
    public WatorWorldCell(int state, int id){
        super(state, id);
        fishTurns = 0;
        sharkTurns = 0;
        sharkStarve = 0;
        wantsToMove = false;
    }

    public WatorWorldCell(int state, int id, int fTurn, int sTurn, int sStarve){
        super(state, id);
        fishTurns = fTurn;
        sharkTurns = sTurn;
        sharkStarve = sStarve;
        wantsToMove = false;
    }

    /**
     * Method that sets the next state of the cell
     * @param neighbors is the list of neighbors of the cell
     * @return next state of the cell
     */
    @Override
    public void setFutureState(List<Cell> neighbors) {
        if (getCurrentState() == 0) { // if the current cell is water
            setFutureStateValue(0); // do nothing
        }
        else { // if the current cell is a fish or shark
            // An animal can move to an empty cell
            if (getCurrentState() == 1){ // If current cell is a fish
                if (getNeighborStates(neighbors).contains(0)){
                    fishTurns++;
                    if (fishTurns == 3){ // if the fish has been alive for 3 turns, then it will breed
                        setFutureStateValue(1);
                        fishTurns = 0;
                        // TODO: Make a new fish in neighboring cell
                    }
                    else {
                        setFutureStateValue(1); // Needs to swap with a water cell
                        wantsToMove = true;
                    }
                }
                else {
                    setFutureStateValue(1);
                }
            }
            else if (getCurrentState() == 2) { // If current cell is a shark and eats a fish
                if (!getNeighborStates(neighbors).contains(0) && !getNeighborStates(neighbors).contains(1)) { // if there are no empty cells or fish, then the shark will starve
                    setFutureStateValue(3);
                }
                else if (getNeighborStates(neighbors).contains(1)) { // if there is a fish, then the shark will eat it
                    sharkTurns++;
                    sharkStarve = 0;
                    if (sharkTurns == 3) { // if the shark has been alive for 3 turns, then it will breed
                        setFutureStateValue(2);
                        sharkTurns = 0;
                    } else {
                        setFutureStateValue(2); // Needs to swap with a water cell
                        wantsToMove = true;
                    }
                }
            }
            else if (getCurrentState() == 3){ // If current cell is a shark and does not eat a fish
                sharkStarve++;
                if (sharkStarve == 3) {
                    setFutureStateValue(0); // Shark dies
                    setSharkStarve(0);
                    wantsToMove = false;
                }
                else {
                    setFutureStateValue(3); // Shark stays alive
                    wantsToMove = true;
                }
            }
        }
    }

    /**
     * Method that returns if the cell wants to move
     * @return true if the cell wants to move, false otherwise
     */
    public boolean getWantsToMove(){
        return wantsToMove;
    }

    /**
     * Method that returns the fish turns of the cell
     * @return fish turns of the cell
     */
    public int getFishTurns(){
        return fishTurns;
    }

    /**
     * Method that returns the shark turns of the cell
     * @return shark turns of the cell
     */
    public int getSharkTurns(){
        return sharkTurns;
    }

    /**
     * Method that returns the shark starve of the cell
     * @return shark starve of the cell
     */
    public int getSharkStarve(){
        return sharkStarve;
    }

    /**
     * Method that sets the shark starve of the cell
     * @param starve
     */
    private void setSharkStarve(int starve) {
        this.sharkStarve = starve;
    }

    /**
     * Method that sets the fish turns of the cell
     * @param fishTurns is the fish turns of the cell
     */
    public void setFishTurns(int fishTurns) {
        this.fishTurns = fishTurns;
    }

    /**
     * Method that sets the shark turns of the cell
     * @param turns is the shark turns of the cell
     */
    public void setSharkTurns(int turns) {
        this.sharkTurns = turns;
    }

    /**
     * Method that swaps a state of a cell with another cell
     * @param cell
     */
    public void swapCell(WatorWorldCell cell) {
        WatorWorldCell tempCell = new WatorWorldCell(cell.getCurrentState(), cell.getId(), cell.getFishTurns(), cell.getSharkTurns(), cell.getSharkStarve());

        cell.setFutureStateValue(getFutureState());
        cell.setFishTurns(getFishTurns());
        cell.setSharkTurns(getSharkTurns());
        cell.setSharkStarve(getSharkStarve());

        setFutureStateValue(tempCell.getCurrentState());
        setFishTurns(tempCell.getFishTurns());
        setSharkTurns(tempCell.getSharkTurns());
        setSharkStarve(tempCell.getSharkStarve());
    }

    /**
     * Method that is used for when a shark moves to a cell and leaves behind nothing
     * @param cell is the cell that the shark moves to
     */
    public void swapAndEmptyCell(WatorWorldCell cell) {
        WatorWorldCell tempCell = new WatorWorldCell(cell.getCurrentState(), cell.getId(), cell.getFishTurns(), cell.getSharkTurns(), cell.getSharkStarve());

        cell.setFutureStateValue(0);
        cell.setFishTurns(0);
        cell.setSharkTurns(0);
        cell.setSharkStarve(0);

        setFutureStateValue(tempCell.getCurrentState());
        setFishTurns(tempCell.getFishTurns());
        setSharkTurns(tempCell.getSharkTurns());
        setSharkStarve(tempCell.getSharkStarve());
    }

    /**
     * Method that creates a new animal type
     * @param cell is the cell that is being changed
     */
    public void spawnNewCell(WatorWorldCell cell, int newState) {
        if (cell.getCurrentState() == 0) { // if cell is empty, then spawn new animal
            cell.setFutureStateValue(newState);
            cell.setFishTurns(0); // reset counters
            cell.setSharkTurns(0);
            cell.setSharkStarve(0);
        }
    }
}
