package com.javarush.module3.textquest.cases;

import com.javarush.module3.textquest.steps.Step;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SaySmthAboutYourselfCase extends AbstractCase {

    public SaySmthAboutYourselfCase() {
        super(Step.Say_Smth_About_Yourself);
    }

    @Override
    protected boolean getAnswerResult(String answer) {
        if (answer == null) {
            throw new NullPointerException("answer can not be null");
        }
        if (answer.isBlank()) {
            throw new IllegalArgumentException("answer can not be blank");
        }
        return answer.equals(answers[0]);
    }

    @Override
    protected void redirectToJSP(Boolean answerResult, HttpServletResponse response) {
        try {
            if (answerResult) {
                response.sendRedirect("win.jsp");
            } else {
                response.sendRedirect("fail.jsp");
            }
        } catch (IOException e){
            try {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            } catch (IOException ex) {
                e.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}
