package main.java.dai.model;

public class Teacher {
    private int id;
    private String name;

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

    public boolean isEmpty() {
        return this.name == null;
    }

    public String getTeacherInfo() {
        return ", 老师： " + this.name;
    }

    @Override
    public String toString() {
        return "\t 教师编号：" + this.id + getTeacherInfo();
    }
}
