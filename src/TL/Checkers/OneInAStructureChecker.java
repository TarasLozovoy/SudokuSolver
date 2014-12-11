package TL.Checkers;


import TL.Cell;

import java.util.ArrayList;

public class OneInAStructureChecker {
    public static void checkOneInARow(int row, ArrayList<Cell> board){
        for (int number = 1; number < 10; number++) {
            boolean isOnlyOne = true;
            boolean isPresent = false;
            int cellRow = -1;
            int cellColumn = -1;
            for (Cell cell : board) {
                if (cell.getRow() == row && cell.getContains() == 0){
                    for (String canContain : cell.getCanContain()){
                        if(number == Integer.parseInt(canContain)){
                            if (isPresent){
                                isOnlyOne = false;
                            }
                            isPresent = true;
                            cellRow = cell.getRow();
                            cellColumn = cell.getColumn();
                        }
                    }
                }
            }
            if (isPresent && isOnlyOne) {
                for (Cell cell : board) {
                    if (cell.getRow() == cellRow && cell.getColumn() == cellColumn) {
                        cell.setContains(number);
                    }
                }
            }
        }
    }

    public static void checkOneInAColumn(int column, ArrayList<Cell> board){
        for (int number = 1; number < 10; number++) {
            boolean isOnlyOne = true;
            boolean isPresent = false;
            int cellRow = -1;
            int cellColumn = -1;
            for (Cell cell : board) {
                if (cell.getColumn() == column && cell.getContains() == 0){
                    for (String canContain : cell.getCanContain()){
                        if(number == Integer.parseInt(canContain)){
                            if (isPresent){
                                isOnlyOne = false;
                            }
                            isPresent = true;
                            cellRow = cell.getRow();
                            cellColumn = cell.getColumn();
                        }
                    }
                }
            }
            if (isPresent && isOnlyOne) {
                for (Cell cell : board) {
                    if (cell.getRow() == cellRow && cell.getColumn() == cellColumn) {
                        cell.setContains(number);
                    }
                }
            }
        }
    }

    public static void checkOneInASquare(int square, ArrayList<Cell> board){
        for (int number = 1; number < 10; number++) {
            boolean isOnlyOne = true;
            boolean isPresent = false;
            int cellRow = -1;
            int cellColumn = -1;
            for (Cell cell : board) {
                if (cell.getSquare() == square && cell.getContains() == 0){
                    for (String canContain : cell.getCanContain()){
                        if(number == Integer.parseInt(canContain)){
                            if (isPresent){
                                isOnlyOne = false;
                            }
                            isPresent = true;
                            cellRow = cell.getRow();
                            cellColumn = cell.getColumn();
                        }
                    }
                }
            }
            if (isPresent && isOnlyOne) {
                for (Cell cell : board) {
                    if (cell.getRow() == cellRow && cell.getColumn() == cellColumn) {
                        cell.setContains(number);
                    }
                }
            }
        }
    }
}
