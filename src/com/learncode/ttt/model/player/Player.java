package com.learncode.ttt.model.player;

import com.learncode.ttt.model.Board;
import com.learncode.ttt.model.Mark;

/**
 * Created by Coen on 11-7-2017.
 */
public abstract class Player {

    protected String name;
    protected Mark mark;

    public Player(String name, Mark mark) {
        this.name = name;
        this.mark = mark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Mark getMark() {
        return mark;
    }

    public abstract int getMove(Board board);

    public final void doMove(Board board) {
        int move = getMove(board);
        board.setCell(move, mark);
    }

    @Override
    public String toString() {
        return getName() + "{"+ getMark() + "}";
    }
}
