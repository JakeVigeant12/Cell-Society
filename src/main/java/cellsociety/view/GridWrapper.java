package cellsociety.view;

import java.util.ArrayList;
import java.util.List;

public class GridWrapper {
    private int row = 0;
    private int column = 0;
    private List<List<Integer>> grid;


    //Initialize a GridWrapper with initial value 0
    public GridWrapper(int row, int column) {
        this.row = row;
        this.column = column;

        grid = new ArrayList<>();
        for (int i = 0; i < this.row; i++) {
            List<Integer> singleList = new ArrayList<>();
            for (int j = 0; j < this.column; j++){
                singleList.add(0);
            }
            grid.add(singleList);
        }
    }

    //Initialize a GridWrapper with size 0
    public GridWrapper() {
        grid = new ArrayList<>();
    }

    public int get(int row, int column) {
        return grid.get(row).get(column);
    }

    //Before using this method, make sure you have added a row and a value of column index to that row
    public void set(int row, int columnIndex, int state) {
        grid.get(row).set(columnIndex, state);
    }

    public void addRow() {
        grid.add(new ArrayList<>());
        row = grid.size();
    }

    public void addValueToRow(int row, int value) {
        grid.get(row).add(value);
    }

    public int row() {
        return row;
    }

    public int column(int row) {
        return grid.get(row).size();
    }

    public List<Integer> getRow(int row) {
        List<Integer> rowList = new ArrayList<>();
        for (int i = 0; i < column; i++) {
            rowList.add(grid.get(row).get(i));
        }
        return rowList;
    }

    public List<Integer> getColumn(int column) {
        List<Integer> columnList = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            columnList.add(grid.get(i).get(column));
        }
        return columnList;
    }

    /**
     * For test purposes
     *
     * @return
     */
    public Object[] toArray() {
        return grid.toArray();
    }

    public List<List<Integer>> getGrid() {
        return grid;
    }
}
