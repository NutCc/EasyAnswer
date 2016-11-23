package cn.nutsky.nutc.easyanswer.data;

import com.avos.avoscloud.AVObject;

import java.text.SimpleDateFormat;

/**
 * Created by NutC on 2016/11/18.
 */

public class Answer {
    private String objectId;
    private String questionId;
    private String name;
    private String answerContent;
    private String createdAt;
    private String updatedAt;
    public Answer(AVObject avObject) {
        if (!avObject.getClassName().equals("Answer")) {
            throw new RuntimeException("Question constructor params must be AVObject<Question>.");
        }
        setObjectId(avObject.getObjectId());
        setQuestionId((String) avObject.get("questionId"));
        setName((String) avObject.get("name"));
        setAnswerContent((String) avObject.get("answerContent"));
        setCreatedAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(avObject.get("createdAt")));
        setUpdatedAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(avObject.get("updatedAt")));
    }

    public Answer(String answerContent,String objectId,String createdAt){
        this.answerContent = answerContent;
        this.objectId = objectId;
        this.createdAt = createdAt;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
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
