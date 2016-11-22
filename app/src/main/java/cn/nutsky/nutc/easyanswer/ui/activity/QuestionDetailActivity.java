package cn.nutsky.nutc.easyanswer.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.GetCallback;
import com.avos.avoscloud.LogUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.nutsky.nutc.easyanswer.R;
import cn.nutsky.nutc.easyanswer.data.Answer;
import cn.nutsky.nutc.easyanswer.ui.adapter.QuestionDetailAdapter;

public class QuestionDetailActivity extends AppCompatActivity {
    private TextView tvName;
    private TextView tvQuestionTime;
    private TextView tvQuestionDetail;
    private TextView tvCollectionNum;
    private TextView tvReplyNum;
    private TextView tvSameAskNum;
    private ImageView ivCollection;
    private ImageView ivReply;
    private ImageView ivSameAsk;
    private RecyclerView mRecyclerView;
    private List<Answer> mAnswers = new ArrayList<>();
    private QuestionDetailAdapter mQuestionDetailAdapter;
    private Toolbar mToolbar;
    private static String questionId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_detail);
        setTitle("问题详情");

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white));

        tvName = (TextView) findViewById(R.id.tv_name);
        tvQuestionTime = (TextView) findViewById(R.id.tv_question_time);
        tvQuestionDetail = (TextView) findViewById(R.id.tv_question_detail);
        tvCollectionNum = (TextView) findViewById(R.id.tv_collection_num);
        tvReplyNum = (TextView) findViewById(R.id.tv_answer_num_detail);
        tvSameAskNum = (TextView) findViewById(R.id.tv_same_ask_num_detail);

        ivCollection = (ImageView) findViewById(R.id.iv_collection);
        ivReply = (ImageView) findViewById(R.id.iv_answer_detail);
        ivSameAsk = (ImageView) findViewById(R.id.iv_same_ask_detail);

        mQuestionDetailAdapter = new QuestionDetailAdapter(mAnswers);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_reply);
        mRecyclerView.setAdapter(mQuestionDetailAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        questionId = getIntent().getStringExtra("questionId");
        Log.d("d1",questionId);
        getQuestionDetail();
        setClick();
    }




    private void getQuestionDetail(){
        AVQuery<AVObject> avQuery = new AVQuery<>("Question");
        avQuery.getInBackground(questionId, new GetCallback<AVObject>() {
            @Override
            public void done(AVObject avObject, AVException e) {
                if(e == null) {
                    String name = avObject.getString("name");
                    String content = avObject.getString("content");
                    Date updatedAt = avObject.getUpdatedAt();
                    String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(updatedAt);
                    int replyNum = avObject.getInt("replyNum");
                    int commentAskNum = avObject.getInt("commentAskNum");
                    int collectionNum = avObject.getInt("collectionNum");

                    tvName.setText(name);
                    tvQuestionTime.setText(time);
                    tvQuestionDetail.setText(content);
                    tvReplyNum.setText(replyNum + "");
                    tvSameAskNum.setText(commentAskNum + "");
                    tvCollectionNum.setText(collectionNum + "");
                }
                else{

                    Toast.makeText(QuestionDetailActivity.this,"下载失败",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void setClick(){
        ivCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO 值加1
                Toast.makeText(view.getContext(),"收藏成功",Toast.LENGTH_SHORT).show();
            }
        });
        ivReply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO 值加1
                Intent intent = new Intent(view.getContext(),AnswerActivity.class);
                intent.putExtra("questionId",questionId);
                Log.d("d2",questionId);
                startActivity(intent);
            }
        });
        ivSameAsk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO 值加1
                Toast.makeText(view.getContext(),"同问",Toast.LENGTH_SHORT).show();
            }
        });
    }


}
