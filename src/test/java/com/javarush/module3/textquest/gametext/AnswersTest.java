package com.javarush.module3.textquest.gametext;

import com.javarush.module3.textquest.steps.Step;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnswersTest {

    @Test
    @DisplayName("Should return correct answers")
    public void answersTest(){
        Answers answers = Answers.getInstance();

        assertEquals("Принять вызов",answers.getAnswersA().get(Step.Challenge));
        assertEquals("Отказаться подниматься на мостик",answers.getAnswersA().get(Step.Captain_Bridge));
        assertEquals("Рассказать правду",answers.getAnswersA().get(Step.Say_Smth_About_Yourself));

        assertEquals("Отклонить вызов",answers.getAnswersB().get(Step.Challenge));
        assertEquals("Подняться на мостик",answers.getAnswersB().get(Step.Captain_Bridge));
        assertEquals("Солгать о себе",answers.getAnswersB().get(Step.Say_Smth_About_Yourself));


    }
}
