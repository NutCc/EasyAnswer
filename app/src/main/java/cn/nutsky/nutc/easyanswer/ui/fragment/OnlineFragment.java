package cn.nutsky.nutc.easyanswer.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.text.style.TtsSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMConversation;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMClientCallback;
import com.avos.avoscloud.im.v2.callback.AVIMConversationQueryCallback;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.nutsky.nutc.easyanswer.R;
import cn.nutsky.nutc.easyanswer.data.Classroom;
import cn.nutsky.nutc.easyanswer.data.Question;
import cn.nutsky.nutc.easyanswer.ui.adapter.HomeAdapter;
import cn.nutsky.nutc.easyanswer.ui.adapter.OnlineAdapter;

/**
 * Created by NutC on 2016/11/2.
 */

public class OnlineFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private List<Classroom> mClassrooms = new ArrayList<>();
    private OnlineAdapter mOnlineAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_online,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mOnlineAdapter = new OnlineAdapter(mClassrooms);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_online);
        mRecyclerView.setAdapter(mOnlineAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        getClassroom();
    }

    private void getClassroom(){

        AVIMClient avimClient = AVIMClient.getInstance(AVUser.getCurrentUser().getObjectId());
        avimClient.open(new AVIMClientCallback() {
            @Override
            public void done(AVIMClient avimClient, AVIMException e) {
                if (e != null) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), "我去，又出错了", Toast.LENGTH_SHORT).show();
                    return;
                }

                avimClient.getQuery().findInBackground(new AVIMConversationQueryCallback() {
                    @Override
                    public void done(List<AVIMConversation> list, AVIMException e) {
                        if (e != null) {
                            e.printStackTrace();
                            Toast.makeText(getContext(), "我去，又出错了", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (list == null || list.isEmpty()) {
                            Toast.makeText(getContext(), "我去，没有？！", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        for (AVIMConversation conversation : list) {
                            Log.d("Conversation name -> ", conversation.getName());
                        }
                    }
                });

            }
        });


        final AVQuery<AVObject> avQuery = new AVQuery<>("Classroom");
        avQuery.orderByDescending("createdAt");
        avQuery.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if (e == null) {
                    mClassrooms.clear();
                    for(AVObject object:list)
                        mClassrooms.add(new Classroom(object));
                    mOnlineAdapter.notifyDataSetChanged();
                } else {
                    e.printStackTrace();
                }
            }
        });
    }

    public void refresh() {
        if (mRecyclerView != null && mOnlineAdapter != null)
            getClassroom();
    }
}
