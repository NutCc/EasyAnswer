package cn.nutsky.nutc.easyanswer.ui.activity;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SaveCallback;

import java.util.ArrayList;
import java.util.List;

import cn.nutsky.nutc.easyanswer.R;
import cn.nutsky.nutc.easyanswer.app.activity.BaseActivity;
import cn.nutsky.nutc.easyanswer.config.Const;
import cn.nutsky.nutc.easyanswer.data.ClassChat;
import cn.nutsky.nutc.easyanswer.data.Classroom;
import cn.nutsky.nutc.easyanswer.ui.adapter.ClassroomAdapter;

public class ClassroomActivity extends BaseActivity {
    private List<ClassChat> mClassChats = new ArrayList<>();
    private ClassroomAdapter mClassroomAdapter;
    private RecyclerView mRecyclerView;
    private Toolbar mToolbar;
    private EditText etEdit;
    private TextView tvSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classroom);
        setTitle("答疑教室");
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitleTextColor(ContextCompat.getColor(this,R.color.white));

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_classroom);
        mRecyclerView.setAdapter(mClassroomAdapter = new ClassroomAdapter(mClassChats));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        etEdit = (EditText) findViewById(R.id.et_edit);
        tvSend = (TextView) findViewById(R.id.tv_send);
        tvSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!etEdit.getText().toString().isEmpty()){
                    putClassChat();
                    etEdit.setText("");
                }
                else{
                    Toast.makeText(view.getContext(),"内容不能为空",Toast.LENGTH_SHORT).show();
                }
            }
        });

        getClassChat();
    }

    private void getClassChat() {

        for (int i = 0; i < 10; i++) {
            mClassChats.add(new ClassChat("aaa"));
        }
        mClassroomAdapter.notifyDataSetChanged();
    }

    private void putClassChat(){
        AVObject classChat = new AVObject("ClassChat");
        classChat.put("classroomId",getIntent().getStringExtra("classroomId"));
        classChat.put("content", etEdit.getText().toString());
        classChat.put("userId", AVUser.getCurrentUser().getObjectId());
        classChat.saveInBackground(new SaveCallback() {
                @Override
                public void done(AVException e) {
                    if (e == null) {

                    } else {
                        Toast.makeText(ClassroomActivity.this, "您的网络太渣了", Toast.LENGTH_SHORT).show();
                    }
                }
        });
    }

}
