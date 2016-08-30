package com.cedric.game.geometry;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cedric.game.ClashingGravity;
import com.cedric.game.obstacles.Renderable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Cedric on 2016-08-27.
 */
public class WallManager extends ArrayList<Wall> implements Renderable{

    private ClashingGravity game;
    private HashMap<Color, String> colors;
    private Color spawningColor;

    private List<Wall> floorWalls;
    private List<Wall> ceilingWalls;

    private int appWidth;
    private int appHeight;

    private int wallWidth;
    private int wallHeight;

    private int wallCountNeeded;


    public WallManager(ClashingGravity game)
    {
        this.game = game;
        this.wallWidth = 16;
        this.wallHeight = 16;
        this.appWidth = Gdx.app.getGraphics().getWidth();
        this.appHeight = Gdx.app.getGraphics().getHeight();
        this.wallCountNeeded = appWidth/wallWidth + 1;

        this.floorWalls = new ArrayList<Wall>();
        this.ceilingWalls = new ArrayList<Wall>();

        this.colors = new HashMap<Color, String>();
        this.spawningColor = Color.RED;

        colors.put(Color.RED, "sqr_wall_red");

        //FLOOR
        for(int i = 0; i < wallCountNeeded; i++)
        {
            floorWalls.add(new Wall(getTextureFromColor(spawningColor), i*wallWidth, 0, this.game));
        }

        for(int i = 0; i < wallCountNeeded; i++)
        {
            ceilingWalls.add(new Wall(getTextureFromColor(spawningColor), i*wallWidth, appHeight - wallHeight, this.game));
        }
    }

    public void update()
    {
        //Floor
        for(int i = 0; i < floorWalls.size(); i++)
        {
            Wall wall = floorWalls.get(i);
            wall.setX((float)(wall.getX() - game.getPlayerSpeed()));

            if(wall.getX() + wall.getWidth() < 0)
            {
                floorWalls.remove(i);
                i--;
            }

            Wall lastFloor = floorWalls.get(floorWalls.size() - 1);
            if(lastFloor.getX() < appWidth)
            {
                floorWalls.add(new Wall(getTextureFromColor(spawningColor), lastFloor.getX() + wallWidth, lastFloor.getY(), game));
                System.out.println(lastFloor.getX() + "   " + (int)(lastFloor.getX() + wallWidth));
            }
        }

        //Ceiling
        for(int i = 0; i < ceilingWalls.size(); i++)
        {
            Wall wall = ceilingWalls.get(i);
            wall.setX((float)(wall.getX() - game.getPlayerSpeed()));

            if(wall.getX() + wall.getWidth() < 0)
            {
                ceilingWalls.remove(i);
                i--;
            }

            Wall lastCeiling = ceilingWalls.get(ceilingWalls.size() - 1);
            if(lastCeiling.getX() < appWidth)
                ceilingWalls.add(new Wall(getTextureFromColor(spawningColor), lastCeiling.getX() + wallWidth, lastCeiling.getY(), game));
        }
    }

    private Texture getTextureFromColor(Color color)
    {
        return game.getAssetManager().get("data/Sprites/" + colors.get(color) + ".png", Texture.class);
    }

    @Override
    public void render() {
        SpriteBatch batch = game.getBatch();

        for(Wall wall : floorWalls)
            batch.draw(wall.getTexture(), wall.getX(), wall.getY());

        for(Wall wall : ceilingWalls)
            batch.draw(wall.getTexture(), wall.getX(), wall.getY());

    }
}