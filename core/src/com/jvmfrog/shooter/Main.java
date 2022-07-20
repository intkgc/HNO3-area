package com.jvmfrog.shooter;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.jvmfrog.shooter.entity.Player;
import com.jvmfrog.shooter.entity.PositionComponent;
import com.jvmfrog.shooter.render.EntityRenderSystem;
import com.jvmfrog.shooter.render.MapRenderSystem;
import com.jvmfrog.shooter.world.World;

public class Main extends Game {
    World world;
    MapRenderSystem mapRender;
    Engine engine;
    Player player;

    @Override
    public void create() {
        engine = new Engine();
        world = new World(50, 50);
        player = new Player();
        engine.addEntity(player);
        engine.addSystem((mapRender = new MapRenderSystem()));
        engine.addSystem(new EntityRenderSystem(mapRender.getBatch()));
        mapRender.init(world, PositionComponent.get(player));
    }

    @Override
    public void render() {
        ScreenUtils.clear(1, 1, 1, 1);
        engine.update(Gdx.graphics.getDeltaTime());
        super.render();
    }
}
