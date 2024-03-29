package com.example.studentlessonservlet.servlet;

import com.example.studentlessonservlet.manager.LessonManager;
import com.example.studentlessonservlet.model.Lesson;
import com.example.studentlessonservlet.model.User;
import com.example.studentlessonservlet.util.TimeUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

@WebServlet(urlPatterns = "/addLesson")

public class AddLessonsServlet extends HttpServlet {
    private final LessonManager lessonManager = new LessonManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/addLesson.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String lessonName = req.getParameter("lessonName");
        if (lessonManager.getLessonByName(lessonName) == null) {
            try {
                lessonManager.addLesson(Lesson.builder()
                        .name(lessonName)
                        .duration(TimeUtil.stringToTime(req.getParameter("lessonDuration")))
                        .lecturerName(req.getParameter("lessonLecturerName"))
                        .price(Double.parseDouble(req.getParameter("lessonPrice")))
                        .user((User) req.getSession().getAttribute("user"))
                        .build());
                resp.sendRedirect("/lessons");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            req.getSession().setAttribute("msg", lessonName + " is already existed");
            req.getRequestDispatcher("/WEB-INF/addLesson.jsp").forward(req, resp);
        }
    }
}
