<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AddLesson</title>
    <link rel="stylesheet" href="../css/style.css">

</head>
<body class="body_addLessons">
<h1>Add new lesson</h1>
<form method="post" action="/addLesson" enctype="multipart/form-data">
    Name: <input type="text" name="lessonName"> <br>
    Duration: <input type="time" name="lessonDuration"><br>
    Lecturer Name: <input type="text" name="lessonLecturerName"><br>
    Price: <input type="number" name="lessonPrice"><br>
    <input type="submit" value="add">
</form>
</body>
</html>
