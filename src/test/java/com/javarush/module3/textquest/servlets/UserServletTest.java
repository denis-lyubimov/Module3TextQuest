package com.javarush.module3.textquest.servlets;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class UserServletTest {

    @Test
    @DisplayName("should verify all checks of doGet method")
    public void doGetTest() throws ServletException, IOException {
        final UserServlet servlet = new UserServlet();

        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final HttpSession session = mock(HttpSession.class);

        when(request.getSession(true)).thenReturn(session);
        when(request.getParameter("name")).thenReturn(null);
        servlet.doGet(request, response);
        verify(response).sendError(400,"no username");

        when(request.getParameter("name")).thenReturn("");
        servlet.doGet(request, response);
        verify(response).sendError(400,"empty username");

        when(request.getParameter("name")).thenReturn("username");
        servlet.doGet(request, response);
        verify(request).getSession(true);
        verify(response).sendRedirect("/game");
    }
}
