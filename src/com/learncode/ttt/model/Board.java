package com.learncode.ttt.model;

/**
 * Created by Coen on 11-7-2017.
 */
public class Board {

    public static final int ROWS = 3;
    public static final int COLUMNS = 3;
    public static final int CELLS = ROWS * COLUMNS;
    public static String LINE = "-";
    static {
        for (int i = 0; i < COLUMNS; i++) {
            LINE += "----";
        }
    }

    private Cell[] cells;

    public Board() {
        reset();
    }

    public Board copy() {
        Board copy = new Board();
        for (int i = 0; i < Board.CELLS; i++) {
            copy.setCell(i, this.getCell(i).getMark());
        }
        return copy;
    }

    public void reset() {
        this.cells = new Cell[CELLS];
        for (int i = 0; i < cells.length; i++) {
            cells[i] = new Cell(i);
        }
    }

    public static int getRow(int index) {
        return index / COLUMNS;
    }

    public static int getColumn(int index) {
        return index % COLUMNS;
    }

    public static int getIndex(int row, int column) {
        return column + row * COLUMNS;
    }

    public static boolean isNthRow(int n, int index) {
        return (index / COLUMNS == n);
    }

    public static boolean isNthColumn(int n, int index) {
        return (index % COLUMNS == n);
    }

    public Cell getCell(int index) {
        return cells[index];
    }

    public Cell getCell(int row, int column) {
        return cells[getIndex(row, column)];
    }

    public Cell[] getCells() {
        return cells;
    }

    public void setCell(int index, Mark mark) {
        cells[index].occupy(mark);
    }

    public void setCell(int row, int column, Mark mark) {
        setCell(getIndex(row, column), mark);
    }

    public static boolean isCell(int index) {
        return 0 <= index && index < CELLS;
    }

    public static boolean isCell(int row, int column) {
        return isCell(getIndex(row, column));
    }

    public boolean isOccupiedCell(int index) {
        return cells[index].isOccupied();
    }

    public boolean isOccupiedCell(int row, int column) {
        return isOccupiedCell(getIndex(row, column));
    }

    public boolean hasWinner() {
        return isWinner(Mark.CROSS) || isWinner(Mark.NOUGHT);
    }

    public boolean isWinner(Mark mark) {
        return hasRow(mark) || hasColumn(mark) || hasDiagonal(mark);
    }

    public boolean hasDiagonal(Mark mark) {
        if (ROWS != COLUMNS) {
            return false;
        }
        boolean hasLeftToRight = true;
        boolean hasRightToLeft = true;
        for (int i = 0; i < ROWS; i++) {
            if (!getCell(i, i).isOccupiedBy(mark)) {
                hasLeftToRight = false;
                break;
            }
        }
        for (int i = 0; i < COLUMNS; i++) {
            if (!getCell(i, COLUMNS - i - 1).isOccupiedBy(mark)) {
                hasRightToLeft = false;
                break;
            }
        }
        return hasLeftToRight || hasRightToLeft;
    }

    public boolean hasRow(Mark mark) {
        for (int row = 0; row < ROWS; row++) {
            boolean hasRow = true;
            for (int column = 0; column < COLUMNS; column++) {
                if (getCell(row, column).getMark() != mark) {
                    hasRow = false;
                    break;
                }
            }
            if (hasRow) {
                return true;
            }
        }
        return false;
    }

    public boolean hasColumn(Mark mark) {
        for (int column = 0; column < COLUMNS; column++) {
            boolean hasColumn = true;
            for (int row = 0; row < ROWS; row++) {
                if (getCell(row, column).getMark() != mark) {
                    hasColumn = false;
                    break;
                }
            }
            if (hasColumn) {
                return true;
            }
        }
        return false;
    }

    public boolean isDraw() {
        return isFull() && !hasWinner();
    }

    public boolean isFull() {
        for (int i = 0; i < CELLS; i++) {
            if (!cells[i].isOccupied()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        String result = LINE + "\n";
        for (int y = 0; y < ROWS; y++) {
            result += "|";
            for (int x = 0; x < COLUMNS; x++) {
                result += " " + cells[getIndex(y, x)].getMark() + " |";
            }
            result += "\n" + LINE + "\n";
        }
        return result;
    }

}
