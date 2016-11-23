package cn.nutsky.nutc.easyanswer.ui.activity;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import cn.nutsky.nutc.easyanswer.R;
import cn.nutsky.nutc.easyanswer.app.activity.BaseActivity;

public class MyQuestionActivity extends BaseActivity {
    Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_question);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setTitle("我的问题");
        mToolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white));
    }
}
