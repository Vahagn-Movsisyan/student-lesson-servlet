<%@ page import="com.example.studentlessonservlet.model.Student" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Students</title>
    <link rel="stylesheet" href="../css/style.css">

</head>
<body class="body_students">
<%
    List<Student> students = (List<Student>) request.getAttribute("students");
%>

<h1>Students</h1>
<a href="/addStudent">Add</a>

<%
    if (students != null && !students.isEmpty()) {
%>

<table>
    <thead>
    <tr>
        <th>Student Id</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Email</th>
        <th>Age</th>
        <th>Lesson</th>
        <th>Delete</th>
    </tr>
    </thead>
    <tbody>
    <%
        for (Student student : students) {
    %>
    <tr>
        <td><%= student.getId() %></td>
        <td><%= student.getName() %></td>
        <th><%=student.getSurname()%></th>
        <td><%= student.getEmail() %></td>
        <th><%=student.getAge()%></th>
        <td><%= student.getLesson().getName() %></td>
        <td><a class="remove-button" href="/deleteStudent?id=<%= student.getId() %>">Remove</a></td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>

<%
} else {
%>
<p>No employees found.</p>
<%
    }
%>
</body>
</html>
