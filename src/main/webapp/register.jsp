<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body class="body_register">
<div class="register-container">
    <h1>Register</h1>
    <form action="/register" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required>

        <%if (session.getAttribute("msg") != null) { %>
        <span style="color: red; font-weight: bold;">❗<%session.getAttribute("msg");%></span>
        <%session.removeAttribute("msg");%>
        <%}%>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required>

        <%if (session.getAttribute("msg") != null) { %>
        <span style="color: red; font-weight: bold;">❗<%session.getAttribute("msg");%></span>
        <%session.removeAttribute("msg");%>
        <%}%>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>

        <label for="confirmPassword">Confirm Password:</label>
        <input type="password" id="confirmPassword" name="confirmPassword" required>

        <input type="submit" value="Register">
    </form>
    <a class="a_default" href="index.jsp">Login here</a>
</div>
</body>
</html>
