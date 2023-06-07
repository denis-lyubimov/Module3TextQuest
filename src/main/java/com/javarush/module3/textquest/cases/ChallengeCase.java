package com.javarush.module3.textquest.cases;

import com.javarush.module3.textquest.steps.Step;

public class ChallengeCase extends AbstractCase{

    public ChallengeCase() {
        super(Step.Challenge);
    }

    @Override
    protected boolean getAnswerResult(String answer) {
        return answer.equals(answers[0]);
    }
}
