package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Minion;
import com.mygdx.game.Utils.Assets;

public class MenuScreen extends ScreenAdapter {

    private Minion game;
    private OrthographicCamera camera;
    private Vector3 touchPoint;

    private Rectangle soundButton;
    private Rectangle playButton;

    public static boolean soundState = true;

    private final float WIDTH = 480;
    private final float HEIGHT = 800;

    public MenuScreen(Minion gameM) {
        game = gameM;

        camera = new OrthographicCamera(WIDTH,HEIGHT);
        camera.position.set(WIDTH/2,HEIGHT/2,0);

        soundButton = new Rectangle( 420, 0, 60, 80);
        playButton = new Rectangle( 460/2 - Assets.getPlayButton().getRegionWidth()/2, 200, 150, 60);
        touchPoint = new Vector3();
    }

    @Override
    public void render(float delta) {
        checkButtons();
        draw();
    }

    private void draw() {

        GL20 gl = Gdx.gl;
        gl.glClearColor(1, 0, 0, 1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();

        Batch batch = game.getBatch();

        batch.setProjectionMatrix(camera.combined);

        batch.disableBlending();
        batch.begin();

        ((Batch) batch).draw(Assets.getBgRegion(), 0, 0, 480, 800);
        batch.end();

        batch.enableBlending();
        batch.begin();

        ((Batch) batch).draw(Assets.getPlayButton(), 460/2 - Assets.getPlayButton().getRegionWidth()/2, 200, 150, 60);
        ((Batch) batch).draw(soundState ? Assets.getSoundOn() : Assets.getSoundOff(), 420, 0, 60, 80);

        batch.end();
    }

    private void checkButtons() {
        if (Gdx.input.justTouched()) {
            camera.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

            if(playButton.contains(touchPoint.x, touchPoint.y)){
                game.setScreen(new GameScreen(game));
            }

            if(soundButton.contains(touchPoint.x, touchPoint.y)){
                soundState = !soundState;
                if(soundState)	Assets.getMusic().play();
                else Assets.getMusic().pause();
            }
        }
    }


}
