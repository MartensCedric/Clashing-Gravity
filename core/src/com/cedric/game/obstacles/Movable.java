package com.cedric.game.obstacles;

/**
 * Created by Cedric on 2016-08-27.
 */
public interface Movable {


    public void move();

    public int getX();
    public int getY();

    public void setX(int x);
    public void setY(int y);

    public int getWidth();
    public int getHeight();
}
