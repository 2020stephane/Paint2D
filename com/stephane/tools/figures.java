/*******************************************************************************
 * Copyright (c) 2022.
 * Modifie le :21/01/2022 15:41
 * Auteur : Stephane Brisse
 * IDE : INTELLIJ IDEA
 * Language : JAVA
 ******************************************************************************/
package com.stephane.tools;

public class figures {

    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private Shape type;

    public figures(int x1, int y1, int x2, int y2, Shape type) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.type = type;
    }
    public int getX1() { return this.x1; }
    public int getX2() { return this.x2; }
    public int getY1() { return this.y1; }
    public int getY2() { return this.y2; }
    public Shape getType() { return this.type; }

}
