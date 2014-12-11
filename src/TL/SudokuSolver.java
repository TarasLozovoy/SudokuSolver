package TL;

import java.util.ArrayList;
import TL.Checkers.*;

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
            Instruments.loopedCheck(1, board,rows,columns,squares);
            Instruments.updateContent(board);
            counter++;
            //System.out.println(board);

          /*  if (counter == 50){
                while (Instruments.isSolved(board)){
                    AlternativesChecker.alternativesCheck(board,rows,columns,squares);
                }
            }*/
        }

        Instruments.printOut(board);
    }


}
