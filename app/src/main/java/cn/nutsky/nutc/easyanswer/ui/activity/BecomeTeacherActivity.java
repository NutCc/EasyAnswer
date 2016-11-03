package cn.nutsky.nutc.easyanswer.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import cn.nutsky.nutc.easyanswer.R;
import cn.nutsky.nutc.easyanswer.app.activity.BaseDoubleClickActivity;

/**
 * Created by NutC on 2016/11/3.
 */

public class BecomeTeacherActivity extends BaseDoubleClickActivity{
    private Toolbar mToolbar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_become_teacher);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("教师认证");
    }
}
