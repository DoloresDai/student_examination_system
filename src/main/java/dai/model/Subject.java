package main.java.dai.model;

public class Subject {
    private int id;
    private String name;
    private String content;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isEmpty() {
        return this.id == 0;
    }

    public String getSubjectInfo() {
        return " , 科目： " + this.name;
    }

    @Override
    public String toString() {
        return "\t 编号： " + this.id + getSubjectInfo() + "，考试描述： " + this.content;
    }
}
