package cn.nutsky.nutc.easyanswer.data;

import android.support.annotation.NonNull;

import com.avos.avoscloud.AVObject;

import java.util.Date;

/**
 * Created by NutC on 2016/11/2.
 */

public class Question {
    private String objectId;
    private String content;
    private String name;
    private int commentAsk_num;
    private int replay_num;
    private String label;
    private String createdAt;
    private String updateAt;

    public Question(AVObject avObject) {
        if (!avObject.getClassName().equals("Question")) {
            throw new RuntimeException("Question constructor params must be AVObject<Question>.");
        }
        setObjectId(avObject.getObjectId());
        setContent((String) avObject.get("content"));
    }

    public Question(@NonNull String content,  @NonNull String label) {
        this.content = content;
        this.label = label;
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

        this.content = content != null ? content : "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCommentAsk_num() {
        return commentAsk_num;
    }

    public void setCommentAsk_num(int commentAsk_num) {
        this.commentAsk_num = commentAsk_num;
    }

    public int getReplay_num() {
        return replay_num;
    }

    public void setReplay_num(int replay_num) {
        this.replay_num = replay_num;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    public AVObject toAVObject() {
        AVObject avObject = new AVObject("Question");
        avObject.setObjectId(objectId);
        avObject.put("content", content);
        avObject.put("label",label);
        return avObject;
    }
}
