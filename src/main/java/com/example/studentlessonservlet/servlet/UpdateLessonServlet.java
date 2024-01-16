package com.example.studentlessonservlet.servlet;

import com.example.studentlessonservlet.manager.LessonManager;
import com.example.studentlessonservlet.model.Lesson;
import com.example.studentlessonservlet.util.TimeUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

@WebServlet(urlPatterns = "/updateLesson")

public class UpdateLessonServlet extends HttpServlet {
    private final LessonManager lessonManager = new LessonManager();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Lesson id = lessonManager.getLessonById(Integer.parseInt(req.getParameter("id")));
        req.setAttribute("lesson", id);
        req.getRequestDispatcher("/WEB-INF/updateLesson.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            lessonManager.updateLesson(Lesson.builder()
                    .id(Integer.parseInt(req.getParameter("lessonId")))
                    .name(req.getParameter("lessonName"))
                    .duration(TimeUtil.stringToTime(req.getParameter("lessonDuration")))
                    .lecturerName(req.getParameter("lessonLecturerName"))
                    .price(Double.parseDouble(req.getParameter("lessonPrice")))
                    .build());
            resp.sendRedirect("/lessons");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
