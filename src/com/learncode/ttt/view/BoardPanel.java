package com.learncode.ttt.view;

import com.learncode.ttt.model.Board;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {

    private static final Dimension SIZE = new Dimension(400, 400);

    private CellPanel[] cellPanels;

    public BoardPanel(GUIView view) {
        super(new GridLayout(Board.ROWS, Board.COLUMNS));
        this.cellPanels = new CellPanel[Board.CELLS];
        for (int i = 0; i < cellPanels.length; i++) {
            cellPanels[i] = new CellPanel(view, i);
            add(cellPanels[i]);
        }
        setPreferredSize(SIZE);
        validate();
    }

    public void redraw() {
        super.repaint();
        for (int i = 0; i < cellPanels.length; i++) {
            cellPanels[i].repaint();
        }
    }

}
