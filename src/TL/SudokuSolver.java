package TL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class SudokuSolver {
    public static ArrayList<Cell> board;
    public static ArrayList<ArrayList<Cell>> rows = new ArrayList<ArrayList<Cell>>();
    public static ArrayList<ArrayList<Cell>> columns= new ArrayList<ArrayList<Cell>>();
    public static ArrayList<ArrayList<Cell>> squares= new ArrayList<ArrayList<Cell>>();
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
	    SudokuSolver solver = new SudokuSolver();
        board = solver.createBoard();
        solver.classifyBoardContent();

        //parse user input
        String input = solver.parseInput();
        solver.addValues(input);

        //add suggestions to unknown cells
        solver.createBasicSuggestions();

        int counter = 0;
        while (solver.isSolved()) {
            solver.loopedCheck(1);
            solver.updateContent();
            counter++;
            //System.out.println(board);

            if (counter == 50){
                while (solver.isSolved()){
                    solver.alternativesCheck();
                }
            }
        }

        solver.printOut();
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

    public void classifyBoardContent(){
        for (int i = 0; i < 9; i++) {
            rows.add(new ArrayList<Cell>());
            columns.add(new ArrayList<Cell>());
            squares.add(new ArrayList<Cell>());

            for (Cell cell : board){
                if (cell.getRow() == i){
                    rows.get(i).add(cell);
                }
                if (cell.getColumn() == i){
                    columns.get(i).add(cell);
                }
                if (cell.getSquare() == i){
                    squares.get(i).add(cell);
                }
            }
        }
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

    public void checkBoard() {
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

    public void loopedCheck(int number){
        for(int i = 0; i < number; i++){
            checkBoard();
            for (int j = 0; j < 9; j++) {
                checkOneInARow(j);
                checkOneInAColumn(j);
                checkOneInASquare(j);
            }
            updateContent();
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

    public void alternativesCheck() {

        boolean solved = false;
        int alternativesCounter = 0;
        for (Cell cell : board) {
            if (cell.getContains() == 0) {


                String content = cell.getCanContain().get(0);
                String boardContent = saveContent(board);
                cell.setContains(Integer.parseInt(content));
                loopedCheck(15);
                System.out.println("noDuplicates: " + noDuplicates() + ", !isSolved:" + !isSolved());
                printOut();
                if (noDuplicates() && !isSolved()) {
                    solved = true;
                } else {
                    for (Cell cellToClear : board) {
                        cellToClear.getCanContain().clear();
                    }
                    addValues(boardContent);
                    createBasicSuggestions();
                    loopedCheck(3);
                    alternativesCounter++;
                    System.out.println(alternativesCounter);
                    cell.getCanContain().remove(0);
                }


                if (solved) break;

            }
        }

    }

    public boolean noDuplicates(){
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
    
    public String saveContent(ArrayList<Cell> board){
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
        String out = "";
        for (int i = 0; i < 9; i++) {
            for (int k = 0; k < 9; k++) {

                for (Cell cell : board) {
                    if (cell.getRow() == i && cell.getColumn() == k) {
                        out = out + cell.getContains();
                        if (k == 2 || k == 5 ){out = out + " ";}//TODO delete it
                    }
                }
            }
            out = out + "\n";
            if (i == 2 || i == 5 ){out = out + "\n";}//TODO delete it

        }
        System.out.println(out);
    }
}
