package cn.nutsky.nutc.easyanswer.app;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;

import cn.nutsky.nutc.easyanswer.config.Api;

/**
 * Created by NutC on 2016/10/30.
 */

public class App extends Application {

    public static boolean isTeacher = false;

    @Override
    public void onCreate() {
        super.onCreate();
        AVOSCloud.initialize(this, Api.APP_ID,Api.APP_KEY);
    }
}
