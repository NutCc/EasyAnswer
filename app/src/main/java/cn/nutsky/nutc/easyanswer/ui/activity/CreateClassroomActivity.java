package cn.nutsky.nutc.easyanswer.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SaveCallback;
import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMConversation;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMClientCallback;
import com.avos.avoscloud.im.v2.callback.AVIMConversationCreatedCallback;

import java.text.SimpleDateFormat;
import java.util.Collections;

import cn.nutsky.nutc.easyanswer.R;
import cn.nutsky.nutc.easyanswer.app.activity.BaseActivity;
import cn.nutsky.nutc.easyanswer.config.Const;
import cn.nutsky.nutc.easyanswer.ui.fragment.OnlineFragment;
import cn.nutsky.nutc.easyanswer.ui.widget.BackToolbar;

public class CreateClassroomActivity extends BaseActivity {
    private Toolbar mToolbar;
    private EditText etContent;
    private EditText etBeginDate;
    private EditText etBeginTime;
    private EditText etEndTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_classroom);
        setTitle("创建教室");
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white));

        etContent = (EditText) findViewById(R.id.et_Content);
        etBeginDate = (EditText) findViewById(R.id.et_begin_date);
        etBeginTime = (EditText) findViewById(R.id.et_begin_time);
        etEndTime = (EditText) findViewById(R.id.et_end_time);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_toolbar_creat_classroom,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.toolbar_ok:
                if(!etContent.getText().toString().isEmpty()) {
                    putClassroom();
                }
                else{
                    Toast.makeText(this, "内容不能为空", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return true;
    }

    private void putClassroom(){
        AVObject classroom = new AVObject("Classroom");
        classroom.put("content", etContent.getText().toString());
        classroom.put("beginDate", etBeginDate.getText().toString());
        classroom.put("beginTime", etBeginTime.getText().toString());
        classroom.put("endTime",etEndTime.getText().toString());
        classroom.put("name", AVUser.getCurrentUser().getUsername());
        classroom.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                if (e == null) {
                    Intent intent = new Intent();
                    intent.putExtra(Const.CREATE_CLASS_SUCCESS, true);
                    setResult(RESULT_OK, intent);
                    Toast.makeText(CreateClassroomActivity.this, "创建成功", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(CreateClassroomActivity.this, "您的网络太渣了", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void creatClassroom(){
        /*AVIMClient tom = AVIMClient.getInstance("Tom");
        tom.open(new AVIMClientCallback(){

            @Override
            public void done(AVIMClient client,AVIMException e){
                if(e==null){
                    //登录成功
                    //创建一个 名为 "HelloKitty PK 加菲猫" 的暂态对话
                    client.createConversation(Collections.emptyList(),"HelloKitty PK 加菲猫",null,true,
                            new AVIMConversationCreatedCallback(){
                                @Override
                                public void done(AVIMConversation conv, AVIMException e){

                                }
                            });
                }
            }
        });*/

    }


}
