package com.learncode.ttt.view;

import com.learncode.ttt.controller.Game;
import com.learncode.ttt.model.Board;
import com.learncode.ttt.model.Mark;
import com.learncode.ttt.model.player.ai.AI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Coen on 11-7-2017.
 */
public class GUIView extends JPanel implements View, ActionListener {

    public static final int CELL_SIZE = 100;
    public static final int PANEL_WIDTH = CELL_SIZE * Board.COLUMNS;
    public static final int PANEL_HEIGHT = CELL_SIZE * Board.ROWS;
    public static final int LINE_WIDTH = 8;
    public static final int MARK_SIZE = 2 * CELL_SIZE / 3;

    private Game controller;
    private Label label;
    private JFrame frame;
    private BoardPanel boardPanel;
    private Button againBtn;

    public GUIView(Game controller) {
        super();
        this.controller = controller;
        frame = new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize((Board.COLUMNS + 1) * CELL_SIZE, (Board.ROWS + 1) * CELL_SIZE + 30);
        frame.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        frame.add(this);
        boardPanel = new BoardPanel(this);
        againBtn = new Button("Play again");
        againBtn.addActionListener(this);
        againBtn.setEnabled(false);
        label = new Label();
        JPanel statusBar = new JPanel(new GridLayout(1, 2));
        statusBar.add(label);
        statusBar.add(againBtn);
        add(boardPanel, BorderLayout.CENTER);
        add(statusBar, BorderLayout.PAGE_END);
        frame.pack();
        frame.setResizable(false);
    }

    public static void main(String[] args) {
        new Game().start();
    }

    @Override
    public void start() {
        frame.setVisible(true);
        showPlayers();
        Thread aiThread = new Thread(() -> {
            while (true) {
                update();
                if (!controller.gameOver()) {
                    if (controller.getCurrentPlayer() instanceof AI) {
                        controller.takeTurn();
                    }
                }
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        aiThread.start();
    }

    @Override
    public void showBoard() {
        // cell panel takes care of this
    }

    @Override
    public void showCurrentPlayer() {
        label.setText(controller.getCurrentPlayer() + "'s turn!");
    }

    @Override
    public void showGameState() {
        if (controller.getBoard().isDraw()) {
            label.setText("Game State: DRAW");
        } else if (controller.getBoard().isWinner(Mark.CROSS)) {
            label.setText("Game State: " + controller.getPlayer(Mark.CROSS) + " WON");
        } else if (controller.getBoard().isWinner(Mark.NOUGHT)) {
            label.setText("Game State: " + controller.getPlayer(Mark.NOUGHT) + " WON");
        } else {
            label.setText("Game State: PLAYING");
        }
    }

    @Override
    public void showPlayers() {
        label.setText("Players: " + controller.getCurrentPlayer() + ", " + controller.getOpponent(controller.getCurrentPlayer()));
    }

    @Override
    public boolean readAgain() {
        return false;
    }

    @Override
    public String readName() {
        return null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == againBtn) {
            controller.reset();
            boardPanel.redraw();
        }
        try {
            Thread.sleep(2);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        update();
    }

    public void update() {
        showBoard();
        if (!controller.gameOver()) {
            showCurrentPlayer();
        } else {
            showGameState();
        }
        againBtn.setEnabled(controller.gameOver());
    }

    public Game getController() {
        return controller;
    }

}
