package com.test.presentation.screeens.notification;

import android.app.Activity;
import android.test.com.testproject.R;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.test.app.App;
import com.test.domain.entity.Coin;
import com.test.domain.usecases.AddNotifUseCase;
import com.test.domain.usecases.DeleteNotifUseCase;
import com.test.domain.usecases.EditNotifUseCase;
import com.test.domain.usecases.GetListCoinUseCase;
import com.test.domain.usecases.GetNotifListUseCase;
import com.test.domain.usecases.SearchCoinLocalUseCase;
import com.test.presentation.base.BaseViewModel;
import com.test.presentation.base.recycler.ClickedItemModel;
import com.test.presentation.base.recycler.NotifItemAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import android.support.v4.util.ArraySet;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class NotifViewModel extends BaseViewModel<NotifRouter, Coin> {

    public NotifItemAdapter adapter = new NotifItemAdapter();

    private Set<String> listCoin = new ArraySet<>();

    @Inject
    public GetNotifListUseCase notifListUseCase;

    @Inject
    public AddNotifUseCase addNotifUseCase;

    @Inject
    public DeleteNotifUseCase deleteNotifUseCase;

    @Inject
    public GetListCoinUseCase listCoinUseCase;

    @Inject
    public SearchCoinLocalUseCase searchCoin;

    @Inject
    public EditNotifUseCase editNotifUseCase;


    @Override
    protected void runInject() {
        App.getAppComponent().runInject(this);
    }

    @Override
    public void setItem(Coin coin) {

    }

    public NotifViewModel() {
        //Add all notification to recyclerview
        notifListUseCase
                .getAllNotif()
                .subscribe(new Consumer<List<Coin>>() {
                    @Override
                    public void accept(List<Coin> coins) throws Exception {
                        adapter.setItems(coins);
                    }
                })
                .isDisposed();


        //Listen click button edit item
        adapter
                .observeButtonOneClick()
                .subscribe(new Observer<ClickedItemModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ClickedItemModel clickedItemModel) {
                        router.editDialog(
                                clickedItemModel.getEntity().getIdNotif(),
                                clickedItemModel.getEntity().getName(),
                                clickedItemModel.getEntity().getPricePosition(),
                                clickedItemModel.getEntity().getMotionPrice()
                        );
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        listCoinUseCase
                .getCoins()
                .subscribe(new Consumer<List<Coin>>() {
                    @Override
                    public void accept(List<Coin> coins) throws Exception {
                        for (Coin coin : coins) {
                            listCoin.add(coin.getName());
                        }
                    }
                })
                .isDisposed();
    }


    public void onClickAddNotif() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i <= listCoin.size() - 1; i++) {
            list.add(listCoin.iterator().next());
        }
        router.addDialog(list);
    }


    public void onClickBack() {
        router.getActivity().finish();
    }


    public void addNotifBd(final String coinName, final double priceCoin, final boolean motion, boolean result, Activity activity) {
        if (result) {
            searchCoin
                    .searchCoin(coinName)
                    .subscribe(new Consumer<List<Coin>>() {
                        @Override
                        public void accept(List<Coin> coins) throws Exception {
                            addNotifUseCase.addNotif(new Coin(0, coins.get(coins.size() - 1).getId(), coinName, priceCoin, motion));
                        }
                    })
                    .isDisposed();
            showAddNotifToast(activity);
        } else {
            showFalseNotifToast(activity);
        }
    }

    public void editNotifBd(int idNotif, double editPrice, boolean morionPrice, boolean result, Activity activity) {
        if (result) {
            editNotifUseCase
                    .editNotif(idNotif, editPrice, morionPrice);
            showEditNotifToast(activity);
        } else {
            showFalseNotifToast(activity);
        }
    }

    public void deletNotif(int idNotif, Activity activity) {
        deleteNotifUseCase.deleteNotif(idNotif);
        showDeleteToast(activity);
    }


    public void showDeleteToast(Activity activity) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View toastLayout = inflater.inflate(R.layout.custom_del_notif_toast, (ViewGroup) activity.findViewById(R.id.custom_del_toast_image));
        getTost(toastLayout);
    }

    public void showAddNotifToast(Activity activity) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View toastLayout = inflater.inflate(R.layout.custom_add_notif_toast, (ViewGroup) activity.findViewById(R.id.custom_add_notif_toast_image));
        getTost(toastLayout);
    }

    public void showEditNotifToast(Activity activity) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View toastLayout = inflater.inflate(R.layout.custom_add_notif_toast, (ViewGroup) activity.findViewById(R.id.custom_add_notif_toast_image));
        getTost(toastLayout);
    }

    public void showFalseNotifToast(Activity activity) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View toastLayout = inflater.inflate(R.layout.custom_false_notif_toast, (ViewGroup) activity.findViewById(R.id.custom_toast_image));
        getTost(toastLayout);
    }


    private void getTost(View toastLayout) {
        Toast toast = new Toast(App.getContext());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 275);
        toast.setView(toastLayout);
        toast.show();
    }
}
