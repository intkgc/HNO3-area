package com.jvmfrog.shooter.entity;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class RenderComponent implements Component {
    public Sprite sprite;

    private static final ComponentMapper<RenderComponent> mapper = ComponentMapper.getFor(RenderComponent.class);

    public static RenderComponent get(Entity entity) {
        return mapper.get(entity);
    }
}
