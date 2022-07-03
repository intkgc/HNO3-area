package com.jvmfrog.shooter.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jvmfrog.shooter.entity.Player;
import com.jvmfrog.shooter.world.World;


public class Render {
    private World world;
    private Player player;
    private SpriteBatch batch;
    private OrthographicCamera camera;

    public void update(World world, Player player) {
        this.world = world;
        this.player = player;
        batch = new SpriteBatch();
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public void render() {

    }
}
