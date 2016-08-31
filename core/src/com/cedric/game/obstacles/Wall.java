package com.cedric.game.obstacles;

import com.badlogic.gdx.graphics.Texture;
import com.cedric.game.ClashingGravity;
import com.cedric.game.interfaces.Renderable;

/**
 * Created by Cedric on 2016-08-27.
 */
public class Wall implements Renderable{

    private ClashingGravity game;

    private int width;
    private int height;

    private float x;
    private float y;

    private Texture texture;

    private Spike spike;

    public Wall(Texture texture, float x, float y, Spike spike, ClashingGravity game)
    {
        width = 16;
        height = 16;

        this.game = game;

        this.x = x;
        this.y = y;

        this.texture = texture;
        this.spike = spike;
    }

    public Wall(Texture texture, float x, float y, ClashingGravity game)
    {
        this(texture, x, y, null, game);
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
        return (int)x;
    }

    public int getY() {
        return (int)y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(float x) {
        this.x = x;
    }

    @Override
    public void render() {
        this.game.getBatch().draw(texture, this.x, this.y);
    }

}
