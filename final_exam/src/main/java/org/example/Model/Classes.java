package org.example.models;

public class Classes {
    int id;
    String classname;

    public Classes(String classname) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public Classes(int id, String classname) {
        this.id = id;
        this.classname = classname;
    }
}