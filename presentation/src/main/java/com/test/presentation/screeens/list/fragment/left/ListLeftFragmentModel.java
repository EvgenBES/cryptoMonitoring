package com.test.presentation.screeens.list.fragment.left;

import android.databinding.ObservableInt;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Toast;

import com.test.app.App;
import com.test.domain.entity.Coin;
import com.test.domain.usecases.DeleteCoinUserListUseCase;
import com.test.domain.usecases.DeleteNotifUseCase;
import com.test.domain.usecases.GetBDCoinUseCase;
import com.test.presentation.base.BaseViewModel;
import com.test.presentation.base.recycler.ClickedItemModel;
import com.test.presentation.base.recycler.ListLeftFragmentItemAdapter;
import com.test.presentation.screeens.notification.NotifRouter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import static android.os.SystemClock.sleep;

public class ListLeftFragmentModel extends BaseViewModel<NotifRouter, Coin> {

    public ListLeftFragmentItemAdapter adapter = new ListLeftFragmentItemAdapter();
    public ObservableInt coinProgress = new ObservableInt(View.VISIBLE);

    @Inject
    public GetBDCoinUseCase getBDCoinUseCase;

    @Inject
    public DeleteCoinUserListUseCase deleteCoinUserListUseCase;


    @Override
    protected void runInject() {
        App.getAppComponent().runInject(this);
    }

    @Override
    public void setItem(Coin coin) {

    }

    public ListLeftFragmentModel() {
        getBDCoinUseCase
                .getBdCoin()
                .subscribe(new Consumer<List<Coin>>() {
                    @Override
                    public void accept(List<Coin> coins) throws Exception {
                        adapter.setItems(coins);
                        sleep(500);
                        coinProgress.set(View.GONE);
                    }
                })
                .isDisposed();


        adapter
                .observeButtonOneClick()
                .subscribe(new Observer<ClickedItemModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ClickedItemModel clickedItemModel) {
                        coinProgress.set(View.VISIBLE);
                        Toast.makeText(App.getContext(), "You delete coin.", Toast.LENGTH_SHORT).show();
                        deleteCoinUserListUseCase.deleteUserCoin(clickedItemModel.getPosition());
                        sleep(500);
//                        coinProgress.set(View.GONE);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    //TODO реализовать так же вызов диалогового окна
    //TODO перейти в инфу
    //TODO реализовать кастомный Тост в случае удаления с информацией об удаление из локальной БД

}
