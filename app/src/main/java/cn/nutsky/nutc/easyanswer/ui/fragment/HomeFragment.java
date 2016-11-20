package cn.nutsky.nutc.easyanswer.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.nutsky.nutc.easyanswer.R;
import cn.nutsky.nutc.easyanswer.data.Question;
import cn.nutsky.nutc.easyanswer.ui.activity.MainActivity;
import cn.nutsky.nutc.easyanswer.ui.adapter.HomeAdapter;

/**
 * Created by NutC on 2016/11/2.
 */

public class HomeFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private List<Question> mQuestion = new ArrayList<>();
    private HomeAdapter mHomeAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mHomeAdapter = new HomeAdapter(mQuestion);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_home);
        mRecyclerView.setAdapter(mHomeAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        this.getQuestion();
    }

    private void getQuestion(){
        for (int i = 0; i < 10; i++) {
            mQuestion.add(new Question("物理","2015年10月29日 - Android 5.x新特性之利用CardView制造温和的阴影效果_利用gradle引进支持包: compile 2015年1月16日 - CardView继承至FrameLayout类,可以在一个卡片布"));
        }
        mHomeAdapter.notifyDataSetChanged();
    }
}
