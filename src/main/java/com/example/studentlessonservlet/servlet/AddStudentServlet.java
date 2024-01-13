package com.example.studentlessonservlet.servlet;

import com.example.studentlessonservlet.manager.LessonManager;
import com.example.studentlessonservlet.manager.StudentManager;
import com.example.studentlessonservlet.model.Lesson;
import com.example.studentlessonservlet.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/addStudent")

public class AddStudentServlet extends HttpServlet {
    private final StudentManager studentManager = new StudentManager();
    private final LessonManager lessonManager = new LessonManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Lesson> lessons = lessonManager.getAllLessons();
        req.setAttribute("lessons", lessons);
        req.getRequestDispatcher("/addStudent.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("studentEmail");
        List<Lesson> lessons = lessonManager.getAllLessons();
        req.setAttribute("lessons", lessons);
        if (lessons != null) {
            if (studentManager.getStudentByEmail(email) == null) {
                try {
                    studentManager.addStudent(Student.builder()
                            .name(req.getParameter("studentName"))
                            .surname(req.getParameter("studentSurname"))
                            .email(email)
                            .age(Integer.parseInt(req.getParameter("studentAge")))
                            .lesson(lessonManager.getLessonById(Integer.parseInt(req.getParameter("studentId"))))
                            .build());
                    resp.sendRedirect("/students");
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input for age or lessonId");
                }
            } else {
                req.setAttribute("emailError", true);
                req.getRequestDispatcher("/addStudent.jsp").forward(req, resp);
            }
        } else {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving lessons");
        }
    }
}
