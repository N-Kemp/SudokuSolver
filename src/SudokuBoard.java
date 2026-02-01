import java.util.*;

public class SudokuBoard {
    private Map<Integer, List<Integer>> adjacencyList;
    private Integer[] board;
    private Set defaultPositions;
    private int subGridSize;

    public SudokuBoard(Integer[] board) {
        adjacencyList = new HashMap<>();
        subGridSize = 9;

        this.board = board;
        defaultPositions = new HashSet();

        for (int pos = 0; pos < board.length; pos++) {
            if (board[pos] != null) {
                defaultPositions.add(pos);
            }
        }

        for (int node = 0; node < 9*9; node++) {
            adjacencyList.put(node, new LinkedList<>());
        }

        buildGraph();
    }

    public void buildGraph() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                int primaryNode = row*subGridSize + col;

                // Add all nodes in the same row
                for (int c = 0; c < subGridSize; c++) {
                    if (c != col) {
                        int secondaryNode = getIndex(row, c);
                        adjacencyList.get(primaryNode).add(secondaryNode);
                    }
                }

                // Add all nodes in the same col
                for (int r = 0; r < subGridSize; r++) {
                    if (r != row) {
                        int seconardNode = getIndex(r, col);
                        adjacencyList.get(primaryNode).add(seconardNode);
                    }
                }

                // Add nodes in sub-grid
                int subGridCol = (col / 3) * 3; // Integer division causes a natural floor()
                int subGridRow = (row / 3) * 3; // Allowing us to get the subgrids values
                for (int r = subGridRow; r < subGridRow+3; r++){
                    for (int c = subGridCol; c < subGridCol+3; c++){
                        if (c != col || r != row) {
                            int secondaryNode = getIndex(r, c);
                            if (!adjacencyList.get(primaryNode).contains(secondaryNode)) {
                                adjacencyList.get(primaryNode).add(secondaryNode);
                            }
                        }
                    }
                }
            }
        }
    }

    public void printBoard() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if(col == 0) {
                    System.out.print("| ");
                }
                if (board[row*subGridSize + col] != null) {
                    System.out.print(board[row*9+col]);
                }
                else {
                    System.out.print(" ");
                }

                if (col == 8){
                    System.out.print(" |");
                }
                else if ((col+1) % 3 == 0){
                    System.out.print(" || ");
                }
                else {
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if (row == 8){
                continue;
            }
            else if ((row+1) % 3 == 0){
                System.out.println("=======================================");
            }
            else{
                System.out.println("---------------------------------------");
            }
        }
        System.out.println();
        System.out.println();
    }

    public void printAdjacencyList() {
        for (Map.Entry<Integer, List<Integer>> entry : adjacencyList.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public List<Integer> getAdjacencyList(int pos) {
        return adjacencyList.get(pos);
    }

    public void setBoardValue(int pos, Integer value) {
        if (defaultPositions.contains(pos)) {
            return;
        }
        board[pos] = value;
    }

    public Integer getBoardValue(int pos) {
        return board[pos];
    }

    private int getIndex(int row, int col) {
        return row*subGridSize + col;
    }

    public boolean isDefaultValue(int pos) {
        return defaultPositions.contains(pos);
    }
}
