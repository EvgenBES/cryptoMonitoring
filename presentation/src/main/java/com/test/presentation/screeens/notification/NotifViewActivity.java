package com.test.presentation.screeens.notification;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.LinearLayoutManager;
import android.test.com.testproject.R;
import android.test.com.testproject.databinding.ActivityNotifViewBinding;
import android.view.MenuItem;

import com.test.presentation.base.BaseMvvmActivity;

public class NotifViewActivity extends BaseMvvmActivity<NotifViewModel, ActivityNotifViewBinding, NotifRouter>{


    @Override
    protected NotifViewModel provideViewModel() {
        return ViewModelProviders.of(this).get(NotifViewModel.class);
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_notif_view;
    }

    @Override
    protected NotifRouter provideRouter() {
        return new NotifRouter(this);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    router.goToStartView();
                    return true;
                case R.id.navigation_list:
                    router.goToListView();
                    return true;
                case R.id.navigation_alert:
                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Animation
        overridePendingTransition(R.anim.right_down_to_left_top, R.anim.lost_activity_out);

        binding.recyclerNotif.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerNotif.setHasFixedSize(true);
        binding.recyclerNotif.setAdapter(viewModel.adapter);
    }

    @Override
    protected void onResume() {
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_alert);
        super.onResume();
    }

    public static Intent getIntent(Activity activity) {
        Intent intent = new Intent(activity, NotifViewActivity.class);
        return intent;
    }

    @Override
    protected void onPause() {
        super.onPause();
        router.getActivity().finish();
    }

}
