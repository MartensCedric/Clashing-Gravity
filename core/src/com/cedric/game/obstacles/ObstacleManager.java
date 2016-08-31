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
    private ArrayList<Spike> spikesCeiling;

    private ClashingGravity game;

    private WallManager walls;

    public ObstacleManager(ClashingGravity game)
    {
        this.game = game;
        spikesFloor = new ArrayList<Spike>();
        spikesCeiling = new ArrayList<Spike>();
        walls = new WallManager(game);
    }

    public void update()
    {
        for(int i = 0; i < spikesFloor.size(); i++)
        {
            Spike spike = spikesFloor.get(i);
            spike.move();

            //TODO Align spikes with walls, no spikes must overlap.

            if(spike.getX() + spike.getWidth() < 0)
            {
                spikesFloor.remove(spike);
                i--;
            }
        }

        for(int i = 0; i < spikesCeiling.size(); i++)
        {
            Spike spike = spikesCeiling.get(i);
            spike.move();

            //TODO Align spikes with walls, no spikes must overlap.

            if(spike.getX() + spike.getWidth() < 0)
            {
                spikesCeiling.remove(spike);
                i--;
            }
        }


        Random randomizer = new Random();

        if(randomizer.nextInt(200) < game.getPlayerSpeed())
        {
            createSpikedFloor((int)game.getPlayerSpeed()*2);
        }

        if(randomizer.nextInt(200) < game.getPlayerSpeed())
        {
            createSpikedCeiling((int)game.getPlayerSpeed()*2);
        }

        walls.update();
    }

    /**
     * Adds to the "spikesFloor" list a line of Spike generated on the floor
     * @param amountOfSpikes the amount wanted
     */
    private void createSpikedFloor(int amountOfSpikes)
    {

        for (int i = 0; i < amountOfSpikes; i++)
        {
            Spike spike = new Spike(Gdx.graphics.getWidth() + i * 16, 16, game);
            spikesFloor.add(spike);
        }
    }

    private void createSpikedCeiling(int amountOfSpikes)
    {
        //The spike wall amount should be based on playerSpeed;

        for (int i = 0; i < amountOfSpikes; i++)
        {
            Spike spike = new Spike(Gdx.graphics.getWidth() + i * 16, Gdx.graphics.getHeight() - 32, game);
            spikesCeiling.add(spike);

        }
    }

    public ArrayList<Spike> getSpikesFloor() {
        return spikesFloor;
    }

    public void setSpikesFloor(ArrayList<Spike> spikesFloor) {
        this.spikesFloor = spikesFloor;
    }

    public ArrayList<Spike> getSpikesCeiling() {
        return spikesCeiling;
    }

    public void setSpikesCeiling(ArrayList<Spike> spikesCeiling) {
        this.spikesCeiling = spikesCeiling;
    }

    @Override
    public void render() {

        for(Spike spike : spikesFloor)
            spike.render();

        for(Spike spike : spikesCeiling)
            spike.render();

        walls.render();
    }
}
