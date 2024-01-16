package com.example.studentlessonservlet;

import com.example.studentlessonservlet.manager.LessonManager;
import com.example.studentlessonservlet.manager.StudentManager;
import com.example.studentlessonservlet.model.Lesson;
import com.example.studentlessonservlet.model.Student;
import com.example.studentlessonservlet.util.TimeUtil;

import java.text.ParseException;

public class main {
    public static void main(String[] args) throws ParseException {
        LessonManager lessonManager = new LessonManager();
        StudentManager studentManager = new StudentManager();
        for (Student student : studentManager.getAllStudent()) {
            System.out.println(student);
        }

        System.out.println();
        studentManager.updateStudent(Student.builder()
                .id(15)
                .picName("5678_user.png")
                .name("studentName")
                .surname("studentSurname")
                .email("email")
                .age(17)
                .lesson(lessonManager.getLessonById((8)))
                .build());

        System.out.println();

        for (Student student : studentManager.getAllStudent()) {
            System.out.println(student);
        }


    }
}
