<%@ page import="com.example.studentlessonservlet.model.Lesson" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Students</title>
    <link rel="stylesheet" href="css/style.css">
    <script src="js/checkEmail.js"></script>
</head>
<body class="body_addStudent">
<%
    Boolean emailError = (Boolean) request.getAttribute("emailError");
    List<Lesson> lessons = (List<Lesson>) request.getAttribute("lessons");
    if (emailError == null) {
        emailError = false;
    }
%>
<h1>Add new student</h1>
<form method="post" action="/addStudent">
    Name: <input type="text" name="studentName"> <br>
    Surname: <input type="text" name="studentSurname"> <br>
    Email: <input type="email" name="studentEmail" id="studentEmail"> <span id="emailError" style="display: <%= emailError ? "inline" : "none" %>; color: red; font-weight: bold;">❗</span> <br>
    Age: <input type="number" name="studentAge"> <br>
    <select name="studentId">
        <% for (Lesson lesson : lessons) { %>
        <option value="<%= lesson.getId() %>">
            <%= lesson.getName() %>
        </option>
        <% } %>
    </select>
    <input type="submit" value="add">
</form>
</body>
</html>
