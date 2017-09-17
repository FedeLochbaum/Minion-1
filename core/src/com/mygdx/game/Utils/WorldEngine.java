package com.mygdx.game.Utils;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Entities.Bird;
import com.mygdx.game.Entities.Coin;
import com.mygdx.game.Entities.Platform;
import com.mygdx.game.Entities.Player;
import com.mygdx.game.Utils.Types.BirdType;
import com.mygdx.game.Utils.Types.PlatformType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.mygdx.game.Utils.States.PlayerState.STATE_JUMP;
import static com.mygdx.game.Utils.Types.PlatformType.TYPE_MOVING_X;
import static com.mygdx.game.Utils.Types.PlatformType.TYPE_MOVING_Y;
import static com.mygdx.game.Utils.Types.PlatformType.TYPE_STATIC;

public class WorldEngine {

    public static final Vector2 gravity = new Vector2(0, -9.8f);
    public static final float SCALE = 30;

    public static final float WIDTH = 16;
    public static final float HEIGHT = 26 * 10;

    private final Player player;

    public final List<Platform> platforms;
    public final List<Bird> birds;
    public final List<Coin> coins;
    public final Random rand;

    public int score = 0;
    public float heightCam;


    public WorldEngine(){
        rand = new Random();
        platforms = new ArrayList<Platform>();
        birds = new ArrayList<Bird>();
        coins = new ArrayList<Coin>();
        player = new Player(10, 0);
        generateLevel();
        heightCam = 0;
    }
    public void update(float delta){
        updatePlayer(delta);
        updatePlatforms(delta);
        updateBirds(delta);
        updateCoins(delta);
        checkCollisions();
    }
    private void checkCollisions(){
        platformsCollisions();
        birdsCollisions();
        coinsCollisions();
    }
    private void updatePlayer(float delta){
        player.update(delta);
        heightCam = Math.max(heightCam, player.position.y);
        playerDead();
    }
    private void updatePlatforms(float delta){
        for(Platform p : platforms){
            p.update(delta);
        }
    }

    private void updateBirds(float delta) {
        for(Bird b : birds){
            b.update(delta);
        }
    }

    private void updateCoins(float delta){
        for(Coin c : coins){
            c.update(delta);
        }
    }
    private void generateLevel(){
        float y = rand.nextFloat() * (6f - 5f) + 5f;

        while(y < HEIGHT) {
            PlatformType type;

            float r = rand.nextFloat();

            if(r > 0.8f) type = TYPE_MOVING_Y;
            else if(r > 0.5f) type = TYPE_MOVING_X;
            else type = TYPE_STATIC;
            float x = rand.nextFloat()*(WIDTH-Platform.WIDTH)+1;
            if(type == TYPE_MOVING_Y) y += rand.nextFloat()*2;
            Platform p = new Platform(x, y, type);
            platforms.add(p);


            if(rand.nextFloat() > 0.5f){
                Coin c = new Coin(p.position.x+rand.nextFloat(), p.position.y+Coin.HEIGHT+rand.nextFloat()*3);
                coins.add(c);
            }

            if(rand.nextFloat() > 0.5f){
                BirdType birdType = BirdType.TYPE_LEFT;
                if(rand.nextInt(2) == 2)
                    birdType = BirdType.TYPE_RIGHT;

                Bird b = new Bird(p.position.x+rand.nextFloat(), p.position.y+Bird.HEIGHT+rand.nextFloat()*3, birdType);
                birds.add(b);
            }

            y += rand.nextFloat() * (6f - 3f) + 3f;

        }
    }

    private void playerDead(){
        if(heightCam - WorldEngine.HEIGHT/2 > player.position.y+player.HEIGHT) player.dead();
    }

    private void birdsCollisions(){
        for(Bird b : birds){
            if(b.bounds.overlaps(player.bounds)){
                if(player.position.y > b.position.y){
                    player.hitBird();
                    score = score*2;
                }
                else player.dead();
            }
        }
    }
    private void platformsCollisions(){
        if(player.getState() == STATE_JUMP) return;
        for(Platform p : platforms){
            if(p.bounds.overlaps(player.bounds) && player.position.y > p.position.y){
                if(player.position.x+1f < p.bounds.x + Platform.WIDTH){
                    player.hitPlatform(p);
                    break;
                }
            }
        }
    }
    private void coinsCollisions(){
        int size = coins.size();
        for(Coin c : coins) {
            if(c.bounds.overlaps(player.bounds)){
                score += Coin.POINTS;
                coins.remove(c);
                size = coins.size();
            }
        }
    }

    public Player getPlayer() {
        return player;
    }

}
