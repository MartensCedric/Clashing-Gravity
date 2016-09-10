package com.cedric.game.obstacles;

import com.badlogic.gdx.Gdx;
import com.cedric.game.ClashingGravity;
import com.cedric.game.interfaces.Renderable;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Cedric on 2016-08-27.
 * Manages obstacles, moves all Movables also
 */
public class ObstacleManager implements Renderable{

    private ArrayList<Spike> spikesFloor;

    private ClashingGravity game;

    private WallManager walls;

    public ObstacleManager(ClashingGravity game)
    {
        this.game = game;

        walls = new WallManager(game);
    }

    public void update()
    {
        walls.update();
    }

    @Override
    public void render() {

        walls.render();
    }
}
