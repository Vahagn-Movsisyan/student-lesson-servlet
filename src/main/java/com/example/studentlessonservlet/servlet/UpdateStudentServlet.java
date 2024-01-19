package com.example.studentlessonservlet.servlet;

import com.example.studentlessonservlet.manager.LessonManager;
import com.example.studentlessonservlet.manager.StudentManager;
import com.example.studentlessonservlet.model.Lesson;
import com.example.studentlessonservlet.model.Student;
import com.example.studentlessonservlet.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/updateStudent")
@MultipartConfig(maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 10, fileSizeThreshold = 1024 * 1024 * 1)
public class UpdateStudentServlet extends HttpServlet {
    private final LessonManager lessonManager = new LessonManager();
    private final StudentManager studentManager = new StudentManager();
    private final String UPLOAD_DIRECTORY = "C:\\Users\\aperk\\IdeaProjects\\student-lesson-servlet\\uploadDirectory";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Student student = studentManager.getStudentById(Integer.parseInt(req.getParameter("id")));
        User user = (User) req.getSession().getAttribute("user");
        List<Lesson> lesson = lessonManager.getLessonsByUser(user.getId());
        req.setAttribute("lesson", lesson);
        req.setAttribute("student", student);
        req.getRequestDispatcher("/WEB-INF/updateStudent.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("studentEmail");
        if (studentManager.getStudentByEmail(email) == null) {
            try {
                Part picture = req.getPart("newPicture");
                String pictureName = null;
                if (picture != null && picture.getSize() > 0) {
                    pictureName = System.currentTimeMillis() + "_" + picture.getSubmittedFileName();
                    picture.write(UPLOAD_DIRECTORY + File.separator + pictureName);
                }
                studentManager.updateStudent(Student.builder()
                        .id(Integer.parseInt(req.getParameter("studentId")))
                        .picName(pictureName)
                        .name(req.getParameter("studentName"))
                        .surname(req.getParameter("studentSurname"))
                        .email(email)
                        .age(Integer.parseInt(req.getParameter("studentAge")))
                        .lesson(lessonManager.getLessonById(Integer.parseInt(req.getParameter("lessonId"))))
                        .build());
                resp.sendRedirect("/students");
            } catch (NumberFormatException e) {
                e.printStackTrace();
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input for age or lessonId");
            }
        } else {
            req.setAttribute("emailError", true);
            req.getRequestDispatcher("/WEB-INF/updateStudent.jsp").forward(req, resp);
        }
    }
}
