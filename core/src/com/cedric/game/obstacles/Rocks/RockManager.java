package com.cedric.game.obstacles.rocks;

import com.badlogic.gdx.Gdx;
import com.cedric.game.ClashingGravity;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by 1544256 on 2016-09-14.
 */
public class RockManager extends ArrayList<Rock> {

    private ClashingGravity game;
    private int rockSpawningTempo;
    private boolean rocksAreSpawning;

    public RockManager(ClashingGravity game)
    {
        this.game = game;
        rockSpawningTempo = 2000;
        rocksAreSpawning = true;
        startSpawningRocks();
    }

    public void update()
    {
        move();
    }

    private void move()
    {
        for(Rock rock:this)
            rock.move();
    }

    public void startSpawningRocks()
    {
        Thread rockSpawningClock = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("Went in run");
                    while(rocksAreSpawning)
                    {
                        System.out.println("RocksAreSpawning");
                        Thread.sleep(rockSpawningTempo);
                        System.out.println("Slept");
                        if(rockSpawningTempo > 500)
                            rockSpawningTempo -= 10;

                        spawnRock();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        rockSpawningClock.start();
    }

    public void render()
    {
        for(Rock rock: this)
            rock.render();
    }

    private void spawnRock()
    {
        //We want to spawn walls that won't touch spikes
        Random randomizer = new Random();

        //Wall size is 16 and so is spike
        //So we need to exclude 64 pixels

        int rockY = randomizer.nextInt(Gdx.graphics.getHeight() - 32) + 32;
        this.add(new Rock(Gdx.graphics.getWidth(), rockY, game));
    }
}
