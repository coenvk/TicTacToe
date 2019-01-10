package com.learncode.ttt.model.player.ai;

import com.learncode.ttt.model.Board;
import com.learncode.ttt.model.Mark;

/**
 * Created by Coen on 11-7-2017.
 */
public class MinimaxAI extends AI {

    public MinimaxAI(Mark mark) {
        super("MinimaxAI", mark);
    }

    @Override
    public int getMove(Board board) {
        return 0;
    }

}
