package cn.nutsky.nutc.easyanswer.data;

import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMConversation;
import com.avos.avoscloud.im.v2.Conversation;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by NutC on 2016/11/23.
 */

public class _Conversation {
    private String objectId;  //会话的唯一标识符
    private boolean unique;   //
    private List<String> m;   //对话中成员列表
    private boolean tr;        //
    private String lm;         //对话中最后一条消息发送的时间
    private String uniqueId;   //对话中唯一的名字name
    private List<String> mu;   //对话中设置了静音的成员
    private String clientId;      //对话创建者的ID,就是creator吧，该死的乱命名
    private boolean sys;        //
    private String createdAt;
    private String updatedAt;
    private String name;

    public _Conversation(AVIMConversation avimConversation){
        setObjectId(avimConversation.getConversationId());
        setM(avimConversation.getMembers());
        setLm(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(avimConversation.getLastMessageAt()));
        setName(avimConversation.getName());
        setMu((List<String>) avimConversation.get("mu"));
        setClientId(avimConversation.getCreator());
        setCreatedAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(avimConversation.getCreatedAt()));
        setUpdatedAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(avimConversation.getUpdatedAt()));
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public boolean isUnique() {
        return unique;
    }

    public void setUnique(boolean unique) {
        this.unique = unique;
    }

    public List<String> getM() {
        return m;
    }

    public void setM(List<String> m) {
        this.m = m;
    }

    public boolean isTr() {
        return tr;
    }

    public void setTr(boolean tr) {
        this.tr = tr;
    }

    public String getLm() {
        return lm;
    }

    public void setLm(String lm) {
        this.lm = lm;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public List<String> getMu() {
        return mu;
    }

    public void setMu(List<String> mu) {
        this.mu = mu;
    }

    public boolean isSys() {
        return sys;
    }

    public void setSys(boolean sys) {
        this.sys = sys;
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
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
