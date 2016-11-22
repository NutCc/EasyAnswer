package cn.nutsky.nutc.easyanswer.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cn.nutsky.nutc.easyanswer.R;
import cn.nutsky.nutc.easyanswer.data.Answer;
import cn.nutsky.nutc.easyanswer.data.Question;

/**
 * Created by NutC on 2016/11/22.
 */

public class QuestionDetailAdapter extends RecyclerView.Adapter<QuestionDetailAdapter.ViewHolder>{
    private List<Answer> mAnswers;

    public QuestionDetailAdapter(List<Answer> answers){mAnswers = answers;}

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_replay,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvName.setText(mAnswers.get(position).getName());
        holder.tvReplyTime.setText(mAnswers.get(position).getCreatedAt());
        holder.tvReplyContent.setText(mAnswers.get(position).getAnswerContent());
    }

    @Override
    public int getItemCount() {
        return mAnswers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvReplyTime;
        TextView tvReplyContent;
        public ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvReplyTime = (TextView) itemView.findViewById(R.id.tv_reply_time);
            tvReplyContent = (TextView) itemView.findViewById(R.id.tv_reply_content);
        }
    }
}
