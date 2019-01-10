package com.learncode.ttt.view;

/**
 * Created by Coen on 11-7-2017.
 */
public interface View {

    void start();
    void showBoard();
    void showCurrentPlayer();
    void showGameState();
    void showPlayers();
    boolean readAgain();
    String readName();

}
