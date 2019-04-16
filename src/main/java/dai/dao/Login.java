package main.java.dai.dao;

public class Login {
    private final String loginSql = "SELECT * FROM admin_account";
    public String getLoginSql() {
        return loginSql;
    }
}
