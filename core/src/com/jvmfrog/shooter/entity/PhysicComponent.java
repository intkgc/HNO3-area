package com.jvmfrog.shooter.entity;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;

public class PhysicComponent implements Component {
    private static final ComponentMapper<PhysicComponent> mapper = ComponentMapper.getFor(PhysicComponent.class);

    public static PhysicComponent get(Entity entity) {
        return mapper.get(entity);
    }
}
