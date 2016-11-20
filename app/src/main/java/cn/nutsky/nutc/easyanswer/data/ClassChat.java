package cn.nutsky.nutc.easyanswer.data;

import com.avos.avoscloud.AVObject;

/**
 * Created by NutC on 2016/11/18.
 */

public class ClassChat {
    private String objectID;
    private String content;
    private String userID;
    private String ALC;
    private String createdAt;
    private String updatedAt;

    public ClassChat(String content) {
        this.content = content;
    }

    public String getObjectID() {
        return objectID;
    }

    public void setObjectID(String objectID) {
        this.objectID = objectID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getALC() {
        return ALC;
    }

    public void setALC(String ALC) {
        this.ALC = ALC;
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

    public AVObject toAVObject(){
        AVObject avObject = new AVObject("ClassChat");
        avObject.setObjectId(objectID);
        avObject.put("content",content);
        return avObject;
    }
}
