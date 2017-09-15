package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Screens.MenuScreen;
import com.mygdx.game.Utils.Assets;

public class Minion extends Game {

	private Batch batch;

	@Override
	public void create() {
		batch = new SpriteBatch();
		Assets.load();
		setScreen(new MenuScreen(this));
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
		super.dispose();
		getScreen().dispose();
	}

	public Batch getBatch() {
		return batch;
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
	}

}
