package cn.nutsky.nutc.easyanswer.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cn.nutsky.nutc.easyanswer.R;
import cn.nutsky.nutc.easyanswer.app.activity.BaseActivity;
import cn.nutsky.nutc.easyanswer.ui.widget.BackToolbar;
import cn.nutsky.nutc.easyanswer.utils.ActivityCollector;

public class SignUpActivity extends BaseActivity {
    private Button button_sign_login;
    private BackToolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        button_sign_login = (Button) findViewById(R.id.bt_sign_login);
        button_sign_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this,MainActivity.class);
                startActivity(intent);
                ActivityCollector.finishAll();
            }
        });
        mToolbar = (BackToolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("注册");

    }
}
