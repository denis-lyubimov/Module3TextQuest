package com.javarush.module3.textquest.gametext;

import com.javarush.module3.textquest.steps.Step;
import lombok.Getter;

import java.util.Map;

@Getter
public class GameEvents {

    private final Map<Step, String> gameEvents = Map.of(
            Step.Challenge, "Ты потерял память",
            Step.Captain_Bridge, "Ты принял вызов",
            Step.Say_Smth_About_Yourself, "Ты поднялся на мостик"
    );

    private final Map<Step, String> gameLosingEvents = Map.of(
            Step.Challenge, "Ты отклонил вызов",
            Step.Captain_Bridge, "Ты не пошел на переговоры",
            Step.Say_Smth_About_Yourself, "Твою ложь разоблачили"
    );

    private final Map<Step, String> gameWinningEvents = Map.of(
            Step.Challenge, "Тебя вернули домой",
            Step.Captain_Bridge, "Тебя вернули домой",
            Step.Say_Smth_About_Yourself, "Тебя вернули домой"
    );

    private GameEvents() {
    }

    private static class SingletonHolder {
        public static final GameEvents HOLDER_INSTANCE = new GameEvents();
    }

    public static GameEvents getInstance() {
        return GameEvents.SingletonHolder.HOLDER_INSTANCE;
    }

}
