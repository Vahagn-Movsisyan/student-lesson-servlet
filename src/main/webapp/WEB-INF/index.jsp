<%@ page import="javax.swing.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body class="body_login">
<div class="login-container">
    <h1>Login</h1>
    <form action="/login" method="post">
        <%if (session.getAttribute("msg") != null) { %>
        <span style="color: red; font-weight: bold;">❗<%=session.getAttribute("msg")%></span>
        <%session.removeAttribute("msg");%>
        <%}%>
        <label for="email">Email:</label>
        <input type="text" id="email" name="email" required>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>

        <input type="submit" value="Login">
    </form>
    <a class="a_default" href="/register">Register here</a>
</div>
</body>
</html>
