/*
 * Copyright (c) 2022. Stephane Brisse
 */

package com.stephane.menu;

import com.stephane.MyPanel;


import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FileMenu implements ActionListener {


    private JMenuItem mOpen;
    private JMenuItem mNew;

    public FileMenu() {

    }

    public JMenu fileItems() {
        JMenu mFile = new JMenu("File");

        mFile.add(fileNew());
        mFile.add(fileOpen());
        mFile.add(fileSave());
        mFile.add(fileSaveAs());
        mFile.add(fileExit());
        return mFile;
    }
    private JMenuItem fileNew() {
        mNew = new JMenuItem("New");
        mNew.setAccelerator(KeyStroke.getKeyStroke("ctrl N"));
        mNew.addActionListener(this);
        return mNew;
    }

    private JMenuItem fileOpen() {
        mOpen = new JMenuItem("Open...");
        mOpen.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
        mOpen.addActionListener(this);
        return mOpen;
    }

    private JMenuItem fileSave() {
        JMenuItem mSave = new JMenuItem("Save");
        mSave.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
        mSave.addActionListener(this);
        return mSave;
    }

    private JMenuItem fileSaveAs() {
        JMenuItem mSaveAs = new JMenuItem("Save as...");
        mSaveAs.setAccelerator(KeyStroke.getKeyStroke("ctrl alt S"));
        mSaveAs.addActionListener(this);
        return mSaveAs;
    }
    private JMenuItem fileExit() {
        JMenuItem mExit = new JMenuItem("Exit");
        mExit.setAccelerator(KeyStroke.getKeyStroke("alt F4"));
        mExit.addActionListener(this);
        return mExit;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object event = e.getSource();
        if (event == mOpen) {
            actionfileopen();
        } else if (event == mNew) {
            new MyPanel().setnewall();
        }
    }
    private void actionfileopen() {
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
}
