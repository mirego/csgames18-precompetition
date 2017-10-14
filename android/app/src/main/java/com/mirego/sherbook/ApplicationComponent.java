package com.mirego.sherbook;

import com.mirego.sherbook.views.HomeFragment;
import com.mirego.sherbook.views.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AndroidModule.class)
public interface ApplicationComponent {

    void inject(MainActivity mainActivity);

    void inject(HomeFragment homeFragment);

}
