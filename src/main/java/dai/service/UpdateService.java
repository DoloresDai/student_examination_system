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

class UpdateService {
    UpdateService(String choice) {
        String sql = new ServiceSQL().getSQL(choice);
        switch (choice) {
            case "3.1":
                System.out.println("请输入要修改的学生信息（例如：学号：20190101，姓名：池昌旭，年龄：18，性别：男）：");
                updateStudent(Tools.getScanner(), sql);
                break;
            case "3.2":
                System.out.println("请输入要修改的课程信息（例如：编号：2001，科目：语文，考试描述：考试内容较简单）：");
                updateSubject(Tools.getScanner(), sql);
                break;
            case "3.3":
                System.out.println("请输入要修改的老师信息（例如：教师编号：1002，姓名：井柏然，科目：2002）：");
                updateTeacher(Tools.getScanner(), sql);
                break;
            case "3.4":
                System.out.println("请输入要修改的学生成绩信息（例如：科目编号：2001，学生编号：20190101，成绩：88）：");
                updateStudentScore(Tools.getScanner(), sql);
                break;
            default:
                System.out.println("————选项输入错误————\n" +
                        "\t请重新输入：\n");
        }
    }

    private void updateStudent(String info, String sql) {
        Connect connect = new Connect();
        Connection connection = connect.getConnect();
        PreparedStatement preparedStatement = connect.getPreparedStatement(connection, sql);
        Student student = Tools.getStudentModel(info);
        try {
            if (!student.isEmpty()) {
                preparedStatement.setInt(4, student.getId());
                preparedStatement.setString(1, student.getName());
                preparedStatement.setInt(2, student.getAge());
                preparedStatement.setString(3, student.getSex());
                preparedStatement.execute();
                System.out.println("修改学生信息[" + student.getStudentInfo() + "]成功！");
            } else {
                System.out.println("————学生信息输入不全————\n" +
                        "\t 请重新输入：");
                new UpdateService("3.1");
            }
        } catch (SQLException e) {
            System.out.println("————修改学生信息失败————\n" + e.toString());
            new UpdateService("3.1");
        } finally {
            connect.closePreparedConnect(preparedStatement, connection);
        }
    }

    private void updateSubject(String info, String sql) {
        Connect connect = new Connect();
        Connection connection = connect.getConnect();
        PreparedStatement preparedStatement = connect.getPreparedStatement(connection, sql);
        Subject subject = Tools.getSubjectModel(info);
        try {
            if (!subject.isEmpty()) {
                preparedStatement.setInt(3, subject.getId());
                preparedStatement.setString(1, subject.getName());
                preparedStatement.setString(2, subject.getDescribe());
                preparedStatement.execute();
                System.out.println("修改课程信息[" + subject.getName() + "]成功！");
            } else {
                System.out.println("————课程信息输入不全————\n" +
                        "\t 请重新输入：");
                new UpdateService("3.2");
            }
        } catch (SQLException e) {
            System.out.println("————修改课程信息失败————\n" + e.toString());
            new UpdateService("3.2");
        } finally {
            connect.closePreparedConnect(preparedStatement, connection);
        }
    }

    private void updateTeacher(String info, String sql) {
        Connect connect = new Connect();
        Connection connection = connect.getConnect();
        PreparedStatement preparedStatement = connect.getPreparedStatement(connection, sql);
        Teacher teacher = Tools.getTeacherModel(info);
        try {
            if (!teacher.isEmpty()) {
                preparedStatement.setInt(3, teacher.getId());
                preparedStatement.setString(1, teacher.getName());
                preparedStatement.setInt(2, teacher.getSubject_id());
                preparedStatement.execute();
                System.out.println("修改老师信息[" + teacher.getName() + "]成功！");
            } else {
                System.out.println("————老师信息输入不全————\n" +
                        "\t 请重新输入：");
                new UpdateService("3.3");
            }
        } catch (SQLException e) {
            System.out.println("————修改老师信息失败————\n" + e.toString());
            new UpdateService("3.3");
        } finally {
            connect.closePreparedConnect(preparedStatement, connection);
        }
    }

    private void updateStudentScore(String info, String sql) {
        Connect connect = new Connect();
        Connection connection = connect.getConnect();
        PreparedStatement preparedStatement = connect.getPreparedStatement(connection, sql);
        Map<Student, Subject> studentSubjectMap = Tools.getStudentScoreModel(info);
        Student student = Tools.getScoreStudent(studentSubjectMap);
        Subject subject = studentSubjectMap.get(student);
        Score score = student.getScore();
        try {
            if (!subject.isEmpty() && !subject.isEmpty() && !score.isEmpty()) {
                preparedStatement.setInt(1, subject.getId());
                preparedStatement.setInt(3, student.getId());
                preparedStatement.setFloat(2, score.getScore());
                preparedStatement.execute();
                System.out.println("修改学生成绩信息[学号：" + student.getId() + score.toString() + "]成功！");
            } else {
                System.out.println("————学生成绩信息输入不全————\n" +
                        "\t 请重新输入：");
                new UpdateService("3.4");
            }
        } catch (SQLException e) {
            System.out.println("————修改学生成绩信息失败————\n" + e.toString());
            new UpdateService("3.4");
        } finally {
            connect.closePreparedConnect(preparedStatement, connection);
        }
    }

}
