package com.jvmfrog.shooter;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.utils.ScreenUtils;
import com.jvmfrog.shooter.entity.Player;
import com.jvmfrog.shooter.render.Render;
import com.jvmfrog.shooter.world.World;

public class Main extends Game {
    World world;
    Render render;
    Player player;

    @Override
    public void create() {
        world = new World(50, 50);
        render = new Render();
        render.update(world, player);
    }

    @Override
    public void render() {
        ScreenUtils.clear(1, 1, 1, 1);
        super.render();
    }
}
