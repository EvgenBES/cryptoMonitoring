package com.test.injection;

import com.test.presentation.screeens.list.ListViewModel;
import com.test.presentation.screeens.list.fragment.left.ListLeftFragmentModel;
import com.test.presentation.screeens.list.fragment.right.ListRightFragmentModel;
import com.test.presentation.screeens.main.StartViewModel;
import com.test.presentation.screeens.notification.NotifViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {
//        AndroidSupportInjectionModule.class,
        AppModule.class})
@Singleton
public interface AppComponent {
    void runInject(StartViewModel startViewModel);
    void runInject(ListViewModel listViewModel);
    void runInject(NotifViewModel notifViewModel);
    void runInject(ListLeftFragmentModel listLeftFragmentModel);
    void runInject(ListRightFragmentModel listRightFragmentModel);

}