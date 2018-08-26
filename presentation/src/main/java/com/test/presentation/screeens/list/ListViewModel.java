package com.test.presentation.screeens.list;

import com.test.app.App;
import com.test.domain.entity.Coin;
import com.test.presentation.base.BaseViewModel;

public class ListViewModel extends BaseViewModel<ListRouter, Coin>{


    @Override
    protected void runInject() {
        App.getAppComponent().runInject(this);
    }

    public void onClickBack() {
        router.getActivity().finish();
    }

    @Override
    public void setItem(Coin coin) {

    }

    public ListViewModel() {
    }

}
