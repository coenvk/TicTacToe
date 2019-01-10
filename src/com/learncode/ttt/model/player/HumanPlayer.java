package com.learncode.ttt.model.player;

import com.learncode.ttt.model.Board;
import com.learncode.ttt.model.Mark;
import com.learncode.ttt.util.InputUtil;

/**
 * Created by Coen on 11-7-2017.
 */
public class HumanPlayer extends Player {

    public HumanPlayer(String name, Mark mark) {
        super(name, mark);
    }

    @Override
    public int getMove(Board board) {
        while (true) {
            int input = InputUtil.readInt("> " + this + ", where do you want to put your mark? (0-" + (Board.CELLS - 1) + ") ");
            if (Board.isCell(input) && !board.isOccupiedCell(input)) {
                return input;
            } else {
                System.out.println("Invalid move!");
            }
        }
    }

}
