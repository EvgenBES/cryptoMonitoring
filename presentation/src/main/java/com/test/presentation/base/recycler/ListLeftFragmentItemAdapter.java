package com.test.presentation.base.recycler;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.test.domain.entity.Coin;
import com.test.presentation.screeens.list.fragment.left.LeftItemFragmentHolder;
import com.test.presentation.screeens.list.fragment.left.LeftItemFragmentModel;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class ListLeftFragmentItemAdapter extends BaseRecyclerViewAdapter<Coin, LeftItemFragmentModel> {

    private PublishSubject<ClickedItemModel> buttonOneClickSubject = PublishSubject.create();

    @NonNull
    @Override
    public LeftItemFragmentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return LeftItemFragmentHolder.create(parent, new LeftItemFragmentModel(buttonOneClickSubject));
    }

    public Observable<ClickedItemModel> observeButtonOneClick() {
        return buttonOneClickSubject;
    }

}
