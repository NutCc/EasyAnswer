package cn.nutsky.nutc.easyanswer;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;

import cn.nutsky.nutc.easyanswer.appconst.Api;

/**
 * Created by NutC on 2016/10/30.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AVOSCloud.initialize(this, Api.APP_ID,Api.APP_KEY);
    }
}
