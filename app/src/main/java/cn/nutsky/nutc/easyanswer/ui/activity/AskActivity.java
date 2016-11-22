package cn.nutsky.nutc.easyanswer.ui.activity;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SaveCallback;

import cn.nutsky.nutc.easyanswer.R;
import cn.nutsky.nutc.easyanswer.config.Const;

public class AskActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private EditText edLabel;
    private EditText edContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask);
        setTitle("我要提问");

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white));

        edLabel = (EditText) findViewById(R.id.et_Label);
        edContent = (EditText) findViewById(R.id.et_question_content);
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
                if(!edLabel.getText().toString().isEmpty() && !edContent.getText().toString().isEmpty()) {
                    putQuestion();
                }
                else {
                    Toast.makeText(this, "标签或内容不能为空", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return true;
    }
    private void putQuestion(){
        AVObject question = new AVObject("Question");
        question.put("label", edLabel.getText().toString());
        question.put("content", edContent.getText().toString());
        question.put("name", AVUser.getCurrentUser().getUsername());
        question.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                if (e == null) {
                    Intent intent = new Intent();
                    intent.putExtra(Const.ASK_SUCCESS, true);
                    setResult(RESULT_OK, intent);
                    Toast.makeText(AskActivity.this, "提问成功", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(AskActivity.this, "您的网络太渣了", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
