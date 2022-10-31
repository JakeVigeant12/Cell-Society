Cell Society
====

This project implements a cellular automata simulator.

Names: Nick Ward, Vaishvi Patel, Jake Vigeant, Eka Ebong, Luyao Wang


### Timeline

Start Date: 

Part 1: 10/12/22

Part 2: 10/18/22

Part 3: 10/26/22

Finish Date: 10/30/22

Part 1: 10/12/22

Part 2: 10/18/22

Part 3: 10/30/22

Hours Spent: 95+ hours

### Primary Roles
Eka Ebong: GUI, User input side 

Luyao Wang: GUI, Grid side

Jake Vigeant: Grid and Model

Vaishvi Patel: Controller, Configuration, Exception Handling

Nick Ward: Cells, Model, Front-End Functionality


### Resources Used

Collaborators:

Attributions for others' work:
- tree.png: Tree icons created by Freepik - Flaticon (https://www.flaticon.com/free-icons/tree)
- fire.png: Fire icons created by Freepik - Flaticon (https://www.flaticon.com/free-icons/fire)
- grass.png: Grass icons created by Freepik - Flaticon (https://www.flaticon.com/free-icons/grass)
- heart.png: Heartbeat icons created by DinosoftLabs - Flaticon (https://www.flaticon.com/free-icons/heartbeat)
- user.png: User icons created by Freepik - Flaticon (https://www.flaticon.com/free-icons/user)
- tombstone.png: Dead icons created by srip - Flaticon (https://www.flaticon.com/free-icons/dead)
- sea.png: Water icons created by Freepik - Flaticon (https://www.flaticon.com/free-icons/water)
- rock.png: Rock icons created by Freepik - Flaticon (https://www.flaticon.com/free-icons/rock)
- empty.png: Blank icons created by Google - Flaticon (https://www.flaticon.com/free-icons/blank)
- paper.png: Paper icons created by Freepik - Flaticon (https://www.flaticon.com/free-icons/paper)
- scissors.png: Scissors icons created by Freepik - Flaticon (https://www.flaticon.com/free-icons/scissors)
- red-person.png: Business and finance icons created by HideMaru - Flaticon (https://www.flaticon.com/free-icons/business-and-finance)
- blue-person.png: User icons created by Freepik - Flaticon (https://www.flaticon.com/free-icons/user)
- fish.png: Ocean icons created by Freepik - Flaticon (https://www.flaticon.com/free-icons/ocean)
- shark.png: Shark icons created by Skyclick - Flaticon (https://www.flaticon.com/free-icons/shark)
- dunes.png: Sand icons created by Freepik - Flaticon https://www.flaticon.com/free-icons/sand
- unc.png: UNC Tar Heels Logo - freebie supply (https://freebiesupply.com/logos/unc-tar-heels-logo-4/)
- duke.png: Wikipedia Commons (https://commons.wikimedia.org/wiki/File:Duke_Blue_Devils_logo.svg)
- //https://stackoverflow.com/questions/68129134/create-hexagonal-field-with-flat-tiles-with-javafx
- //https://www.geeksforgeeks.org/reading-csv-file-java-using-opencsv/


### Running the Program

Main class: Main.java

How to use the program: Run the Main.java class. From there, a pop-up should appear with the main menu. There, a language can be selected. From there, a sim file can be uploaded.

Once the sim file is uploaded, if sucessfully uploaded with proper formatting, the grid and the UI will appear. To play the simulation, press play. To pause, press pause. To step forward one frame at a time, press the step function. To speed up the simulation, use the slider to speed it up. To save the current configuration of cells to a CSV, then press the save button. The user can easily exit, go back, and upload a new simulation with the top bar. The user can also view the information from the sim file using the panel on the left.

Data files needed: 
* A sim file and optional corresponding csv with the grid states depending on type of initial state
  * Required sim parameters:
    * Type (Fire, Wator, Game of Life, etc.)
    * Title
    * Author
    * Description
    * InitialStates (path to csv, Random, Proportions)
  * Optional sim parameters:
    * Parameters
      * Depending on simulation Type to define simulation parameters (default parameters used if not provided)
    * InitialProportions 
      * Required if the InitialStates value is Proportions. Assumes that user provides the correct percentage of for each of the states in the simulation
    * StateColor
      * Hex codes for the color of each state (default values for color are set if missing)
    * StateImages
      * Path to image files for each of the different states (defaults to colors if not provided)
    * Outlined
      * true or false - whether or not the grid is outlined in the UI
    * EdgePolicy
      * toroidal or finite (default value finite)
    * Tiling
     * square or hexagon (default value square if not provided)

Features implemented:

Fully working simulations:
 * Conway's Game of Life
 * Spreading of Fire
 * Rock, Paper, Scissors
 * Percolation
 * Schelling's Model of Segregation
 * Falling Sand

Nearly Complete Simulations:
 * Wa-Tor World

### Notes/Assumptions

Assumptions or Simplifications:
* For hexagon tiling, edge policy is not supported

Interesting data files:

Known Bugs:
* Hexagon tiling will have extra blank space in lower part of the screen because the alignment is done by a gridpane, and
each cell is translated up by some distance closely align them.

Noteworthy features:
* Try running the simulations with "styled" in the file name to see cool pictures for the different simulations. It's really cute! 

### Impressions

