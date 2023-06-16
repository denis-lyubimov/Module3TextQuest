package com.javarush.module3.textquest.cases;

import com.javarush.module3.textquest.steps.Step;

public class ChallengeCase extends AbstractCase{

    public ChallengeCase() {
        super(Step.Challenge);
    }

    @Override
    protected boolean getAnswerResult(String answer) {
        if (answer == null){
            throw new NullPointerException("answer can not be null");
        }
        if (answer.isBlank()){
            throw new IllegalArgumentException("answer can not be blank");
        }
        return answer.equals(answers[0]);
    }
}
