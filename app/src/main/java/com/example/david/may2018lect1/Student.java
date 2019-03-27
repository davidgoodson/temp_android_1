package com.example.david.may2018lect1;

/**
 * Created by David on 9/11/2018.
 */

public class Student {
    String Surname, Othername, Sex, SID;

    public Student(){}

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getOthername() {
        return Othername;
    }

    public void setOthername(String othername) {
        Othername = othername;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getSID() {
        return SID;
    }

    public void setSID(String SID) {
        this.SID = SID;
    }
}
