package com.javarush.module3.textquest.servlets;


import com.javarush.module3.textquest.player.User;
import com.javarush.module3.textquest.steps.Step;

import javax.servlet.ServletException;
import javax.servlet.SessionCookieConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(value = "/user", name = "UserServlet")
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("name");
        if (!validateUserName(userName, resp)) {
            return;
        }
        User user = new User(userName);
        HttpSession currentSession = req.getSession(true);
        currentSession.setAttribute("user", user);
        currentSession.setAttribute("step", null);
        resp.sendRedirect("/game");
    }

    private boolean validateUserName(String userName, HttpServletResponse resp) {
        try {
            if (userName == null) {
                resp.sendError(400, "no username");
                return false;
            }
            if (userName.isBlank()) {
                resp.sendError(400, "empty username");
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}

