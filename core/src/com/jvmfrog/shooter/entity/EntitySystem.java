package com.jvmfrog.shooter.entity;

import com.badlogic.ashley.core.Engine;

public abstract class EntitySystem {
    public abstract void addedToEngine(Engine engine);
    public abstract void removedFromEngine(Engine engine);
    public abstract void update(float deltaTime);
}