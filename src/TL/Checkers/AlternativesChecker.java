package TL.Checkers;


import TL.Cell;
import TL.Instruments;

import java.util.ArrayList;
import java.util.Arrays;

public class AlternativesChecker {
    private static ArrayList<String> boardContent = new ArrayList<String>(Arrays.asList("","","","","","","","","",""));

    public static void alternativesCheck(int recursion, ArrayList<Cell> board,ArrayList<ArrayList<Cell>> rows, ArrayList<ArrayList<Cell>> columns, ArrayList<ArrayList<Cell>>squares) {


        if (recursion == 0) return;
        recursion--;

        for (int i = 0; i < 9; i++) {
            boolean solved = false;
            for (Cell cell : board) {
                if (cell.getContains() == 0 && cell.getCanContain().size() > i) {

                    //System.out.println(cell + " check " + cell.getCanContain().get(i) + " iteration: " + i);
                    String content = cell.getCanContain().get(i);
                    boardContent.set(recursion, saveContent(board));
                    cell.setContains(Integer.parseInt(content));
                    alternativesCheck(recursion, board, rows, columns, squares);
                    Instruments.loopedCheck(15, board, rows, columns, squares);

                    if (noDuplicates(rows, columns, squares) && !Instruments.isSolved(board)) {
                        solved = true;
                    } else {
                        for (Cell cellToClear : board) {
                            cellToClear.getCanContain().clear();
                        }
                        Instruments.addValues(boardContent.get(recursion), board);
                        Instruments.createBasicSuggestions(board);
                        Instruments.loopedCheck(3, board, rows, columns, squares);
                    }


                    if (solved) break;

                }
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
