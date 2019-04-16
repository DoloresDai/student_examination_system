package main.java.dai.service;

import main.java.dai.dao.Connect;
import main.java.dai.dao.ServiceSQL;
import main.java.dai.model.Score;
import main.java.dai.model.Student;
import main.java.dai.model.Subject;
import main.java.dai.model.Teacher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class QueryService {
    QueryService(String choice) {
        switch (choice) {
            case "1.1.1":
                System.out.println("查询所有学生信息如下：");
                queryAll(choice);
                break;
            case "1.1.2":
                System.out.println("请输入要查询的学生姓名（例如：池昌旭）：");
                queryAll(choice);
                break;
            case "1.1.3":
                System.out.println("请输入要查询的老师（例如：井柏然）：");
                queryAll(choice);
                break;
            case "1.1.4":
                System.out.println("请输入要查询的课程（例如：语文）：");
                queryAll(choice);
                break;
            case "1.2.1":
                System.out.println("查询所有课程信息如下：");
                queryAll(choice);
                break;
            case "1.2.2":
                System.out.println("请输入要查询的课程名称：（例如：语文）");
                queryAll(choice);
                break;
            case "1.2.3":
                System.out.println("请输入要查询的老师（例如：井柏然）：");
                queryAll(choice);
                break;
            case "1.3.1":
                System.out.println("查询所有课程信息为：");
                queryAll(choice);
                break;
            case "1.3.2":
                System.out.println("请输入要查询的老师（例如：井柏然）：");
                queryAll(choice);
                break;
            default:
                System.out.println("选项输入错误————\n" +
                        "\t请重新输入：\n");
        }
    }

    private void queryAll(String choice) {
        Connect connect = new Connect();
        String sql = new ServiceSQL().getSQL(choice);
        Connection connection = connect.getConnect();
        Statement statement = connect.getStatement(connection);
        ResultSet resultSet = connect.executeSQL(statement, sql);
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

}
