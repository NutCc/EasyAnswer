package cn.nutsky.nutc.easyanswer.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cn.nutsky.nutc.easyanswer.R;
import cn.nutsky.nutc.easyanswer.data.ClassChat;
import cn.nutsky.nutc.easyanswer.data.Classroom;
import cn.nutsky.nutc.easyanswer.data.Question;

/**
 * Created by NutC on 2016/11/17.
 */

public class ClassroomAdapter extends RecyclerView.Adapter<ClassroomAdapter.ViewHolder>{

    private List<ClassChat> mClassChat;

    public ClassroomAdapter(List<ClassChat> classChat){
        mClassChat = classChat;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_classroom,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv.setText(mClassChat.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return mClassChat.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv ;

        public ViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv_classroom_chat_content);
        }
    }
}

