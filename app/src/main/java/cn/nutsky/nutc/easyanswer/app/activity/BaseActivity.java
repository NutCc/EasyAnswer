package cn.nutsky.nutc.easyanswer.app.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import cn.nutsky.nutc.easyanswer.utils.ActivityCollector;
import cn.nutsky.nutc.easyanswer.utils.KeyboardUtil;

/**
 * Created by NutC on 2016/11/2.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            // 触摸EditText以外区域自动让键盘消失
            KeyboardUtil.autoHideInput(v, ev);
            return super.dispatchTouchEvent(ev);
        }
        return getWindow().superDispatchTouchEvent(ev) || onTouchEvent(ev);
    }
}
