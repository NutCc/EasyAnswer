package cn.nutsky.nutc.easyanswer.ui.adapter;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cn.nutsky.nutc.easyanswer.R;
import cn.nutsky.nutc.easyanswer.data.Question;
import cn.nutsky.nutc.easyanswer.ui.activity.MainActivity;
import cn.nutsky.nutc.easyanswer.ui.activity.QuestionDetailActivity;

/**
 * Created by NutC on 2016/11/2.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private List<Question> mQuestions;

    public HomeAdapter(List<Question> questions){
        mQuestions = questions;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home,parent,false));
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tvLabel.setText(mQuestions.get(position).getLabel());
        holder.tvContent.setText(mQuestions.get(position).getContent());
        holder.tvReplyNum.setText(mQuestions.get(position).getReplyNum() + "");
        holder.tvCommentAskNum.setText(mQuestions.get(position).getCommentAskNum() + "");
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),QuestionDetailActivity.class);
                intent.putExtra("questionId",mQuestions.get(position).getObjectId());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mQuestions.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvLabel;
        TextView tvContent;
        TextView tvReplyNum;
        TextView tvCommentAskNum;
        private CardView mCardView;
        public ViewHolder(View itemView) {
            super(itemView);
            tvLabel = (TextView) itemView.findViewById(R.id.tv_label);
            mCardView = (CardView) itemView.findViewById(R.id.cv_home);
            tvContent = (TextView) itemView.findViewById(R.id.tv_content);
            tvReplyNum = (TextView) itemView.findViewById(R.id.tv_answer_num);
            tvCommentAskNum = (TextView) itemView.findViewById(R.id.tv_same_ask_num);
        }
    }
}
