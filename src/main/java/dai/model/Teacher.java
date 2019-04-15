package main.java.dai.model;

public class Teacher {
    private int id;
    private String teacher;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public boolean isEmpty() {
        return this.teacher == null;
    }

    public String getTeacherInfo() {
        return ", 老师： " + this.teacher;
    }

    @Override
    public String toString() {
        return "\t 教师编号：" + this.id + getTeacherInfo();
    }
}
