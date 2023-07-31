package com.javarush.module3.textquest.cases;

import com.javarush.module3.textquest.steps.Step;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static java.util.Objects.isNull;

public abstract class AbstractCase {

    protected String[] answers = {"answerA", "answerB"};
    protected Step step;
    protected AbstractCase nextCase;

    public AbstractCase(Step step) {
        this.step = step;
    }

    public void setNextCase(AbstractCase nextCase) {
        if (isNull(this.nextCase)) {
            this.nextCase = nextCase;
        }
    }

    public AbstractCase getNextCase() {
        return this.nextCase;
    }

    protected abstract boolean getAnswerResult(String answer);

    public void checkStep(Step step, String answer, HttpServletResponse response) throws IOException {
        if (this.step.equals(step)) {
            redirectToJSP(getAnswerResult(answer), response);
        } else {
            nextCase.checkStep(step, answer, response);
        }
    }

    protected void redirectToJSP(Boolean answerResult, HttpServletResponse response) {
        try {
            if (answerResult) {
                response.sendRedirect("question.jsp");
            } else {
                response.sendRedirect("fail.jsp");
            }
        }catch (IOException e){
            try {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            } catch (IOException ex) {
                e.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}
