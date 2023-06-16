package com.javarush.module3.textquest.servlets;


import com.javarush.module3.textquest.player.User;

import javax.servlet.ServletException;
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
        if ( userName == null ){
            resp.sendError(400,"no username");
            return;
        }
        if ( userName.isBlank()){
            resp.sendError(400,"empty username");
            return;
        }

        User user = new User(userName);
        HttpSession currentSession = req.getSession(true);
        currentSession.setAttribute("user", user);
        resp.sendRedirect("/game");
    }
}

