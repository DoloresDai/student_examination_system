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
import java.util.Map;

public class Service {
    public Service() {
        getLogin();
    }

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
                    isLogin();
                } else {
                    System.out.println("不能进入系统————\n" +
                            "\t账号或密码错误！\n" +
                            "\t请重新输入：");
                    getLogin();
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("账号密码信息输入不全————\n" +
                    "\t请重新输入:");
            getLogin();
        } catch (SQLException e) {
            System.out.println("进入SQL系统失败————\n" +
                    "\t请再次尝试：");
            getLogin();
        } finally {
            connect.closeConnect(resultSet, statement, connection);
        }
    }

    public void isLogin() {
        Tools.printMenu();
        choiceService(Tools.getScanner());
    }

    public void choiceService(String choice) {
        String one = choice.split("\\.")[0];
        switch (one) {
            case "1":
                queryService(choice);
                isLogin();
                break;
            case "2":
                addService(choice);
                isLogin();
                break;
            case "3":
                updateService(choice);
                isLogin();
                break;
            case "4":
                deleteService(choice);
                isLogin();
                break;
            case "5":
                System.out.println("超级管理员已退出系统————\n" +
                        "\t欢迎下次使用！");
                break;
            default:
                System.out.println("选项输入错误————\n" +
                        "\t请重新输入：\n");
                isLogin();
        }

    }

    public void queryService(String choice) {
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

    public void queryAll(String choice) {
        Connect connect = new Connect();
        String sql = new SQL().getSQL(choice);
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

    public void addService(String choice) {
        String sql = new SQL().getSQL(choice);
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
                System.out.println("选项输入错误————\n" +
                        "\t请重新输入：\n");
        }
    }

    public void addStudent(String info, String sql) {
        Student student = Tools.getStudentModel(info);
        Connect connect = new Connect();
        Connection connection = connect.getConnect();
        PreparedStatement preparedStatement = null;
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
                System.out.println("老师信息输入不全————\n" +
                        "\t 请重新输入：");
                addService("2.1");
            }
        } catch (SQLException e) {
            System.out.println("增添学生信息失败！\n" + e.toString());
            addService("2.1");
        } finally {
            try {
                connection.close();
                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println("关闭连接失败！");
            } catch (NullPointerException e) {
            }
        }
    }

    public void addSubject(String info, String sql) {
        Subject subject = Tools.getSubjectModel(info);
        Connect connect = new Connect();
        Connection connection = connect.getConnect();
        PreparedStatement preparedStatement = null;
        try {
            if (!subject.isEmpty()) {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, subject.getId());
                preparedStatement.setString(2, subject.getName());
                preparedStatement.setString(3, subject.getDescribe());
                preparedStatement.execute();
                System.out.println("添加课程信息[" + subject.getName() + "]成功！");
            } else {
                System.out.println("课程信息输入不全————\n" +
                        "\t 请重新输入：");
                addService("2.2");
            }
        } catch (SQLException e) {
            System.out.println("增加课程信息失败！\n" + e.toString());
            addService("2.2");
        } finally {
            try {
                connection.close();
                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println("关闭连接失败！");
            } catch (NullPointerException e) {
            }
        }
    }

    public void addTeacher(String info, String sql) {
        Teacher teacher = Tools.getTeacherModel(info);
        Connect connect = new Connect();
        Connection connection = connect.getConnect();
        PreparedStatement preparedStatement = null;
        try {
            if (!teacher.isEmpty()) {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, teacher.getId());
                preparedStatement.setString(2, teacher.getName());
                preparedStatement.setInt(3, teacher.getSubject_id());
                preparedStatement.execute();
                System.out.println("添加老师信息[" + teacher.getName() + "]成功！");
            } else {
                System.out.println("老师信息输入不全————\n" +
                        "\t 请重新输入：");
                addService("2.3");
            }
        } catch (SQLException e) {
            System.out.println("增添老师信息失败！\n" + e.toString());
            addService("2.3");
        } finally {
            try {
                connection.close();
                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println("关闭连接失败！");
            } catch (NullPointerException e) {
            }
        }
    }

    public void addStudentScore(String info, String sql) {
        Map<Student, Subject> studentSubjectMap = Tools.getStudentScoreModel(info);
        Student student = Tools.getScoreStudent(studentSubjectMap);
        Subject subject = studentSubjectMap.get(student);
        Score score = student.getScore();
        Connect connect = new Connect();
        Connection connection = connect.getConnect();
        PreparedStatement preparedStatement = null;
        try {
            if (!subject.isEmpty() && !student.isEmpty() && !score.isEmpty()) {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, subject.getId());
                preparedStatement.setInt(2, student.getId());
                preparedStatement.setFloat(3, score.getScore());
                System.out.println("添加学生成绩信息[学号：" + student.getId() + score.toString() + "]成功！");
            } else {
                System.out.println("学生成绩信息输入不全————\n" +
                        "\t 请重新输入：");
                addService("2.4");
            }
        } catch (SQLException e) {
            System.out.println("增添学生成绩信息失败！\n" + e.toString());
            addService("2.4");
        } finally {
            try {
                connection.close();
                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println("关闭连接失败！");
            } catch (NullPointerException e) {
            }
        }
    }

    public void updateService(String choice) {
        String sql = new SQL().getSQL(choice);
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
                System.out.println("选项输入错误————\n" +
                        "\t请重新输入：\n");
        }
    }

    public void updateStudent(String info, String sql) {
        Connect connect = new Connect();
        Connection connection = connect.getConnect();
        PreparedStatement preparedStatement = null;
        Student student = Tools.getStudentModel(info);
        try {
            if (!student.isEmpty()) {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(4, student.getId());
                preparedStatement.setString(1, student.getName());
                preparedStatement.setInt(2, student.getAge());
                preparedStatement.setString(3, student.getSex());
                preparedStatement.execute();
                System.out.println("修改学生信息[" + student.getStudentInfo() + "]成功！");
            } else {
                System.out.println("学生信息输入不全————\n" +
                        "\t 请重新输入：");
                updateService("3.1");
            }
        } catch (SQLException e) {
            System.out.println("修改学生信息失败！\n" + e.toString());
            updateService("3.1");
        } finally {
            try {
                connection.close();
                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println("关闭连接失败！");
            } catch (NullPointerException e) {
            }
        }
    }

    public void updateSubject(String info, String sql) {
        Connect connect = new Connect();
        Connection connection = connect.getConnect();
        PreparedStatement preparedStatement = null;
        Subject subject = Tools.getSubjectModel(info);
        try {
            if (!subject.isEmpty()) {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(3, subject.getId());
                preparedStatement.setString(1, subject.getName());
                preparedStatement.setString(2, subject.getDescribe());
                preparedStatement.execute();
                System.out.println("修改课程信息[" + subject.getName() + "]成功！");
            } else {
                System.out.println("课程信息输入不全————\n" +
                        "\t 请重新输入：");
                updateService("3.2");
            }
        } catch (SQLException e) {
            System.out.println("修改课程信息失败！\n" + e.toString());
            updateService("3.2");
        } finally {
            try {
                connection.close();
                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println("关闭连接失败！");
            } catch (NullPointerException e) {
            }
        }
    }

    public void updateTeacher(String info, String sql) {
        Connect connect = new Connect();
        Connection connection = connect.getConnect();
        PreparedStatement preparedStatement = null;
        Teacher teacher = Tools.getTeacherModel(info);
        try {
            if (!teacher.isEmpty()) {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(3, teacher.getId());
                preparedStatement.setString(1, teacher.getName());
                preparedStatement.setInt(2, teacher.getSubject_id());
                preparedStatement.execute();
                System.out.println("修改老师信息[" + teacher.getName() + "]成功！");
            } else {
                System.out.println("老师信息输入不全————\n" +
                        "\t 请重新输入：");
                updateService("3.3");
            }
        } catch (SQLException e) {
            System.out.println("修改老师信息失败！\n" + e.toString());
            updateService("3.3");
        } finally {
            try {
                connection.close();
                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println("关闭连接失败！");
            } catch (NullPointerException e) {
            }
        }
    }

    public void updateStudentScore(String info, String sql) {
        Connect connect = new Connect();
        Connection connection = connect.getConnect();
        PreparedStatement preparedStatement = null;
        Map<Student, Subject> studentSubjectMap = Tools.getStudentScoreModel(info);
        Student student = Tools.getScoreStudent(studentSubjectMap);
        Subject subject = studentSubjectMap.get(student);
        Score score = student.getScore();
        try {
            if (!subject.isEmpty() && !subject.isEmpty() && !score.isEmpty()) {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, subject.getId());
                preparedStatement.setInt(3, student.getId());
                preparedStatement.setFloat(2, score.getScore());
                preparedStatement.execute();
                System.out.println("修改学生成绩信息[学号：" + student.getId() + score.toString() + "]成功！");
            } else {
                System.out.println("学生成绩信息输入不全————\n" +
                        "\t 请重新输入：");
                updateService("3.4");
            }
        } catch (SQLException e) {
            System.out.println("修改学生成绩信息失败！\n" + e.toString());
            updateService("3.4");
        } finally {
            try {
                connection.close();
                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println("关闭连接失败！");
            } catch (NullPointerException e) {
            }
        }
    }

    public void deleteService(String choice) {
        String sql = new SQL().getSQL(choice);
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
                System.out.println("选项输入错误————\n" +
                        "\t请重新输入：\n");
        }
    }

    public void deleteStudent(String info, String sql) {
        Connect connect = new Connect();
        Connection connection = connect.getConnect();
        PreparedStatement preparedStatement = null;
        Student student = Tools.getStudentModel(info);
        try {
            if (!student.isEmpty()) {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, student.getId());
                preparedStatement.execute();
                System.out.println("删除学生信息[学号：" + student.getId() + "]成功！");
            } else {
                System.out.println("不匹配任何学生信息————\n" +
                        "\t请重新输入：");
                deleteService("4.1");
            }
        } catch (SQLException e) {
            System.out.println("删除学生信息失败！\n" + e.toString());
            deleteService("4.1");
        } finally {
            try {
                connection.close();
                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println("关闭连接失败！");
            } catch (NullPointerException e) {
            }
        }
    }

    public void deleteSubject(String info, String sql) {
        Connect connect = new Connect();
        Connection connection = connect.getConnect();
        PreparedStatement preparedStatement = null;
        Subject subject = Tools.getSubjectModel(info);
        try {
            if (!subject.isEmpty()) {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, subject.getId());
                preparedStatement.execute();
                System.out.println("删除课程信息[" + subject.getId() + "]成功！");
            } else {
                System.out.println("不匹配任何课程信息————\n" +
                        "\t请重新输入：");
                deleteService("4.2");
            }
        } catch (SQLException e) {
            System.out.println("删除课程信息失败！\n" + e.toString());
            deleteService("4.2");
        } finally {
            try {
                connection.close();
                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println("关闭连接失败！");
            } catch (NullPointerException e) {
            }
        }
    }

    public void deleteTeacher(String info, String sql) {
        Connect connect = new Connect();
        Connection connection = connect.getConnect();
        PreparedStatement preparedStatement = null;
        Teacher teacher = Tools.getTeacherModel(info);
        try {
            if (!teacher.isEmpty()) {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, teacher.getId());
                preparedStatement.execute();
                System.out.println("删除老师信息[" + teacher.getId() + "]成功！");
            } else {
                System.out.println("不匹配任何老师信息————\n" +
                        "\t请重新输入：");
                deleteService("4.3");
            }
        } catch (SQLException e) {
            System.out.println("删除老师信息失败！\n" + e.toString());
            deleteService("4.3");
        } finally {
            try {
                connection.close();
                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println("关闭连接失败！");
            } catch (NullPointerException e) {
            }
        }
    }
}

