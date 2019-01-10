package com.learncode.ttt.controller;

import com.learncode.ttt.model.Board;
import com.learncode.ttt.model.Mark;
import com.learncode.ttt.model.player.HumanPlayer;
import com.learncode.ttt.model.player.Player;
import com.learncode.ttt.model.player.ai.DumbAI;
import com.learncode.ttt.model.player.ai.MinimaxAI;
import com.learncode.ttt.model.player.ai.SmartAI;
import com.learncode.ttt.view.GUIView;
import com.learncode.ttt.view.TUIView;
import com.learncode.ttt.view.View;

/**
 * Created by Coen on 11-7-2017.
 */
public class Game {

    private Player[] players;
    private int currentPlayer;
    private Board board;
    private View view;

    public Game() {
        this("Player1", "Player2");
    }

    public Game(String player1Name, String player2Name) {
        board = new Board();
        players = new Player[2];
        players[0] = new HumanPlayer(player1Name, Mark.CROSS);
        players[1] = new HumanPlayer(player2Name, Mark.NOUGHT);
        currentPlayer = 0;
    }

    public static void main(String[] args) {
        new Game().start();
    }

    public void start() {
        view = new GUIView(this);
        view.start();
    }

    public void reset() {
        currentPlayer = 0;
        board.reset();
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public void createPlayers(String player1Name, String player2Name) {
        Player[] players = new Player[2];
        Player player1 = null;
        Player player2 = null;
        if (player1Name.toLowerCase().equals("/dumbai")) {
            player1 = new DumbAI(Mark.CROSS);
        } else if (player1Name.toLowerCase().equals("/smartai")) {
            player1 = new SmartAI(Mark.CROSS);
        } else if (player1Name.toLowerCase().equals("/minimaxai")) {
            player1 = new MinimaxAI(Mark.CROSS);
        } else {
            player1 = new HumanPlayer(player1Name, Mark.CROSS);
        }
        if (player2Name.toLowerCase().equals("/dumbai")) {
            player2 = new DumbAI(Mark.NOUGHT);
        } else if (player2Name.toLowerCase().equals("/smartai")) {
            player2 = new SmartAI(Mark.NOUGHT);
        } else if (player2Name.toLowerCase().equals("/minimaxai")) {
            player2 = new MinimaxAI(Mark.NOUGHT);
        } else {
            player2 = new HumanPlayer(player2Name, Mark.NOUGHT);
        }
        players[0] = player1;
        players[1] = player2;
        setPlayers(players);
    }

    public Player getCurrentPlayer() {
        return players[currentPlayer];
    }

    public Player getPlayer(Mark mark) {
        if (getCurrentPlayer().getMark() == mark) {
            return getCurrentPlayer();
        } else {
            return getOpponent(getCurrentPlayer());
        }
    }

    public Board getBoard() {
        return board;
    }

    public boolean isWinner(Player player) {
        return board.isWinner(player.getMark());
    }

    public boolean gameOver() {
        return board.isFull() || board.hasWinner();
    }

    public Player getOpponent(Player player) {
        if (player.equals(players[0])) {
            return players[1];
        }
        return players[0];
    }

    public void takeTurn() {
        getCurrentPlayer().doMove(board);
        changePlayer();
    }

    public void takeTurn(int index) {
        board.setCell(index, getCurrentPlayer().getMark());
        changePlayer();
    }

    public void changePlayer() {
        currentPlayer = ++currentPlayer % 2;
    }

}
