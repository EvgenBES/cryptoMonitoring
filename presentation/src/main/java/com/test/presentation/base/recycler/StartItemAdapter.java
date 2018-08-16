package com.test.presentation.base.recycler;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.test.domain.entity.Coin;
import com.test.presentation.screeens.main.StartItemViewHolder;
import com.test.presentation.screeens.main.StartItemViewModel;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class StartItemAdapter extends BaseRecyclerViewAdapter<Coin, StartItemViewModel> {

    //эти две штуки не обязательно, это для специфических кликах
    //например когда внутри item есть две кнопки и нужно обрабатывать клики на них
    private PublishSubject<ClickedItemModel> buttonOneClickSubject = PublishSubject.create();

    @NonNull
    @Override
    public StartItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return StartItemViewHolder.create(parent, new StartItemViewModel(buttonOneClickSubject));
    }

    public Observable<ClickedItemModel> observeButtonOneClick() {
        return buttonOneClickSubject;
    }

}
