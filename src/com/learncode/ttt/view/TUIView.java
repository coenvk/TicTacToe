package com.learncode.ttt.view;

import com.learncode.ttt.controller.Game;
import com.learncode.ttt.util.InputUtil;
import com.learncode.ttt.model.player.Player;

/**
 * Created by Coen on 11-7-2017.
 */
public class TUIView implements View {

    private Game controller;

    public TUIView(Game controller) {
        this.controller = controller;
    }

    @Override
    public void start() {
        boolean again = true;
        System.out.print("Player1, ");
        String player1Name = readName();
        System.out.print("Player2, ");
        String player2Name = readName();
        controller.createPlayers(player1Name, player2Name);
        while (again) {
            controller.reset();
            showBoard();
            showGameState();
            while (!controller.gameOver()) {
                showCurrentPlayer();
                controller.takeTurn();
                showBoard();
                showGameState();
            }
            again = readAgain();
        }
    }

    @Override
    public void showBoard() {
        System.out.println(controller.getBoard());
    }

    @Override
    public void showCurrentPlayer() {
        System.out.println("Current Player: " + controller.getCurrentPlayer());
    }

    @Override
    public void showPlayers() {
        System.out.println("Players: " + controller.getCurrentPlayer() + ", " + controller.getOpponent(controller.getCurrentPlayer()));
    }

    @Override
    public void showGameState() {
        String gameState = "PLAYING";
        Player currentPlayer = controller.getCurrentPlayer();
        Player opponent = controller.getOpponent(currentPlayer);
        if (controller.isWinner(currentPlayer)) {
            gameState = "WON\n" + currentPlayer + " has won!";
        } else if (controller.isWinner(opponent)) {
            gameState = "WON\n" + opponent + " has won!";
        } else if (controller.getBoard().isDraw()) {
            gameState = "DRAW";
        }
        System.out.println("Current state of the util: " + gameState);
    }

    @Override
    public boolean readAgain() {
        return InputUtil.readBoolean("Do you want to play again? (y/n) ");
    }

    @Override
    public String readName() {
        return InputUtil.readString("what is your name? (if you want to play as an AI" +
                    ", type /dumbai, /smartai or /minimaxai) ");
    }

}
