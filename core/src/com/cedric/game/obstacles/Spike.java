package com.cedric.game.obstacles;

import com.badlogic.gdx.graphics.Texture;
import com.cedric.game.ClashingGravity;
import com.cedric.game.interfaces.Movable;

/**
 * Created by Cedric on 2016-08-27.
 */
public class Spike implements Movable {

    private Texture texture;
    private ClashingGravity game;

    private int x;
    private int y;

    private int width;
    private int height;

    public Spike(int x, int y, ClashingGravity game)
    {
        this.texture = game.getSprite("obs_spike");
        this.game = game;

        this.width = 16;
        this.height = 16;

        this.x = x;
        this.y = y;

        this.width = 16;
        this.height = 16;
    }

    @Override
    public void move() {
        x -= game.getPlayerSpeed();
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setX(int x) {

    }

    @Override
    public void setY(int y) {

    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }
}
