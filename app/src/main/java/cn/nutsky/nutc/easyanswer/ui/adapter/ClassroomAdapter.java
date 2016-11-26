package cn.nutsky.nutc.easyanswer.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;

import java.util.List;

import cn.nutsky.nutc.easyanswer.R;
import cn.nutsky.nutc.easyanswer.data.ClassChat;
import cn.nutsky.nutc.easyanswer.data.Classroom;
import cn.nutsky.nutc.easyanswer.data.Question;
import cn.nutsky.nutc.easyanswer.data._User;

/**
 * Created by NutC on 2016/11/17.
 */

public class ClassroomAdapter extends RecyclerView.Adapter<ClassroomAdapter.ViewHolder>{

    private List<ClassChat> mClassChat;
    private List<_User> currentUser;

    public ClassroomAdapter(List<ClassChat> classChat){
        mClassChat = classChat;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_classroom,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(!mClassChat.get(position).isMe){
            holder.tvChatLeft.setText(mClassChat.get(position).getContent());
            holder.tvChatRight.setVisibility(View.GONE);
            holder.tvChatLeft.setVisibility(View.VISIBLE);
            holder.ivIconRight.setVisibility(View.GONE);
            holder.ivIconLeft.setVisibility(View.VISIBLE);

        }
        else {
            holder.tvChatRight.setText(mClassChat.get(position).getContent());
            holder.tvChatRight.setVisibility(View.VISIBLE);
            holder.tvChatLeft.setVisibility(View.GONE);
            holder.ivIconRight.setVisibility(View.VISIBLE);
            holder.ivIconLeft.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mClassChat.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvChatLeft ;
        TextView tvChatRight;
        ImageView ivIconLeft;
        ImageView ivIconRight;

        public ViewHolder(View itemView) {
            super(itemView);
            tvChatLeft = (TextView) itemView.findViewById(R.id.tv_classroom_chat_left);
            tvChatRight = (TextView) itemView.findViewById(R.id.tv_classroom_chat_right);
            ivIconLeft = (ImageView) itemView.findViewById(R.id.iv_icon_left);
            ivIconRight = (ImageView) itemView.findViewById(R.id.iv_icon_right);
        }
    }

    private String getUserTeachear(){
        String s = AVUser.getCurrentUser().getUsername();
        return s;
    }
}

