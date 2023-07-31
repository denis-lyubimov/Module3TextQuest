package com.javarush.module3.textquest.servlets;


import com.javarush.module3.textquest.cases.AbstractCase;
import com.javarush.module3.textquest.cases.CaptainBridgeCase;
import com.javarush.module3.textquest.cases.ChallengeCase;
import com.javarush.module3.textquest.cases.SaySmthAboutYourselfCase;
import com.javarush.module3.textquest.player.User;
import com.javarush.module3.textquest.steps.Step;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "GameServlet", value = "/game")
public class GameServlet extends HttpServlet {

    private Step initialStep = Step.Challenge;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession currentSession = req.getSession(true);

        User user = (User) currentSession.getAttribute("user");
        if (!validateUser(user, resp)) {
            return;
        }

        Step currentStep = (Step) currentSession.getAttribute("step");
        if (currentStep == null) {
            currentStep = initialStep;
            currentSession.setAttribute("step", currentStep);
            resp.sendRedirect("question.jsp");
            return;
        }

        String answerResult = req.getParameter("answer");
        if (!validateAnswer(answerResult, resp)) {
            return;
        }

        AbstractCase initalCase = new ChallengeCase();
        initalCase.setNextCase(new CaptainBridgeCase());
        initalCase.getNextCase().setNextCase(new SaySmthAboutYourselfCase());
        initalCase.checkStep(currentStep, answerResult, resp);

        if (currentStep != Step.values()[Step.values().length - 1]) {
            currentStep = Step.values()[currentStep.ordinal() + 1];
            currentSession.setAttribute("step", currentStep);
        }
    }

    private boolean validateAnswer(String answerResult, HttpServletResponse resp) {
        try {
            if (answerResult == null) {
                resp.sendError(400, "no answer value");
                return false;
            }
            if (answerResult.isBlank()) {
                resp.sendError(400, "answer is empty");
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    private boolean validateUser(User user, HttpServletResponse resp) {
        try {
            if (user == null) {
                resp.sendError(400, "no user value");
                return false;
            }
            if (user.getName() == null) {
                resp.sendError(400, "no username value");
                return false;
            } else if (user.getName().isBlank()) {
                resp.sendError(400, "username is empty");
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}

