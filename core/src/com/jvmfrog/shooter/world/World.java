package com.jvmfrog.shooter.world;

public class World {
    public Tile[][] map;

    public World(int width, int height) {
        map = new Tile[width][height];
    }
}
