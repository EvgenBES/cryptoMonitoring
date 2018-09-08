package com.test.presentation.screeens.list;

import android.content.Intent;

import com.test.presentation.base.BaseRouter;
import com.test.presentation.screeens.main.StartViewActivity;
import com.test.presentation.screeens.notification.NotifViewActivity;

public class ListRouter extends BaseRouter<ListViewActivity> {

    public ListRouter(ListViewActivity activity) {
        super(activity);
    }


    public void goToStartView() {
        Intent intent = StartViewActivity.getIntent(activity);
        activity.startActivity(intent);
    }

    public void goToNotifView() {
        Intent intent = NotifViewActivity.getIntent(activity);
        activity.startActivity(intent);
    }
}
