package TL.Checkers;


import TL.Cell;

import java.util.ArrayList;

public class BasicChecker {
    public static void checkBoard(ArrayList<ArrayList<Cell>> rows, ArrayList<ArrayList<Cell>> columns, ArrayList<ArrayList<Cell>>squares) {
        for (int i = 0; i < 9; i++) {
            for (Cell cell : rows.get(i)) {
                if (cell.getContains() != 0){
                    for (Cell cellToUpdate : rows.get(i)){
                        if (    cellToUpdate.getContains() == 0 &&
                                cellToUpdate.getCanContain().contains("" + cell.getContains())){
                            cellToUpdate.getCanContain().remove("" + cell.getContains());
                        }
                    }
                }
            }
            for (Cell cell : columns.get(i)) {
                if (cell.getContains() != 0){
                    for (Cell cellToUpdate : columns.get(i)){
                        if (    cellToUpdate.getContains() == 0 &&
                                cellToUpdate.getCanContain().contains("" + cell.getContains())){
                            cellToUpdate.getCanContain().remove("" + cell.getContains());
                        }
                    }
                }
            }
            for (Cell cell : squares.get(i)) {
                if (cell.getContains() != 0){
                    for (Cell cellToUpdate : squares.get(i)){
                        if (    cellToUpdate.getContains() == 0 &&
                                cellToUpdate.getCanContain().contains("" + cell.getContains())){
                            cellToUpdate.getCanContain().remove("" + cell.getContains());
                        }
                    }
                }
            }

        }
    }
}
