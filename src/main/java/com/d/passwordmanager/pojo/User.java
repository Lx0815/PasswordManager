package com.d.passwordmanager.pojo;


import java.util.ArrayList;
import java.util.List;

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
    private Integer id;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 密保问题和答案
     */
    private String question1;

    private String answer1;

    private String question2;

    private String answer2;

    private String question3;

    private String answer3;

    public User() {
    }

    public User(String password, String question1, String answer1, String question2, String answer2, String question3, String answer3) {
        this.password = password;
        this.question1 = question1;
        this.answer1 = answer1;
        this.question2 = question2;
        this.answer2 = answer2;
        this.question3 = question3;
        this.answer3 = answer3;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getQuestion1() {
        return question1;
    }

    public void setQuestion1(String question1) {
        this.question1 = question1;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getQuestion2() {
        return question2;
    }

    public void setQuestion2(String question2) {
        this.question2 = question2;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getQuestion3() {
        return question3;
    }

    public void setQuestion3(String question3) {
        this.question3 = question3;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public List<String> getQuestions() {
        List<String> questionList = new ArrayList<>(3);
        questionList.add(question1);
        questionList.add(question2);
        questionList.add(question3);
        return questionList;
    }
}
