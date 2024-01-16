<%@ page import="com.example.studentlessonservlet.model.Lesson" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.studentlessonservlet.model.Student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Student</title>
    <link rel="stylesheet" href="../css/style.css">
    <script src="../js/checkEmail.js"></script>
</head>
<body class="body_addStudent">
<%
    Boolean emailError = (Boolean) request.getAttribute("emailError");
    List<Lesson> lessons = (List<Lesson>) request.getAttribute("lesson");
    Student student = (Student) request.getAttribute("student");
    if (emailError == null) {
        emailError = false;
    }
%>
<h1>Update new student</h1> <br>

<h2>Current Picture:</h2>
<% if (student.getPicName() != null && !student.getPicName().isEmpty()) { %>
<img src="/downloadImage?imageName=<%=student.getPicName()%>" width="100">
<% } else { %>
<p>No picture available.</p>
<% } %>

<form method="post" action="/addStudent" enctype="multipart/form-data">
    Name: <input type="text" name="studentName"> <br>
    Surname: <input type="text" name="studentSurname"> <br>
    Email: <input type="email" name="studentEmail" id="studentEmail"> <span id="emailError" style="display: <%= emailError ? "inline" : "none" %>; color: red; font-weight: bold;">❗Email already existed</span> <br>
    Age: <input type="number" name="studentAge"> <br>
    <select name="studentId">
        <% for (Lesson lesson : lessons) { %>
        <option value="<%= lesson.getId() %>">
            <%= student.getLesson().getName() %>
        </option>
        <% } %>
    </select> <br>

    <input type="file" name="newPicture">
    <input type="submit" value="update">
</form>
</body>
</html>
