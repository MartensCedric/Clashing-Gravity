package com.cedric.game.obstacles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Queue;
import com.cedric.game.ClashingGravity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Cedric on 2016-08-27.
 * Manages obstacles, moves all Movables also
 */
public class ObstacleManager{

    ArrayList<Spike> spikes;

    ClashingGravity game;

    public ObstacleManager(ClashingGravity game)
    {
        spikes = new ArrayList<Spike>();
        this.game = game;
    }

    public void update()
    {
        for(int i = 0; i < spikes.size(); i++)
        {
            Spike spike = spikes.get(i);
            spike.move();

            //TODO Align spikes with walls, no spikes must overlap.

            if(spike.getX() + spike.getWidth() <= 0)
            {
                spikes.remove(spike);
                i--;
            }
        }


        Random randomizer = new Random();

        if(randomizer.nextInt(1000) < game.getPlayerSpeed())
        {
            createSpikedFloor((int)game.getPlayerSpeed());
            System.out.println("Spikes created : " + (int)game.getPlayerSpeed());
        }
    }

    /**
     * Adds to the "spikes" list a line of Spike generated on the floor
     * @param amountOfSpikes the amount wanted
     */
    private void createSpikedFloor(int amountOfSpikes)
    {
        //The spike wall amount should be based on playerSpeed;

        for (int i = 0; i < amountOfSpikes; i++)
        {
            Spike spike = new Spike(Gdx.graphics.getWidth() + i * 16, 16, game);
            System.out.println("Made a new spike with x: " + (Gdx.graphics.getWidth() + i * 16) + "   y:" + 16);
            spikes.add(spike);
        }
    }

    public ArrayList<Spike> getSpikes() {
        return spikes;
    }

    public void setSpikes(ArrayList<Spike> spikes) {
        this.spikes = spikes;
    }
}
