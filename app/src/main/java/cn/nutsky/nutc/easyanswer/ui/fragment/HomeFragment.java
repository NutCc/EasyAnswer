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
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.GetCallback;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.nutsky.nutc.easyanswer.R;
import cn.nutsky.nutc.easyanswer.data.Question;
import cn.nutsky.nutc.easyanswer.ui.activity.MainActivity;
import cn.nutsky.nutc.easyanswer.ui.adapter.HomeAdapter;
import cn.nutsky.nutc.easyanswer.ui.callback.RefreshListener;

/**
 * Created by NutC on 2016/11/2.
 */

public class HomeFragment extends Fragment implements RefreshListener{
    private RecyclerView mRecyclerView;
    private List<Question> mQuestions = new ArrayList<>();
    private HomeAdapter mHomeAdapter;
    private TextView tvReplyNum;
    private TextView tvCommentAskNum;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mHomeAdapter = new HomeAdapter(mQuestions);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_home);
        mRecyclerView.setAdapter(mHomeAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        getQuestions();
    }

    private void getQuestions(){
        final AVQuery<AVObject> avQuery = new AVQuery<>("Question");
        avQuery.orderByDescending("createdAt");
        avQuery.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if (e == null) {
                    mQuestions.clear();
                    for(AVObject object:list) {
                        mQuestions.add(new Question(object));
                    }
                    mHomeAdapter.notifyDataSetChanged();
                } else {
                    e.printStackTrace();
                }
            }
        });
    }


    @Override
    public void refresh() {
        if (mRecyclerView != null && mHomeAdapter != null)
            getQuestions();
    }
}
