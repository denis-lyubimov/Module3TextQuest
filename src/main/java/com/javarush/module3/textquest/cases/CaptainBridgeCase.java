package com.javarush.module3.textquest.cases;

import com.javarush.module3.textquest.steps.Step;

public class CaptainBridgeCase extends AbstractCase {

    public CaptainBridgeCase() {
        super(Step.Captain_Bridge);
    }

    @Override
    protected boolean getAnswerResult(String answer) {
        return answer.equals(answers[1]);
    }
}
