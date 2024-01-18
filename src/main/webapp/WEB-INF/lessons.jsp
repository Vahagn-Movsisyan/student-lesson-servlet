<%@ page import="com.example.studentlessonservlet.model.Lesson" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lesson</title>
    <link rel="stylesheet" href="../css/style.css">

</head>
<body class="body_lessons">
<%
    List<Lesson> lessons = (List<Lesson>) request.getAttribute("lessons");
%>

<h1>Lessons</h1>
<a href="/addLesson">Add</a>

<%
    if (lessons != null && !lessons.isEmpty()) {
%>

<table>
    <thead>
    <tr>
        <th>Lesson Id</th>
        <th>Name</th>
        <th>Duration</th>
        <th>Lecturer</th>
        <th>Price</th>
        <th>User Added</th>
        <th>Update</th>
        <th>Delete</th>
    </tr>
    </thead>
    <tbody>
    <%
        for (Lesson lesson : lessons) {
    %>
    <tr>
        <td><%= lesson.getId() %></td>
        <td><%= lesson.getName() %></td>
        <th><%=lesson.getDuration()%></th>
        <td><%= lesson.getLecturerName() %></td>
        <th><%=lesson.getPrice()%></th>
        <th><%=lesson.getUser().getUsername()%></th>
        <th><a class="update-button" href="/updateLesson?id=<%=lesson.getId()%>">Update</a></th>
        <td><a class="remove-button" href="/deleteLesson?id=<%= lesson.getId() %>">Remove</a></td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>

<%
} else {
%>
<p>No Lessons found.</p>
<%
    }
%>
</body>
</html>
