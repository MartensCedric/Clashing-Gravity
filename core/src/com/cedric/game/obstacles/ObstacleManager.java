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

    private ArrayList<Rock> rocks;
    private int rockSpawningTempo;
    private boolean rocksAreSpawning;

    private ClashingGravity game;

    private WallManager walls;

    public ObstacleManager(ClashingGravity game)
    {
        this.game = game;
        rockSpawningTempo = 2000;
        rocksAreSpawning = true;

        walls = new WallManager(game);
    }

    public void update()
    {
        walls.update();

        //TODO FIX THIS SHIT, You made a  fucking while in update
        //Put a bool (rocksarespawning) to keep it at 2s interval
        Thread rockSpawningClock = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while(rocksAreSpawning)
                    {
                        Thread.sleep(rockSpawningTempo);

                        if(rockSpawningTempo > 500)
                            rockSpawningTempo -= 10;

                        spawnRock();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        for(Rock rock : rocks)
            rock.move();
    }

    @Override
    public void render() {

        walls.render();

        for(Rock rock : rocks)
        {
            rock.render();
        }
    }

    private void spawnRock()
    {
        //We want to spawn walls that won't touch spikes
        Random randomizer = new Random();

        //Wall size is 16 and so is spike
        //So we need to exclude 64 pixels

        int rockY = randomizer.nextInt(Gdx.graphics.getHeight() - 32) + 32;
        rocks.add(new Rock(Gdx.graphics.getWidth(), rockY, game));
    }
}
