package com.javarush.module3.textquest.servlets;

import com.javarush.module3.textquest.player.User;
import com.javarush.module3.textquest.steps.Step;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class RestartGameServletTest {

    @Test
    @DisplayName("should check actions of doGet method")
    public void doGetTest() throws ServletException, IOException {
        final RestartGameServlet servlet = new RestartGameServlet();

        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final HttpSession session = mock(HttpSession.class);

        when(request.getSession(true)).thenReturn(session);
        when(session.getAttribute("user")).thenReturn( new User("user"));
        servlet.doGet(request,response);
        verify(request, times(1)).getParameter("reason");
        verify(session, times(1)).setAttribute("step", Step.Challenge);
        verify(session, times(1)).getAttribute("user");
        verify(response, times(1)).sendRedirect("question.jsp");



    }
}
