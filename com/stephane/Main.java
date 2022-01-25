
/*******************************************************************************
 * Copyright (c) 2022.
 * Modifie le :21/01/2022 08:55
 * Auteur : Stephane Brisse
 * IDE : INTELLIJ IDEA
 * Language : JAVA
 ******************************************************************************/

package com.stephane;

import com.stephane.menu.MenuBar;
import com.stephane.menu.StatusBar;
import com.stephane.menu.ToolBar;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Main {

    private static JFrame frame = null;
    private static MyPanel mypanel;
    private static StatusBar mystatusbar;

    public Main() {

        frame = new MyFrame();
    }

    public static JFrame getFrame() { return frame; }
    public static MyPanel getMypanel() { return mypanel; }
    public static StatusBar getStatusBar() { return mystatusbar; }
    
    public static class MyFrame extends JFrame {

        public MyFrame() {
            this.setTitle("Paint2D");
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ImageIcon logo = new ImageIcon(Objects.requireNonNull
                    (getClass().getResource("icon/moon.png")));
            setIconImage(logo.getImage());
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            int height = dim.height;
            int width = dim.width;
            this.setLocation(width / 4, height / 4);
            this.setSize(width / 2, height / 2);
            mypanel = new MyPanel();
            MenuBar mymenubar = new MenuBar();
            ToolBar mytoolbar = new ToolBar();
            mystatusbar = new StatusBar();
            this.setJMenuBar(mymenubar.getMenuBar());
            this.add(mystatusbar, BorderLayout.PAGE_END);
            this.add(mytoolbar.getToolbar(), BorderLayout.PAGE_START);
            this.add(mypanel, BorderLayout.CENTER);
            this.setVisible(true);
        }

    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(Main::new);
    }
}

