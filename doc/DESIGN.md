# Cell Society Design Final
### Names: Nick Ward, Vaishvi Patel, Luyao Wang, Jake Vigeant, Eka Ebong

## Team Roles and Responsibilities

 * Team Member #1: Nick Ward

My primary responsibility was the cell logic for each of the simulations. However, I also worked on the View with creating the buttons and the general UI layout, and worked on the grids and adjacency list implementation.

 * Team Member #2: Eka Ebong

My main responsibilty was the user interface including the splash screens, the background images, as well as the graphs that show current cell states. 

 * Team Member #3: Luyao Wang

 * Team Member #4: Vaishvi Patel
   * My primary responsibility was to implement the controller, use reflection to modify the UI, and implement functionality surrounding configuration. I worked on refactoring the UI and handling excpetions as well.

 * Team Member #5: Jake Vigeant


## Design goals
For this project, we spent a lot of time brainstorming our project structure and design before starting coding. Therefore, we focused on Model-View Separation with the Model-View controller, encapsulation with not restricting our design to a single data structure, testing thoroughly and consistently, and using abstractions to easily add new features and functionality in the future (rather than having it work just for the current version).

#### What Features are Easy to Add
We implemented the hexagonal neighbor functionality, so it will be much easier to add the Snow Flake Crystallization cell type and implement a new view of those cells.
It was easy to add new initial configuration of grids because we had a GridWrapper class. With csv initial states, we used a grid wrapper function to take parsed CSV data and store the grid. All we had to do was create a new way to initialize the gridwrapper class depending on the parameter in the configuration files.

## High-level Design
* Model:
The Model is an abstract class that implements computeStates, setCellCurrentState, getCells methods.
Then, InitialModelImplementation implements the model, uses reflection to pick a type of grid.
Then, the GraphGrid sets up the neighbors and adjacency list, the cells are picked using reflection, the edge policy is decided, and the computeStates method is overriden to do the corresponding passes for each simulation.
The cells decide the logic for each simulation, and the future state is set based on the states of the neighbors. The cells also hold its own state, and extra parameters that are needed for each cell type.
* Controller:
The controller is initialized by the view (on the file input screen) and then that is used to connect the model and the view for the grid. It's main functionality includes getting important parameters from the configuration files, calling the csv parser if necessary, and facilitating communication between the model and the view.  


#### Core Classes
Cells: All cell types extend the Cell super class, which has the general methods that are needed for each cell and can be easily overridden.

## Assumptions that Affect the Design

#### Features Affected by Assumptions
Right now, the falling sand simulation needs to have all 8 neighbors in order function. Therefore, there is a ring around grid so that no out of bounds exceptions occur. This could be fixed with proper neighbor handling in the model, which we did not have time to finish.
An assumption for creating random initial grids, is that the grid can only have up to 25 rows and columns.

## Significant differences from Original Plan
Originally, we thought we only needed one grid class to be able to run all of the simulations. However, with certain simulations, we need to do more than two iterations, leading us to making a grid class for each of the simulations.

## New Features HowTo

#### Easy to Add Features

#### Other Features not yet Done
Wator World Full Functionality (still very buggy)

Snow Flake Crystallization

Langton's Loop

Dynamically changing color of the simulation states