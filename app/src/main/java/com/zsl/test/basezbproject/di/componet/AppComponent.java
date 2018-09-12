package com.zsl.test.basezbproject.di.componet;

import android.content.Context;

import com.zsl.test.basezbproject.App;
import com.zsl.test.basezbproject.di.module.AppModule;
import com.zsl.test.basezbproject.http.APIService;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Created by stone
 * @since 2018/7/10
 */
@Singleton
@Component(modules= AppModule.class)
public interface AppComponent {

    void inject(App app);

    Context getContext();
    APIService getAPIService();

}
