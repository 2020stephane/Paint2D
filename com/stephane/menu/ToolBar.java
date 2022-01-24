/*******************************************************************************
 * Copyright (c) 2022.
 * Modifie le :21/01/2022 09:14
 * Auteur : Stephane Brisse
 * IDE : INTELLIJ IDEA
 * Language : JAVA
 ******************************************************************************/
package com.stephane.menu;

import com.stephane.MyPanel;
import com.stephane.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static com.stephane.tools.Shape.*;

public class ToolBar implements ActionListener {

    private JButton btnColor;
    private JButton btnUndo;
    private JButton btnRedo;
    private JButton btnNew;
    private JButton btnOpen;
    private final JDialog colorDialog;
    private final JColorChooser colorChooser;
    private JComboBox<String> cbTools;
    private JComboBox<String> cbStrokeWidth;
    private final JToolBar toolBar;

    public ToolBar() {

        colorDialog = new JDialog(Main.getFrame(), "Color Chooser", false);
        colorChooser = new JColorChooser(Color.black);
        colorDialog.add(colorChooser);

        toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.setRollover(true);

        toolBar.add(createSeparator());
        toolBar.add(newImage());
        toolBar.add(openImage());
        toolBar.add(saveImage());
        toolBar.add(createSeparator());
        toolBar.add(cutImage());
        toolBar.add(copyImage());
        toolBar.add(createSeparator());
        toolBar.add(undoImage());
        toolBar.add(redoImage());
        toolBar.add(createSeparator());
        toolBar.add(colors());
        toolBar.add(createSeparator());
        toolBar.add(cbTools());
        toolBar.add(createSeparator());
        toolBar.add(cbStrokeWidth());

    }
    public JToolBar getToolbar() {
        return toolBar;
    }

    private JComboBox<String> cbTools() {
        cbTools = new JComboBox<>();

        cbTools.setModel(new javax.swing.DefaultComboBoxModel<>(
                new String[]{"Stylo", "Ligne", "Carre", "Rectangle",
                        "Cercle", "Oval", "Gomme"}));
        cbTools.setMaximumSize(new java.awt.Dimension(140, 25));
        cbTools.addActionListener(this);
        return cbTools;
    }
    private JComboBox<String> cbStrokeWidth() {
        cbStrokeWidth = new JComboBox<>();

        cbStrokeWidth.setModel(new javax.swing.DefaultComboBoxModel<>(
                new String[]{"1", "2", "5", "8", "12", "15", "25", "30"}));
        cbStrokeWidth.setMaximumSize(new java.awt.Dimension(80, 25));
        cbStrokeWidth.addActionListener(this);
        return cbStrokeWidth;
    }

    private JButton newImage() {
        btnNew = new JButton();
        btnNew.setIcon(new ImageIcon(Objects.requireNonNull(getClass()
                .getResource("../icon/newFile.png"))));
        btnNew.addActionListener(this);
        return btnNew;
    }

    private JButton openImage() {
        btnOpen = new JButton();
        btnOpen.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(
                "../icon/openFile.png"))));
        btnOpen.addActionListener(this);
        return btnOpen;
    }

    private JButton saveImage() {
        JButton btnSave = new JButton();
        btnSave.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(
                "../icon/save.png"))));
        btnSave.addActionListener(this);
        return btnSave;
    }

    private JButton cutImage() {
        JButton btnCut = new JButton();
        btnCut.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(
                "../icon/scissors.png"))));
        btnCut.addActionListener(this);
        return btnCut;
    }

    private JButton copyImage() {
        JButton btnCopy = new JButton();
        btnCopy.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(
                "../icon/copy.png"))));
        btnCopy.addActionListener(this);
        return btnCopy;
    }


    private JButton undoImage() {
        btnUndo = new JButton();
        btnUndo.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(
                "../icon/undo.png"))));
        btnUndo.addActionListener(this);
        return btnUndo;
    }

    private JButton redoImage() {
        btnRedo = new JButton();
        btnRedo.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(
                "../icon/redo.png"))));
        btnRedo.addActionListener(this);
        return btnRedo;
    }

    private JButton colors() {
        btnColor = new JButton();
        btnColor.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(
                "../icon/palete.png"))));
        btnColor.addActionListener(this);
        return btnColor;
    }

    private JToolBar.Separator createSeparator() {

        return new JToolBar.Separator();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object event = e.getSource();

        if (event == btnColor) {
            actionColorPicker();
        } else if (event == cbTools) {
            actionPickTools(e);
        } else if (event == cbStrokeWidth){
            actionStrokeWidth(e);
        } else if ( event == btnUndo) {
            actionUndo(e);
        } else if ( event == btnRedo) {
            actionRedo(e);
        } else if (event == btnNew) {
            Main.getMypanel().clearBufferImage();
        } else if ( event == btnOpen) {
            actionOpen(e);
        }
    }

    private void actionColorPicker() {
        colorDialog.setVisible(true);
        colorDialog.setBounds(300, 250, 620, 350);
        colorChooser.getSelectionModel().addChangeListener(
                e -> {
                    Color newColor = colorChooser.getColor();
                    if (newColor != null) {
                        MyPanel.setColor(newColor);
                    }

                });
    }
    private void actionPickTools(ActionEvent e) {
        JComboBox<?> cb = (JComboBox<?>) e.getSource();
        String string = (String) cb.getSelectedItem();
        switch (Objects.requireNonNull(string)) {
            case "Stylo" -> MyPanel.setShape(PENCIL);
            case "Ligne" -> MyPanel.setShape(LINE);
            case "Carre" -> MyPanel.setShape(SQUARE);
            case "Rectangle" -> MyPanel.setShape(RECTANGLE);
            case "Oval" -> MyPanel.setShape(OVAL);
            case "Cercle" -> MyPanel.setShape(CIRCLE);
            case "Gomme" -> MyPanel.setShape(ERASER);

        }
    }
    private void actionStrokeWidth(ActionEvent e) {
        JComboBox<?> cb = (JComboBox<?>) e.getSource();
        String str = (String) cb.getSelectedItem();
        assert str != null;
        int intsw = Integer.parseInt(str);
        MyPanel.setStrokeWidth(intsw);

    }
    private  void actionOpen(ActionEvent e) {

            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "jpg, gif, png images", "jpg", "gif", "png");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(null);

            if (returnVal == JFileChooser.CANCEL_OPTION) {
            } else if (returnVal == JFileChooser.APPROVE_OPTION) {
                File imageInput = chooser.getSelectedFile();
                try {
                    BufferedImage bufferedImage = ImageIO.read(imageInput);
                    MyPanel.setBufferImage(bufferedImage) ;


                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
    }
    private void actionUndo(ActionEvent e) {
        MyPanel.setUndo(true);
    }
    private void actionRedo(ActionEvent e) { }

}
