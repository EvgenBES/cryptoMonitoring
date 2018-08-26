package com.test.presentation.screeens.main;

import android.content.Intent;

import com.test.domain.entity.Coin;
import com.test.presentation.base.BaseRouter;
import com.test.presentation.screeens.list.ListViewActivity;
import com.test.presentation.screeens.notification.NotifViewActivity;

public class StartRouter extends BaseRouter<StartViewActivity> {

    public StartRouter(StartViewActivity activity) {
        super(activity);
    }

    public void goToListView() {
        Intent intent = ListViewActivity.getIntent(activity);
        activity.startActivity(intent);
    }

    public void goToNotifView() {
        Intent intent = NotifViewActivity.getIntent(activity);
        activity.startActivity(intent);
    }

    public void goDialog(Coin entity) {
       activity.startDialog(entity);
    }

    public void loadQuantityCoins(Coin coin) {
        StartViewModel model = new StartViewModel();
        model.addQuantityCoinBd(coin);
    }
}
