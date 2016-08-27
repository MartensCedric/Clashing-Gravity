package com.cedric.game.geometry;

/**
 * OBSOLETE -- DO NOT USE
 * Created by Cedric on 2016-08-27.
 * Allows to get coordinates from corners of a box
 */
public class BoxCoords {

    private double x;
    private double y;

    private int w;
    private int h;

    public BoxCoords(double x, double y, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.w = width;
        this.h = height;
    }

    public BoxCoords(int x, int y, int width, int height)
    {
        new BoxCoords((double)x, (double)y, width, height);
    }


    public int getWidth()
    {
        return w;
    }

    public int getHeight()
    {
        return h;
    }

    public double getXatCorner(Corner corner)
    {
        if(corner == Corner.TOP_LEFT || corner == Corner.BOTTOM_LEFT)
            return this.x;

        return this.x + this.w;
    }

    public double getYatCorner(Corner corner)
    {
        if(corner == Corner.TOP_LEFT || corner == Corner.TOP_RIGHT)
            return this.y;

        return this.y + this.h;
    }
}
