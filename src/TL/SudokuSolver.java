package TL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class SudokuSolver {
    public static ArrayList<Cell> board;
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
	    SudokuSolver solver = new SudokuSolver();
        board = solver.createBoard();

        //parse user input
        String input = solver.parseInput();
        solver.addValues(input);

        //add suggestions to unknown cells
        solver.createBasicSuggestions();

        while (solver.isSolved()) {
            for (int i = 0; i < 9; i++) {
                solver.checkRow(i);
                solver.checkOneInARow(i);
            }
            for (int i = 0; i < 9; i++) {
                solver.checkColumn(i);
                solver.checkOneInAColumn(i);
            }
            for (int i = 0; i < 9; i++) {
                solver.checkSquare(i);
                solver.checkOneInASquare(i);
            }
            solver.updateContent();
            //System.out.println(board);
        }

        solver.printOut();



        //debug printout
        //System.out.println(board);





    }

    public ArrayList<Cell> createBoard(){
        ArrayList<Cell> board = new ArrayList<Cell>();
        int square =0;
        for (int row = 0; row < 9; row++) { //row
            for (int column = 0; column < 9; column++) { //column
                if (row < 3 && column < 3) square = 0;
                else if (row < 3 && column <6) square = 1;
                else if (row < 3 && column <9) square = 2;
                else if (row < 6 && column <3) square = 3;
                else if (row < 6 && column <6) square = 4;
                else if (row < 6 && column <9) square = 5;
                else if (row < 9 && column <3) square = 6;
                else if (row < 9 && column <6) square = 7;
                else if (row < 9 && column <9) square = 8;
                Cell cell = new Cell(row,column,square);
                board.add(cell);
            }
        }
        return board;
    }

    public String parseInput(){
        String input ="";
        int counter = 0;
        while (true){

            try {
                if (counter == 9) break;
                String read = reader.readLine();
                if (read.equals("")) break;
                else input = input + read;
                counter++;

            } catch (IOException ex){}
        }
        return input;
    }

    public void addValues(String input){
        for (int i = 0; i < 81; i++) {
            board.get(i).setContains(Integer.parseInt("" + input.charAt(i)));
        }
    }

    public void createBasicSuggestions(){
        for (int i = 0; i < 81; i++) {
            Cell cell = board.get(i);
            if (cell.getContains() == 0){
                cell.setCanContain(new ArrayList<String>(Arrays.asList("1","2","3","4","5","6","7","8","9")));
            }
        }
    }

    public void checkRow(int row){
        for (int i = 0; i < 81; i++) {
            Cell cell = board.get(i);
            int  cellContent = cell.getContains();
            if (cell.getRow() == row) {
                if (cellContent != 0) {
                    for (Cell cellToUpdate : board){
                        if (cellToUpdate.getRow() == row && cellToUpdate.getCanContain().contains("" + cellContent)){
                            ArrayList<String> updated = cellToUpdate.getCanContain();
                            updated.remove(updated.indexOf("" + cellContent));
                            cellToUpdate.setCanContain(updated);
                        }
                    }
                }
            }
        }
    }

    public void checkColumn(int column){
        for (int i = 0; i < 81; i++) {
            Cell cell = board.get(i);
            int  cellContent = cell.getContains();
            if (cell.getColumn() == column) {
                if (cellContent != 0) {
                    for (Cell cellToUpdate : board){
                        if (cellToUpdate.getColumn() == column && cellToUpdate.getCanContain().contains("" + cellContent)){
                            ArrayList<String> updated = cellToUpdate.getCanContain();
                            updated.remove(updated.indexOf("" + cellContent));
                            cellToUpdate.setCanContain(updated);
                        }
                    }
                }
            }
        }
    }


    public void checkSquare(int square){
        for (int i = 0; i < 81; i++) {
            Cell cell = board.get(i);
            int  cellContent = cell.getContains();
            if (cell.getSquare() == square) {
                if (cellContent != 0) {
                    for (Cell cellToUpdate : board){
                        if (cellToUpdate.getSquare() == square && cellToUpdate.getCanContain().contains("" + cellContent)){
                            ArrayList<String> updated = cellToUpdate.getCanContain();
                            updated.remove(updated.indexOf("" + cellContent));
                            cellToUpdate.setCanContain(updated);
                        }
                    }
                }
            }
        }
    }

    public void checkOneInARow(int row){
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

    public void checkOneInAColumn(int column){
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

    public void checkOneInASquare(int square){
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

    public void updateContent(){
        for (Cell cell : board){
            if(cell.getContains() == 0 && cell.getCanContain().size() == 1){
                int update = Integer.parseInt(cell.getCanContain().get(0));
                cell.setContains(update);
            }
        }
    }


    public boolean isSolved(){
        boolean solve = false;
        for (Cell cell : board){
            if(cell.getContains() == 0){
                solve = true;
            }
        }
        return solve;
    }

    public void printOut (){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board.get(2 * i + j).getContains());
            }
            System.out.println();
        }
    }
}
