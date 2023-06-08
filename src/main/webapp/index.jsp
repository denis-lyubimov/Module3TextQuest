<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>start quest</title>
    <link href="static/main.css" rel="stylesheet">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <script src="<c:url value="/static/jquery-3.6.0.min.js"/>"></script>
</head>
<body>
<h2>Привет!</h2>
<h3>Это приложение текстового квеста по
    <a  href="https://javarush.com/quests/lectures/jru.module3.lecture04">задаче</a>
    javarush<br>
    Чотобы начать нажми на кнопку
    <button class="button button1" onclick="window.location='/introduction.jsp'">старт</button>
</h3>

</body>
</html>
