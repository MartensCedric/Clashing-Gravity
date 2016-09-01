package com.cedric.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.cedric.game.interfaces.Renderable;

/**
 * Created by Cedric Martens on 2016-08-26.
 */
public class Player implements Renderable{

    private final double MIN_VELOCITY = 2.0;
    private final double MAX_VELOCITY = 3.0;
    private final double FORCE = 0.15;

    private Sprite playerSprite;

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

    private boolean canSwitchGravity;

    public Player(ClashingGravity game)
    {
        this.game = game;

        this.textureName = game.getPlayerTextureName();

        this.texture = game.getSprite(textureName);


        this.force = FORCE;

        this.velocity = MIN_VELOCITY;

        this.x = 75;
        this.y = 200;

        this.width = 32;
        this.height = 32;

        this.appWidth = Gdx.app.getGraphics().getWidth();
        this.appHeight = Gdx.app.getGraphics().getHeight();

        this.canSwitchGravity = true;

        //this.playerSprite = new Sprite()
    }

    public void applyGravity()
    {
        if(y - velocity < WALL_HEIGHT && game.getPlayerSpeed() > 0)
        {
            y = WALL_HEIGHT;
            velocity = 0;
        }
        else if(y - velocity >  appHeight - WALL_HEIGHT - height  && game.getPlayerSpeed() > 0)
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
        if(canSwitchGravity)
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

    public void deathAnimation()
    {
        if(canSwitchGravity)
        {
            canSwitchGravity = false;

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //This is a hardcoded animation
                        boolean restartGame = false;
                        force = -(FORCE*2);
                        game.setPlayerSpeed(0);
                        velocity = 0;
                        Thread.sleep(200);
                        force = FORCE*1.2;

                        while(!restartGame)
                        {
                            Thread.sleep(50);

                            if(y + height < 0)
                            {
                                Thread.sleep(250);
                                restartGame = true;
                            }
                        }
                        game.restart();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

            thread.start();
        }
    }

    @Override
    public void render() {
        game.getBatch().draw(texture, (int)this.x, (int)this.y);
    }
}

