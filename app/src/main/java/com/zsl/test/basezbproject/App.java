package com.zsl.test.basezbproject;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.os.StrictMode;
import android.support.multidex.MultiDex;

import com.zsl.test.basezbproject.di.componet.AppComponent;
import com.zsl.test.basezbproject.di.componet.DaggerAppComponent;
import com.zsl.test.basezbproject.di.module.AppModule;

/**
 * @author Created by stone
 * @since 2018/7/10
 */

public class App extends Application {


    public AppComponent getmAppComponent() {
        return mAppComponent;
    }

    private AppComponent mAppComponent;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);

    }

    @Override
    public void onCreate() {
        super.onCreate();
        setStrictMode();//严苛模式
       mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this, Constants.BASE_URL)).build();
    }


    @TargetApi(9)
    protected void setStrictMode() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build());
    }
}
