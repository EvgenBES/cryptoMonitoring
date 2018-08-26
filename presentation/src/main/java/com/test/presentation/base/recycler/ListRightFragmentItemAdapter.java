package com.test.presentation.base.recycler;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.test.domain.entity.Coin;
import com.test.presentation.screeens.list.fragment.right.RightItemFragmentHolder;
import com.test.presentation.screeens.list.fragment.right.RightItemFragmentModel;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class ListRightFragmentItemAdapter extends BaseRecyclerViewAdapter<Coin, RightItemFragmentModel> {

    private PublishSubject<ClickedItemModel<Coin>> buttonOneClickSubject = PublishSubject.create();

    @NonNull
    @Override
    public RightItemFragmentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return RightItemFragmentHolder.create(parent, new RightItemFragmentModel(buttonOneClickSubject));
    }

    public Observable<ClickedItemModel<Coin>> observeButtonOneClick() {
        return buttonOneClickSubject;
    }
}
