package com.test.presentation.screeens.notification;

import android.content.Intent;

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
}
