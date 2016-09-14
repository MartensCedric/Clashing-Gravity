package com.cedric.game.obstacles.rocks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.cedric.game.ClashingGravity;
import com.cedric.game.interfaces.KillsPlayerOnTouch;
import com.cedric.game.interfaces.Renderable;

/**
 * Created by Cedric on 2016-09-09.
 */
public class Rock implements Renderable, KillsPlayerOnTouch{

    private Texture texture;
    private ClashingGravity game;

    private int x;
    private int y;

    private int width;
    private int height;

    public Rock(int x, int y, ClashingGravity game) {

        this.game = game;
        this.texture = game.getSprite("obs_rock");
        this.width = texture.getWidth();
        this.height = texture.getHeight();
        this.x = x;
        this.y = y;
    }

    public void move()
    {
        this.x -= game.getPlayerSpeed();
        checkForCollisionWithPlayer();
    }

    @Override
    public void checkForCollisionWithPlayer(){
        Rectangle rock = new Rectangle(this.x, this.y, this.width, this.height);
        Rectangle player = new Rectangle((float)game.getPlayer().getX(), (float)game.getPlayer().getY(), 32, 32);

        if(rock.overlaps(player))
            game.getPlayer().deathAnimation();
    }

    @Override
    public void render() {
        game.getBatch().draw(texture, x, y);
    }
}
