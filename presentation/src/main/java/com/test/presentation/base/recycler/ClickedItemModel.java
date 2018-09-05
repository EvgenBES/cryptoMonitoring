package com.test.presentation.base.recycler;

import com.test.domain.entity.Coin;

public class ClickedItemModel<Entity extends Coin> {

    private Entity entity;
    private int position;

    public ClickedItemModel(Entity entity, int position) {
        this.entity = entity;
        this.position = position;
    }

    public Entity getEntity() {
        return entity;
    }

    public int getPosition() {
        return position;
    }
}
