package com.learncode.ttt.view;

import com.learncode.ttt.controller.Game;
import com.learncode.ttt.model.Board;
import com.learncode.ttt.model.Mark;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CellPanel extends JPanel implements MouseListener {

    private static final int DRAW_OFFSET = 20;
    private static final Dimension SIZE = new Dimension(100, 100);

    private int index;
    private GUIView view;

    public CellPanel(GUIView view, int index) {
        super(new GridBagLayout());
        this.index = index;
        this.view = view;
        setPreferredSize(SIZE);
        addMouseListener(this);
        validate();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.DARK_GRAY);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        Board board = view.getController().getBoard();
        drawGrid(g2d);
        drawMark(board, g2d);
    }

    private void drawMark(Board board, Graphics2D g2d) {
        g2d.setStroke(new BasicStroke(18, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        if (board.getCell(index).isOccupied()) {
            Mark mark = board.getCell(index).getMark();
            if (mark == Mark.NOUGHT) {
                g2d.setColor(Color.BLUE);
                g2d.drawOval(DRAW_OFFSET, DRAW_OFFSET, getWidth() - DRAW_OFFSET * 2, getHeight() - DRAW_OFFSET * 2);
            } else if (mark == Mark.CROSS) {
                g2d.setColor(Color.YELLOW);
                g2d.drawLine(DRAW_OFFSET, DRAW_OFFSET, getWidth() - DRAW_OFFSET, getHeight() - DRAW_OFFSET);
                g2d.drawLine(getWidth() - DRAW_OFFSET, DRAW_OFFSET, DRAW_OFFSET, getHeight() - DRAW_OFFSET);
            }
        }
    }

    private void drawGrid(Graphics2D g2d) {
        g2d.setStroke(new BasicStroke(8, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        int x1 = 0;
        int x2 = getWidth();
        if (Board.isNthColumn(0, index)) {
            x1 += DRAW_OFFSET;
        } else if (Board.isNthColumn(Board.COLUMNS - 1, index)) {
            x2 -= DRAW_OFFSET;
        }
        int y1 = 0;
        int y2 = getHeight();
        if (Board.isNthRow(0, index)) {
            y1 += DRAW_OFFSET;
        } else if (Board.isNthRow(Board.ROWS - 1, index)) {
            y2 -= DRAW_OFFSET;
        }
        if (!Board.isNthColumn(Board.COLUMNS - 1, index)) {
            g2d.drawLine(getWidth(), y1, getWidth(), y2);
        }
        if (!Board.isNthColumn(0, index)) {
            g2d.drawLine(0, y1, 0, y2);
        }
        if (!Board.isNthRow(Board.ROWS - 1, index)) {
            g2d.drawLine(x1, getHeight(), x2, getHeight());
        }
        if (!Board.isNthRow(0, index)) {
            g2d.drawLine(x1, 0, x2, 0);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Game controller = view.getController();
        if (!controller.gameOver()) {
            if (!controller.getBoard().getCell(index).isOccupied()) {
                controller.takeTurn(index);
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                repaint();
            }
        }
        try {
            Thread.sleep(2);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
