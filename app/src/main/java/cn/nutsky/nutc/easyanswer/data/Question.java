package cn.nutsky.nutc.easyanswer.data;

/**
 * Created by NutC on 2016/11/2.
 */

public class Question {
    private String text ;
    public Question(String text){
        this.text = text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText(){
        return text;
    }
}
