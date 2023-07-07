package com.javarush.module3.textquest.gametext;

import com.javarush.module3.textquest.steps.Step;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuestionsTest {

    @Test
    @DisplayName("should return correct questions")
    public void questionsTest() {
        Questions questions = Questions.getInstance();

        assertEquals("Принять вызов НЛО?", questions.getQuestions().get(Step.Challenge));
        assertEquals("Подняться на мостик к капитану?", questions.getQuestions().get(Step.Captain_Bridge));
        assertEquals("Ты кто?", questions.getQuestions().get(Step.Say_Smth_About_Yourself));
    }
}
