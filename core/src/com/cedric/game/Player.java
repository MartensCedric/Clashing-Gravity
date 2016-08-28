package com.cedric.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.cedric.game.geometry.BoxCoords;

/**
 * Created by Cedric Martens on 2016-08-26.
 */
public class Player{

    private final double MIN_VELOCITY = 2.0;
    private final double MAX_VELOCITY = 3.0;

    private final int WALL_HEIGHT = 16;

    private ClashingGravity game;
    private Texture texture;
    private String textureName;

    private double force;

    private double velocity;

    private double x;
    private double y;

    private double width;
    private double height;

    private int appWidth;
    private int appHeight;

    public Player(ClashingGravity game)
    {
        this.game = game;

        this.textureName = game.getPlayerTextureName();

        this.texture = game.getSprite(textureName);


        this.force = 0.15;

        this.velocity = MIN_VELOCITY;

        this.x = 75;
        this.y = 200;

        this.width = 32;
        this.height = 32;

        this.appWidth = Gdx.app.getGraphics().getWidth();
        this.appHeight = Gdx.app.getGraphics().getHeight();
    }

    public void applyGravity()
    {
        if(y - velocity < WALL_HEIGHT)
        {
            y = WALL_HEIGHT;
            velocity = 0;
        }
        else if(y - velocity >  appHeight - WALL_HEIGHT - height)
        {
            y = appHeight - WALL_HEIGHT - height;
            velocity = 0;
        }
        else
        {
            y -= velocity;
            velocity += force;
        }
    }

    public void update()
    {
        applyGravity();
    }

    public void gravitySwitch()
    {
        force = -force;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public double getForce() {
        return force;
    }

    public void setForce(double force) {
        this.force = force;
    }

    public double getY() {
        return this.y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return this.x;
    }

    public void setX(double x) {
        this.x = x;
    }
}

