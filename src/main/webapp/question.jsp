<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.javarush.module3.textquest.gametext.Questions" %>
<%@ page import="com.javarush.module3.textquest.gametext.Answers" %>
<%@ page import="com.javarush.module3.textquest.gametext.GameEvents" %>
<%@ page import="com.javarush.module3.textquest.steps.Step" %>
<% Step step = (Step) session.getAttribute("step");
    if (step == null) {
        step = Step.Challenge;
        session.setAttribute("step", step);
    }
%>

<html>
<head>
    <title>Game step <% out.print(step);%></title>
    <link href="static/main.css" rel="stylesheet">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <script src="<c:url value="/static/jquery-3.6.0.min.js"/>"></script>

</head>

<body>
<div><%= GameEvents.getInstance().getGameEvents().get(step)%>
</div>
<div><%= Questions.getInstance().getQuestions().get(step)%>
</div>
<form action="/game" method="get">
    <div>
        <input type="radio" id="answerA" name="answer" value="answerA" required checked>
        <label for="answerA"><%= Answers.getInstance().getAnswersA().get(step)%>
        </label>
    </div>
    <div>
        <input type="radio" id="answerB" name="answer" value="answerB">
        <label for="answerB"><%= Answers.getInstance().getAnswersB().get(step)%>
        </label>
    </div>
    <button class="button button1" type="submit">Ответить</button>
</form>
</body>
</html>
<jsp:include page="templates/statistics.jsp"/>