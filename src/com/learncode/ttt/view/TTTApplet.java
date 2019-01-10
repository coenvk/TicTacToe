package com.learncode.ttt.view;

import com.learncode.ttt.controller.Game;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by Coen on 12-7-2017.
 */
public class TTTApplet extends JApplet {

    @Override
    public void init() {
        try {
            SwingUtilities.invokeAndWait(() -> setContentPane(new GUIView(new Game())));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
