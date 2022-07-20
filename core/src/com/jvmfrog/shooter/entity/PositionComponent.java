package com.jvmfrog.shooter.entity;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;

public class PositionComponent implements Component {
    public Vector2 position = new Vector2();
    private static final ComponentMapper<PositionComponent> mapper = ComponentMapper.getFor(PositionComponent.class);

    public static PositionComponent get(Entity entity) {
        return mapper.get(entity);
    }
}
