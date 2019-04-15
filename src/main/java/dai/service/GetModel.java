package main.java.dai.service;

import main.java.dai.model.Student;
import main.java.dai.model.Subject;
import main.java.dai.model.Teacher;

import main.java.dai.model.Score;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GetModel {
    public Student getStudent(ResultSet resultSet) {
        Student student = new Student();
        try {
            student.setId(resultSet.getInt("student.id"));
            student.setName(resultSet.getString("student.name"));
            student.setAge(resultSet.getInt("student.age"));
            student.setSex(resultSet.getString("student.sex"));
            student.setScore(getScore(resultSet));
            return student;
        } catch (SQLException e) {
            System.out.println("获取Student失败！");
        }
        return student;
    }

    public Teacher getTeacher(ResultSet resultSet) {
        Teacher teacher = new Teacher();
        try {
            teacher.setId(resultSet.getInt("teacher.id"));
            teacher.setTeacher(resultSet.getString("teacher.name"));
            return teacher;
        } catch (SQLException e) {
            System.out.println("获取Teacher失败！");
        }
        return teacher;
    }

    public Subject getSubject(ResultSet resultSet) {
        Subject subject = new Subject();
        try {
            subject.setId(resultSet.getInt("subject.id"));
            subject.setName(resultSet.getString("subject.name"));
            subject.setDescribe(resultSet.getString("subject.describe"));
            return subject;
        } catch (SQLException e) {
            System.out.println("获取Subject失败！");
        }
        return subject;
    }

    public Score getScore(ResultSet resultSet) {
        Score score = new Score();
        try {
            score.setScore(resultSet.getFloat("score.score"));
            return score;
        } catch (SQLException e) {
            System.out.println("获取Score失败！");
        }
        return score;
    }
}

