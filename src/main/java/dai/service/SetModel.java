package main.java.dai.service;

import main.java.dai.model.Score;
import main.java.dai.model.Student;
import main.java.dai.model.Subject;
import main.java.dai.model.Teacher;
import main.java.dai.tools.Tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SetModel {
    public Student setStudent(String s) {
        Student student = new Student();
        ArrayList<String> studentInfo = Tools.getInfo(s);
        try {
            student.setId(Integer.parseInt(studentInfo.get(0)));
            student.setName(studentInfo.get(1));
            student.setAge(Integer.parseInt(studentInfo.get(2)));
            student.setSex(studentInfo.get(3));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("学生信息输入错误！");
        }catch (IndexOutOfBoundsException e){
        }
        return student;
    }

    public Teacher setTeacher(String s) {
        Teacher teacher = new Teacher();
        ArrayList<String> studentInfo = Tools.getInfo(s);
        try {
            teacher.setId(Integer.parseInt(studentInfo.get(0)));
            teacher.setName(studentInfo.get(1));
            teacher.setSubject_id(Integer.parseInt(studentInfo.get(2)));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("老师信息输入错误！");
        }catch (IndexOutOfBoundsException e){
        }
        return teacher;
    }

    public Subject setSubject(String s) {
        Subject subject = new Subject();
        ArrayList<String> studentInfo = Tools.getInfo(s);
        try {
            subject.setId(Integer.parseInt(studentInfo.get(0)));
            subject.setName(studentInfo.get(1));
            subject.setContent(studentInfo.get(2));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("科目信息输入错误！");
        }catch (IndexOutOfBoundsException e){
        }
        return subject;
    }

    public Map<Student, Subject> setStudentScore(String s) {
        Map<Student, Subject> studentScore = new HashMap<>();
        Student student = new Student();
        Subject subject = new Subject();
        Score score = new Score();
        ArrayList<String> studentInfo = Tools.getInfo(s);
        try {
            subject.setId(Integer.parseInt(studentInfo.get(0)));
            student.setId(Integer.parseInt(studentInfo.get(1)));
            score.setScore(Float.parseFloat(studentInfo.get(2)));
            student.setScore(score);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("学生成绩信息输入错误！");
        }
        studentScore.put(student, subject);
        return studentScore;
    }

}
