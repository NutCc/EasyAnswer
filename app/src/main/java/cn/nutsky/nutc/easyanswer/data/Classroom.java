package cn.nutsky.nutc.easyanswer.data;

import android.support.annotation.NonNull;

import com.avos.avoscloud.AVObject;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by NutC on 2016/11/15.
 */

public class Classroom {
    private String objectId;     //答疑id
    private String content;      //答疑内容
    private String name;         //user昵称
    private String classContent; //教室内容
    private String createdAt;    //创建时间
    private String updatedAt;    //更新时间
    private String beginDate;
    private String endTime;      //答疑结束时间
    private String beginTime;    //答疑开始时间

    private String time;

    public Classroom(AVObject avObject) {
        if (!avObject.getClassName().equals("Classroom")) {
            throw new RuntimeException("Question constructor params must be AVObject<ClassroomActivity>.");
        }
        setObjectId(avObject.getObjectId());
        setContent((String) avObject.get("content"));
        setName((String) avObject.get("name"));
        setClassContent((String) avObject.get("classContent"));
        setCreatedAt(new SimpleDateFormat("yyyy-MM-dd").format(avObject.get("createdAt")));
        setUpdatedAt(new SimpleDateFormat("yyyy-MM-dd").format(avObject.get("updatedAt")));
        setBeginDate((String) avObject.get("beginDate"));
        setEndTime((String) avObject.get("endTime"));
        setBeginTime((String) avObject.get("beginTime"));
    }

    public Classroom(@NonNull String content,@NonNull String name,@NonNull String beginDate,@NonNull String beginTime,@NonNull String endTime){
        this.content = content;
        this.name = name;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.beginDate = beginDate;
    }


    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassContent() {
        return classContent;
    }

    public void setClassContent(String classContent) {
        this.classContent = classContent;
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

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public String getBeginDate(){return beginDate;}

    public void setBeginDate(String beginDate){this.beginDate = beginDate;}

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getTime(){
        return getBeginDate()+"-"+getBeginTime()+" - " + getEndTime();
    }

    public AVObject toAVObject() {
        AVObject avObject = new AVObject("Classroom");
        avObject.setObjectId(objectId);
        avObject.put("content", content);
        avObject.put("name","NutC");
        avObject.put("beginDate",beginDate);
        avObject.put("beginTime",beginTime);
        avObject.put("endTime",endTime);
        return avObject;
    }
}
