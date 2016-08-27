package com.cedric.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cedric.game.geometry.Wall;
import com.cedric.game.geometry.WallList;

import java.util.HashMap;
import java.util.Map;

//TODO ADD FLOOR AND CEILING
//TODO ADD SIDESCROLLING EFFECT

public class ClashingGravity extends ApplicationAdapter {

	private SpriteBatch batch;
	private BitmapFont font;
	private Player player;

	private int screenWidth;
	private int screenHeight;

	private AssetManager assetManager;
	private String playerTextureName;
	private WallList walls;

	private boolean loaded = false;

	private double playerSpeed;

	@Override
	public void create () {

		batch = new SpriteBatch();
		font = new BitmapFont(false);

		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();

		font.setColor(Color.RED);

		assetManager = new AssetManager();

		playerTextureName = "sqr_blue"; //Later this will load last texture the player used as skin

		loadAssets();

		this.playerSpeed = 2.0;

		player = null;

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if(assetManager.update())
			loaded = true;

		batch.begin();
		if(loaded)
		{
			if(player == null && walls == null)
			{
				player = new Player(this);
				walls = new WallList(this);
			}


			batch.draw(player.getTexture(),(float)player.getX(), (float)player.getY());

			for(Wall wall:walls)
				batch.draw(wall.getTexture(), wall.getX(), wall.getY());

		}
		batch.end();


		if(loaded)
			update();
	}

	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
		assetManager.dispose();
	}

	public void update()
	{
		player.update();
		walls.update();
	}

	private void loadAssets()
	{
		assetManager.load("data/Sprites/sqr_blue.png", Texture.class);
		assetManager.load("data/Sprites/sqr_wall_red.png", Texture.class);
	}

	public Texture getSprite(String textureName)
	{
		return assetManager.get("data/Sprites/" + textureName + ".png", Texture.class);
	}

	public String getPlayerTextureName()
	{
		return playerTextureName;
	}

	public AssetManager getAssetManager() {
		return assetManager;
	}

	public void setAssetManager(AssetManager assetManager) {
		this.assetManager = assetManager;
	}

	public double getPlayerSpeed() {
		return playerSpeed;
	}

	public void setPlayerSpeed(double playerSpeed) {
		this.playerSpeed = playerSpeed;
	}

	public WallList getWalls() {
		return walls;
	}

	public void setWalls(WallList walls) {
		this.walls = walls;
	}
}
