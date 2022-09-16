package com.d.passwordmanager.pojo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

/**
 * @author: Ding
 * @date: 2022/8/20 15:20
 * @description:
 * @modify:
 */


public class User {

    /**
     * id
     */
    private IntegerProperty id;

    /**
     * 用户密码
     */
    private StringProperty password;

    /**
     * 密保问题
     */
    private StringProperty question;

    /**
     * 密保问题答案
     */
    private StringProperty answer;

    private IntegerProperty errorCount;

    public User() {
    }

    public User(StringProperty password, StringProperty question, StringProperty answer, IntegerProperty errorCount) {
        this.password = password;
        this.question = question;
        this.answer = answer;
        this.errorCount = errorCount;
    }

    public StringProperty getPassword() {
        return password;
    }

    public void setPassword(StringProperty password) {
        this.password = password;
    }

    public StringProperty getQuestion() {
        return question;
    }

    public void setQuestion(StringProperty question) {
        this.question = question;
    }

    public StringProperty getAnswer() {
        return answer;
    }

    public void setAnswer(StringProperty answer) {
        this.answer = answer;
    }

    public IntegerProperty getId() {
        return id;
    }

    public void setId(IntegerProperty id) {
        this.id = id;
    }

    public IntegerProperty getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(IntegerProperty errorCount) {
        this.errorCount = errorCount;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", errorCount=" + errorCount +
                '}';
    }
}
