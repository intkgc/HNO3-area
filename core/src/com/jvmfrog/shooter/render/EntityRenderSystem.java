package com.jvmfrog.shooter.render;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jvmfrog.shooter.entity.EntitySystem;
import com.jvmfrog.shooter.entity.PositionComponent;
import com.jvmfrog.shooter.entity.RenderComponent;

public class EntityRenderSystem extends EntitySystem {

    private SpriteBatch batch;

    private ComponentMapper<RenderComponent> renderComponentMapper = ComponentMapper.getFor(RenderComponent.class);
    private ComponentMapper<PositionComponent> positionComponentMapper = ComponentMapper.getFor(PositionComponent.class);
    private ImmutableArray<Entity> entities;

    public EntityRenderSystem(SpriteBatch batch) {
        this.batch = batch;
    }

    @Override
    public void addedToEngine(Engine engine) {
        entities = engine.getEntities();
    }

    @Override
    public void removedFromEngine(Engine engine) {

    }

    @Override
    public void update(float deltaTime) {
        for (Entity entity : entities) {
            RenderComponent renderComponent = renderComponentMapper.get(entity);
            PositionComponent positionComponent = positionComponentMapper.get(entity);
            renderComponent.sprite.setPosition(positionComponent.position.x, positionComponent.position.y);
            renderComponent.sprite.draw(batch);
        }
    }
}
