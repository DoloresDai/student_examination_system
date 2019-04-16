package main.java.dai.dao;

import main.java.dai.tools.Tools;

public class ServiceSQL {
    public String getSQL(String choice) {
        String[] selectChoice = choice.split("\\.");
        switch (selectChoice[0]) {
            case "1":
                switch (selectChoice[1]) {
                    case "1":
                        return queryStudentSQL(choice);
                    case "2":
                        return querySubjectSQL(choice);
                    case "3":
                        return queryTeacherSQL(choice);
                }
                break;
            case "2":
                return addInfoSQL(choice);
            case "3":
                return updateSQL(choice);
            case "4":
                return deleteSQL(choice);
        }
        return "";
    }

    public String queryStudentSQL(String choice) {
        String sql = "SELECT * FROM score";
        switch (choice) {
            case "1.1.1":
                sql = "SELECT * FROM student";
                break;
            case "1.1.2":
                sql = sql + " LEFT JOIN SUBJECT ON score.subject_id = subject.id " +
                        " LEFT OUTER JOIN student ON score.student_id = student.id WHERE student.name ='"
                        + Tools.getScanner() + "'";
                break;
            case "1.1.3":
                sql = sql + " LEFT OUTER JOIN teacher ON score.subject_id= teacher.subject_id "
                        + " LEFT OUTER JOIN student ON score.student_id = student.id " +
                        "WHERE teacher.name ='" + Tools.getScanner() + "'";
                break;
            case "1.1.4":
                sql = sql + " LEFT JOIN student ON score.student_id = student.id "
                        + " LEFT OUTER JOIN SUBJECT ON subject.id = score.subject_id WHERE subject.name = '"
                        + Tools.getScanner() + "'";
                break;
        }
        return sql;
    }

    public String querySubjectSQL(String choice) {
        String sql = "SELECT * FROM SUBJECT LEFT JOIN teacher ON teacher.subject_id = subject.id";
        switch (choice) {
            case "1.2.1":
                return sql;
            case "1.2.2":
                sql = sql + " WHERE subject.name = '" + Tools.getScanner() + "'";
                break;
            case "1.2.3":
                sql = sql + " WHERE teacher.name = '" + Tools.getScanner() + "'";
                break;
        }
        return sql;
    }

    public String queryTeacherSQL(String choice) {
        String sql = "SELECT * FROM teacher";
        switch (choice) {
            case "1.3.1":
                return sql;
            case "1.3.2":
                sql = sql + " WHERE teacher.name = '" + Tools.getScanner() + "'";
                break;
        }
        return sql;
    }

    public String addInfoSQL(String choice) {
        String sql = "INSERT INTO ";
        switch (choice) {
            case "2.1":
                sql = sql + " student(id,name,age,sex) VALUES(?,?,?,?)";
                break;
            case "2.2":
                sql = sql + " subject(id,name,content) VALUES(?,?,?)";
                break;
            case "2.3":
                sql = sql + " teacher(id,name,subject_id) VALUES(?,?,?)";
                break;
            case "2.4":
                sql = sql + " score(subject_id,student_id,score) VALUES(?,?,?)";
                break;
        }
        return sql;
    }

    public String updateSQL(String choice) {
        String sql = "UPDATE ";
        switch (choice) {
            case "3.1":
                sql = sql + "student SET NAME = ? ,age =? ,sex =? WHERE id = ?";
                break;
            case "3.2":
                sql = sql + "SUBJECT SET NAME = ?,content = ? WHERE id = ?";
                break;
            case "3.3":
                sql = sql + "teacher SET NAME = ? ,subject_id = ? WHERE id = ?";
                break;
            case "3.4":
                sql = sql + "score SET subject_id = ?,score=? WHERE student_id = ?";
        }
        return sql;
    }

    public String deleteSQL(String choice) {
        String sql = "DELETE FROM ";
        switch (choice) {
            case "4.1":
                sql = sql + "student WHERE id = ?";
                break;
            case "4.2":
                sql = sql + "subject WHERE id = ?";
                break;
            case "4.3":
                sql = sql + "teacher WHERE id = ?";
                break;
        }
        return sql;
    }
}
