package cellsociety.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

  /***
   * creates a random grid based on initial proportions for all states
   * @param numStates
   * @param initialProportions
   */
  public GridWrapper(int numStates, String[] initialProportions) {
    Random rand = new Random();
    this.row = rand.nextInt(1,25);
    this.column = rand.nextInt(1,25);
    int total = row*column;
    int[] countStates = new int[numStates];
    int[] currentCount = new int[numStates];
    for(int i = 0; i < initialProportions.length; i++) {
      countStates[i] = Integer.parseInt(initialProportions[i]) * total;
      currentCount[i] = 0;
    }

    grid = new ArrayList<>();
    for (int i = 0; i < this.row; i++) {
      List<Integer> singleList = new ArrayList<>();
      for (int j = 0; j < this.column; j++){
        int newState = rand.nextInt(numStates);
        if(currentCount[newState] < countStates[newState]) {
          singleList.add(newState);
          currentCount[newState] ++;
        }
      }
      grid.add(singleList);
    }
  }

  //Initialize a GridWrapper with size 0
  public GridWrapper() {
    grid = new ArrayList<>();
  }

  /***
   * Creates a random grid with cells with a certain number of states
   * @param numStates
   */
  public GridWrapper(int numStates) {
    Random rand = new Random();
    this.row = rand.nextInt(1,25);
    this.column = rand.nextInt(1,25);

    grid = new ArrayList<>();
    for (int i = 0; i < this.row; i++) {
      List<Integer> singleList = new ArrayList<>();
      for (int j = 0; j < this.column; j++){
        singleList.add(rand.nextInt(numStates));
      }
      grid.add(singleList);
    }
  }

  public int getState(int row, int column) {
    return grid.get(row).get(column);
  }

  //Before using this method, make sure you have added a row and a value of column index to that row
  public void setState(int row, int columnIndex, int state) {
    grid.get(row).set(columnIndex, state);
  }

  public void addRow() {
    grid.add(new ArrayList<>());
    row = grid.size();
  }

  public void addValueToRow(int row, int value) {
    grid.get(row).add(value);
  }

  public int getRowCount() {
//    return row;
    return grid.size();
  }

  public int getRowSize(int row) {
    return grid.get(row).size();
  }
  
  /**
   * For test purpose
   *
   * @return
   */
  public Object[] toArray() {
    return grid.toArray();
  }

  //TODO: refactor this, it should not return a list of list
  public List<List<Integer>> getGrid() {
    return grid;
  }
}
