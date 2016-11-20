package cn.nutsky.nutc.easyanswer.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.text.style.TtsSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.nutsky.nutc.easyanswer.R;
import cn.nutsky.nutc.easyanswer.data.Classroom;
import cn.nutsky.nutc.easyanswer.ui.adapter.HomeAdapter;
import cn.nutsky.nutc.easyanswer.ui.adapter.OnlineAdapter;

/**
 * Created by NutC on 2016/11/2.
 */

public class OnlineFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private List<Classroom> mClassroom = new ArrayList<>();
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
        mOnlineAdapter = new OnlineAdapter(mClassroom);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_online);
        mRecyclerView.setAdapter(mOnlineAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        getClassroom();

    }

    public void getClassroom(){
        for (int i = 0; i < 10; i++) {
            mClassroom.add(new Classroom("content","name","2016-01-05"," 14:00","16:00"));
        }

        mOnlineAdapter.notifyDataSetChanged();
    }
}
