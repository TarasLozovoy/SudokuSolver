package TL;


import TL.Checkers.BasicChecker;
import TL.Checkers.OneInAStructureChecker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Instruments {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static ArrayList<Cell> createBoard(){
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

    public static void classifyBoardContent(ArrayList<Cell> board, ArrayList<ArrayList<Cell>> rows, ArrayList<ArrayList<Cell>> columns, ArrayList<ArrayList<Cell>>squares){
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

    public static String parseInput(){
        String input ="";
        int counter = 0;
        while (true){

            try {
                if (counter == 9) break;
                String read = reader.readLine();
                if (read.equals("")) break;
                else input = input + read;
                counter++;

            } catch (IOException ex){ex.printStackTrace();}
        }
        return input;
    }

    public static void addValues(String input, ArrayList<Cell> board){
        for (int i = 0; i < 81; i++) {
            board.get(i).setContains(Integer.parseInt("" + input.charAt(i)));
        }
    }

    public static void createBasicSuggestions(ArrayList<Cell> board){
        for (int i = 0; i < 81; i++) {
            Cell cell = board.get(i);
            if (cell.getContains() == 0){
                cell.setCanContain(new ArrayList<String>(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9")));
            }
        }
    }



    public static void loopedCheck(int number, ArrayList<Cell> board, ArrayList<ArrayList<Cell>> rows, ArrayList<ArrayList<Cell>> columns, ArrayList<ArrayList<Cell>>squares){
        for(int i = 0; i < number; i++){
            BasicChecker.checkBoard(rows, columns, squares);
//            for (int j = 0; j < 9; j++) {
//                OneInAStructureChecker.checkOneInARow(j, board);
//                OneInAStructureChecker.checkOneInAColumn(j, board);
//                OneInAStructureChecker.checkOneInASquare(j, board);
//            }
            updateContent(board);
        }
    }


    public static void updateContent(ArrayList<Cell> board){
        for (Cell cell : board){
            if(cell.getContains() == 0 && cell.getCanContain().size() == 1){
                int update = Integer.parseInt(cell.getCanContain().get(0));
                cell.setContains(update);
            }
        }
    }


    public static boolean isSolved(ArrayList<Cell> board){
        boolean solve = false;
        for (Cell cell : board){
            if(cell.getContains() == 0){
                solve = true;
            }
        }
        return solve;
    }

    public static void printOut (ArrayList<Cell> board){
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
        System.out.println("Solwed!");
        System.out.println(out);
    }
}
