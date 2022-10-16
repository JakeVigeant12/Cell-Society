package cellsociety.model;
//abstraction of grid to allow flexibility in implementation
public abstract class Grid {

  public void computeStates() {
    return;
  }
  public abstract Grid extractState();
}
