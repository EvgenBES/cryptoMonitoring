package com.test.presentation.base.recycler;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.test.domain.entity.Coin;
import com.test.presentation.screeens.notification.NotifItemViewHolder;
import com.test.presentation.screeens.notification.NotifItemViewModel;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class NotifItemAdapter extends BaseRecyclerViewAdapter<Coin, NotifItemViewModel> {

   //оброботчик нажатия
    private PublishSubject<ClickedItemModel> buttonOneClickSubject = PublishSubject.create();

    @NonNull
    @Override
    public NotifItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return NotifItemViewHolder.create(parent, new NotifItemViewModel(buttonOneClickSubject));
    }

    public Observable<ClickedItemModel> observeButtonOneClick() {
        return buttonOneClickSubject;
    }
}
