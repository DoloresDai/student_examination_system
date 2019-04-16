package main.java.dai.service;

import main.java.dai.dao.Connect;
import main.java.dai.dao.Login;
import main.java.dai.dao.SQL;
import main.java.dai.model.Score;
import main.java.dai.model.Student;
import main.java.dai.model.Subject;
import main.java.dai.model.Teacher;
import main.java.dai.tools.Tools;

import java.sql.*;
import java.util.Iterator;
import java.util.Map;

public class Service {
    public void getLogin() {
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
                    addService(Tools.getScanner());
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

    public void queryService() {
        Connect connect = new Connect();
        SQL sqlString = new SQL();
        Connection connection = connect.getConnect();
        Statement statement = connect.getStatement(connection);
        ResultSet resultSet = connect.executeSQL(statement, sqlString.query(Tools.getScanner()));

        try {
            while (resultSet.next()) {
                GetModel getModel = new GetModel();
                Score score = getModel.getScore(resultSet);
                Student student = getModel.getStudent(resultSet);
                student.setScore(score);
                Subject subject = getModel.getSubject(resultSet);
                Teacher teacher = getModel.getTeacher(resultSet);
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
            connect.closeConnect(resultSet, statement, connection);
        }
    }

    public void addService(String choice) {
        String sql = new SQL().addInfo(choice);
        switch (choice) {
            case "2.1":
                System.out.println("请输入学生信息(例如：学号：20190101，姓名：池昌旭,年龄：18,性别：男)：");
                addStudent(Tools.getScanner(), sql);
                break;
            case "2.2":
                System.out.println("请输入要增加的课程信息（例如：编号：2001，科目：语文，考试描述：考试内容较简单");
                addSubject(Tools.getScanner(), sql);
                break;
            case "2.3":
                System.out.println("请输入要增加的老师信息（例如：教师编号：1001，姓名：井柏然，科目：2001");
                addTeacher(Tools.getScanner(), sql);
                break;
            case "2.4":
                System.out.println("请输入要增加的学生成绩信息（例如：科目编号：2001，学生编号：20190101，成绩：88");
                addStudentScore(Tools.getScanner(), sql);
                break;
        }
    }

    public void addStudent(String info, String sql) {
        SetModel setModel = new SetModel();
        Student student = setModel.setStudent(info);
        Connect connect = new Connect();
        Connection connection = connect.getConnect();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, student.getId());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setInt(3, student.getAge());
            preparedStatement.setString(4, student.getSex());
            preparedStatement.execute();
            System.out.println("添加学生信息[" + student.getStudentInfo() + "]成功！");

        } catch (SQLException e) {
            System.out.println("增添学生信息失败！");
        }
    }

    public void addSubject(String info, String sql) {
        SetModel setModel = new SetModel();
        Subject subject = setModel.setSubject(info);
        Connect connect = new Connect();
        Connection connection = connect.getConnect();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, subject.getId());
            preparedStatement.setString(2, subject.getName());
            preparedStatement.setString(3, subject.getDescribe());
            if (preparedStatement.execute()) {
                System.out.println("添加课程信息[" + subject.getName() + "]成功！");
            }
        } catch (SQLException e) {
            System.out.println("增添课程信息失败！");
        }
    }

    public void addTeacher(String info, String sql) {
        SetModel setModel = new SetModel();
        Teacher teacher = setModel.setTeacher(info);
        Connect connect = new Connect();
        Connection connection = connect.getConnect();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, teacher.getId());
            preparedStatement.setString(2, teacher.getName());
            if (preparedStatement.execute()) {
                System.out.println("添加老师信息[" + teacher.getName() + "]成功！");
            }
        } catch (SQLException e) {
            System.out.println("增添老师信息失败！");
        }
    }

    public void addStudentScore(String info, String sql) {
        SetModel setModel = new SetModel();
        Map<Student, Subject> studentScore = setModel.setStudentScore(info);
        Iterator<Student> iterator = studentScore.keySet().iterator();
        Student student = new Student();
        while (iterator.hasNext()) {
            student = iterator.next();
        }
        Subject subject = studentScore.get(student);
        Connect connect = new Connect();
        Connection connection = connect.getConnect();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, subject.getId());
            preparedStatement.setInt(2, student.getId());
            preparedStatement.setFloat(3, student.getScore().getScore());
            if (preparedStatement.execute()) {
                System.out.println("添加学生成绩信息[学号：" + student.getId() + " ，成绩： " + student.getScore().toString() + "]成功！");
            }
        } catch (SQLException e) {
            System.out.println("增添学生信息失败！");
        }
    }
}

