package cn.nutsky.nutc.easyanswer.ui.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cn.nutsky.nutc.easyanswer.R;
import cn.nutsky.nutc.easyanswer.data.Classroom;
import cn.nutsky.nutc.easyanswer.ui.activity.ClassroomActivity;

/**
 * Created by NutC on 2016/11/14.
 */

public class OnlineAdapter extends RecyclerView.Adapter<OnlineAdapter.ViewHolder> {
    private List<Classroom> mClassrooms;

    public OnlineAdapter(List<Classroom> classrooms){
        mClassrooms = classrooms;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_online,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textViewName.setText(mClassrooms.get(position).getName());
        holder.textViewQuestionBrief.setText(mClassrooms.get(position).getContent());
        holder.textViewTime.setText(mClassrooms.get(position).getTime());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ClassroomActivity.class);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mClassrooms.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewName;
        TextView textViewQuestionBrief;
        TextView textViewTime;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewName = $(R.id.tv_online_name);
            textViewQuestionBrief  = $(R.id.tv_online_question_brief);
            textViewTime = $(R.id.tv_online_time);

        }

        <T extends View> T $(int id) {
            return (T) itemView.findViewById(id);
        }
    }
}
