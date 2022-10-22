package cellsociety.view;

import java.util.ArrayList;
import java.util.List;

public class GridWrapper {
    private int row;
    private int column;
    private int[][] grid;


    public GridWrapper(int row, int column) {
        this.row = row;
        this.column = column;

        grid = new int[row][column];
    }

    public int get(int row, int column) {
        return grid[row][column];
    }

    public void set(int row, int column, int state) {
        grid[row][column] = state;
    }

    public int row() {
        return row;
    }

    public int column() {
        return column;
    }

    public List<Integer> getRow(int row) {
        List<Integer> rowList = new ArrayList<>();
        for (int i = 0; i < column; i++) {
            rowList.add(grid[row][i]);
        }
        return rowList;
    }

    /**
     * For test purposes
     * @return
     */
    public int[][] toArray() {
        return grid;
    }
}
