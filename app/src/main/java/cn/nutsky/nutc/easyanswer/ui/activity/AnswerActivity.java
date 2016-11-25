package cn.nutsky.nutc.easyanswer.ui.activity;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SaveCallback;

import cn.nutsky.nutc.easyanswer.R;
import cn.nutsky.nutc.easyanswer.app.activity.BaseActivity;
import cn.nutsky.nutc.easyanswer.config.Const;
import cn.nutsky.nutc.easyanswer.data.Answer;

public class AnswerActivity extends BaseActivity {
    Toolbar mToolbar;
    EditText etReplyContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        setTitle("回答");
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitleTextColor(ContextCompat.getColor(this,R.color.white));
        etReplyContent = (EditText) findViewById(R.id.et_reply_content);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_toolbar_ask,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.toolbar_ok:
                if (!etReplyContent.getText().toString().isEmpty()){
                   putAnswer();
                }
                else{
                    Toast.makeText(AnswerActivity.this,"内容不能为空",Toast.LENGTH_SHORT).show();
                }
        }
        return true;
    }

    private void putAnswer(){
        AVObject answer = new AVObject("Answer");
        answer.put("answerContent", etReplyContent.getText().toString());
        answer.put("name", AVUser.getCurrentUser().getUsername());
        answer.put("questionId",getIntent().getStringExtra("questionId"));
        answer.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                if (e == null) {
                    Toast.makeText(AnswerActivity.this, "回答成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.putExtra("ANSWER_SUCCESS",true);
                    setResult(RESULT_OK,intent);
                    finish();
                } else {
                    Toast.makeText(AnswerActivity.this, "您的网络太渣了", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
