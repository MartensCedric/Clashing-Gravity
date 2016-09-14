package com.cedric.game.obstacles.walls;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.cedric.game.ClashingGravity;
import com.cedric.game.interfaces.Renderable;
import com.cedric.game.obstacles.Spike;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Manages how the walls are drawn and spawned.
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
        colors.put(Color.GREEN, "sqr_wall_green");
        colors.put(Color.YELLOW, "sqr_wall_yellow");
        colors.put(Color.WHITE, "sqr_wall_white");
        colors.put(Color.ORANGE, "sqr_wall_orange");
        colors.put(Color.LIME, "sqr_wall_lime");
        colors.put(Color.PURPLE, "sqr_wall_purple");

        //FLOOR
        for(int i = 0; i < wallCountNeeded; i++)
        {
            floorWalls.add(new Wall(getTextureFromColor(spawningColor), i*wallWidth, 0, this.game));
        }

        for(int i = 0; i < wallCountNeeded; i++) {
            ceilingWalls.add(new Wall(getTextureFromColor(spawningColor), i * wallWidth, appHeight - wallHeight, this.game));
        }
    }

    public void update()
    {
        //Floor
        for(int i = 0; i < floorWalls.size(); i++)
        {
            Wall wall = floorWalls.get(i);
            wall.move();

            if(wall.getX() + wall.getWidth() < 0)
            {
                //TODO Spawn spikes on walls, make it more likely to spawn if spike is on neighbor
                floorWalls.remove(i);
                i--;
            }

            Wall lastFloor = floorWalls.get(floorWalls.size() - 1);
            if(lastFloor.getX() < appWidth)
            {
                Random randomizer = new Random();

                //Checks if this wall will be spiked

                if(randomizer.nextInt(100) + (game.getPlayerSpeed()-4)*4 > 35)
                {
                    Spike spike = new Spike(lastFloor.getX(), lastFloor.getY() + wallHeight, game);
                    floorWalls.add(new Wall(getTextureFromColor(spawningColor),(int)(lastFloor.getX() + wallWidth + game.getPlayerSpeed()), lastFloor.getY(), spike, game));
                }else{
                    floorWalls.add(new Wall(getTextureFromColor(spawningColor),(int)(lastFloor.getX() + wallWidth + game.getPlayerSpeed()), lastFloor.getY(), game));
                }
            }

        }

        //Ceiling
        for(int i = 0; i < ceilingWalls.size(); i++)
        {
            Wall wall = ceilingWalls.get(i);
            wall.move();

            if(wall.getX() + wall.getWidth() < 0)
            {
                ceilingWalls.remove(i);
                i--;
            }

            Wall lastCeiling = ceilingWalls.get(ceilingWalls.size() - 1);
            if(lastCeiling.getX() < appWidth) {
                Random randomizer = new Random();
                if (randomizer.nextInt(100) + (game.getPlayerSpeed() - 4) * 4 > 35) {
                    Spike spike = new Spike(lastCeiling.getX(), lastCeiling.getY() - wallHeight, game);
                    ceilingWalls.add(new Wall(getTextureFromColor(spawningColor), (int)(lastCeiling.getX() + wallWidth  + game.getPlayerSpeed()), lastCeiling.getY(), spike, game));
                } else {
                    ceilingWalls.add(new Wall(getTextureFromColor(spawningColor), (int)(lastCeiling.getX() + wallWidth + game.getPlayerSpeed()), lastCeiling.getY(), game));
                }
            }
        }

        if(game.getPlayerSpeed() >= 2.5 && game.getPlayerSpeed() < 3)
            spawningColor = Color.GREEN;
        else if(game.getPlayerSpeed() >= 3.5 && game.getPlayerSpeed() < 4)
            spawningColor = Color.YELLOW;
        else if(game.getPlayerSpeed() >= 4 && game.getPlayerSpeed() < 4.5)
            spawningColor = Color.WHITE;
        else if(game.getPlayerSpeed() >= 4.5 && game.getPlayerSpeed() < 5)
            spawningColor = Color.ORANGE;
        else if(game.getPlayerSpeed() >= 5 && game.getPlayerSpeed() < 5.5)
            spawningColor = Color.LIME;
        else if(game.getPlayerSpeed() >= 5.5 && game.getPlayerSpeed() < 6)
            spawningColor = Color.PURPLE;

    }

    public Texture getTextureFromColor(Color color)
    {
        return game.getAssetManager().get("data/Sprites/" + colors.get(color) + ".png", Texture.class);
    }

    @Override
    public void render() {

        for(Wall wall : floorWalls)
            wall.render();

        for(Wall wall : ceilingWalls)
            wall.render();

    }

    public List<Wall> getCeilingWalls() {
        return ceilingWalls;
    }

    public void setCeilingWalls(List<Wall> ceilingWalls) {
        this.ceilingWalls = ceilingWalls;
    }

    public List<Wall> getFloorWalls() {
        return floorWalls;
    }

    public void setFloorWalls(List<Wall> floorWalls) {
        this.floorWalls = floorWalls;
    }

    public Color getSpawningColor() {
        return spawningColor;
    }

    public void setSpawningColor(Color spawningColor) {
        this.spawningColor = spawningColor;
    }
}
