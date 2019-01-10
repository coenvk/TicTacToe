package com.learncode.ttt.model.player.ai;

import com.learncode.ttt.model.Board;
import com.learncode.ttt.model.Mark;

/**
 * Created by Coen on 11-7-2017.
 */
public class SmartAI extends AI {

    public SmartAI(Mark mark) {
        super("SmartAI", mark);
    }

    @Override
    public int getMove(Board board) {
        int index = Board.getIndex(Board.ROWS / 2, Board.COLUMNS / 2);
        if (!board.isOccupiedCell(index)) {
            return index;
        }
        for (int i = 0; i < Board.CELLS; i++) {
            Board copy = board.copy();
            if (!copy.isOccupiedCell(i)) {
                copy.setCell(i, mark);
                if (copy.isWinner(mark)) {
                    return i;
                }
            }
        }
        for (int i = 0; i < Board.CELLS; i++) {
            Board copy = board.copy();
            if (!copy.isOccupiedCell(i)) {
                copy.setCell(i, mark.getOther());
                if (copy.isWinner(mark.getOther())) {
                    return i;
                }
            }
        }
        return new DumbAI(mark).getMove(board);
    }

}
