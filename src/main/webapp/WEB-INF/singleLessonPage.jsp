<%@ page import="com.example.studentlessonservlet.model.Lesson" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%Lesson lesson = (Lesson) request.getAttribute("lesson");%>
    <title><%=lesson.getName()%></title>
</head>
<body>

</body>
</html>
