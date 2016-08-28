package com.cedric.game.geometry;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.cedric.game.ClashingGravity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Cedric on 2016-08-27.
 */
public class WallList extends ArrayList<Wall> {

    private ClashingGravity game;
    private HashMap<Color, String> colors;
    private Color spawningColor;

    private int appWidth;
    private int appHeight;

    private int wallWidth;
    private int wallHeight;

    private int wallCountNeeded;

    public WallList(ClashingGravity game)
    {
        this.game = game;
        this.wallWidth = 16;
        this.wallHeight = 16;
        this.appWidth = Gdx.app.getGraphics().getWidth();
        this.appHeight = Gdx.app.getGraphics().getHeight();
        this.wallCountNeeded = appWidth/wallWidth + 1;

        this.colors = new HashMap<Color, String>();
        this.spawningColor = Color.red;

        colors.put(Color.red, "sqr_wall_red");

        //FLOOR
        for(int i = 0; i < wallCountNeeded; i++)
        {
            this.add(new Wall(getTextureFromColor(spawningColor), i*wallWidth, 0, this.game));
        }

        for(int i = 0; i < wallCountNeeded; i++)
        {
            this.add(new Wall(getTextureFromColor(spawningColor), i*wallWidth, appHeight - wallHeight, this.game));
        }
    }

    public void update()
    {
        for(int i = 0; i <  this.size(); i++)
        {
            Wall wall = this.get(i);
            wall.setX((int) (wall.getX() - game.getPlayerSpeed()));

            if(wall.getX() + wall.getWidth() < 0)
            {
                this.add(new Wall(getTextureFromColor(spawningColor), wall.getX() + wallCountNeeded * 16, wall.getY(), game));
                this.remove(i);
            }
        }
    }

    private Texture getTextureFromColor(Color color)
    {
        return game.getAssetManager().get("data/Sprites/" + colors.get(color) + ".png", Texture.class);
    }
}
