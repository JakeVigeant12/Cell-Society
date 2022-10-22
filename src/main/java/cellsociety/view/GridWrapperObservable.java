package cellsociety.view;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class GridWrapperObservable {

    private int row;
    private int column;
    private IntegerProperty[][] grid;


    public GridWrapperObservable(int row, int column) {
        this.row = row;
        this.column = column;

        grid = new IntegerProperty[row][column];
    }

    public IntegerProperty get(int row, int column) {
        return grid[row][column];
    }

    public void set(int row, int column, IntegerProperty state) {
        grid[row][column] = state;
    }

    public void set(int row, int column, int state) {
        grid[row][column] = new SimpleIntegerProperty(state);
    }

    public int row() {
        return row;
    }

    public int column() {
        return column;
    }

    /**
     * For test purposes
     *
     * @return
     */
    public IntegerProperty[][] toArray() {
        return grid;
    }
}
