package com.test.presentation.screeens.notification;

import android.app.Activity;
import android.content.Intent;

import com.test.domain.entity.Coin;
import com.test.presentation.base.BaseRouter;
import com.test.presentation.screeens.list.ListViewActivity;
import com.test.presentation.screeens.main.StartViewActivity;

import java.util.ArrayList;
import java.util.List;

public class NotifRouter extends BaseRouter<NotifViewActivity> {

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

    public void addDialog(ArrayList<String> listSearchCoin) {
        activity.addNotifDialog(listSearchCoin);
    }

    public void editDialog(int idNotif, String name, double price, boolean motionPrice) {
        activity.editNotifDialog(idNotif, name, price, motionPrice);
    }

    public void addNotif(String coinName, double priceCoin, boolean motion, boolean result, Activity activity) {
        NotifViewModel model = new NotifViewModel();
        model.addNotifBd(coinName, priceCoin, motion, result, activity);
    }


    public void editNotif(int idNotif, double editPrice, boolean morionPrice, boolean result, Activity activity) {
        NotifViewModel model = new NotifViewModel();
        model.editNotifBd(idNotif, editPrice, morionPrice, result, activity);
    }

    public void deletNotif(int idNotif, Activity activity) {
        NotifViewModel model = new NotifViewModel();
        model.deletNotif(idNotif, activity);

    }
}
