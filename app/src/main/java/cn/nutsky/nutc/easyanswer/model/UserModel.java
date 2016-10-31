package cn.nutsky.nutc.easyanswer.model;

import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SignUpCallback;

/**
 * Created by NutC on 2016/10/30.
 */

public class UserModel {
    public static void signUp(String name, String pwd, String email, SignUpCallback callback) {
        AVUser user = new AVUser();
        user.setUsername(name);
        user.setEmail(email);
        user.setPassword(pwd);
        user.signUpInBackground(callback);
    }
}
