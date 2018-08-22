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

import com.test.domain.entity.Coin;
import com.test.presentation.base.BaseMvvmActivity;
import com.test.presentation.screeens.main.QuantityDialog;

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

    public void addNotifDialog() {
        AddNotificationDialog dialog = new AddNotificationDialog();
        dialog.show(getSupportFragmentManager(), "AddNotificationDialog");
    }

    public void editNotifDialog() {
        EditNotificationDialog dialog = new EditNotificationDialog();
        dialog.show(getSupportFragmentManager(), "EditNotificationDialog");
    }

    public void addNotificationBd(Coin coin) {
        router.addNotif(coin);
    }
}
