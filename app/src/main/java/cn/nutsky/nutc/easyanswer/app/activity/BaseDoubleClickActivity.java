package cn.nutsky.nutc.easyanswer.app.activity;

import android.view.KeyEvent;
import android.widget.Toast;

import cn.nutsky.nutc.easyanswer.utils.ActivityCollector;

/**
 * Created by NutC on 2016/11/2.
 */

public abstract class BaseDoubleClickActivity extends BaseActivity {
    private long mLastClickTime;


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                long currentTime = System.currentTimeMillis();
                if (currentTime - mLastClickTime < 2000) {
                    ActivityCollector.finishAll();
                } else {
                    mLastClickTime = currentTime;
                    Toast.makeText(this,"再按一次退出易答",Toast.LENGTH_SHORT).show();
                }
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}
