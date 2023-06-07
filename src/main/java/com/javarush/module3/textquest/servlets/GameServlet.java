package com.javarush.module3.textquest.servlets;


import com.javarush.module3.textquest.cases.AbstractCase;
import com.javarush.module3.textquest.cases.CaptainBridgeCase;
import com.javarush.module3.textquest.cases.ChallengeCase;
import com.javarush.module3.textquest.cases.SaySmthAboutYourselfCase;
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
    private int gameNumber;
    private int playerName;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession currentSession = req.getSession(true);

        Step currentStep = (Step) currentSession.getAttribute("step");
        if (currentStep == null){
            currentStep = initialStep;
            currentSession.setAttribute("step", currentStep);
            resp.sendRedirect("question.jsp");
            return;
        }

        String answerResult = req.getParameter("answer");
        System.out.println("answer = " + answerResult );
        if ( answerResult == null){
            resp.sendRedirect("question.jsp");
            return;
        }


        AbstractCase initalCase = new ChallengeCase();
        initalCase.setNextCase(new CaptainBridgeCase()).setNextCase(new SaySmthAboutYourselfCase());
        initalCase.checkStep(currentStep, answerResult, resp);

        if (currentStep != Step.values()[Step.values().length - 1] ) {
            currentStep = Step.values()[currentStep.ordinal() + 1];
            currentSession.setAttribute("step", currentStep);
        }
    }
}
