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
        <th>Picture</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Email</th>
        <th>Age</th>
        <th>Lesson</th>
        <th>Update</th>
        <th>Delete</th>
    </tr>
    </thead>
    <tbody>
    <%
        for (Student student : students) {
    %>
    <tr>
        <td><%= student.getId() %></td>
        <th>
            <%if (student.getPicName() != null && !student.getPicName().isEmpty()) {%>
            <img src="/downloadImage?imageName=<%=student.getPicName()%>" width="50">
            <%} else {%>
            <img src="../userDefaultImg/user.png" width="50">
            <%}%>
        </th>
        <td><%= student.getName() %></td>
        <th><%=student.getSurname()%></th>
        <td><%= student.getEmail() %></td>
        <th><%=student.getAge()%></th>
        <td><%= student.getLesson().getName() %></td>
        <td><a class="update-button" href="/updateStudent?id=<%= student.getId() %>">Update</a></td>
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
