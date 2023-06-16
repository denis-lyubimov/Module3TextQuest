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

    public AbstractCase setNextCase(AbstractCase nextCase) {
        if (isNull(this.nextCase)) {
            this.nextCase = nextCase;
            return this.nextCase;
        }
        return this.nextCase;
    }

    protected abstract boolean getAnswerResult(String answer);

    public void checkStep(Step step, String answer, HttpServletResponse response) throws IOException {
        if (this.step == step) {
            redirectToJSP(getAnswerResult(answer), response);
        } else {
            nextCase.checkStep(step, answer, response);
        }
    }

    protected void redirectToJSP(Boolean answerResult, HttpServletResponse response) throws IOException {
        if (answerResult) {
            try {
                response.sendRedirect("question.jsp");
            } catch (IOException e) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);;
            }
        } else {
            try {
                response.sendRedirect("fail.jsp");
            } catch (IOException e) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);;
            }
        }
    }




}
