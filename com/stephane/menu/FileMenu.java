/*
 * Copyright (c) 2022. Stephane Brisse
 */

package com.stephane.menu;

import com.stephane.Main;
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
    private JMenuItem mSave;
    private JMenuItem mSaveAs;

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
        mSave = new JMenuItem("Save");
        mSave.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
        mSave.addActionListener(this);
        return mSave;
    }

    private JMenuItem fileSaveAs() {
        mSaveAs = new JMenuItem("Save as...");
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
            Main.getMypanel().clearBufferImage();
        } else if (event == mSave | event == mSaveAs) {
            actionfilesaveAs(e);
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
    private void actionfilesaveAs(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int dialog = chooser.showSaveDialog(null);

        if (dialog == JFileChooser.CANCEL_OPTION) {
            return;
        } else if (dialog == JFileChooser.OPEN_DIALOG) {
            File myFile = chooser.getSelectedFile();

            if (myFile.exists()) {
                dialog = JOptionPane
                        .showConfirmDialog(null,
                                "A file with same name already exists!\nAre you sure want to overwrite?");

                if (dialog != 0) {
                    return;
                }
            }
            try {
                String filePath = myFile.getPath();
                BufferedImage bi = MyPanel.getBufferImage();
                File outputfile = new File(filePath);
                ImageIO.write(bi, "png", outputfile);
            } catch (IOException e1) {
                JOptionPane.showMessageDialog(null, "Failed to save the file",
                        "Error", JOptionPane.ERROR_MESSAGE);

            }
        }
    }
}
