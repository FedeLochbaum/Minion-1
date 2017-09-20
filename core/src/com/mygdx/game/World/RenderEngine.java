package com.mygdx.game.World;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Animations.BirdAnimation;
import com.mygdx.game.Animations.CoinAnimation;
import com.mygdx.game.Animations.PlayerAnimation;
import com.mygdx.game.Entities.GameEntities.Bird;
import com.mygdx.game.Entities.GameEntities.Coin;
import com.mygdx.game.Entities.GameEntities.Platform;
import com.mygdx.game.Entities.GameEntities.Player;
import com.mygdx.game.Utils.Assets;

import static com.mygdx.game.Utils.States.PlayerState.STATE_DEAD;
import static com.mygdx.game.Utils.Types.BirdType.TYPE_RIGHT;

public class RenderEngine {

    static final float WIDTH = 16;
    static final float HEIGHT = 26;

    private WorldEngine world;
    private OrthographicCamera cam;
    private SpriteBatch batch;

    private PlayerAnimation playerAnimation;
    private BirdAnimation birdAnimation;
    private CoinAnimation coinAnimation;
    private ShapeRenderer shapeRenderer;

    private int side = 1;


    public RenderEngine(SpriteBatch batchC, WorldEngine worldE) {

        world = worldE;
        cam = new OrthographicCamera(WIDTH, HEIGHT);
        cam.position.set(WIDTH/2, HEIGHT/2, 0);
        batch = batchC;

        playerAnimation = new PlayerAnimation();
        birdAnimation = new BirdAnimation();
        coinAnimation = new CoinAnimation();
        shapeRenderer = new ShapeRenderer();
    }

    public void render() {
        Vector2 playerPosition = world.getPlayer().position;

        if(playerPosition.y > cam.position.y) cam.position.y = playerPosition.y;
        cam.update();
        batch.setProjectionMatrix(cam.combined);
        renderBackground();
        batch.enableBlending();
        batch.begin();
        renderPlatforms();
        renderCharacter();
        renderBirds();
        renderCoins();
        batch.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.RED);

        for(Bird b : world.birds){
            shapeRenderer.rect(playerPosition.x, playerPosition.y, world.getPlayer().bounds.width, world.getPlayer().bounds.height);
            shapeRenderer.rect(b.bounds.x, b.bounds.y, b.bounds.width, b.bounds.height);
        }

        shapeRenderer.rect(playerPosition.x, playerPosition.y, world.getPlayer().bounds.width, world.getPlayer().bounds.height);
        shapeRenderer.end();
    }

    private void renderCoins(){
        TextureRegion coinFrame;
        for(Coin c : world.coins){
            coinFrame = (TextureRegion) coinAnimation.getCoinAnimation().getKeyFrame(c.getStateTime(), true);
            batch.draw(coinFrame, c.position.x, c.position.y, Coin.WIDTH, Coin.HEIGHT);
        }
    }

    private void renderBirds(){
        TextureRegion birdFrame;
        for(Bird b : world.birds){
            if(b.getState().equals(STATE_DEAD)) birdFrame = birdAnimation.getDieFrames();
            else birdFrame = (TextureRegion) birdAnimation.getFlyAnimation().getKeyFrame(b.getStateTime(), true);
            int side = 1;
            if(b.getType() == TYPE_RIGHT) side = -1;
            batch.draw(birdFrame, b.position.x-Bird.WIDTH/2, b.position.y-Bird.HEIGHT/2,
                    Bird.WIDTH/2, Bird.HEIGHT/2,
                    Bird.WIDTH, Bird.HEIGHT, side, 1, 0);
        }
    }

    private void renderBackground() {
        batch.disableBlending();
        batch.begin();
        batch.draw(Assets.getBgRegion(), cam.position.x - WIDTH/2, cam.position.y - HEIGHT/2,
                WIDTH, HEIGHT);
        batch.end();
    }

    private void renderPlatforms() {
        for(Platform p : world.platforms){
            batch.draw(Assets.getPlatform(), p.position.x-Platform.WIDTH/2, p.position.y-Platform.HEIGHT/2,
                    Platform.WIDTH, Platform.HEIGHT);
        }
    }

    private void renderCharacter() {
        TextureRegion currentFrame;
        Player gamePlayer = world.getPlayer();
        switch(gamePlayer.getState()){
            case STATE_JUMP:
                currentFrame = playerAnimation.getJump();
                break;
            case STATE_INGROUND:
                currentFrame = (TextureRegion) playerAnimation.getPlatformAnimation().getKeyFrame(gamePlayer.getStateTime(), false);
                break;
            default:
                currentFrame = playerAnimation.getFall();
                break;
        }

        if(gamePlayer.velocity.x > 0) side = 1;
        else if(gamePlayer.velocity.x < 0) side = -1;
        batch.draw(currentFrame, gamePlayer.position.x, gamePlayer.position.y,
                (currentFrame.getRegionWidth()/30)/2, (currentFrame.getRegionHeight()/30)/2,
                (currentFrame.getRegionWidth()/30), (currentFrame.getRegionHeight()/30), side, 1, 0);

    }

}
