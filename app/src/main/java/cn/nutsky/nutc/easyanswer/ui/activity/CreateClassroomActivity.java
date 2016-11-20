package cn.nutsky.nutc.easyanswer.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import cn.nutsky.nutc.easyanswer.R;
import cn.nutsky.nutc.easyanswer.ui.fragment.OnlineFragment;
import cn.nutsky.nutc.easyanswer.ui.widget.BackToolbar;

public class CreateClassroomActivity extends AppCompatActivity {
    Toolbar mToolbar;
    public static final String TAG = CreateClassroomActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_classroom);
        setTitle("创建教室");
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_toolbar_creat_classroom,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        Log.d(TAG, "onOptionsItemSelected: item -> " + item.getItemId());
        switch (item.getItemId()) {
            case R.id.toolbar_ok:
                Toast.makeText(this,"提问成功",Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
        return true;
    }

}
