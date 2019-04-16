package main.java.dai.dao;

import main.java.dai.tools.Tools;

public class SQL {
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
                sql = "Select * from student";
                break;
            case "1.1.2":
                sql = sql + " left join subject on score.subject_id = subject.id " + " LEFT OUTER JOIN student on score.student_id = student.id WHERE student.NAME='" + Tools.getScanner() + "'";
                break;
            case "1.1.3":
                sql = sql + " LEFT OUTER JOIN teacher on score.subject_id= teacher.subject_id " + " LEFT OUTER JOIN student on score.student_id = student.id " +
                        "WHERE teacher.NAME ='" + Tools.getScanner() + "'";
                break;
            case "1.1.4":
                sql = sql + " left join student on score.student_id = student.id " + " left outer join subject on subject.id = score.subject_id where subject.name = '" + Tools.getScanner() + "'";
                break;
        }
        return sql;
    }

    public String querySubjectSQL(String choice) {
        String sql = "select * from subject left join teacher on teacher.subject_id = subject.id";
        switch (choice) {
            case "1.2.1":
                return sql;
            case "1.2.2":
                sql = sql + " where subject.name = '" + Tools.getScanner() + "'";
                break;
            case "1.2.3":
                sql = sql + " where teacher.name = '" + Tools.getScanner() + "'";
                break;
        }
        return sql;
    }

    public String queryTeacherSQL(String choice) {
        String sql = "select * from teacher";
        switch (choice) {
            case "1.3.1":
                return sql;
            case "1.3.2":
                sql = sql + " where teacher.name = '" + Tools.getScanner() + "'";
                break;
        }
        return sql;
    }

    public String addInfoSQL(String choice) {
        String sql = "insert into ";
        switch (choice) {
            case "2.1":
                sql = sql + " student(id,name,age,sex) values(?,?,?,?)";
                break;
            case "2.2":
                sql = sql + " subject(id,name,content) values(?,?,?)";
                break;
            case "2.3":
                sql = sql + " teacher(id,name,subject_id) values(?,?,?)";
                break;
            case "2.4":
                sql = sql + " score(subject_id,student_id,score) values(?,?,?)";
                break;
        }
        return sql;
    }

    public String updateSQL(String choice) {
        String sql = "Update ";
        switch (choice) {
            case "3.1":
                sql = sql + "student set name = ? ,age =? ,sex =? where id = ?";
                break;
            case "3.2":
                sql = sql + "subject set name = ?,content = ? where id = ?";
                break;
            case "3.3":
                sql = sql + "teacher set name = ? ,subject_id = ? where id = ?";
                break;
            case "3.4":
                sql = sql + "score set subject_id = ?,score=? where student_id = ?";
        }
        return sql;
    }

    public String deleteSQL(String choice) {
        String sql = "Delete from ";
        switch (choice) {
            case "4.1":
                sql = sql + "student where id = ?";
                break;
            case "4.2":
                sql = sql + "subject where id = ?";
                break;
            case "4.3":
                sql = sql + "teacher where id = ?";
                break;
        }
        return sql;
    }
}
