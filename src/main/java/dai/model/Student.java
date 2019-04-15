package main.java.dai.model;

public class Student {
    private int id;
    private String name;
    private int age;
    private String sex;
    private Score score;

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

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

    public boolean isEmpty() {
        return this.id == 0;
    }

    public String getStudentInfo() {
        return "学号： " + this.id + "，姓名： " + this.name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        Student student = (Student) obj;
        return this.id == student.getId();
    }

    @Override
    public int hashCode() {
        return this.id * 33 + this.age;
    }

    @Override
    public String toString() {
        String info = getStudentInfo() + " , 年龄：" + this.age + " , 性别： " + this.sex;
        if (this.score.isEmpty()) {
            return info;
        } else return info + score.toString();
    }
}
