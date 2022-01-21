
/*******************************************************************************
 * Copyright (c) 2022.
 * Modifie le :21/01/2022 08:53
 * Auteur : Stephane Brisse
 * IDE : INTELLIJ IDEA
 * Language : JAVA
 ******************************************************************************/

package com.stephane.menu;

import javax.swing.*;

public class MenuBar {

    private JMenuBar mBar;

    public  MenuBar() {

        mBar = new JMenuBar();
        FileMenu mFile = new FileMenu();
        EditMenu mEdit = new EditMenu();
        HelpMenu mHelp = new HelpMenu();

        mBar.add(mFile.fileItems());
        mBar.add(mEdit.editItems());
        mBar.add(mHelp.helpItems());
    }
    public JMenuBar getMenuBar() {
        return mBar;
    }
}
