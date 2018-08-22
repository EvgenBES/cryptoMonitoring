package com.test.presentation.screeens.notification;

import android.content.Intent;

import com.test.domain.entity.Coin;
import com.test.presentation.base.BaseRouter;
import com.test.presentation.screeens.list.ListViewActivity;
import com.test.presentation.screeens.main.StartViewActivity;

public class NotifRouter extends BaseRouter<NotifViewActivity>{

    public NotifRouter(NotifViewActivity activity) {
        super(activity);
    }

    public void goToStartView() {
        Intent intent = StartViewActivity.getIntent(activity);
        activity.startActivity(intent);
    }

    public void goToListView() {
        Intent intent = ListViewActivity.getIntent(activity);
        activity.startActivity(intent);
    }

    public void addDialog() {
        activity.addNotifDialog();
    }

    public void editDialog() {
        activity.editNotifDialog();
    }

    public void addNotif(Coin coin) {
        NotifViewModel model = new NotifViewModel();
        model.addNotifBd(coin);
    }
}
