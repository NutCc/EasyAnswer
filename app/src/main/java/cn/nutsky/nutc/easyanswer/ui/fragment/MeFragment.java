package cn.nutsky.nutc.easyanswer.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVUser;

import cn.nutsky.nutc.easyanswer.R;
import cn.nutsky.nutc.easyanswer.ui.activity.BecomeTeacherActivity;
import cn.nutsky.nutc.easyanswer.ui.activity.LoginActivity;
import cn.nutsky.nutc.easyanswer.ui.activity.MainActivity;
import cn.nutsky.nutc.easyanswer.ui.activity.MyQuestionActivity;
import cn.nutsky.nutc.easyanswer.ui.activity.SignUpActivity;
import cn.nutsky.nutc.easyanswer.ui.widget.BackToolbar;
import cn.nutsky.nutc.easyanswer.utils.ActivityCollector;

/**
 * Created by NutC on 2016/11/2.
 */

public class MeFragment extends Fragment implements View.OnClickListener {

    private Button btExitLogin;
    private Button btBecomeTeacher;
    private Intent intent;
    private TextView tvMyQuestion;
    private TextView tvName;
    private EditText etMail;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_me,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btExitLogin = (Button) view.findViewById(R.id.bt_exit_login);
        btBecomeTeacher = (Button) view.findViewById(R.id.bt_become_teacher);
        tvMyQuestion = (TextView) view.findViewById(R.id.tv_my_question);
        tvName = (TextView) view.findViewById(R.id.tv_name);
        tvName.setText(AVUser.getCurrentUser().getUsername());
        etMail = (EditText) view.findViewById(R.id.et_mail);
        etMail.setText(AVUser.getCurrentUser().getEmail());
        btExitLogin.setOnClickListener(this);
        btBecomeTeacher.setOnClickListener(this);
        tvMyQuestion.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_my_question:
                Toast.makeText(getContext(),"我的问题",Toast.LENGTH_SHORT).show();
                intent = new Intent(getContext(), MyQuestionActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_exit_login:
                intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
                ActivityCollector.finishAll();
                break;
            case R.id.bt_become_teacher:
                intent = new Intent(getContext(), BecomeTeacherActivity.class);
                startActivity(intent);
                break;
        }
    }
}
