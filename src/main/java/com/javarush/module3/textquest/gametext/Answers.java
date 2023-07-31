package com.javarush.module3.textquest.gametext;

import com.javarush.module3.textquest.steps.Step;
import lombok.Getter;

import java.util.Map;


@Getter
public class Answers {

    private final Map<Step, String> answersA = Map.of(
            Step.Challenge, "Принять вызов",
            Step.Captain_Bridge, "Отказаться подниматься на мостик",
            Step.Say_Smth_About_Yourself, "Рассказать правду"
    );

    private final Map<Step, String> answersB = Map.of(
            Step.Challenge, "Отклонить вызов",
            Step.Captain_Bridge, "Подняться на мостик",
            Step.Say_Smth_About_Yourself, "Солгать о себе"
    );

    private Answers() {
    }

    private static class SingletonHolder {
        public static final Answers HOLDER_INSTANCE = new Answers();
    }

    public static Answers getInstance() {
        return SingletonHolder.HOLDER_INSTANCE;
    }
}
