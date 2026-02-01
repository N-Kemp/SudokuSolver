public class Main {
    public static void main(String[] args) {
        // Setup board
        // Boards starting state
//        Integer[] boardValues = {
//                null,null,null,4,null,null,null,7,null,
//                9,4,null,null,null,8,null,null,null,
//                null,null,6,1,null,null,2,null,null,
//                3,null,null,null,null,7,null,8,2,
//                null,9,null,null,1,null,null,4,null,
//                8,2,null,3,null,null,null,null,9,
//                null,null,2,null,null,1,5,null,null,
//                null,null,null,2,null,null,null,1,6,
//                null,7,null,null,null,6,null,null,null
//        };

        // load starting state into sudoku board
//        SudokuBoard board = new SudokuBoard(boardValues);
        SudokuSolver solver = new SudokuSolver();

        // Add board to solver
//        solver.loadBoard(board);

        // Before solving
       // board.printBoard();

        // Perform graph colouring (solve sudoku board)
        //solver.graphColouring();

        //board.printBoard();
        //board.printAdjacencyList();

    }
}