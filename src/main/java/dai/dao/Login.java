package main.java.dai.dao;

public class Login {
    private final String loginSql = "SELECT * FROM admin";
    public String getLoginSql() {
        return loginSql;
    }
}
