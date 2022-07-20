package com.jvmfrog.shooter.world;

import com.badlogic.ashley.core.Engine;

public class World {
    public Tile[][] map;
    public World(int width, int height) {
        map = new Tile[width][height];
    }
}
