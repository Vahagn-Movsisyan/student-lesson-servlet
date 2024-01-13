package com.example.studentlessonservlet.servlet;

import com.example.studentlessonservlet.manager.StudentManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/deleteStudent")
public class DeleteStudentServlet extends HttpServlet {
    private final StudentManager studentManager = new StudentManager();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       studentManager.deleteStudent(Integer.parseInt(req.getParameter("id")));
       resp.sendRedirect("/students");
    }
}
