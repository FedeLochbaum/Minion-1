package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Minion;
import com.mygdx.game.Utils.GameState;
import com.mygdx.game.Utils.RenderEngine;
import com.mygdx.game.Utils.WorldUtil;

import static com.mygdx.game.Utils.Constants.HEIGHT;
import static com.mygdx.game.Utils.Constants.WIDTH;
import static com.mygdx.game.Utils.GameState.GAME_RUNNING;

public class GameScreen extends ScreenAdapter {

    private Minion game;

    WorldUtil worldutil;
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

        worldutil = new WorldUtil();
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
//                renderScore();
//                renderPauseBt();
                break;
            case GAME_PAUSED:
//                renderPauseMenu();
                break;
        }

        batch.end();

    }

    public void update(float delta){
        switch(state){
            case GAME_RUNNING:
//                gameRunning(delta);
                break;
            case GAME_PAUSED:
//                gamePaused();
                break;
        }

    }
}
