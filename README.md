# SudokuSolver

A simple Java-based Sudoku solver with a Swing GUI. The solver represents the Sudoku board as a graph (each cell is a node, edges connect cells that cannot share the same value) and uses a backtracking graph-colouring approach to fill the board.

## Features

- Graph-based model of the Sudoku board
- Backtracking / graph-colouring solver
- Swing-based GUI to input puzzles and view the solution
- Ability to clear the board and solve interactively

## How it works (brief)

- The board is stored as an array of 81 values (9×9 grid).
- A graph adjacency list maps each cell to the set of other cells in the same row, column, and 3×3 subgrid.
- The solver iterates through empty cells and tries values 1–9 that don't conflict with adjacent nodes, using recursion and backtracking to find a valid assignment.

## Requirements

- Java 8 or later (JDK)
- Uses Java Swing for the UI

## Project structure

- src/Main.java — entry point; example usage / hooks to load boards
- src/SudokuSolver.java — UI and solver orchestration (graph-colouring and UI controls)
- src/SudokuBoard.java — board representation and adjacency graph
- (Other classes may exist in `src/` depending on local edits)

## Build and run

From the project root (where `src/` lives), you can compile and run using the JDK:

1. Compile:
   - Create an output directory and compile:
     - Windows / macOS / Linux (bash):
       ```
       mkdir -p out
       javac -d out src/*.java
       ```
2. Run:
   ```
   java -cp out Main
   ```

Alternatively, open the project in your IDE (IntelliJ IDEA, Eclipse, VS Code with Java extensions) and run the `Main` class.

## Usage (GUI)

- When the application starts, a 9×9 grid of input boxes appears.
- Enter digits 1–9 for known cells. Leave empty or enter 0 for unknown cells.
- Click `Solve` to run the solver. If a valid solution is found it will populate the grid.
- Click `Clear` to clear all inputs.

Notes:
- Pre-filled cells (the initial puzzle) are treated as fixed and will not be changed by the solver.
- The solver implements a deterministic backtracking approach — some puzzles may take longer depending on difficulty.

## License

No license is included in the repository currently. If you want this project to be open source, add a `LICENSE` file (for example, MIT, Apache 2.0, etc.). If you tell me which license you prefer, I can create the file content for you.
