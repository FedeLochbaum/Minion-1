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
import com.mygdx.game.Utils.States.GameState;
import com.mygdx.game.Utils.States.PlayerState;
import com.mygdx.game.World.RenderEngine;
import com.mygdx.game.World.WorldEngine;

import static com.mygdx.game.Utils.Constants.HEIGHT;
import static com.mygdx.game.Utils.Constants.WIDTH;
import static com.mygdx.game.Utils.States.GameState.GAME_PAUSED;
import static com.mygdx.game.Utils.States.GameState.GAME_RUNNING;

public class GameScreen extends ScreenAdapter {

    private Minion game;

    WorldEngine worldutil;
    RenderEngine renderer;

    OrthographicCamera camera;
    Vector3 touchPoint;

    GameState state;

    String scoreString;

    Rectangle pauseRectangle;
    Rectangle resumeRectangle;
    Rectangle restartRectangle;
    Rectangle exitRectangle;

    public GameScreen(Minion gameM) {
        game = gameM;

        camera = new OrthographicCamera(WIDTH,HEIGHT);
        camera.position.set(WIDTH/2,HEIGHT/2,0);
        touchPoint = new Vector3();

        worldutil = new WorldEngine();
        renderer = new RenderEngine(game.getBatch(), worldutil);

        state = GAME_RUNNING;

        scoreString = "SCORE: ";

        pauseRectangle = new Rectangle(WIDTH-65, 1, 64, 64);
        resumeRectangle = new Rectangle(135, 470-(86/2), 209, 86);
        restartRectangle = new Rectangle(110, HEIGHT/2-(86/2), 260, 86);
        exitRectangle = new Rectangle(175, 330-(86/2), 137, 86);
    }

    public void draw(){
        GL20 gl = Gdx.gl;
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();
        camera.update();

        Batch batch = game.getBatch();

        batch.setProjectionMatrix(camera.combined);
        batch.enableBlending();
        batch.begin();

        switch(state){
            case GAME_RUNNING:
                renderScore();
                renderPauseBt();
                break;
            case GAME_PAUSED:
                renderPauseMenu();
                break;
        }
        batch.end();
    }

    public void update(float delta){
        switch(state){
            case GAME_RUNNING:
                gameRunning(delta);
                break;
            case GAME_PAUSED:
                gamePaused();
                break;
        }
    }

    public void gamePaused(){
        if(Gdx.input.justTouched()){
            camera.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

            if(resumeRectangle.contains(touchPoint.x, touchPoint.y))
                state = GAME_RUNNING;

            if(restartRectangle.contains(touchPoint.x, touchPoint.y)){
                game.setScreen(new GameScreen(game));
                return;
            }
            if(exitRectangle.contains(touchPoint.x, touchPoint.y)){
                game.setScreen(new MenuScreen(game));
                return;
            }
        }
    }
    public void gameRunning(float delta){
        if(Gdx.input.justTouched()){
            camera.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
            if(pauseRectangle.contains(touchPoint.x, touchPoint.y)){
                state = GAME_PAUSED;
                return;
            }
        }
        worldutil.update(delta);
        if(worldutil.getPlayer().getState() == PlayerState.STATE_DEAD) state = GAME_PAUSED;
    }

    private void renderPauseMenu(){
        game.getBatch().draw(Assets.getResumeButton(), 135, 470-(86/2), 209, 86);
    }

    private void renderPauseBt() {
        game.getBatch().draw(Assets.getPauseButton(), WIDTH-65, 1, 64, 64);
    }

    private void renderScore(){
//        Assets.getFont().setScale(0.6f);
        Assets.getFont().draw(game.getBatch(), scoreString + worldutil.score, 10, 750);
    }

    @Override
    public void render(float delta) {
        update(delta);
        draw();

    }
    @Override
    public void pause() {
        if(state == GAME_RUNNING) state = GAME_PAUSED;
    }

}
