<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Introduction</title>
    <link href="static/main.css" rel="stylesheet">
</head>
<div>
    Введите свое имя:
    <form action="/user" method="get">
        <div>
            <label>Имя</label>
            <input type="text" name="name" id="userName" required>
        </div>
        <button type="submit" class="button button1">Войти</button>
    </form>
</div>
</html>