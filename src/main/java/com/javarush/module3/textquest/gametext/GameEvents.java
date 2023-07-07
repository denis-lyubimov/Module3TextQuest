package com.javarush.module3.textquest.gametext;

import com.javarush.module3.textquest.steps.Step;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class GameEvents {

    private final Map<Step, String> gameEvents = new HashMap<>() {{
        put(Step.Challenge, "Ты потерял память");
        put(Step.Captain_Bridge, "Ты принял вызов");
        put(Step.Say_Smth_About_Yourself, "Ты поднялся на мостик");
    }};

    private final Map<Step, String> gameLosingEvents = new HashMap<>() {{
        put(Step.Challenge, "Ты отклонил вызов");
        put(Step.Captain_Bridge, "Ты не пошел на переговоры");
        put(Step.Say_Smth_About_Yourself, "Твою ложь разоблачили");
    }};

    private final Map<Step, String> gameWinningEvents = new HashMap<>() {{
        put(Step.Challenge, "Тебя вернули домой");
        put(Step.Captain_Bridge, "Тебя вернули домой");
        put(Step.Say_Smth_About_Yourself, "Тебя вернули домой");
    }};

    private GameEvents(){
    }

    private static class SingletonHolder {
        public static final GameEvents HOLDER_INSTANCE = new GameEvents();
    }

    public static GameEvents getInstance() {
        return GameEvents.SingletonHolder.HOLDER_INSTANCE;
    }

}
