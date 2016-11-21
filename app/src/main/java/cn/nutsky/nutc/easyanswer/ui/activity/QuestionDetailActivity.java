package cn.nutsky.nutc.easyanswer.ui.activity;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import cn.nutsky.nutc.easyanswer.R;

public class QuestionDetailActivity extends AppCompatActivity {
    private TextView tvName;
    private TextView tvQuestionTime;
    private TextView tvQuestionDetail;
    private TextView tvCollectionNum;
    private TextView tvAwserNum;
    private TextView tvSameAskNum;

    private Toolbar mToolbar;

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
        tvAwserNum = (TextView) findViewById(R.id.tv_answer_num_detail);
        tvSameAskNum = (TextView) findViewById(R.id.tv_same_ask_num_detail);
    }

    private void getQuestionDetail(){

    }
}
