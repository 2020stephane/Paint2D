/*******************************************************************************
 * Copyright (c) 2022. Auteur : Stephane Brisse
 * IDE : INTELLIJ IDEA
 * Language : JAVA
 ******************************************************************************/

package com.stephane.menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class HelpMenu implements ActionListener {

    JMenuItem mabout;
    JMenuItem mihelp;

    public HelpMenu() {
    }

    public JMenu helpItems() {

        JMenu mHelp = new JMenu("Help");
        mHelp.add(menuhelp());
        mHelp.add(menuabout());
        return mHelp;
    }
    public JMenuItem menuabout() {

        mabout = new JMenuItem("About");
        mabout.setAccelerator(KeyStroke.getKeyStroke("ctrl A"));
        mabout.addActionListener(this);
        return mabout;
    }
    public JMenuItem menuhelp() {
        mihelp = new JMenuItem("Help");
        mihelp.setAccelerator(KeyStroke.getKeyStroke("ctrl H"));
        mihelp.addActionListener(this);
        return mihelp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object event = e.getSource();

        if (event == mabout) {
            actionabout();
        }
    }
    public void actionabout() {

        AboutDialog mydialog = new AboutDialog("About Paint 2D");

    }
}
