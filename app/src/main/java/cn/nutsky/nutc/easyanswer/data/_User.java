package cn.nutsky.nutc.easyanswer.data;

import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;

import java.text.SimpleDateFormat;

/**
 * Created by NutC on 2016/11/21.
 */

public class _User {
    private String objectId;
    private String email;
    private String sessionToken;
    private String realName;
    private String sex;
    private String password;
    private String name;
    private String portrait;
    private boolean teacher;
    private String mobilePhoneNumber;
    private int classId;
    private String studentId;
    private String createdAt;
    private String updatedAt;

    public _User(AVUser avUser) {
        if (!avUser.getClassName().equals("_User")) {
            throw new RuntimeException("_User constructor params must be AVObject<_User>.");
        }
        setObjectId(avUser.getObjectId());
        setRealName((String) avUser.get("realName"));
        setSex((String) avUser.get("sex"));
        setPassword((String) avUser.get("password"));
        setName((String) avUser.get("name"));
        setPortrait((String) avUser.get("portrait"));
        setTeacher(avUser.getBoolean("teacher"));
        setMobilePhoneNumber((String) avUser.get("mobilePhoneNumber"));
        setClassId(avUser.getInt("classId"));
        setStudentId((String) avUser.get("studentId"));
        setCreatedAt(new SimpleDateFormat("yyyy-MM-dd").format(avUser.get("createdAt")));
        setUpdatedAt(new SimpleDateFormat("yyyy-MM-dd").format(avUser.get("updatedAt")));
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public boolean getTeacher() {
        return teacher;
    }

    public void setTeacher(boolean teacher) {
        this.teacher = teacher;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
