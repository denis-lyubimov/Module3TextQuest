package com.javarush.module3.textquest.gametext;

import com.javarush.module3.textquest.steps.Step;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;


@Getter
public class Answers {

    private final Map<Step, String> answersA = new HashMap<>() {{
        put(Step.Challenge, "Принять вызов");
        put(Step.Captain_Bridge, "Отказаться подниматься на мостик");
        put(Step.Say_Smth_About_Yourself, "Рассказать правду");
    }};

    private final Map<Step, String> answersB = new HashMap<>() {{
        put(Step.Challenge, "Отклонить вызов");
        put(Step.Captain_Bridge, "подняться на мостик");
        put(Step.Say_Smth_About_Yourself, "Солгать о себе");
    }};


    private Answers(){
    }

    private static class SingletonHolder {
        public static final Answers HOLDER_INSTANCE = new Answers();
    }

    public static Answers getInstance() {
        return SingletonHolder.HOLDER_INSTANCE;
    }
}
