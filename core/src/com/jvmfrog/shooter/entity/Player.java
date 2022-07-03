package com.jvmfrog.shooter.entity;

import com.badlogic.ashley.core.Entity;

public class Player extends Entity {
    public Player() {
        add(new PhysicComponent());
        add(new PositionComponent());
        add(new RenderComponent());
        add(new VelocityComponent());

    }
}
