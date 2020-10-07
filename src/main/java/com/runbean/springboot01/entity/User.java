package com.runbean.springboot01.entity;

public class User {

    private String name;
    private int age;
    private String sex;
    private Student student;

    public User() {
    }

    public User(String name, int age, String sex, Student student) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.student = student;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", student=" + student +
                '}';
    }
}
