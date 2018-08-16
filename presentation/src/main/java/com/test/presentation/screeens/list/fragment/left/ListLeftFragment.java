package com.test.presentation.screeens.list.fragment.left;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.test.com.testproject.R;
import android.test.com.testproject.databinding.FragmentListLeftBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.presentation.base.BaseMvvmFragment;
import com.test.presentation.screeens.list.ListRouter;
import com.test.presentation.screeens.list.ListViewActivity;
import com.test.presentation.screeens.list.ListViewModel;

public class ListLeftFragment extends BaseMvvmFragment<ListLeftFragmentModel, FragmentListLeftBinding, ListRouter> {
    private static final int LAYOUT = R.layout.fragment_list_left;


    public static ListLeftFragment newInstance() {
        Bundle args = new Bundle();
        ListLeftFragment fragment = new ListLeftFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected ListLeftFragmentModel provideViewModel() {
        return ViewModelProviders.of(this).get(ListLeftFragmentModel.class);
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


        binding.recyclerFragmentListLeft.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerFragmentListLeft.setHasFixedSize(true);
        binding.recyclerFragmentListLeft.setAdapter(viewModel.adapter);

    }
}
