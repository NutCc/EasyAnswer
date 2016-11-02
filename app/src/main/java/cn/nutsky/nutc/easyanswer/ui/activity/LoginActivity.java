package cn.nutsky.nutc.easyanswer.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cn.nutsky.nutc.easyanswer.R;
import cn.nutsky.nutc.easyanswer.app.activity.BaseDoubleClickActivity;

public class LoginActivity extends BaseDoubleClickActivity {
    private Button button_login,button_sign_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        button_login = (Button) findViewById(R.id.bt_login);
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        button_sign_up = (Button) findViewById(R.id.bt_sign_up);
        button_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
}
