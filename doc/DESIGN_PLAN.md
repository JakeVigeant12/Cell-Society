# Cell Society Design Plan
### Team 3
### Nick Ward, Jake Vigeant, Vaishvi Patel, Luyao Wang, and Eka Ebong


## Design Overview


#### Overall design goals


#### Classes, their behavior and relationships (**not** instance variables)

 * As CRC

This class's purpose or value is to represent a customer's order:

|Order| |
|---|---|
|boolean isInStock(OrderLine)         |OrderLine|
|double getTotalPrice(OrderLine)      |Customer|
|boolean isValidPayment (Customer)    | |
|void deliverTo (OrderLine, Customer) | |


 *  OR UML in Markdown:

@startuml
Super <|-- Sub

class Super {
  void overrideMe()
  String toString()
}

class Sub {
  void overrideMe()
}
@enduml


 * OR UML Image

Here is a graphical look at our design:

![This is cool, too bad you can't see it](online-shopping-uml-example.png "An initial UI")

or made from [a tool that generates UML from existing code](http://staruml.io/).



## Design Details

#### Use Cases

 * A new game is started with five players, their scores are reset to 0.
 ```java
 Something thing = new Something();
 Value v = thing.getValue();
 v.update(13);
 ```

#### Extension Cases

 * What commonalities will be factored out into superclasses?
 
 * How will differences be handled when superclasses are extended?
 
 

## Design Considerations

Justification for classes and methods given in the design.

#### Design Issue #1
One of the biggest points of contention of our design was how we would represent the locations of the cells in the grid.

 * Design #1 Tradeoffs
   * Description

The locations of each cell could be represented as a row and a column (corresponding to the x and y coordinates of the cell).

   * Pros

The row and column approach would be easy to implement and would allow for easy translation from the view to the model, as the location of each cell could be easily determined.

   * Cons

Rows and columns would make it difficult for the grid to be non-rectangular.
This would require more memory and parameters to keep track of, as each cell would need to store two integers, one for the row and one for the column.

 * Design #2 Tradeoffs
   * Description
The other way would be to use a cell ID, and then have a map that maps the ID to the row and column (in the current case of a rectangular visual representation).

   * Pros

The ID approach would allow for non-rectangular grids, as numbers could be mapped to any position and relation to each other.

   * Cons

The ID approach would make it difficult to implement the front-end and visual representation.

 * Justification for choice
In the end, even though it would be more difficult to initially implement, the ID approach is the best way to add more representations and makes our design more open to new changes, even if it isn't completely necessary for this upcoming deadline.

#### Design Issue #2
Another design issue we faced was how we were going to have the view and model access the grid itself.

 * Design #1 Tradeoffs
   * Description

The first idea we had was to just pass the data structure from the view to the model.

   * Pros

It would be easy to have both the view and the model modify grid, and makes intuitive sense.

   * Cons

However, this would contradict the ideas we discussed in class about how the code reveals too much information about the structure of our design and would be very co-dependent on each other.

 * Design #2 Tradeoffs
   * Description
Another idea was to have two different representations of the grid in the model and the view, and the controller would pass only necessary information to each side.
   
   * Pros
 
This would make it so that the grid can be represented in any way on the back-end and front-end, and is flexible to change as long as we create a wrapper around each data structure that can access and change the data in the same way.

   * Cons

It would be more work on the controller, and require many more methods.
It will be important to have both the model and view agree on the current state of the grid, and to have safeguards to prevent that.
 
 * Justification for choice

In the end, we decided on the two separate representations, which will allow for more easy changes in the future due to our flexible design decisions.

#### Data Structure Implementation Change

 * Implementation #1
   * Description
   
   * Classes possibly affected
 
   * Methods possibly affected

 * Implementation #2
   * Description
   
   * Classes possibly affected
 
   * Methods possibly affected
 
 * Justification for how implementation choices is hidden
 

#### File Format Implementation Change

 * Implementation #1
   * Description
   
   * Classes possibly affected
 
   * Methods possibly affected

 * Implementation #2
   * Description
   
   * Classes possibly affected
 
   * Methods possibly affected
 
 * Justification for how implementation choices is hidden


#### JavaFX "Grid" Component Implementation Change

 * Implementation #1
   * Description
   
   * Classes possibly affected
 
   * Methods possibly affected

 * Implementation #2
   * Description
   
   * Classes possibly affected
 
   * Methods possibly affected
 
 * Justification for how implementation choices is hidden




## User Interface

Here is our amazing UI:

![This is cool, too bad you can't see it](images/29-sketched-ui-wireframe.jpg "An alternate design")

taken from [Brilliant Examples of Sketched UI Wireframes and Mock-Ups](https://onextrapixel.com/40-brilliant-examples-of-sketched-ui-wireframes-and-mock-ups/).




## Team Responsibilities

#### Primary Responsibilities
 * Nick Ward

 * Jake Vigeant

 * Vaishvi Patel

 * Luyao Wang

 * Eka Ebong


#### Secondary Responsibilities
 * Nick Ward

 * Jake Vigeant

 * Vaishvi Patel

 * Luyao Wang

 * Eka Ebong


#### Schedule Plan

