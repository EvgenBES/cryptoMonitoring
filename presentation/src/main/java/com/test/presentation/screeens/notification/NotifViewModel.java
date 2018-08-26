package com.test.presentation.screeens.notification;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.test.com.testproject.R;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.test.app.App;
import com.test.domain.entity.Coin;
import com.test.domain.usecases.AddNotifUseCase;
import com.test.domain.usecases.DeleteNotifUseCase;
import com.test.domain.usecases.GetNotifListUseCase;
import com.test.presentation.base.BaseViewModel;
import com.test.presentation.base.recycler.ClickedItemModel;
import com.test.presentation.base.recycler.NotifItemAdapter;

import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class NotifViewModel extends BaseViewModel<NotifRouter, Coin> {

    public NotifItemAdapter adapter = new NotifItemAdapter();

    Random random = new Random();

    private Coin addNotifCoin;

    @Inject
    public GetNotifListUseCase notifListUseCase;

    @Inject
    public AddNotifUseCase addNotifUseCase;

    @Inject
    public DeleteNotifUseCase deleteNotifUseCase;


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
                      router.editDialog();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * TODO до реализовать диалоговые окна! Ввод данных, поиск из списка, изменение!
     *
     * @param id элемента из БД который мы меняем
     */

    private void onClickEditNotif(int id) {
        final long editCoinId = id;

        final AlertDialog.Builder builder = new AlertDialog.Builder(router.getActivity(), AlertDialog.THEME_HOLO_DARK);
        LayoutInflater inflater = router.getActivity().getLayoutInflater();
        builder
                .setView(inflater.inflate(R.layout.item_notif_edit_dialog, null))
                // Add action buttons
                .setPositiveButton(R.string.editNotif, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

//                        Coin(long id, String name, String symbol, String image, int motion, double position)


                        //TODO переделать под изменение одного поля базы
                        addNotifUseCase.addNotif(new Coin(
                                editCoinId,
                                "Magarita",
                                "MGT",
                                random.nextInt(999),
                                random.nextBoolean()));

                    }
                })
                .setNegativeButton(R.string.deletNotif, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        deleteNotifUseCase
                                .deleteNotif(editCoinId);

                        showDeleteToast();
                    }
                })
                .show();

    }


    public void onClickAddNotif() {
       router.addDialog();
    }


    public void onClickBack() {
        router.getActivity().finish();
    }


    public void showDeleteToast() {
        LayoutInflater inflater = router.getActivity().getLayoutInflater();
        View toastLayout = inflater.inflate(R.layout.custom_del_notif_toast, (ViewGroup) router.getActivity().findViewById(R.id.custom_del_toast_image));

        Toast toast = new Toast(App.getContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 275);
        toast.setView(toastLayout);
        toast.show();
    }

    public void showAddNotifToast() {
        LayoutInflater inflater = router.getActivity().getLayoutInflater();
        View toastLayout = inflater.inflate(R.layout.custom_add_notif_toast, (ViewGroup) router.getActivity().findViewById(R.id.custom_add_notif_toast_image));

        Toast toast = new Toast(App.getContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 275);
        toast.setView(toastLayout);
        toast.show();
    }

    public void addNotifBd(Coin coin) {
        addNotifUseCase.addNotif(coin);
    }
}
