package com.mygdx.game.Animations;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Utils.Assets;

public class PlayerAnimation {

    private static final int COLS = 7;

    @SuppressWarnings("unused")
    private static final int ROWS = 2;

    Animation walkAnimation;
    Animation platformAnimation;

    TextureRegion[][] frames;
    TextureRegion[] walkFrames;
    TextureRegion standby;
    TextureRegion jump;
    TextureRegion fall;
    TextureRegion[] platformFrames;

    public PlayerAnimation(){
        frames = TextureRegion.split(Assets.getPlayer(), 72, 97);
        walkFrames = new TextureRegion[6];
        for(int i=1; i < COLS; i++){
            walkFrames[i-1] = frames[0][i];
        }
        walkAnimation = new Animation(0.2f, walkFrames);
        standby = frames[0][0];
        jump = frames[1][0];
        fall = frames[1][1];
        platformFrames = new TextureRegion[2];
        platformFrames[0] = new TextureRegion(Assets.getChar_inplatform(), 0, 0, 69, 71);
        platformFrames[1] = standby;
        platformAnimation = new Animation(1f, platformFrames);
    }

    public Animation getWalkAnimation() {
        return walkAnimation;
    }

    public void setWalkAnimation(Animation walkAnimation) {
        this.walkAnimation = walkAnimation;
    }

    public Animation getPlatformAnimation() {
        return platformAnimation;
    }

    public void setPlatformAnimation(Animation platformAnimation) {
        this.platformAnimation = platformAnimation;
    }

    public TextureRegion[][] getFrames() {
        return frames;
    }

    public void setFrames(TextureRegion[][] frames) {
        this.frames = frames;
    }

    public TextureRegion[] getWalkFrames() {
        return walkFrames;
    }

    public void setWalkFrames(TextureRegion[] walkFrames) {
        this.walkFrames = walkFrames;
    }

    public TextureRegion getStandby() {
        return standby;
    }

    public void setStandby(TextureRegion standby) {
        this.standby = standby;
    }

    public TextureRegion getJump() {
        return jump;
    }

    public void setJump(TextureRegion jump) {
        this.jump = jump;
    }

    public TextureRegion getFall() {
        return fall;
    }

    public void setFall(TextureRegion fall) {
        this.fall = fall;
    }

    public TextureRegion[] getPlatformFrames() {
        return platformFrames;
    }

    public void setPlatformFrames(TextureRegion[] platformFrames) {
        this.platformFrames = platformFrames;
    }
}
