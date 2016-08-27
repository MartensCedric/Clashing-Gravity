package com.cedric.game.geometry;

import com.badlogic.gdx.graphics.Texture;
import com.cedric.game.ClashingGravity;

/**
 * Created by Cedric on 2016-08-27.
 */
public class Wall {

    private ClashingGravity game;

    private int width;
    private int height;

    private int x;
    private int y;

    private Texture texture;

    public Wall(Texture texture, int x, int y, ClashingGravity game)
    {
        width = 16;
        height = 16;

        this.game = game;

        this.x = x;
        this.y = y;

        this.texture = texture;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
