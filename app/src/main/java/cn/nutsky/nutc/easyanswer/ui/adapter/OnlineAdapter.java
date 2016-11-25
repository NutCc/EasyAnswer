package cn.nutsky.nutc.easyanswer.ui.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.GetCallback;

import java.util.List;

import cn.nutsky.nutc.easyanswer.R;
import cn.nutsky.nutc.easyanswer.data.Classroom;
import cn.nutsky.nutc.easyanswer.data._Conversation;
import cn.nutsky.nutc.easyanswer.data._User;
import cn.nutsky.nutc.easyanswer.ui.activity.ClassroomActivity;

/**
 * Created by NutC on 2016/11/14.
 */

public class OnlineAdapter extends RecyclerView.Adapter<OnlineAdapter.ViewHolder> {
    private List<_Conversation> mConversations;

    public OnlineAdapter(List<_Conversation> conversations){
        mConversations = conversations;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_online,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.textViewQuestionBrief.setText(mConversations.get(position).getName());
        holder.textViewTime.setText(mConversations.get(position).getCreatedAt());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ClassroomActivity.class);
                intent.putExtra("classroomId",mConversations.get(position).getObjectId());
                intent.putExtra("classroomName",mConversations.get(position).getName());
                view.getContext().startActivity(intent);
            }
        });
        getUserName(holder,position);
    }

    public void getUserName(final ViewHolder holder,final int position){
        AVQuery<AVUser> userQuery = new AVQuery<>("_User");
        userQuery.whereEqualTo("objectId",mConversations.get(position).getClientId());
        Log.d("clientId",mConversations.get(position).getClientId());
        userQuery.findInBackground(new FindCallback<AVUser>() {
            @Override
            public void done(List<AVUser> list, AVException e) {
                if (e != null){
                    e.printStackTrace();
                    return;
                }
                holder.textViewName.setText(list.get(0).getUsername());
            }
        });

    }
    @Override
    public int getItemCount() {
        return mConversations.size();
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
