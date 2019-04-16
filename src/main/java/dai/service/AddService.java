package main.java.dai.service;

import main.java.dai.dao.Connect;
import main.java.dai.dao.ServiceSQL;
import main.java.dai.model.Score;
import main.java.dai.model.Student;
import main.java.dai.model.Subject;
import main.java.dai.model.Teacher;
import main.java.dai.tools.Tools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

class AddService {
    AddService(String choice) {
        String sql = new ServiceSQL().getSQL(choice);
        switch (choice) {
            case "2.1":
                System.out.println("请输入要增加的学生信息（例如：学号：20190101，姓名：池昌旭，年龄：18，性别：男）：");
                addStudent(Tools.getScanner(), sql);
                break;
            case "2.2":
                System.out.println("请输入要增加的课程信息（例如：编号：2001，科目：语文，考试描述：考试内容较简单）：");
                addSubject(Tools.getScanner(), sql);
                break;
            case "2.3":
                System.out.println("请输入要增加的老师信息（例如：教师编号：1002，姓名：井柏然，科目：2001）：");
                addTeacher(Tools.getScanner(), sql);
                break;
            case "2.4":
                System.out.println("请输入要增加的学生成绩信息（例如：科目编号：2001，学生编号：20190101，成绩：88）：");
                addStudentScore(Tools.getScanner(), sql);
                break;
            default:
                System.out.println("————选项输入错误————\n" +
                        "\t请重新输入：\n");
        }
    }

    private void addStudent(String info, String sql) {
        Student student = Tools.getStudentModel(info);
        Connect connect = new Connect();
        Connection connection = connect.getConnect();
        PreparedStatement preparedStatement = connect.getPreparedStatement(connection, sql);
        try {
            if (!student.isEmpty()) {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, student.getId());
                preparedStatement.setString(2, student.getName());
                preparedStatement.setInt(3, student.getAge());
                preparedStatement.setString(4, student.getSex());
                preparedStatement.execute();
                System.out.println("添加学生信息[" + student.getStudentInfo() + "]成功！");
            } else {
                System.out.println("————老师信息输入不全————\n" +
                        "\t 请重新输入：");
                new AddService("2.1");
            }
        } catch (SQLException e) {
            System.out.println("————增添学生信息失败————\n" + e.toString());
            new AddService("2.1");
        } finally {
            connect.closePreparedConnect(preparedStatement, connection);
        }
    }

    private void addSubject(String info, String sql) {
        Subject subject = Tools.getSubjectModel(info);
        Connect connect = new Connect();
        Connection connection = connect.getConnect();
        PreparedStatement preparedStatement = connect.getPreparedStatement(connection, sql);
        try {
            if (!subject.isEmpty()) {
                preparedStatement.setInt(1, subject.getId());
                preparedStatement.setString(2, subject.getName());
                preparedStatement.setString(3, subject.getContent());
                preparedStatement.execute();
                System.out.println("添加课程信息[" + subject.getName() + "]成功！");
            } else {
                System.out.println("————课程信息输入不全————\n" +
                        "\t 请重新输入：");
                new AddService("2.2");
            }
        } catch (SQLException e) {
            System.out.println("————增加课程信息失败————\n" + e.toString());
            new AddService("2.2");
        } finally {
            connect.closePreparedConnect(preparedStatement, connection);
        }
    }

    private void addTeacher(String info, String sql) {
        Teacher teacher = Tools.getTeacherModel(info);
        Connect connect = new Connect();
        Connection connection = connect.getConnect();
        PreparedStatement preparedStatement = connect.getPreparedStatement(connection, sql);
        try {
            if (!teacher.isEmpty()) {
                preparedStatement.setInt(1, teacher.getId());
                preparedStatement.setString(2, teacher.getName());
                preparedStatement.setInt(3, teacher.getSubject_id());
                preparedStatement.execute();
                System.out.println("添加老师信息[" + teacher.getName() + "]成功！");
            } else {
                System.out.println("————老师信息输入不全————\n" +
                        "\t 请重新输入：");
                new AddService("2.3");
            }
        } catch (SQLException e) {
            System.out.println("————增加老师信息失败————\n" + e.toString());
            new AddService("2.3");
        } finally {
            connect.closePreparedConnect(preparedStatement, connection);
        }
    }

    private void addStudentScore(String info, String sql) {
        Map<Student, Subject> studentSubjectMap = Tools.getStudentScoreModel(info);
        Student student = Tools.getScoreStudent(studentSubjectMap);
        Subject subject = studentSubjectMap.get(student);
        Score score = student.getScore();
        Connect connect = new Connect();
        Connection connection = connect.getConnect();
        PreparedStatement preparedStatement = null;
        try {
            if (!subject.isEmpty() && !student.isEmpty() && !score.isEmpty()) {
                preparedStatement = connect.getPreparedStatement(connection, sql);
                preparedStatement.setInt(1, subject.getId());
                preparedStatement.setInt(2, student.getId());
                preparedStatement.setFloat(3, score.getScore());
                System.out.println("添加学生成绩信息[学号：" + student.getId() + score.toString() + "]成功！");
            } else {
                System.out.println("————学生成绩信息输入不全————\n" +
                        "\t 请重新输入：");
                new AddService("2.4");
            }
        } catch (SQLException e) {
            System.out.println("————增添学生成绩信息失败————\n" + e.toString());
            new AddService("2.4");
        } finally {
            connect.closePreparedConnect(preparedStatement, connection);
        }
    }

}
