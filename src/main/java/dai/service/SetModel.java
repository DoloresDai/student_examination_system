package main.java.dai.service;

import main.java.dai.model.Score;
import main.java.dai.model.Student;
import main.java.dai.model.Subject;
import main.java.dai.model.Teacher;
import main.java.dai.tools.Tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SetModel {
    public Student setStudent(String s) {
        Student student = new Student();
        ArrayList<String> studentInfo = Tools.getInfo(s);
        student.setId(Integer.parseInt(studentInfo.get(0)));
        student.setName(studentInfo.get(1));
        student.setAge(Integer.parseInt(studentInfo.get(2)));
        student.setSex(studentInfo.get(3));
        return student;
    }

    public Teacher setTeacher(String s) {
        Teacher teacher = new Teacher();
        ArrayList<String> studentInfo = Tools.getInfo(s);
        teacher.setId(Integer.parseInt(studentInfo.get(0)));
        teacher.setName(studentInfo.get(1));
        return teacher;
    }

    public Subject setSubject(String s) {
        Subject subject = new Subject();
        ArrayList<String> studentInfo = Tools.getInfo(s);
        subject.setId(Integer.parseInt(studentInfo.get(0)));
        subject.setName(studentInfo.get(1));
        subject.setDescribe(studentInfo.get(2));
        return subject;
    }

    public Map<Student,Subject> setStudentScore(String s) {
        Map<Student,Subject> studentScore = new HashMap<>();
        Student student = new Student();
        Score score = new Score();
        Subject subject = new Subject();
        ArrayList<String> studentInfo = Tools.getInfo(s);
        subject.setId(Integer.parseInt(studentInfo.get(0)));
        student.setId(Integer.parseInt(studentInfo.get(1)));
        score.setScore(Float.parseFloat(studentInfo.get(2)));
        studentScore.put(student,subject);
        return studentScore;
    }
}
