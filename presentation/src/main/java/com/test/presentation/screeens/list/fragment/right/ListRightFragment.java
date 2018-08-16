package com.test.presentation.screeens.list.fragment.right;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.test.com.testproject.R;
import android.test.com.testproject.databinding.FragmentListRightBinding;
import android.view.View;

import com.test.presentation.base.BaseMvvmFragment;
import com.test.presentation.screeens.list.ListRouter;
import com.test.presentation.screeens.list.ListViewActivity;

public class ListRightFragment extends BaseMvvmFragment<ListRightFragmentModel, FragmentListRightBinding, ListRouter> {
    private static final int LAYOUT = R.layout.fragment_list_right;


    public static ListRightFragment newInstance() {
        Bundle args = new Bundle();
        ListRightFragment fragment = new ListRightFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected ListRightFragmentModel provideViewModel() {
        return ViewModelProviders.of(this).get(ListRightFragmentModel.class);
    }

    @Override
    protected int provideLayoutId() {
        return LAYOUT;
    }

    @Override
    protected ListRouter provideRouter() {
        return new ListRouter((ListViewActivity)getActivity());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.recyclerFragmentListRight.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerFragmentListRight.setHasFixedSize(true);
        binding.recyclerFragmentListRight.setAdapter(viewModel.adapter);

    }
}
