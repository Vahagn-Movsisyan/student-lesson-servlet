package com.example.studentlessonservlet.servlet;

import com.example.studentlessonservlet.manager.LessonManager;
import com.example.studentlessonservlet.model.Lesson;
import com.example.studentlessonservlet.util.TimeUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;

@WebServlet(urlPatterns = "/addLesson")
@MultipartConfig (maxFileSize = 1024 * 1024 * 5, //5mb
        maxRequestSize = 1024 * 1024 * 10,
        fileSizeThreshold = 1024 * 1024 * 1)
public class AddLessonsServlet extends HttpServlet {
    private final LessonManager lessonManager = new LessonManager();
    private final String UPLOAD_DIRECTORY = "C:\\Users\\aperk\\IdeaProjects\\student-lesson-servlet\\downloadImage";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/addLesson.jsp").forward(req, resp);
        if (req.getAttribute("emailError") == null) {
            req.setAttribute("emailError", false);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Part picture = req.getPart("pictureName");
            String pictureName = null;
            if (picture != null && picture.getSize() > 0) {
                pictureName = System.currentTimeMillis() + "_" + picture.getSubmittedFileName();
                picture.write(UPLOAD_DIRECTORY + File.separator + pictureName);
            }
            lessonManager.addLesson(Lesson.builder()
                    .name(req.getParameter("lessonName"))
                    .duration(TimeUtil.stringToTime(req.getParameter("lessonDuration")))
                    .lecturerName(req.getParameter("lessonLecturerName"))
                    .price(Double.parseDouble(req.getParameter("lessonPrice")))
                    .picName(pictureName)
                    .build());
            resp.sendRedirect("/lessons");
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }
}
