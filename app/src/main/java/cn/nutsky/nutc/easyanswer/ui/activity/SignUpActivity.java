package cn.nutsky.nutc.easyanswer.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogUtil;
import com.avos.avoscloud.LogUtil.log;
import com.avos.avoscloud.SaveCallback;
import com.avos.avoscloud.SignUpCallback;

import java.util.ArrayList;
import java.util.List;

import cn.nutsky.nutc.easyanswer.R;
import cn.nutsky.nutc.easyanswer.app.activity.BaseActivity;
import cn.nutsky.nutc.easyanswer.data._User;
import cn.nutsky.nutc.easyanswer.ui.widget.BackToolbar;
import cn.nutsky.nutc.easyanswer.utils.ActivityCollector;

import static java.security.AccessController.getContext;

public class SignUpActivity extends BaseActivity {
    private Button button_sign_login;
    private Toolbar mToolbar;
    private EditText etName;
    private EditText etEmail;
    private EditText etPassword;
    private EditText etConfirmPassword;
    private List<_User> users = new ArrayList<>();

    private String name;
    private String mail;
    private String password;
    private String passwordConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setTitle("注册");
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white));

        etName = (EditText) findViewById(R.id.et_name);
        etEmail = (EditText) findViewById(R.id.et_mail);
        etPassword = (EditText) findViewById(R.id.et_password);
        etConfirmPassword = (EditText) findViewById(R.id.et_password_confirm);

        button_sign_login = (Button) findViewById(R.id.bt_sign_login);
        button_sign_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init();
                if(name.length() == 0 || name.length() > 12){
                    Toast.makeText(v.getContext(),"昵称不符合规格",Toast.LENGTH_SHORT).show();
                }
                else if(mail.isEmpty() || !mail.contains("@")){
                    Toast.makeText(v.getContext(),"邮箱格式不正确",Toast.LENGTH_SHORT).show();
                }
                else if(!password.equals(passwordConfirm) || password.isEmpty()){
                    Toast.makeText(v.getContext(),"请重新确认密码",Toast.LENGTH_SHORT).show();
                }
                else{
                    putUser();
                    Intent intent = new Intent(SignUpActivity.this,MainActivity.class);
                    startActivity(intent);
                    ActivityCollector.finishAll();
                }
            }
        });

    }


    private void putUser(){
        AVUser user = new AVUser();// 新建 AVUser 对象实例
        user.setUsername(name);// 设置用户名
        user.setPassword(password);// 设置密码
        user.setEmail(mail);// 设置邮箱
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(AVException e) {
                if (e == null) {
                    // 注册成功
                    Toast.makeText(SignUpActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                } else {
                    // 失败的原因可能有多种，常见的是用户名已经存在。
                    Toast.makeText(SignUpActivity.this,e.toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void init(){
        name = etName.getText().toString();
        mail = etEmail.getText().toString();
        password = etPassword.getText().toString();
        passwordConfirm = etConfirmPassword.getText().toString();
    }
}
