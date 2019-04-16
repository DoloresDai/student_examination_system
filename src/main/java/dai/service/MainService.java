package main.java.dai.service;

import main.java.dai.dao.Connect;
import main.java.dai.dao.LoginSQL;
import main.java.dai.tools.Tools;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainService {
    public MainService() {
        getLogin();
    }

    private void getLogin() {
        Connect connect = new Connect();
        Connection connection = connect.getConnect();
        Statement statement = connect.getStatement(connection);
        System.out.println("您好，欢迎登陆学生考试系统，请输入您的用户名和密码(用户名:密码)：\n" +
                "例如：张三:123");
        String systemIn = Tools.getScanner();
        ResultSet resultSet = connect.executeSQL(statement, new LoginSQL().getLoginSql());
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

    private void isLogin() {
        Tools.printMenu();
        choiceService(Tools.getScanner());
    }

    private void choiceService(String choice) {
        String one = choice.split("\\.")[0];
        switch (one) {
            case "1":
                new QueryService(choice);
                isLogin();
                break;
            case "2":
                new AddService(choice);
                isLogin();
                break;
            case "3":
                new UpdateService(choice);
                isLogin();
                break;
            case "4":
                new DeleteService(choice);
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
}

