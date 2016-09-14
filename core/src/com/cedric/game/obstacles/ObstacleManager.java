package com.cedric.game.obstacles;

import com.cedric.game.ClashingGravity;
import com.cedric.game.interfaces.Renderable;
import com.cedric.game.obstacles.rocks.RockManager;
import com.cedric.game.obstacles.rocks.Rock;
import com.cedric.game.obstacles.walls.Wall;
import com.cedric.game.obstacles.walls.WallManager;

/**
 * Created by Cedric on 2016-08-27.
 * Manages obstacles, moves all Movables also
 */
public class ObstacleManager implements Renderable{

    private ClashingGravity game;

    private WallManager walls;
    private RockManager rocks;

    public ObstacleManager(ClashingGravity game)
    {
        this.game = game;


        walls = new WallManager(game);
        rocks = new RockManager(game);


    }

    public void update()
    {
        walls.update();
        rocks.update();

    }

    @Override
    public void render() {

        walls.render();
        rocks.render();
    }




}
