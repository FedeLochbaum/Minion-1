package com.mygdx.game.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {

    private static Texture background, items, player, bird, coin;

    private static BitmapFont font;
    private static Music music;

    private static TextureRegion bgRegion, soundOn, soundOff, playButton;

    public static void load(){

        background = new Texture(Gdx.files.internal("data/background.png"));
        items = new Texture(Gdx.files.internal("data/items.png"));
        player = new Texture(Gdx.files.internal("data/player.png"));
        bird = new Texture(Gdx.files.internal("data/bird.png"));
        coin = new Texture(Gdx.files.internal("data/coin.png"));

        font = new BitmapFont(Gdx.files.internal("font/font.fnt"));

        music = Gdx.audio.newMusic(Gdx.files.internal("music/music.mp3"));
        music.setLooping(true);
        music.play();

        bgRegion = new TextureRegion(background, 0, 0, 480, 800);
        soundOn = new TextureRegion(items, 0, 0, 80, 80);
        soundOff = new TextureRegion(items, 80, 0, 80, 80);
        playButton = new TextureRegion(items, 104, 80, 104, 46);


    }

    public static Texture getBackground() {
        return background;
    }

    public static void setBackground(Texture background) {
        Assets.background = background;
    }

    public static Texture getItems() {
        return items;
    }

    public static void setItems(Texture items) {
        Assets.items = items;
    }

    public static Texture getPlayer() {
        return player;
    }

    public static void setPlayer(Texture player) {
        Assets.player = player;
    }

    public static Texture getBird() {
        return bird;
    }

    public static void setBird(Texture bird) {
        Assets.bird = bird;
    }

    public static Texture getCoin() {
        return coin;
    }

    public static void setCoin(Texture coin) {
        Assets.coin = coin;
    }

    public static BitmapFont getFont() {
        return font;
    }

    public static void setFont(BitmapFont font) {
        Assets.font = font;
    }

    public static Music getMusic() {
        return music;
    }

    public static void setMusic(Music music) {
        Assets.music = music;
    }

    public static TextureRegion getBgRegion() {
        return bgRegion;
    }

    public static void setBgRegion(TextureRegion bgRegion) {
        Assets.bgRegion = bgRegion;
    }

    public static TextureRegion getSoundOn() {
        return soundOn;
    }

    public static void setSoundOn(TextureRegion soundOn) {
        Assets.soundOn = soundOn;
    }

    public static TextureRegion getSoundOff() {
        return soundOff;
    }

    public static void setSoundOff(TextureRegion soundOff) {
        Assets.soundOff = soundOff;
    }

    public static TextureRegion getPlayButton() {
        return playButton;
    }

    public static void setPlayButton(TextureRegion playButton) {
        Assets.playButton = playButton;
    }


}
