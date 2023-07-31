package com.javarush.module3.textquest.cases;

import com.javarush.module3.textquest.steps.Step;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.ValueSource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class SaySmthAboutYourselfCaseTest {
    private SaySmthAboutYourselfCase saySmthAboutYourselfCase = new SaySmthAboutYourselfCase();


    @Test
    @DisplayName("should return experctedNextCase")
    public void setNextCaseRetrunsCorrectNextCase() {
        AbstractCase expectedNextCase = mock(AbstractCase.class);
        AbstractCase unexpectedNextCase = mock(AbstractCase.class);
        saySmthAboutYourselfCase.setNextCase(expectedNextCase);
        AbstractCase nextCase = saySmthAboutYourselfCase.getNextCase();
        saySmthAboutYourselfCase.setNextCase(unexpectedNextCase);
        nextCase = saySmthAboutYourselfCase.getNextCase();
        assertEquals(expectedNextCase, nextCase);
    }

    @Test
    @DisplayName("should retrun true on answerA, false — answerB")
    public void getAnswerResultTest() {
        assertTrue(saySmthAboutYourselfCase.getAnswerResult("answerA"));
        assertFalse(saySmthAboutYourselfCase.getAnswerResult("answerB"));
    }

    @Test
    @DisplayName("should retrun illegalArgumentException on blank answer")
    @ValueSource(strings = {"", " ", "       "})
    public void getBlankAnswerResultTest() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> saySmthAboutYourselfCase.getAnswerResult(" "));
        assertEquals("answer can not be blank", illegalArgumentException.getMessage());
    }

    @Test
    @DisplayName("should retrun NullPointerException on null answer")
    public void getNullAnswerResultTest() {
        NullPointerException nullPointerException = assertThrows(NullPointerException.class, () -> saySmthAboutYourselfCase.getAnswerResult(null));
        assertEquals("answer can not be null", nullPointerException.getMessage());
    }

    @Test
    @DisplayName("should check checkStep method calls redirectToJSP")
    public void checkStepTest() throws ServletException, IOException {
        HttpServletResponse response = mock(HttpServletResponse.class);
        SaySmthAboutYourselfCase saySmthAboutYourselfCaseMock = mock(SaySmthAboutYourselfCase.class);

        saySmthAboutYourselfCaseMock.step = Step.Challenge;
        saySmthAboutYourselfCaseMock.nextCase = new SaySmthAboutYourselfCase();
        saySmthAboutYourselfCaseMock.answers = new String[]{"answerA", "answerB"};


        doCallRealMethod().when(saySmthAboutYourselfCaseMock).setNextCase(new CaptainBridgeCase());
        doCallRealMethod().when(saySmthAboutYourselfCaseMock).checkStep(Step.Challenge, "answerA", response);
        doCallRealMethod().when(saySmthAboutYourselfCaseMock).getAnswerResult("answerA");

        saySmthAboutYourselfCaseMock.checkStep(Step.Challenge, "answerA", response);
        verify(saySmthAboutYourselfCaseMock, atLeastOnce()).redirectToJSP(true, response);

        doCallRealMethod().when(saySmthAboutYourselfCaseMock).checkStep(Step.Challenge, "answerB", response);

        saySmthAboutYourselfCaseMock.checkStep(Step.Challenge, "answerB", response);
        verify(saySmthAboutYourselfCaseMock, atLeastOnce()).redirectToJSP(false, response);
    }

    @Test
    @DisplayName("should check redirectToJSP method calls sendRedirect")
    public void redirectToJSPTest() throws IOException {
        HttpServletResponse response = mock(HttpServletResponse.class);
        SaySmthAboutYourselfCase saySmthAboutYourselfCaseMock = mock(SaySmthAboutYourselfCase.class);

        doCallRealMethod().when(saySmthAboutYourselfCaseMock).redirectToJSP(true, response);
        saySmthAboutYourselfCaseMock.redirectToJSP(true, response);
        verify(response, times(1)).sendRedirect("win.jsp");

        doCallRealMethod().when(saySmthAboutYourselfCaseMock).redirectToJSP(false, response);
        saySmthAboutYourselfCaseMock.redirectToJSP(false, response);
        verify(response, times(1)).sendRedirect("fail.jsp");
    }
}
