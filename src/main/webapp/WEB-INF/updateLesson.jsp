<%@ page import="com.example.studentlessonservlet.model.Lesson" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Lesson</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body class="body_addLessons">
<% Lesson lesson = (Lesson) request.getAttribute("lesson"); %>
<h1>Update Lesson</h1>
<form method="post" action="/updateLesson">
    <input type="hidden" name="lessonId" value="<%= lesson.getId()%>">
    Name: <input type="text" name="lessonName" value="<%= lesson.getName()%>"> <br>
    Duration: <input type="time" name="lessonDuration" value="<%= lesson.getDuration() %>"><br>
    Lecturer Name: <input type="text" name="lessonLecturerName" value="<%= lesson.getLecturerName() %>"><br>
    Price: <input type="number" name="lessonPrice" value="<%= lesson.getPrice() %>"><br>
    <br>
    <input type="hidden" name="lessonId" value="<%= lesson.getId() %>">
    <input type="submit" value="Update">
</form>
</body>
</html>
