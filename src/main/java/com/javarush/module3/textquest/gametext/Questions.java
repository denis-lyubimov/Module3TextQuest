package com.javarush.module3.textquest.gametext;

import com.javarush.module3.textquest.steps.Step;
import lombok.Getter;

import java.util.Map;

@Getter
public class Questions {

    private final Map<Step, String> questions = Map.of(
            Step.Challenge, "Принять вызов НЛО?",
            Step.Captain_Bridge, "Подняться на мостик к капитану?",
            Step.Say_Smth_About_Yourself, "Ты кто?"
    );


    private Questions() {
    }

    private static class SingletonHolder {
        public static final Questions HOLDER_INSTANCE = new Questions();
    }

    public static Questions getInstance() {
        return Questions.SingletonHolder.HOLDER_INSTANCE;
    }
}
