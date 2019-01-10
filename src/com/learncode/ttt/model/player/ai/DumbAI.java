package com.learncode.ttt.model.player.ai;

import com.learncode.ttt.model.Board;
import com.learncode.ttt.model.Mark;

import java.util.Random;

/**
 * Created by Coen on 11-7-2017.
 */
public class DumbAI extends AI {

    private Random random = new Random();

    public DumbAI(Mark mark) {
        super("DumbAI", mark);
    }

    @Override
    public int getMove(Board board) {
        int move;
        do {
            move = random.nextInt(Board.CELLS);
        } while (board.isOccupiedCell(move));
        return move;
    }

}
