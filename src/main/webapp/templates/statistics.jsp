<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.javarush.module3.textquest.player.User" %>
<% User user = (User) session.getAttribute("user");%>

<div>
  <table style="width: 500px;">
    <tr>
      <td>Имя</td>
      <td><%=user.getName()%></td>
    </tr>
    <tr>
      <td>Количество игр</td>
      <td><%=user.getGameCounter()%></td>
    </tr>
    <tr>
      <td>IP адрес</td>
      <td><%=request.getRemoteAddr()%></td>
    </tr>
  </table>
</div>