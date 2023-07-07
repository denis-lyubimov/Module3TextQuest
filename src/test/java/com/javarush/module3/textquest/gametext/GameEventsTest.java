package com.javarush.module3.textquest.gametext;

import com.javarush.module3.textquest.steps.Step;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameEventsTest {

    @Test
    @DisplayName("should return correct event messages")
    public void gameEventsTest() {
        GameEvents gameEvents = GameEvents.getInstance();

        assertEquals("Ты потерял память", gameEvents.getGameEvents().get(Step.Challenge));
        assertEquals("Ты принял вызов", gameEvents.getGameEvents().get(Step.Captain_Bridge));
        assertEquals("Ты поднялся на мостик", gameEvents.getGameEvents().get(Step.Say_Smth_About_Yourself));

        assertEquals("Ты отклонил вызов", gameEvents.getGameLosingEvents().get(Step.Challenge));
        assertEquals("Ты не пошел на переговоры", gameEvents.getGameLosingEvents().get(Step.Captain_Bridge));
        assertEquals("Твою ложь разоблачили", gameEvents.getGameLosingEvents().get(Step.Say_Smth_About_Yourself));

        assertEquals("Тебя вернули домой", gameEvents.getGameWinningEvents().get(Step.Challenge));
        assertEquals("Тебя вернули домой", gameEvents.getGameWinningEvents().get(Step.Captain_Bridge));
        assertEquals("Тебя вернули домой", gameEvents.getGameWinningEvents().get(Step.Say_Smth_About_Yourself));


    }
}
