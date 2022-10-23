package cellsociety.view;

import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class GridWrapperObservable {

    private int row;
    private int column;
    private ObservableList<ObservableList<IntegerProperty>> grid;


    //https://stackoverflow.com/questions/42134915/listchangelistener-change-how-to-properly-handle-updated-and-permutated-items
    public GridWrapperObservable(int row, int column) {
        this.row = row;
        this.column = column;

        grid = FXCollections.<ObservableList<IntegerProperty>>observableArrayList(
                item -> new Observable[]{item});

        for (int i = 0; i < this.row; i++) {
            ObservableList<IntegerProperty> singleList = FXCollections.observableArrayList(
                    item -> new Observable[]{item});
            for (int j = 0; j < this.column; j++) {
                singleList.add(new SimpleIntegerProperty());
            }
            grid.add(singleList);
        }
    }

    public int get(int row, int column) {
        return grid.get(row).get(column).get();
    }

    public IntegerProperty getProperty(int row, int column) {
        return grid.get(row).get(column);
    }

    public void set(int row, int column, IntegerProperty state) {
        grid.get(row).set(column, state);
    }

    public void set(int row, int column, int state) {
        grid.get(row).set(column, new SimpleIntegerProperty(state));
    }

    public void setListener(Consumer<GridWrapperObservable> fitFunction) {
        for (int k = 0; k < row; k++) {
            grid.get(k).addListener((ListChangeListener.Change<? extends IntegerProperty> c1) -> {
                while (c1.next()) {
                    if (c1.wasUpdated()) {
                        fitFunction.accept(this);
                    }
                }
            });
        }
    }

    public ObservableList<ObservableList<IntegerProperty>> getGrid() {
        return grid;
    }

    public int row() {
        return row;
    }

    public int column() {
        return column;
    }

    public List<IntegerProperty> getRow(int row) {
        List<IntegerProperty> rowList = new ArrayList<>();
        for (int i = 0; i < column; i++) {
            rowList.add(grid.get(row).get(i));
        }
        return rowList;
    }

    public List<IntegerProperty> getColumn(int column) {
        List<IntegerProperty> columnList = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            columnList.add(grid.get(i).get(row));
        }
        return columnList;
    }

    public GridWrapper toGridWrapper() {
        GridWrapper gridWrapper = new GridWrapper(row, column);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                gridWrapper.set(i, j, grid.get(i).get(j).get());
            }
        }
        return gridWrapper;
    }

//    /**
//     * For test purposes
//     *
//     * @return
//     */
//    public IntegerProperty[][] toArray() {
//        return grid;
//    }
}
