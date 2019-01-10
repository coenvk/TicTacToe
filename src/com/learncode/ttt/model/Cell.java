package com.learncode.ttt.model;

/**
 * Created by Coen on 11-7-2017.
 */
public class Cell {

    private Mark mark;
    private int row, column;

    public Cell(Mark mark, int row, int column) {
        this.mark = mark;
        this.row = row;
        this.column = column;
    }

    public Cell(Mark mark, int index) {
        this(mark, Board.getRow(index), Board.getColumn(index));
    }

    public Cell(int index) {
        this(Mark.NONE, index);
    }

    public Cell(int row, int column) {
        this(Mark.NONE, row, column);
    }

    public int getRow() {
        return row;
    }

    public Mark getMark() {
        return mark;
    }

    public int getColumn() {
        return column;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public boolean isOccupied() {
        return mark != Mark.NONE;
    }

    public boolean isOccupiedBy(Mark mark) {
        return this.mark == mark;
    }

    public void occupy(Mark mark) {
        this.mark = mark;
    }

    public void clear() {
        mark = Mark.NONE;
    }

    @Override
    public String toString() {
        return mark.toString();
    }

}
