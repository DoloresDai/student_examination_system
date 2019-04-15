package main.java.dai.model;

public class Subject {
    private int id;
    private String name;
    private String describe;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public boolean isEmpty() {
        return this.id == 0;
    }

    public String getSubjectInfo() {
        return " , 科目： " + this.name;
    }

    @Override
    public String toString() {
        return "\t 编号： " + this.id + getSubjectInfo() + "，考试描述： " + this.describe;
    }
}
