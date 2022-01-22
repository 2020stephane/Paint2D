package com.stephane.menu;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class StatusBar extends JToolBar {

    private JLabel lblCoord;
    private JLabel lblSize;

    public StatusBar() {
        setFloatable(false);
        setRollover(true);
        setBackground(Color.GRAY);
        add(new JLabel((new ImageIcon(Objects.requireNonNull(getClass().getResource(
                "../icon/coordsPosition.png"))))));
        lblCoord = new JLabel();
        lblCoord.setText("  0 x 0  ");
        add(lblCoord);
        add(new JLabel((new ImageIcon(Objects.requireNonNull(getClass().getResource(
                "../icon/panelSize.png"))))));
        lblSize = new JLabel();
        lblSize.setText("  0 x 0  ");
        add(lblSize);
    }
    public JLabel getLblCoord() { return lblCoord; }
    public JLabel getLblSize() { return  lblSize; }

}
