package com.test.presentation.base.recycler;

import com.test.domain.entity.DomainModel;

//Entity то что приходит из domain
public abstract class BaseItemViewModel<Entity extends DomainModel> {

    public abstract void setItem(Entity entity, int position);

    public void onItemClick() {

    }
}
