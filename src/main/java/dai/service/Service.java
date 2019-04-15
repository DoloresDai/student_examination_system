package main.java.dai.service;

import main.java.dai.common.Common;
import main.java.dai.dao.Connect;
import main.java.dai.dao.Login;
import main.java.dai.dao.SQL;
import main.java.dai.model.Score;
import main.java.dai.model.Student;
import main.java.dai.model.Subject;
import main.java.dai.model.Teacher;
import main.java.dai.tools.Tools;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Service {
    public void getInput() {
        Connect connect = new Connect();
        Connection connection = connect.getConnect();
        Statement statement = connect.getStatement(connection);
        System.out.println("您好，欢迎登陆学生考试系统，请输入您的用户名和密码(用户名:密码)：\n" +
                "例如：张三:123");
        String systemIn = Tools.getScanner();
        ResultSet resultSet = connect.executeSQL(statement, new Login().getLoginSql());
        String[] input = systemIn.split(":");

        try {
            while (resultSet.next()) {
                String user = resultSet.getString("user");
                String password = resultSet.getString("password");
                if (input[0].equals(user) && input[1].equals(password)) {
                    System.out.println("您好，超级管理员，请选择你需要进行的操作：\n" +
                            "1. 查询\n" +
                            "\t1.1 查询学生信息以及成绩\n" +
                            "\t\t1.1.1 所有学生信息\n" +
                            "\t\t1.1.2 指定学生姓名的信息以及所有课程的成绩\n" +
                            "\t\t1.1.3 指定老师的所有学生及其成绩\n" +
                            "\t\t1.1.4 指定课程的所有学生及其成绩\n" +
                            "\t1.2 查询课程信息\n" +
                            "\t    1.2.1 所有课程信息\n" +
                            "\t    1.2.2 指定课程名称的信息\n" +
                            "\t    1.2.3 指定老师的所有课程信息\n" +
                            "\t1.3 查询老师信息\n" +
                            "\t    1. 所有老师信息\n" +
                            "\t    2. 指定老师信息\n" +
                            "2. 新增\n" +
                            "\t2.1 新增学生信息\n" +
                            "\t2.2 新增课程信息\n" +
                            "\t2.3 新增老师信息\n" +
                            "\t2.4 给指定学生新增成绩\n" +
                            "3. 修改\n" +
                            "    3.1 修改指定学号的学生\n" +
                            "    3.2 修改指定课程的信息\n" +
                            "    3.3 修改指定老师的信息\n" +
                            "    3.4 修改指定学生的成绩\n" +
                            "4. 删除\n" +
                            "\t4.1 删除指定学生\n" +
                            "\t4.3 删除指定课程\n" +
                            "\t4.3 删除指定老师");
                    getDate();
                } else {
                    System.out.println("不能进入系统————\n" +
                            "\t账号或密码错误！");
                }
            }
        } catch (SQLException e) {
            System.out.println("进入系统失败！");
        } finally {
            connect.closeConnect(resultSet, statement, connection);
        }
    }

    public void getDate() {
        Connect connect2 = new Connect();
        SQL sqlString2 = new SQL();
        Connection connection2 = connect2.getConnect();
        Statement statement2 = connect2.getStatement(connection2);
        ResultSet resultSet2 = connect2.executeSQL(statement2, sqlString2.selectFunction(Tools.getScanner()));

//获取打印信息

        try {
            while (resultSet2.next()) {
                Common common = new Common();
                Score score = common.getScore(resultSet2);
                Student student = common.getStudent(resultSet2);
                student.setScore(score);
                Subject subject = common.getSubject(resultSet2);
                Teacher teacher = common.getTeacher(resultSet2);
                if (subject.isEmpty() && teacher.isEmpty() && !student.isEmpty()) {
                    System.out.println(student.toString());
                } else if (!subject.isEmpty() && teacher.isEmpty() && student.isEmpty()) {
                    System.out.println(subject.toString());
                } else if (!teacher.isEmpty() && subject.isEmpty() && student.isEmpty()) {
                    System.out.println(teacher.toString());
                } else if (!subject.isEmpty() && !teacher.isEmpty() && student.isEmpty()) {
                    System.out.println(subject.toString() + teacher.getTeacherInfo());
                } else if (subject.isEmpty() && !teacher.isEmpty() && !student.isEmpty()) {
                    System.out.println(student.toString() + teacher.getTeacherInfo());
                } else if (!subject.isEmpty() && teacher.isEmpty() && !student.isEmpty()) {
                    System.out.println(student.toString() + subject.getSubjectInfo());
                } else if (!subject.isEmpty() && !teacher.isEmpty() && !student.isEmpty()) {
                    System.out.println(student.getStudentInfo() + teacher.getTeacherInfo() + subject.getSubjectInfo());
                } else {
                    System.out.println("信息查询为空！");
                }
            }
        } catch (SQLException e) {
            System.out.println("生成信息失败！");
        } finally {
            connect2.closeConnect(resultSet2, statement2, connection2);
        }
    }
}

