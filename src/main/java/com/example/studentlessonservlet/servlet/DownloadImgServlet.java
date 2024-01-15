package com.example.studentlessonservlet.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet(urlPatterns = "/downloadImage")
public class DownloadImgServlet extends HttpServlet {
    private static final String UPLOAD_DIRECTORY = "C:\\Users\\aperk\\IdeaProjects\\student-lesson-servlet\\downloadImage";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String imageName = req.getParameter("pictureName");

        // читает входной файл из абсолютного пути
        File imageFile = new File(UPLOAD_DIRECTORY, imageName);
        if (imageFile.exists()) {
            try (FileInputStream inStream = new FileInputStream(imageFile)) {
                // изменяет ответ
                resp.setContentType("image/jpeg");
                resp.setContentLength((int) imageFile.length());

                // получает выходной поток ответа
                OutputStream outStream = resp.getOutputStream();

                byte[] buffer = new byte[4096];
                int bytesRead = -1;

                while ((bytesRead = inStream.read(buffer)) != -1) {
                    outStream.write(buffer, 0, bytesRead);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
