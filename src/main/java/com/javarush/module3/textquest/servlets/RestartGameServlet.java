package com.javarush.module3.textquest.servlets;

import com.javarush.module3.textquest.steps.Step;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "RestartGameServlet", value = "/restart")
public class RestartGameServlet extends HttpServlet {
    private Step initialStep = Step.Challenge;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String restartReason = req.getParameter("reason");
        HttpSession currentSession = req.getSession(true);
        currentSession.setAttribute("step", initialStep);
        resp.sendRedirect("question.jsp");
    }
}
