package main.java.dai.dao;

import java.sql.*;

public class Connect {
    private static final String URL = "jdbc:mysql://localhost:3306/student_examination_system";
    private static final String NAME = "root";
    private static final String PASSWORD = "abc960101";
    private static final String DRIVER = "com.mysql.jdbc.Driver";

    public Connection getConnect() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("数据库驱动不存在！");
        }
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, NAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("数据库连接失败！");
        }
        return conn;
    }

    public Statement getStatement(Connection conn) {
        Statement statement = null;
        try {
            statement = conn.createStatement();
        } catch (SQLException e) {
            System.out.println("创建Statement失败！");
        }
        return statement;
    }

    public PreparedStatement getPreparedStatement(Connection conn, String sql) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
        } catch (SQLException e) {
            System.out.println("创建PrepareStatement失败！");
        }
        return preparedStatement;
    }

    public ResultSet executeSQL(Statement statement, String sql) {
        ResultSet rs = null;
        try {
            rs = statement.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("SQL语句错误！");
        }
        return rs;
    }

    public void closeConnect(ResultSet resultSet, Statement statement, Connection connection) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                System.out.println("关闭ResultSet异常!");
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("关闭Statement异常！");
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("关闭Connection异常！");
            }
        }
    }

    public void closePreparedConnect(PreparedStatement preparedStatement, Connection connection) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println("关闭PreparedStatement异常！");
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("关闭Connection异常！");
            }
        }
    }
}
