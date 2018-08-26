package com.test.presentation.screeens.list.fragment.right;

import android.databinding.ObservableInt;
import android.test.com.testproject.R;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.test.app.App;
import com.test.domain.entity.Coin;
import com.test.domain.usecases.AddCoinUseCase;
import com.test.domain.usecases.GetListCoinUseCase;
import com.test.presentation.base.BaseViewModel;
import com.test.presentation.base.recycler.ClickedItemModel;
import com.test.presentation.base.recycler.ListRightFragmentItemAdapter;
import com.test.presentation.screeens.list.ListRouter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static android.os.SystemClock.sleep;

public class ListRightFragmentModel extends BaseViewModel<ListRouter, Coin> {

    public ListRightFragmentItemAdapter adapter = new ListRightFragmentItemAdapter();
    public ObservableInt coinProgress = new ObservableInt(View.VISIBLE);

    @Inject
    public GetListCoinUseCase getListCoinUseCase;

    @Inject
    public AddCoinUseCase addCoinUseCase;


    @Override
    protected void runInject() {
        App.getAppComponent().runInject(this);
    }

    @Override
    public void setItem(Coin item) {

    }

    public ListRightFragmentModel() {
        getListCoinUseCase
                .getCoins()
                .subscribe(new Observer<List<Coin>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        getCompositeDisposable().add(d);
                    }

                    @Override
                    public void onNext(List<Coin> coins) {
                        adapter.setItems(coins);

                        sleep(1000);
                        coinProgress.set(View.GONE);
                    }

                    @Override
                    public void onError(Throwable e) {
//                        router.showError(e);
                    }

                    @Override
                    public void onComplete() {
                    }
                });


//        adapter
//                .observeItemClick()
//                .subscribe(new Observer<ClickedItemModel<Coin>>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                    }
//
//                    @Override
//
//                    public void onNext(ClickedItemModel<Coin> coinClickedItemModel) {
//
//                        //Toast add coin user list
////                        showCostomToast();
//
//                        Toast.makeText(App.getContext(), "You add " + coinClickedItemModel.getEntity().getName(), Toast.LENGTH_SHORT).show();
//
//                        addCoinUseCase
//                                .addCoin(String.valueOf(coinClickedItemModel.getEntity().getId()))
//                                .subscribe(new Observer<Coin>() {
//                                    @Override
//                                    public void onSubscribe(Disposable d) {
//
//                                    }
//
//                                    @Override
//                                    public void onNext(Coin coin) {
//                                    }
//
//                                    @Override
//                                    public void onError(Throwable e) {
//
//                                    }
//
//                                    @Override
//                                    public void onComplete() {
//                                    }
//                                });
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });


        adapter
                .observeButtonOneClick()
                .subscribe(new Observer<ClickedItemModel<Coin>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ClickedItemModel<Coin> clickedItemModel) {

                        Toast.makeText(App.getContext(), "You add " + clickedItemModel.getEntity().getName(), Toast.LENGTH_SHORT).show();

                        addCoinUseCase
                                .addCoin(clickedItemModel.getEntity());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    //TODO доделать вызов Тоста при добавление новой монетки в локальную БД
    //TODO доделать вызов диалогового окна, перед тем как добавить монетку в БД, возможно юзер промахнулся


    public void showCostomToast() {
        LayoutInflater inflater = router.getActivity().getLayoutInflater();
        View toastLayout = inflater.inflate(R.layout.custom_add_toast, (ViewGroup) router.getActivity().findViewById(R.id.custom_toast_image));

        Toast toast = new Toast(App.getContext());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(toastLayout);
        toast.show();
    }


}


