package com.jvmfrog.shooter.render;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jvmfrog.shooter.entity.PositionComponent;
import com.jvmfrog.shooter.world.World;


public class MapRenderSystem extends EntitySystem {
    private World world;
    private PositionComponent playerPosition;
    private SpriteBatch batch;
    private OrthographicCamera camera;

    public void init(World world, PositionComponent player) {
        this.world = world;
        this.playerPosition = player;
        batch = new SpriteBatch();
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }

    public SpriteBatch getBatch() {
        return batch;
    }
}
