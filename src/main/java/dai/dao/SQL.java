package main.java.dai.dao;

import main.java.dai.tools.Tools;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.stream.Stream;

public class SQL {
    public String selectFunction(String choice) {
        String[] selectChoice = choice.split("\\.");
        switch (selectChoice[0]) {
            case "1":
                switch (selectChoice[1]) {
                    case "1":
                        return selectStudentInfo(choice);
                    case "2":
                        return selectSubjectInfo(choice);
                    case "3":
                        return selectTeacherInfo(choice);
                }
                break;
            case "2":
                return addStudent(choice);
        }
        return "";
    }

    public String selectStudentInfo(String choice) {
        String sql = "SELECT * FROM score";
        switch (choice) {
            case "1.1.1":
                sql = "Select * from student";
                break;
            case "1.1.2":
                System.out.println("请输入要查询的学生姓名：");
                sql = sql + " left join subject on score.subject_id = subject.id " + " LEFT OUTER JOIN student on score.student_id = student.id WHERE student.NAME='" + Tools.getScanner() + "'";
                break;
            case "1.1.3":
                System.out.println("请输入要查询的老师：");
                sql = sql + " LEFT OUTER JOIN teacher on score.subject_id= teacher.subject_id " + " LEFT OUTER JOIN student on score.student_id = student.id " +
                        "WHERE teacher.NAME ='" + Tools.getScanner() + "'";
                break;
            case "1.1.4":
                System.out.println("请输入要查询的课程：");
                sql = sql + " left join student on score.student_id = student.id " + " left outer join subject on subject.id = score.subject_id where subject.name = '" + Tools.getScanner() + "'";
                break;
        }
        return sql;
    }

    public String selectSubjectInfo(String choice) {
        String sql = "select * from subject left join teacher on teacher.subject_id = subject.id";
        switch (choice) {
            case "1.2.1":
                return sql;
            case "1.2.2":
                System.out.println("请输入要查询的课程名称：");
                sql = sql + " where subject.name = '" + Tools.getScanner() + "'";
                break;
            case "1.2.3":
                System.out.println("请输入要查询的老师：");
                sql = sql + " where teacher.name = '" + Tools.getScanner() + "'";
                break;
        }
        return sql;
    }

    public String selectTeacherInfo(String choice) {
        String sql = "select * from teacher";
        switch (choice) {
            case "1.3.1":
                return sql;
            case "1.3.2":
                System.out.println("请输入要查询的老师：");
                sql = sql + " where teacher.name = '" + Tools.getScanner() + "'";
                break;
        }
        return sql;
    }

    public String addStudent(String choice) {
        String sql = "insert into ";
        switch (choice) {
            case "2.1":
                System.out.println("请输入学生信息(例如：学号：20190101，姓名：池昌旭,年龄：18,性别：男)：");
                String[] student = Tools.getScanner().split("，");
                ArrayList<String> studentInfo = new ArrayList<>();
                for(String s : student){
                    studentInfo.add(s.split("：")[1]);
                }
                sql = sql + " student values(" + Integer.parseInt(studentInfo.get(0)) + ",'" + studentInfo.get(1) +
                        "'," + Integer.parseInt(studentInfo.get(2)) + ",'" + studentInfo.get(3) + "')";
                System.out.println("添加学生成功！");
                break;
            case "2.2":
                System.out.println("请输入要增加的课程信息（例如：编号：2001，科目：语文，考试描述：考试内容较简单");
                String[] subjectInfo = Tools.getScanner().split("，").toString().split("：");
                sql = sql + " subject values(" + Integer.parseInt(subjectInfo[1]) + ",'" + subjectInfo[3] + "','" + subjectInfo[5] + "')";
                System.out.println("添加课程信息成功！");
                break;
            case "2.3":
                System.out.println("请输入要增加的老师信息（例如：教师编号：1001，姓名：井柏然，科目：2001");
                String[] teacherInfo = Tools.getScanner().split("，").toString().split("：");
                sql = sql + " teacher values(" + Integer.parseInt(teacherInfo[1]) + ",'" + teacherInfo[3] + "'," + Integer.parseInt(teacherInfo[5]) + ")";
                break;
        }
        return sql;
    }
}
