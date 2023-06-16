package com.javarush.module3.textquest.servlets;

import com.javarush.module3.textquest.cases.AbstractCase;
import com.javarush.module3.textquest.cases.CaptainBridgeCase;
import com.javarush.module3.textquest.cases.ChallengeCase;
import com.javarush.module3.textquest.cases.SaySmthAboutYourselfCase;
import com.javarush.module3.textquest.player.User;
import com.javarush.module3.textquest.steps.Step;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class GameServletTest {

    @Test
    @DisplayName("should verify all checks of doGet method")
    public void doGetTest() throws ServletException, IOException {
        final GameServlet servlet = new GameServlet();

        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final HttpSession session = mock(HttpSession.class);

        when(request.getSession(true)).thenReturn(session);
        when(session.getAttribute("user")).thenReturn( null);

        servlet.doGet(request,response);
        verify(request, times(1)).getSession(true);
        verify(session, times(1)).getAttribute("user");
        verify(response, times(1)).sendError(400,"no user");

        when(session.getAttribute("user")).thenReturn(new User(null));
        servlet.doGet(request,response);
        verify(response, times(1)).sendError(400, "no username");

        when(session.getAttribute("user")).thenReturn(new User(""));
        servlet.doGet(request,response);
        verify(response, times(1)).sendError(400, "empty username");

        when(session.getAttribute("user")).thenReturn(new User("user"));
        when(session.getAttribute("step")).thenReturn(null);
        servlet.doGet(request,response);
        verify(session, times(1)).setAttribute("step", Step.Challenge);
        verify(response, times(1)).sendRedirect("question.jsp");

        when(session.getAttribute("step")).thenReturn(Step.Challenge);
        when(request.getParameter("answer")).thenReturn(null);
        servlet.doGet(request,response);
        verify(response, times(1)).sendError(400, "no answer value");

        when(request.getParameter("answer")).thenReturn("");
        servlet.doGet(request,response);
        verify(response, times(1)).sendError(400, "answer is empty");

        when(request.getParameter("answer")).thenReturn("answer");
        servlet.doGet(request,response);
        verify(session).setAttribute("step", Step.Captain_Bridge);

    }
}
