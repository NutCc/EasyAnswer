package cn.nutsky.nutc.easyanswer.ui.widget;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;

import cn.nutsky.nutc.easyanswer.R;

/**
 * Created by NutC on 2016/11/2.
 */

public class BackToolbar extends Toolbar {

    public BackToolbar(final Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        setNavigationOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Activity)context).finish();
            }
        });
    }

}
