package cn.nutsky.nutc.easyanswer.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.nutsky.nutc.easyanswer.R;
import cn.nutsky.nutc.easyanswer.data.ClassChat;
import cn.nutsky.nutc.easyanswer.data.Classroom;
import cn.nutsky.nutc.easyanswer.ui.adapter.ClassroomAdapter;

public class ClassroomActivity extends AppCompatActivity {
    private List<ClassChat> mClassChats = new ArrayList<>();
    private ClassroomAdapter mClassroomAdapter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classroom);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_classroom);
        mRecyclerView.setAdapter(mClassroomAdapter = new ClassroomAdapter(mClassChats));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        getData();
    }

    public void getData() {
        for (int i = 0; i < 100; i++) {
            mClassChats.add(new ClassChat("aaa"));
        }
        mClassroomAdapter.notifyDataSetChanged();
    }
}
