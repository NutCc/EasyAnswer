package cn.nutsky.nutc.easyanswer.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.LogInCallback;
import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMConversation;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMClientCallback;
import com.avos.avoscloud.im.v2.messages.AVIMTextMessage;

import java.lang.reflect.Array;
import java.util.List;

import javax.security.auth.SubjectDomainCombiner;

import cn.nutsky.nutc.easyanswer.R;
import cn.nutsky.nutc.easyanswer.app.App;
import cn.nutsky.nutc.easyanswer.app.activity.BaseDoubleClickActivity;
import cn.nutsky.nutc.easyanswer.data.Answer;

public class LoginActivity extends BaseDoubleClickActivity {
    private Button button_login,button_sign_up;
    private EditText etName;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        button_login = (Button) findViewById(R.id.bt_login);
        etName = (EditText) findViewById(R.id.et_name);
        etPassword = (EditText) findViewById(R.id.et_password);

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!etName.getText().toString().isEmpty() && !etPassword.getText().toString().isEmpty()){
                    login();
                }
                else {
                    Toast.makeText(v.getContext(),"用户名或密码不能为空",Toast.LENGTH_SHORT).show();
                }
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


    private void login(){
        AVUser.logInInBackground(etName.getText().toString(), etPassword.getText().toString(), new LogInCallback<AVUser>() {
            @Override
            public void done(AVUser avUser, AVException e) {
                if (e == null) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);

                    final AVQuery<AVObject> avQuery = new AVQuery<>("_User");
                    avQuery.whereEqualTo("objectId", avUser.getObjectId());

                    avQuery.findInBackground(new FindCallback<AVObject>() {
                        @Override
                        public void done(List<AVObject> list, AVException e) {
                            if (e == null) {
                                App.isTeacher = list.get(0).getBoolean("teacher");
                                Log.d("TAG", App.isTeacher + "");
                            } else {
                                e.printStackTrace();
                            }
                        }
                    });

                    finish();
                }
                else {
                    Toast.makeText(LoginActivity.this,e.toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
