/*******************************************************************************
 * Copyright (c) 2022. Auteur : Stephane Brisse
 * IDE : INTELLIJ IDEA
 * Language : JAVA
 ******************************************************************************/

package com.stephane.menu;

import javax.swing.*;

public class EditMenu {

    JMenuItem mcut;
    JMenuItem mcopy;
    JMenuItem mpaste;

    public EditMenu() {
    }

    public JMenu editItems() {

        JMenu mEdit = new JMenu("Edit");
        mEdit.add(menuItemcut());
        mEdit.add(menuItemcopy());
        mEdit.add(menuItempaste());
        return mEdit;
    }
    public JMenuItem menuItemcut() {
        mcut = new JMenuItem("Cut");
        return mcut;
    }
    public JMenuItem menuItemcopy() {
        mcopy = new JMenuItem("Copy");
        return mcopy;
    }
    public JMenuItem menuItempaste() {
        mpaste = new JMenuItem("Paste");
        return mpaste;
    }
}
