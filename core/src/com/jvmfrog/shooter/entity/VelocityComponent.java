package com.jvmfrog.shooter.entity;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;

public class VelocityComponent implements Component {
    public Vector2 velocity = new Vector2();

    private static final ComponentMapper<VelocityComponent> mapper = ComponentMapper.getFor(VelocityComponent.class);

    public static VelocityComponent get(Entity entity) {
        return mapper.get(entity);
    }
}
