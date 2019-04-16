package main.java.dai.tools;

import main.java.dai.model.Student;
import main.java.dai.model.Subject;
import main.java.dai.model.Teacher;
import main.java.dai.service.SetModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class Tools {
    public static String getScanner() {
        return new Scanner(System.in).nextLine();
    }

    public static ArrayList<String> getInfo(String s) {
        String[] strings = s.split("，");
        ArrayList<String> info = new ArrayList<>();
        try {
            for (String string : strings) {
                info.add(string.split("：")[1]);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("信息输入错误！");
        }

        return info;
    }

    public static Student getStudentModel(String info) {
        return new SetModel().setStudent(info);
    }

    public static Teacher getTeacherModel(String info) {
        return new SetModel().setTeacher(info);
    }

    public static Subject getSubjectModel(String info) {
        return new SetModel().setSubject(info);
    }

    public static Map<Student, Subject> getStudentScoreModel(String info) {
        return new SetModel().setStudentScore(info);
    }

    public static Student getScoreStudent(Map<Student, Subject> map) {
        Iterator<Student> iterator = map.keySet().iterator();
        Student student = new Student();
        while (iterator.hasNext()) {
            student = iterator.next();
        }
        return student;
    }

    public static void printMenu() {
        System.out.print("——————————————————————————————————————————");
        System.out.println("您好，超级管理员，请选择你需要进行的操作——————————————————————————————————————————\n" +
                "|1. 查询\n" +
                "|\t1.1 查询学生信息以及成绩\n" +
                "|\t\t1.1.1 所有学生信息\n" +
                "|\t\t1.1.2 指定学生姓名的信息以及所有课程的成绩\n" +
                "|\t\t1.1.3 指定老师的所有学生及其成绩\n" +
                "|\t\t1.1.4 指定课程的所有学生及其成绩\n" +
                "|\t1.2 查询课程信息\n" +
                "|\t\t1.2.1 所有课程信息\n" +
                "|\t\t1.2.2 指定课程名称的信息\n" +
                "|\t\t1.2.3 指定老师的所有课程信息\n" +
                "|\t1.3 查询老师信息\n" +
                "|\t\t1.3.1 所有老师信息\n" +
                "|\t\t1.3.2 指定老师信息\n" +
                "|2. 新增\n" +
                "|\t2.1 新增学生信息\n" +
                "|\t2.2 新增课程信息\n" +
                "|\t2.3 新增老师信息\n" +
                "|\t2.4 给指定学生新增成绩\n" +
                "|3. 修改\n" +
                "|\t3.1 修改指定学号的学生\n" +
                "|\t3.2 修改指定课程的信息\n" +
                "|\t3.3 修改指定老师的信息\n" +
                "|\t3.4 修改指定学生的成绩\n" +
                "|4. 删除\n" +
                "|\t4.1 删除指定学生\n" +
                "|\t4.2 删除指定课程\n" +
                "|\t4.3 删除指定老师\n" +
                "|5. 退出系统\n" +
                "———————————————————————————————————————————————————————————————————————————————————————————————————————————————————————");
    }
}
