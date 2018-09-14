package com.test.presentation.screeens.list;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.test.com.testproject.R;
import android.test.com.testproject.databinding.ActivityListViewBinding;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;

import com.test.executor.EditTextSearch;
import com.test.presentation.base.BaseMvvmActivity;
import com.test.presentation.base.recycler.ClickedItemModel;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;

public class ListViewActivity extends BaseMvvmActivity<ListViewModel, ActivityListViewBinding, ListRouter> {
    public static EditText editTextSearchView;


    @Override
    protected ListViewModel provideViewModel() {
        return ViewModelProviders.of(this).get(ListViewModel.class);
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_list_view;
    }

    @Override
    public ListRouter provideRouter() {
        return new ListRouter(this);
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    router.goToStartView();
                case R.id.navigation_list:
                    return true;
                case R.id.navigation_alert:
                    router.goToNotifView();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        editTextSearchView = findViewById(R.id.testIdSeart);

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new TabsPageFragmentAdapter(getSupportFragmentManager(),
                ListViewActivity.this));

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void onResume() {
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_list);
        super.onResume();
    }


    public static Intent getIntent(Activity activity) {
        Intent intent = new Intent(activity, ListViewActivity.class);
        return intent;
    }

    @Override
    protected void onPause() {
        super.onPause();
        router.getActivity().finish();
    }


}
