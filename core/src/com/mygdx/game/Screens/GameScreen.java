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

    private WorldEngine worldEngine;
    private RenderEngine worldRenderer;
    private OrthographicCamera camera;

    private Vector3 touchPoint;

    private GameState state;

    private String scoreString;

    private Batch batch;

    private Rectangle pauseRectangle;
    private Rectangle resumeRectangle;


    private Rectangle restartRectangle;
    private Rectangle exitRectangle;

    public GameScreen(Minion gameM) {
        game = gameM;

        batch = game.getBatch();

        camera = new OrthographicCamera(WIDTH,HEIGHT);
        camera.position.set(WIDTH/2,HEIGHT/2,0);
        touchPoint = new Vector3();

        worldEngine = new WorldEngine();
        worldRenderer = new RenderEngine(game.getBatch(), worldEngine);

        state = GAME_RUNNING;

        scoreString = "SCORE: ";

        pauseRectangle = new Rectangle(WIDTH-65, 1, 64, 64);
        resumeRectangle = new Rectangle(135, 470-(86/2), 209, 86);

        restartRectangle = new Rectangle(110, HEIGHT/2-(86/2), 260, 86);
        exitRectangle = new Rectangle(175, 330-(86/2), 137, 86);
    }

    @Override
    public void render(float delta) {
        update(delta);
        draw();
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

    public void draw(){
        GL20 gl = Gdx.gl;
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        worldRenderer.render();
        camera.update();

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

        worldEngine.update(delta);
        if(isDead()) game.setScreen(new GameScreen(game));
    }

    private boolean isDead() {
        return worldEngine.getPlayer().getState() == PlayerState.STATE_DEAD;
    }

    private void renderPauseMenu(){
        batch.draw(Assets.getResumeButton(), 135, 470-(86/2), 209, 86);
        batch.draw(Assets.getRestartButton(), 110, HEIGHT/2-(86/2), 260, 86);
        batch.draw(Assets.getExitButton(), 175, 330-(86/2), 137, 86);
    }

    private void renderPauseBt() {
        batch.draw(Assets.getPauseButton(), WIDTH-65, 1, 64, 64);
    }

    private void renderScore(){
        Assets.getFont().draw(batch, scoreString + worldEngine.score, 10, 750);
    }

    @Override
    public void pause() {
        if(state == GAME_RUNNING) state = GAME_PAUSED;
    }

}
