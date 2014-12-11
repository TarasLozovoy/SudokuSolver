package TL;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Cell implements Serializable{
    private int row;
    private int column;
    private int square;
    private ArrayList<String> canContain = new ArrayList<String>();
    private int contains = 0;

    public Cell(int row, int column, int square) {
        this.row = row;
        this.column = column;
        this.square = square;
    }
    public Cell() {
    }

    //setters
    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setSquare(int square) {
        this.square = square;
    }

    public void setCanContain(ArrayList<String> canContain) {
        this.canContain = canContain;
    }

    public void setContains(int contains) {
        this.contains = contains;
    }

    //getters
    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getSquare() {
        return square;
    }

    public ArrayList<String> getCanContain() {
        return canContain;
    }

    public int getContains() {
        return contains;
    }




    //переделать
    @Override
    public String toString() {

            Cell cell = this;
            return ("rcs: " + cell.getRow() + " " + cell.getColumn() + " " + cell.getSquare() + " contains " + cell.getContains() +
             (contains == 0 ? getCanContain() : "")  + "\n");

    }
}
