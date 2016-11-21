package cn.nutsky.nutc.easyanswer.ui.activity;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import cn.nutsky.nutc.easyanswer.R;
import cn.nutsky.nutc.easyanswer.app.activity.BaseDoubleClickActivity;
import cn.nutsky.nutc.easyanswer.config.Const;
import cn.nutsky.nutc.easyanswer.ui.callback.RefreshListener;
import cn.nutsky.nutc.easyanswer.ui.fragment.HomeFragment;
import cn.nutsky.nutc.easyanswer.ui.fragment.MeFragment;
import cn.nutsky.nutc.easyanswer.ui.fragment.OnlineFragment;

import static java.security.AccessController.getContext;


public class MainActivity extends BaseDoubleClickActivity {

    private BottomNavigationView bottomNavigationView;
    private Toolbar mToolbar;
    private HomeFragment homeFragment;
    private MeFragment meFragment;
    private OnlineFragment onlineFragment;
    private ImageView mAskButton;

    public static final int REQUEST_ASK = 1;
    public static final int REQUEST_CREATCLASS = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeFragment = new HomeFragment();
        onlineFragment = new OnlineFragment();
        meFragment = new MeFragment();

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView
                .OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                onNavigationItemSelect(item.getItemId());
                return false;
            }
        });

        mAskButton = (ImageView) findViewById(R.id.iv_ask);
        mAskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AskActivity.class);
                startActivityForResult(intent, REQUEST_ASK);
            }
        });

        selectNavigationItem(1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK)
            return;

        switch (requestCode) {
            case REQUEST_ASK:
                if (data.getBooleanExtra(Const.ASK_SUCCESS, false))
                    homeFragment.refresh();
                break;
            case REQUEST_CREATCLASS:
                if(data.getBooleanExtra(Const.CREATE_CLASS_SUCCESS,false))
                    onlineFragment.refresh();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.toolbar_search, menu);
        return true;
    }
    //Todo 搜索框
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.home_bar_search:
                Toast.makeText(this, "搜索", Toast.LENGTH_SHORT).show();
                break;
            case R.id.toolbar_history:
                Toast.makeText(this, "历史记录", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    private void onNavigationItemSelect(int itemId) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        switch(itemId){
            case R.id.nv_online:
                setTitle("在线答疑");
                mAskButton.setVisibility(View.GONE);
                mToolbar.getMenu().clear();
                mToolbar.inflateMenu(R.menu.menu_toolbar_online);
                transaction.replace(R.id.framelayout,onlineFragment);
                mToolbar.setNavigationIcon(R.drawable.ic_add_black_24dp);
                mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MainActivity.this, CreateClassroomActivity.class);
                        startActivityForResult(intent,REQUEST_CREATCLASS);
                    }
                });
                break;
            case R.id.nv_home:
                setTitle("易答");
                mAskButton.setVisibility(View.VISIBLE);
                mToolbar.getMenu().clear();
                mToolbar.setNavigationIcon(null);
                mToolbar.inflateMenu(R.menu.toolbar_search);
                transaction.replace(R.id.framelayout,homeFragment);
                break;
            case R.id.nv_me:
                setTitle("我的");
                mAskButton.setVisibility(View.GONE);
                mToolbar.getMenu().clear();
                mToolbar.setNavigationIcon(null);
                mToolbar.inflateMenu(R.menu.menu_toolbar_me);
                transaction.replace(R.id.framelayout,meFragment);
                break;
        }
        transaction.commit();
    }

    private void selectNavigationItem(int position) {
        try {
            BottomNavigationMenuView bottomNavigationMenuView = null;
            Field[] fields = BottomNavigationView.class.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String type = field.getType().toString();
                if (type.endsWith("BottomNavigationMenuView")) {
                    bottomNavigationMenuView = (BottomNavigationMenuView) field.get(bottomNavigationView);
                }
            }
            if (bottomNavigationMenuView != null) {
                Method activateNewButton;
                activateNewButton = BottomNavigationMenuView.class.getDeclaredMethod("activateNewButton", int.class);
                activateNewButton.setAccessible(true);
                activateNewButton.invoke(bottomNavigationMenuView, position);
            }
            onNavigationItemSelect(R.id.nv_home);
        } catch(NoSuchMethodException e) {
            e.printStackTrace();
        } catch(IllegalAccessException e) {
            e.printStackTrace();
        } catch(InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void setTitle(String title) {
        ((TextView) findViewById(R.id.tv_title)).setText(title);
    }
}
