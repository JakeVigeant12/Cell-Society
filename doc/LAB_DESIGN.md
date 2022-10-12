# Cell Society Design Discussion
### Team Number: 10
### Names: Vaishvi Patel, Nick Ward, Eka Ebong, Luyao Wang, Jake Vigeant



## Cellular Automata

#### Commonalities


#### Variations



## Discussion Questions

 * How does a Cell know what rules to apply for its simulation?
   * Grid would apply the rule by looping through the cells and apply the rule stored in the type of cell
   * Cell should be its own class and then use inheritance to make specific cells for the type for simulation. They will have a common method like applyRule() which will be different for each type of cell.

 * How does a Cell know about its neighbors?
   * The Grid itself would be used to access cell neighbors or a method that accesses the neighbors of the cell

 * How can a Cell update itself without affecting its neighbors update?
   * Two Grids: a current and history grid. The current grid's cells are updated based on the states of its neighbors on the history grid. Once the current grid is calculated, it is published and then it now becomes the new history grid.

 * What behaviors does the Grid itself have?
   * Change size/add more cells, background color, generate grid (constructor?) based on a parsed csv, ability to access each cell.

 * How can a Grid update all the Cells it contains?
   * Loop through the cells and call a method on the Cell class to apply rule

 * How is configuration information used to set up a simulation?
   * The step function we use for each cell will be determined by the type and that will determine the type of cell

 * How is the GUI updated after all the cells have been updated?
   * A signal from model to update Grid GUI to based on the published current grid



## Alternate Designs

#### Design Idea #1

 * Data Structure #1

 * Data Structure #2


#### Design Idea #2

 * File Format #1

 * File Format #2



## Design Principles

#### Open-Closed Design Principle


#### Dependency Inversion Principle


#### Interface Separation Principle




### CRC Card Classes

This class's purpose or value is to represent a customer's order:
![Order Class CRC Card](order_crc_card.png "Order Class")


This class's purpose or value is to represent a customer's order:

|Order| |
|---|---|
|boolean isInStock(OrderLine)         |OrderLine|
|double getTotalPrice(OrderLine)      |Customer|
|boolean isValidPayment (Customer)    | |
|void deliverTo (OrderLine, Customer) | |


This class's purpose or value is to represent a customer's order:
```java
public class Order {
     // returns whether or not the given items are available to order
     public boolean isInStock (OrderLine items)
     // sums the price of all the given items
     public double getTotalPrice (OrderLine items)
     // returns whether or not the customer's payment is valid
     public boolean isValidPayment (Customer customer)
     // dispatches the items to be ordered to the customer's selected address
     public void deliverTo (OrderLine items, Customer customer)
 }
 ```
 

### Use Cases

 * Apply the rules to a cell: set the next state of a cell to dead by counting its number of neighbors using the Game of Life rules for a cell in the middle (i.e., with all of its neighbors)
```java
 OrderLine items = new OrderLine();
 if (order.isInStock(items)) {
     total = order.getTotalPrice(items);
 }
```

 * Move to the next generation: update all cells in a simulation from their current state to their next state
```java
 OrderLine items = new OrderLine();
 if (order.isInStock(items)) {
     total = order.getTotalPrice(items);
 }
```

 * Switch simulations: load a new simulation from a data file, replacing the current running simulation with the newly loaded one
```java
 OrderLine items = new OrderLine();
 if (order.isInStock(items)) {
     total = order.getTotalPrice(items);
 }
```
