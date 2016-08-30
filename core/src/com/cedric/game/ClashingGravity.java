package com.cedric.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cedric.game.obstacles.WallManager;
import com.cedric.game.obstacles.ObstacleManager;
import com.cedric.game.obstacles.Spike;


public class ClashingGravity extends ApplicationAdapter{

	private SpriteBatch batch;
	private BitmapFont font;
	private Player player;

	private int screenWidth;
	private int screenHeight;

	private AssetManager assetManager;
	private String playerTextureName;
	private WallManager walls;

	private boolean loaded = false;

	private double playerSpeed;
	private ObstacleManager obstacleManager;

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

		Gdx.input.setInputProcessor(new InputAdapter() {

			@Override
			public boolean touchDown (int x, int y, int pointer, int button) {
				if(player != null)
					player.gravitySwitch();

				return true;
			}
		});
	}

	@Override
	public void render (){
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if(assetManager.update())
			loaded = true;


		if(loaded)
		{
			if(player == null && walls == null)
			{
				player = new Player(this);
				walls = new WallManager(this);
				obstacleManager =  new ObstacleManager(this);
			}

            batch.begin();

            walls.render();
			batch.draw(player.getTexture(),(float)player.getX(), (float)player.getY());
			obstacleManager.render();

            batch.end();
		}



		if(loaded)
			update();
	}

	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
		assetManager.dispose();
	}

	private void restart()
	{

	}

	public void update()
	{
		player.update();
		walls.update();
		obstacleManager.update();

		playerSpeed += 0.0006;
	}

	private void loadAssets()
	{
		assetManager.load("data/Sprites/sqr_blue.png", Texture.class);

		assetManager.load("data/Sprites/sqr_wall_red.png", Texture.class);
		assetManager.load("data/Sprites/sqr_wall_green.png", Texture.class);
		assetManager.load("data/Sprites/sqr_wall_yellow.png", Texture.class);

		assetManager.load("data/Sprites/obs_spike.png", Texture.class);
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

	public WallManager getWalls() {
		return walls;
	}

	public void setWalls(WallManager walls) {
		this.walls = walls;
	}

    public SpriteBatch getBatch() {
        return batch;
    }
}
