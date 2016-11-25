package cn.nutsky.nutc.easyanswer.ui.activity;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SaveCallback;
import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMConversation;
import com.avos.avoscloud.im.v2.AVIMConversationEventHandler;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.AVIMMessageManager;
import com.avos.avoscloud.im.v2.AVIMTypedMessageHandler;
import com.avos.avoscloud.im.v2.callback.AVIMClientCallback;
import com.avos.avoscloud.im.v2.callback.AVIMConversationCallback;
import com.avos.avoscloud.im.v2.messages.AVIMTextMessage;

import java.util.ArrayList;
import java.util.List;

import cn.nutsky.nutc.easyanswer.R;
import cn.nutsky.nutc.easyanswer.app.activity.BaseActivity;
import cn.nutsky.nutc.easyanswer.config.Const;
import cn.nutsky.nutc.easyanswer.data.ClassChat;
import cn.nutsky.nutc.easyanswer.data.Classroom;
import cn.nutsky.nutc.easyanswer.ui.adapter.ClassroomAdapter;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class ClassroomActivity extends BaseActivity {
    private List<ClassChat> mClassChats = new ArrayList<>();
    private ClassroomAdapter mClassroomAdapter;
    private RecyclerView mRecyclerView;
    private Toolbar mToolbar;
    private EditText etEdit;
    private TextView tvSend;
    private AVIMClient user;
    private AVIMConversation conversation;
    private CustomMessageHandler messageHandler;
    private LinearLayoutManager linearLayoutManager;

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
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        mClassroomAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                mRecyclerView.smoothScrollToPosition(mClassroomAdapter.getItemCount() - 1);
            }
        });


        etEdit = (EditText) findViewById(R.id.et_edit);
        tvSend = (TextView) findViewById(R.id.tv_send);
        tvSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!etEdit.getText().toString().isEmpty()){
                    putClassChat();
                    mClassChats.add(new ClassChat(etEdit.getText().toString()));
                    mClassroomAdapter.notifyDataSetChanged();
                    etEdit.setText("");
                }
                else{
                    Toast.makeText(view.getContext(),"内容不能为空",Toast.LENGTH_SHORT).show();
                }
            }
        });
        AVIMMessageManager.setConversationEventHandler(new CustomConversationEventHandler());
        joinClassroom();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (messageHandler != null)
            AVIMMessageManager.unregisterMessageHandler(AVIMTextMessage.class,messageHandler);
    }

    private void joinClassroom(){
        user = AVIMClient.getInstance(AVUser.getCurrentUser().getObjectId());
        user.open(new AVIMClientCallback(){

            @Override
            public void done(AVIMClient client,AVIMException e){
                if(e==null){
                    conversation = client.getConversation(getIntent().getStringExtra("classroomId"));
                    conversation.join(new AVIMConversationCallback(){
                        @Override
                        public void done(AVIMException e){
                            if(e==null){
                                Log.d("join","success");
                                AVIMMessageManager.registerMessageHandler(AVIMTextMessage.class,messageHandler = new CustomMessageHandler());
                            }
                        }
                    });
                }
            }
        });
    }
    private void putClassChat(){
        if (conversation != null) {
            AVIMTextMessage msg = new AVIMTextMessage();
            msg.setText(etEdit.getText().toString());  //发送消息
            conversation.sendMessage(msg, new AVIMConversationCallback() {

                @Override
                public void done(AVIMException e) {
                    Log.d("send","success");
                }
            });
        }
    }

    public class CustomMessageHandler extends AVIMTypedMessageHandler<AVIMTextMessage>  {


        @Override
        public void onMessage(AVIMTextMessage msg,AVIMConversation conv, AVIMClient client){
            Log.d("Tom & Jerry",msg.getText());
            mClassChats.add(new ClassChat(msg.getText()));
            mClassroomAdapter.notifyDataSetChanged();
        }

        public void onMessageReceipt(AVIMTextMessage msg,AVIMConversation conv,AVIMClient client){

        }
    }

    public class CustomConversationEventHandler extends AVIMConversationEventHandler {

        @Override
        public void onMemberLeft(AVIMClient client, AVIMConversation conversation, List<String> members,
                                 String kickedBy) {

        }

        @Override
        public void onMemberJoined(AVIMClient client, AVIMConversation conversation,
                                   List<String> members, String invitedBy) {
            // 手机屏幕上会显示一小段文字：Tom 加入到 551260efe4b01608686c3e0f ；操作者为：Tom
            Toast.makeText(AVOSCloud.applicationContext,
                    members + "加入到" + conversation.getConversationId() + "；操作者为： "
                            + invitedBy, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onKicked(AVIMClient client, AVIMConversation conversation, String kickedBy) {
            // 当前 ClientId(Bob) 被踢出对话，执行此处逻辑
        }

        @Override
        public void onInvited(AVIMClient client, AVIMConversation conversation, String invitedBy) {
            // 当前 ClientId(Bob) 被邀请到对话，执行此处逻辑
        }
    }

}
