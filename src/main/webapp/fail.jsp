<%@ page contentType="text/html;charset=UTF-8" %>
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
    <title>Fail step <% out.print(step);%></title>
    <link href="static/main.css" rel="stylesheet">
<%--    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
</head>
<body>
<div><%= GameEvents.getInstance().getGameLosingEvents().get(step)%>
</div>
<div>ПОРАЖЕНИЕ!
</div>
<button class="button button1" onclick="window.location='/restart?reason=fail'">начать с начала</button>
</form>
</body>
</html>
<jsp:include page="templates/statistics.jsp"/>