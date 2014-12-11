package TL.Checkers;


import TL.Cell;
import TL.Instruments;

import java.util.ArrayList;

public class AlternativesChecker {
    public static void alternativesCheck(ArrayList<Cell> board,ArrayList<ArrayList<Cell>> rows, ArrayList<ArrayList<Cell>> columns, ArrayList<ArrayList<Cell>>squares) {

        boolean solved = false;
        int alternativesCounter = 0;
        for (Cell cell : board) {
            if (cell.getContains() == 0) {


                String content = cell.getCanContain().get(0);
                String boardContent = saveContent(board);
                cell.setContains(Integer.parseInt(content));
                Instruments.loopedCheck(15,board,rows,columns,squares);
                System.out.println("noDuplicates: " + noDuplicates(rows,columns,squares) + ", !isSolved:" + !Instruments.isSolved(board));
                Instruments.printOut(board);
                if (noDuplicates(rows, columns, squares) && !Instruments.isSolved(board)) {
                    solved = true;
                } else {
                    for (Cell cellToClear : board) {
                        cellToClear.getCanContain().clear();
                    }
                    Instruments.addValues(boardContent, board);
                    Instruments.createBasicSuggestions(board);
                    Instruments.loopedCheck(3,board,rows,columns,squares);
                    alternativesCounter++;
                    System.out.println(alternativesCounter);
                    cell.getCanContain().remove(0);
                }


                if (solved) break;

            }
        }

    }

    public static boolean noDuplicates(ArrayList<ArrayList<Cell>> rows, ArrayList<ArrayList<Cell>> columns, ArrayList<ArrayList<Cell>>squares){
        for (int i = 0; i < 9; i++) {
            for(Cell cell : rows.get(i)){
                for(Cell cellToCompare : rows.get(i)){
                    if (!cell.equals(cellToCompare) && cell.getContains() == cellToCompare.getContains()){
                        return false;
                    }
                }
            }
            for(Cell cell : columns.get(i)){
                for(Cell cellToCompare : columns.get(i)){
                    if (!cell.equals(cellToCompare) && cell.getContains() == cellToCompare.getContains()){
                        return false;
                    }
                }
            }
            for(Cell cell : squares.get(i)){
                for(Cell cellToCompare : squares.get(i)){
                    if (!cell.equals(cellToCompare) && cell.getContains() == cellToCompare.getContains()){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static String saveContent(ArrayList<Cell> board){
        String out = "";
        for (int i = 0; i < 9; i++) {
            for (int k = 0; k < 9; k++) {

                for (Cell cell : board) {
                    if (cell.getRow() == i && cell.getColumn() == k) {
                        out = out + cell.getContains();
                    }
                }
            }

        }
        return out;
    }
}
