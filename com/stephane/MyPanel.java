
/*******************************************************************************
 * Copyright (c) 2022.
 * Modifie le :21/01/2022 08:42
 * Auteur : Stephane Brisse
 * IDE : INTELLIJ IDEA
 * Language : JAVA
 ******************************************************************************/

package com.stephane;

import com.stephane.menu.StatusBar;
import com.stephane.tools.Shape;
import com.stephane.tools.figures;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.util.ArrayList;
import java.util.Objects;

import static com.stephane.tools.Shape.*;

/**
 * le conteneur principal
 */
public class  MyPanel extends JPanel implements MouseListener, MouseMotionListener {


    private static Shape currentShape = null;
    private static int strokeWidth = 0;
    private static Color mycolor = null;
    private static Point startPoint = null;
    private static Point endPoint = null;
    private static Point posMouse = null;
    private static BufferedImage bufferImage = null;
    private static BufferedImage bufferImage2 = null;
    private static ArrayList<figures>  myfigures = new ArrayList<>();
    private static boolean askundo = false;
    private static boolean askfill = false;



    public MyPanel() {
        setLayout(null);
        setDoubleBuffered(true);
        clearBufferImage();
        strokeWidth = 2;
        mycolor = Color.black;
        currentShape = PENCIL;
        addMouseListener(this);
        addMouseMotionListener(this);

    }
    public static void setShape(Shape shape) {
        currentShape = shape;
    }
    public static void setColor(Color color) {
        mycolor = color;
    }
    public static void setStrokeWidth(int  w) {
        strokeWidth = w;
    }
    public static void setBufferImage(BufferedImage buf) { bufferImage2 = buf; }
    public static BufferedImage getBufferImage() { return bufferImage; }
    public static void setUndo(boolean b) {
        askundo = b;
    }
    public static void setFill(boolean b) {
        askfill = b;
    }
    public static boolean getFill() {
        return  askfill;
    }

    public void printCoords() {
        String posX = String.valueOf(posMouse.x);
        String posY = String.valueOf(posMouse.y);
        JLabel lsb = Main.getStatusBar().getLblCoord();
        lsb.setText(posX + ",  " + posY + " px");
    }
    public void printSize() {
        String width = String.valueOf(this.getWidth());
        String height = String.valueOf(this.getHeight());
        JLabel lds =  new StatusBar().getLblSize();
        lds.setText(width + ",  " + height + " px");
    }

    @Override
    public void paintComponent(Graphics g) {

        Graphics2D g2D = (Graphics2D) g;
        if (bufferImage == null) {
            bufferImage = (BufferedImage) createImage(getWidth(), getHeight());
            Graphics2D gc = bufferImage.createGraphics();
            gc.setColor(Color.WHITE);
            gc.fillRect(0, 0, getWidth(), getHeight());
        }

        g2D.drawImage(bufferImage, null, 0, 0);
        if (startPoint != null && endPoint != null) {
            renderShape(g2D);
        }
    }

    public void clearBufferImage() {
        bufferImage = null;
        startPoint = null;
        endPoint = null;
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        startPoint = e.getPoint();
        endPoint = startPoint;

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        endPoint = e.getPoint();

        renderShape(bufferImage.createGraphics());
        repaint();
        if (bufferImage2 != null) bufferImage2 = null;
        startPoint = null;
        endPoint = null;
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        if ((currentShape == PENCIL) | (currentShape == ERASER)) {
            startPoint = endPoint;
            endPoint = e.getPoint();
            posMouse = e.getPoint();
            renderShape(bufferImage.createGraphics());
        } else  {endPoint = e.getPoint(); }
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        posMouse = e.getPoint();
        printCoords();
        printSize();
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        /*if (askfill) {
            Toolkit tk = Toolkit.getDefaultToolkit();
            Image fillcurs = tk.createImage("icon/moon.png");
            Cursor mycursor = tk.createCustomCursor( fillcurs,
                    new Point(0,0),"fillcursor");
            setCursor(Cursor.mycursor);
        } else {*/
            setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));

    }
    @Override
    public void mouseExited(MouseEvent e) { }

    public void renderShape(Graphics2D g2D) {

        if ((startPoint == null) || (endPoint == null))
            return;
        if (bufferImage2 != null) {
            g2D.drawImage(bufferImage2,null, endPoint.x, endPoint.y);
            return;
        }
        g2D.setColor(mycolor);
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2D.setStroke(new BasicStroke(strokeWidth, BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND));
        printCoords();
        switch (currentShape) {
            case PENCIL:
                g2D.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND,
                        BasicStroke.JOIN_ROUND));
                g2D.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
                myfigures.add(new figures(startPoint.x, startPoint.y,
                        endPoint.x, endPoint.y, PENCIL));
                printCoords();
                break;
            case LINE:

                g2D.setStroke(new BasicStroke(strokeWidth, BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_ROUND));
                g2D.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
                myfigures.add(new figures(startPoint.x, startPoint.y,
                        endPoint.x, endPoint.y, LINE));
                printCoords();
                break;
            case SQUARE:
                g2D.setStroke(new BasicStroke(strokeWidth, BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER));
                g2D.setColor(Color.blue);
                if (startPoint.x < endPoint.x & startPoint.y < endPoint.y) {
                    g2D.drawRect(startPoint.x, startPoint.y, endPoint.x - startPoint.x,
                        endPoint.x - startPoint.x);
                    myfigures.add(new figures(startPoint.x, startPoint.y,
                            endPoint.x, endPoint.y, SQUARE));
                break;
                }
            case RECTANGLE:
                g2D.setStroke(new BasicStroke(strokeWidth, BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER));
                g2D.drawRect(startPoint.x, startPoint.y, endPoint.x - startPoint.x,
                        endPoint.y - startPoint.y);
                myfigures.add(new figures(startPoint.x, startPoint.y,
                        endPoint.x, endPoint.y, SQUARE));
                break;
            case CIRCLE:
                g2D.setStroke(new BasicStroke(strokeWidth, BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER));
                g2D.drawOval(startPoint.x, startPoint.y, Math.abs(endPoint.x - startPoint.x),
                        Math.abs(endPoint.x - startPoint.x));
                break;
            case OVAL:
                g2D.drawOval(startPoint.x, startPoint.y, endPoint.x - startPoint.x,
                        endPoint.y - startPoint.y);
                break;
            case ERASER:
                g2D.setColor(Color.WHITE);
                g2D.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND,
                        BasicStroke.JOIN_ROUND));
                g2D.drawLine(startPoint.x - (strokeWidth ), startPoint.y
                        - (strokeWidth), endPoint.x + (strokeWidth), endPoint.y +
                        strokeWidth);
                break;
            default:
                break;
        }
        printCoords();
    }
    public void drawRectangle(Graphics2D g2D) {
        if (((startPoint.x - endPoint.x) < 0)
                && ((startPoint.y - endPoint.y < 0))) {
            g2D.drawRect(startPoint.x, startPoint.y,
                    Math.abs(startPoint.x - endPoint.x),
                    Math.abs(startPoint.y - endPoint.y));
        }

        if (((startPoint.x - endPoint.x) > 0)
                && ((startPoint.y - endPoint.y < 0))) {
            g2D.drawRect(endPoint.x, startPoint.y,
                    Math.abs(startPoint.x - endPoint.x),
                    Math.abs(startPoint.y - endPoint.y));
        }

        if (((startPoint.x - endPoint.x) > 0)
                && ((startPoint.y - endPoint.y > 0))) {
            g2D.drawRect(endPoint.x, endPoint.y,
                    Math.abs(startPoint.x - endPoint.x),
                    Math.abs(startPoint.y - endPoint.y));
        }

        if (((startPoint.x - endPoint.x) < 0)
                && ((startPoint.y - endPoint.y > 0))) {
            g2D.drawRect(startPoint.x, endPoint.y,
                    Math.abs(startPoint.x - endPoint.x),
                    Math.abs(startPoint.y - endPoint.y));
        }
    }
    public void drawOvwal(Graphics2D g2D) {
        if (((startPoint.x - endPoint.x) < 0)
                && ((startPoint.y - endPoint.y < 0))) {
            g2D.drawOval(startPoint.x, startPoint.y,
                    Math.abs(startPoint.x - endPoint.x),
                    Math.abs(startPoint.y - endPoint.y));
        }

        if (((startPoint.x - endPoint.x) > 0)
                && ((startPoint.y - endPoint.y < 0))) {
            g2D.drawOval(endPoint.x, startPoint.y,
                    Math.abs(startPoint.x - endPoint.x),
                    Math.abs(startPoint.y - endPoint.y));
        }

        if (((startPoint.x - endPoint.x) > 0)
                && ((startPoint.y - endPoint.y > 0))) {
            g2D.drawOval(endPoint.x, endPoint.y,
                    Math.abs(startPoint.x - endPoint.x),
                    Math.abs(startPoint.y - endPoint.y));
        }

        if (((startPoint.x - endPoint.x) < 0)
                && ((startPoint.y - endPoint.y > 0))) {
            g2D.drawOval(startPoint.x, endPoint.y,
                    Math.abs(startPoint.x - endPoint.x),
                    Math.abs(startPoint.y - endPoint.y));
        }
    }

}
