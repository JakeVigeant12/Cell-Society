package cellsociety.model;

import java.util.List;

public class WaTorWorldCell extends Cell {
    private int fishTurns;
    private int sharkTurns;
    private int sharkStarve;

    /**
     * Constructor for WaTorWorldCell class
     * @param state is the state of the cell
     * @param row is the row of the cell
     * @param col is the column of the cell
     */
    public WaTorWorldCell(int state, int id){
        super(state, id);
        fishTurns = 0;
        sharkTurns = 0;
        sharkStarve = 0;
    }

    public WaTorWorldCell(int state, int id, int fTurn, int sTurn, int sStarve){
        super(state, id);
        fishTurns = fTurn;
        sharkTurns = sTurn;
        sharkStarve = sStarve;
    }

    /**
     * Method that sets the next state of the cell
     * @param neighbors is the list of neighbors of the cell
     * @return next state of the cell
     */
    @Override
    public void setFutureState(List<Cell> neighbors) {
        if(getCurrentState() == 1){
            fishTurns++;
            if (fishTurns == 3){
                setFutureStateValue(0);
            }
            else {
                setFutureStateValue(1);
            }
        }
        else if (getCurrentState() == 2) {
            sharkTurns++;
            if (sharkTurns == 3) {
                setFutureStateValue(0);
            }
            else {
                setFutureStateValue(2);
            }
        }
        else if (getCurrentState() == 3){
            sharkStarve++;
            if (sharkStarve == 3) {
                setFutureStateValue(0);
            }
            else {
                setFutureStateValue(3);
            }
        }
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

    private void setSharkTurns(int turns) {
        this.sharkTurns = turns;
    }

    /**
     * Method that swaps a state of a cell with another cell
     * @param cell
     */
    public void swapCell(WaTorWorldCell cell) {
        WaTorWorldCell tempCell = new WaTorWorldCell(cell.getCurrentState(), cell.getId(), cell.getFishTurns(), cell.getSharkTurns(), cell.getSharkStarve());

        cell.setFutureStateValue(getCurrentState());
        cell.setFishTurns(getFishTurns());
        cell.setSharkTurns(getSharkTurns());
        cell.setSharkStarve(getSharkStarve());

        setFutureStateValue(tempCell.getCurrentState());
        setFishTurns(tempCell.getFishTurns());
        setSharkTurns(tempCell.getSharkTurns());
        setSharkStarve(tempCell.getSharkStarve());
    }

    public void swapAndEmptyCell(WaTorWorldCell cell) {
        WaTorWorldCell tempCell = new WaTorWorldCell(cell.getCurrentState(), cell.getId(), cell.getFishTurns(), cell.getSharkTurns(), cell.getSharkStarve());

        cell.setFutureStateValue(0);
        cell.setFishTurns(0);
        cell.setSharkTurns(0);
        cell.setSharkStarve(0);

        setFutureStateValue(tempCell.getCurrentState());
        setFishTurns(tempCell.getFishTurns());
        setSharkTurns(tempCell.getSharkTurns());
        setSharkStarve(tempCell.getSharkStarve());
    }
}
