package main.java.dai.service;

import main.java.dai.dao.Connect;
import main.java.dai.dao.ServiceSQL;
import main.java.dai.model.Student;
import main.java.dai.model.Subject;
import main.java.dai.model.Teacher;
import main.java.dai.tools.Tools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

class DeleteService {
    DeleteService(String choice) {
        String sql = new ServiceSQL().getSQL(choice);
        switch (choice) {
            case "4.1":
                System.out.println("请输入要删除的学生信息（例如：学号：20190101）：");
                deleteStudent(Tools.getScanner(), sql);
                break;
            case "4.2":
                System.out.println("请输入要删除的课程信息（例如：编号：2001）：");
                deleteSubject(Tools.getScanner(), sql);
                break;
            case "4.3":
                System.out.println("请输入要删除的老师信息（例如：教师编号：1002）：");
                deleteTeacher(Tools.getScanner(), sql);
                break;
            default:
                System.out.println("————选项输入错误————\n" +
                        "\t请重新输入：\n");
        }
    }

    private void deleteStudent(String info, String sql) {
        Connect connect = new Connect();
        Connection connection = connect.getConnect();
        PreparedStatement preparedStatement = connect.getPreparedStatement(connection, sql);
        Student student = Tools.getStudentModel(info);
        try {
            if (!student.isEmpty()) {
                preparedStatement.setInt(1, student.getId());
                preparedStatement.execute();
                System.out.println("删除学生信息[学号：" + student.getId() + "]成功！");
            } else {
                System.out.println("————不匹配任何学生信息————\n" +
                        "\t请重新输入：");
                new DeleteService("4.1");
            }
        } catch (SQLException e) {
            System.out.println("————删除学生信息失败————\n" + e.toString());
            new DeleteService("4.1");
        } finally {
            connect.closePreparedConnect(preparedStatement, connection);
        }
    }

    private void deleteSubject(String info, String sql) {
        Connect connect = new Connect();
        Connection connection = connect.getConnect();
        Subject subject = Tools.getSubjectModel(info);
        PreparedStatement preparedStatement = connect.getPreparedStatement(connection, sql);
        try {
            if (!subject.isEmpty()) {
                preparedStatement.setInt(1, subject.getId());
                preparedStatement.execute();
                System.out.println("删除课程信息[" + subject.getId() + "]成功！");
            } else {
                System.out.println("————不匹配任何课程信息————\n" +
                        "\t请重新输入：");
                new DeleteService("4.2");
            }
        } catch (SQLException e) {
            System.out.println("————删除课程信息失败————\n" + e.toString());
            new DeleteService("4.2");
        } finally {
            connect.closePreparedConnect(preparedStatement, connection);
        }
    }

    private void deleteTeacher(String info, String sql) {
        Connect connect = new Connect();
        Connection connection = connect.getConnect();
        PreparedStatement preparedStatement = connect.getPreparedStatement(connection, sql);
        Teacher teacher = Tools.getTeacherModel(info);
        try {
            if (!teacher.isEmpty()) {
                preparedStatement.setInt(1, teacher.getId());
                preparedStatement.execute();
                System.out.println("删除老师信息[" + teacher.getId() + "]成功！");
            } else {
                System.out.println("————不匹配任何老师信息————\n" +
                        "\t请重新输入：");
                new DeleteService("4.3");
            }
        } catch (SQLException e) {
            System.out.println("————删除老师信息失败————\n" + e.toString());
            new DeleteService("4.3");
        } finally {
            connect.closePreparedConnect(preparedStatement, connection);
        }
    }
}
