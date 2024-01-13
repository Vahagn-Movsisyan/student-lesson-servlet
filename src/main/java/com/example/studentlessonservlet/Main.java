package com.example.studentlessonservlet;

import com.example.studentlessonservlet.manager.StudentManager;
import com.example.studentlessonservlet.model.Lesson;
import com.example.studentlessonservlet.model.Student;

public class Main {
    public static void main(String[] args) {
        StudentManager studentManager = new StudentManager();
        System.out.println(studentManager.getStudentByEmail("poxos@mail.com"));
    }
}
