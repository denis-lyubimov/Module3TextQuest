package com.javarush.module3.textquest.cases;

import com.javarush.module3.textquest.steps.Step;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SaySmthAboutYourselfCase extends AbstractCase{

    public SaySmthAboutYourselfCase() {
        super(Step.Say_Smth_About_Yourself);
    }

    @Override
    protected boolean getAnswerResult(String answer) {
        return answer.equals(answers[0]);
    }
    @Override
    protected void redirectToJSP(Boolean answerResult, HttpServletResponse response){
         if (answerResult ) {
            try {
                response.sendRedirect("win.jsp");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                response.sendRedirect("fail.jsp");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
