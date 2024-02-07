package com.green.First.vo;

import java.util.Arrays;

public class RegMemberVO {
    private String id;
    private String pw;
    private String name;
    private String[] tel;
    private String email;
    private String birth;

    private String[] lesson;
    private String yesOrNo;
    private String intro;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getTel() {
        return tel;
    }

    public void setTel(String[] tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String[] getLesson() {
        return lesson;
    }

    public void setLesson(String[] lesson) {
        this.lesson = lesson;
    }

    public String getYesOrNo() {
        return yesOrNo;
    }

    public void setYesOrNo(String yesOrNo) {
        this.yesOrNo = yesOrNo;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    @Override
    public String toString() {
        return "RegMemberVO{" +
                "id='" + id + '\'' +
                ", pw='" + pw + '\'' +
                ", name='" + name + '\'' +
                ", tel=" + Arrays.toString(tel) +
                ", email='" + email + '\'' +
                ", birth='" + birth + '\'' +
                ", lesson=" + Arrays.toString(lesson) +
                ", yesOrNo='" + yesOrNo + '\'' +
                ", intro='" + intro + '\'' +
                '}';
    }
}
