package com.test.presentation.screeens.main;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.LinearLayoutManager;
import android.test.com.testproject.R;
import android.test.com.testproject.databinding.StartActivityViewBinding;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.test.app.App;
import com.test.domain.entity.Coin;
import com.test.presentation.base.BaseMvvmActivity;

public class StartViewActivity extends BaseMvvmActivity<StartViewModel, StartActivityViewBinding, StartRouter> {
    private static final String TAG = "StartViewActivity";

    private Coin coin;


    @Override
    protected StartViewModel provideViewModel() {
        return ViewModelProviders.of(this).get(StartViewModel.class);
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.start_activity_view;
    }

    @Override
    protected StartRouter provideRouter() {
        return new StartRouter(this);
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    return true;
                case R.id.navigation_list:
                    router.goToListView();
                    return true;
                case R.id.navigation_alert:
                    router.goToNotifView();
            }
            return false;
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding.startRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.startRecyclerView.setHasFixedSize(true);
        binding.startRecyclerView.setAdapter(viewModel.adapter);
    }


    @Override
    protected void onResume() {
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_home);
        super.onResume();
    }

    public static Intent getIntent(Activity activity) {
        Intent intent = new Intent(activity, StartViewActivity.class);
        return intent;
    }


    public void startDialog(Coin entity) {
        coin = entity;
        QuantityDialog dialog = new QuantityDialog();
        dialog.show(getSupportFragmentManager(), "QuantityDialog");
    }

    public void loadQuantityCoin(String quantity) {
        showEditQuantityToast();
        coin.setQuantity(Double.valueOf(quantity));
        router.loadQuantityCoins(coin);
    }



    public void showEditQuantityToast() {
        LayoutInflater inflater = router.getActivity().getLayoutInflater();
        View toastLayout = inflater.inflate(R.layout.custom_edit_quantity_toast, (ViewGroup) router.getActivity().findViewById(R.id.custom_add_toast_image));

        Toast toast = new Toast(App.getContext());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 275);
        toast.setView(toastLayout);
        toast.show();
    }
}
