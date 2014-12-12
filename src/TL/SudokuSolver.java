package TL;

import TL.Checkers.AlternativesChecker;
import java.util.ArrayList;

public class SudokuSolver {

    public static void main(String[] args) {
        ArrayList<Cell> board = Instruments.createBoard();
        ArrayList<ArrayList<Cell>> rows = new ArrayList<ArrayList<Cell>>();
        ArrayList<ArrayList<Cell>> columns= new ArrayList<ArrayList<Cell>>();
        ArrayList<ArrayList<Cell>> squares= new ArrayList<ArrayList<Cell>>();
        Instruments.classifyBoardContent(board,rows,columns,squares);

        //parse user input
        String input = Instruments.parseInput();
        Instruments.addValues(input, board);

        //add suggestions to unknown cells
        Instruments.createBasicSuggestions(board);

        int counter = 0;
        while (Instruments.isSolved(board)) {
            Instruments.loopedCheck(1, board, rows, columns, squares);
            counter++;
            System.out.println(counter);
            if (counter == 50){
                String backup = AlternativesChecker.saveContent(board);
                int recursionCounter = 1;
                while (Instruments.isSolved(board)){

                    AlternativesChecker.alternativesCheck(recursionCounter, board, rows, columns, squares);
                    counter++;
                    System.out.println(counter);
                    if (!Instruments.isSolved(board)) break;
                    recursionCounter++;
                }
                if (counter > 1000){
                    System.out.println("It's too hard! =(");
                    break;
                }
            }
        }

        if (!Instruments.isSolved(board)) Instruments.printOut(board);
    }


}
