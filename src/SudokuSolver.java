import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.Timer;


public class SudokuSolver {
    private List<Integer> choices;
    private SudokuBoard sudokuBoard;

    private JFrame window;
    private Screen screen;

    private static JTextField[] sudokuInputBoxes;

    public SudokuSolver() {
        sudokuInputBoxes = new JTextField[9*9];

        choices = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            choices.add(i);
        }

        initUI();
    }

    public void initUI() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                sudokuInputBoxes[row*9+col] = new JTextField(2);
                Dimension inputBoxSize = sudokuInputBoxes[row*col].getPreferredSize();
                sudokuInputBoxes[row * 9 + col].setBounds(col * 40 + 20, row * 35 + 20, inputBoxSize.width, inputBoxSize.height);
            }
        }

        window = new JFrame("Sudoku Solver");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen = new Screen();

        screen.setBounds(0, 0, screen.getWidth(), screen.getHeight());

        for (int i = 0; i < 9 * 9; i++) {
            if (sudokuInputBoxes[i] != null) {
                screen.add(sudokuInputBoxes[i]);
            }
        }

        JButton solveButton = new JButton("Solve");
        Dimension solveButtonSize = solveButton.getPreferredSize();
        solveButton.setBounds(400,50, solveButtonSize.width, solveButtonSize.height);
        solveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Integer[] boardValues = new Integer[9*9];
                for (int i = 0; i < 9*9; i++) {
                    if (sudokuInputBoxes[i].getText().isEmpty() || sudokuInputBoxes[i].getText().equals("0")) {
                        boardValues[i] = null;
                    }
                    else {
                        boardValues[i] = Integer.parseInt(sudokuInputBoxes[i].getText());
                    }
                }
                SudokuBoard newBoard = new SudokuBoard(boardValues);
                sudokuBoard = newBoard;

                graphColouring();
            }
        });

        screen.add(solveButton);

        JButton clearButton = new JButton("Clear");
        Dimension clearButtonSize = clearButton.getPreferredSize();
        clearButton.setBounds(400,100, clearButtonSize.width, clearButtonSize.height);
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < 9*9; i++) {
                    sudokuInputBoxes[i].setText("");
                }
            }
        });

        screen.add(clearButton);

        window.add(screen);
        window.addKeyListener(screen);
        window.setResizable(false);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    // A dedicated method for loading boards so we can hotswap if needed.
    public void loadBoard(SudokuBoard board) {
        this.sudokuBoard = board;
    }

    public void graphColouring(){
        if (sudokuBoard == null) {
            return;
        }

        for (int pos = 0; pos < 9*9; pos++) {
            if (sudokuBoard.isDefaultValue(pos)){
                continue;
            }

            if (!colourNode(pos)) {
                continue;
            }
        }
    }

    public boolean colourNode(int currentNode) {
        while (currentNode < 81 && sudokuBoard.isDefaultValue(currentNode)) {
            currentNode++;
        }

        if (currentNode == 81) {
            return true;
        }
        List<Integer> adjacentNodes = sudokuBoard.getAdjacencyList(currentNode);

//                for (int i = 0; i < 9*9; i++) {
//                    if ( sudokuInputBoxes[i].getText().length() > 1){
//                        System.out.println("Entry too long");
//                    }
//                    else {
//                        if (sudokuBoard.getBoardValue(i) == null) {
//                            sudokuInputBoxes[i].setText("0");
//                        }
//                        else {
//                            sudokuInputBoxes[i].setText(sudokuBoard.getBoardValue(i).toString());
//                        }
//                    }
//                }

        for (int choice : choices) {
            boolean isSafeChoice = checkNeighbours(adjacentNodes, choice);
            if (isSafeChoice) {
                Integer prevValue = sudokuBoard.getBoardValue(currentNode);
                sudokuBoard.setBoardValue(currentNode, choice);

                sudokuInputBoxes[currentNode].setText(Integer.toString(choice));

                if (colourNode(currentNode+1)){
                    return true;
                }
                else {
                    sudokuBoard.setBoardValue(currentNode, prevValue);
                    if (prevValue == null) {
                        sudokuInputBoxes[currentNode].setText("0");
                    }
                    else {
                        sudokuInputBoxes[currentNode].setText(Integer.toString(prevValue));
                    }
                }
            }
        }
        return false;
    }

    private boolean checkNeighbours(List<Integer> adjacentNodes, int choice){
        if (adjacentNodes == null || adjacentNodes.size() == 0) {
            return true; // if no adjacent values then no conflict
        }

        for (int node : adjacentNodes) {
            Integer value = sudokuBoard.getBoardValue(node);
            if (value != null && value == choice) {
                return false;
            }
        }
        return true;
    }
}
