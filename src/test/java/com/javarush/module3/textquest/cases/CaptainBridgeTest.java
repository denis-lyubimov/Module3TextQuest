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

public class CaptainBridgeTest {

    private CaptainBridgeCase captainBridgeCase = new CaptainBridgeCase();


    @Test
    @DisplayName("should return experctedNextCase")
    public void setNextCaseRetrunsCorrectNextCase() {
        AbstractCase expectedNextCase = mock(AbstractCase.class);
        AbstractCase unexpectedNextCase = mock(AbstractCase.class);
        AbstractCase nextCase = captainBridgeCase.setNextCase(expectedNextCase);
        nextCase = captainBridgeCase.setNextCase(unexpectedNextCase);
        assertEquals(expectedNextCase, nextCase);
    }

    @Test
    @DisplayName("should retrun true on answerA, false — answerB")
    public void getAnswerResultTest() {
        assertTrue(captainBridgeCase.getAnswerResult("answerB"));
        assertFalse(captainBridgeCase.getAnswerResult("answerA"));
    }

    @Test
    @DisplayName("should retrun illegalArgumentException on blank answer")
    @ValueSource(strings = {"", " ", "       "})
    public void getBlankAnswerResultTest() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> captainBridgeCase.getAnswerResult(" "));
        assertEquals("answer can not be blank", illegalArgumentException.getMessage());
    }

    @Test
    @DisplayName("should retrun NullPointerException on null answer")
    public void getNullAnswerResultTest() {
        NullPointerException nullPointerException = assertThrows(NullPointerException.class, () -> captainBridgeCase.getAnswerResult(null));
        assertEquals("answer can not be null", nullPointerException.getMessage());
    }

    @Test
    @DisplayName("should check checkStep method calls redirectToJSP")
    public void checkStepTest() throws ServletException, IOException {
        HttpServletResponse response = mock(HttpServletResponse.class);
        CaptainBridgeCase captainBridgeCaseMock = mock(CaptainBridgeCase.class);

        captainBridgeCaseMock.step = Step.Challenge;
        captainBridgeCaseMock.nextCase = new SaySmthAboutYourselfCase();
        captainBridgeCaseMock.answers = new String[]{"answerA", "answerB"};


        doCallRealMethod().when(captainBridgeCaseMock).setNextCase(new CaptainBridgeCase());
        doCallRealMethod().when(captainBridgeCaseMock).checkStep(Step.Challenge, "answerB", response);
        doCallRealMethod().when(captainBridgeCaseMock).getAnswerResult("answerB");

        captainBridgeCaseMock.checkStep(Step.Challenge, "answerB", response);
        verify(captainBridgeCaseMock, atLeastOnce()).redirectToJSP(true, response);

        doCallRealMethod().when(captainBridgeCaseMock).checkStep(Step.Challenge, "answerA", response);

        captainBridgeCaseMock.checkStep(Step.Challenge, "answerA", response);
        verify(captainBridgeCaseMock, atLeastOnce()).redirectToJSP(false, response);
    }

    @Test
    @DisplayName("should check redirectToJSP method calls sendRedirect")
    public void redirectToJSPTest() throws IOException {
        HttpServletResponse response = mock(HttpServletResponse.class);
        CaptainBridgeCase captainBridgeCaseMock = mock(CaptainBridgeCase.class);

        doCallRealMethod().when(captainBridgeCaseMock).redirectToJSP(true, response);
        captainBridgeCaseMock.redirectToJSP(true, response);
        verify(response, times(1)).sendRedirect("question.jsp");

        doCallRealMethod().when(captainBridgeCaseMock).redirectToJSP(false, response);
        captainBridgeCaseMock.redirectToJSP(false, response);
        verify(response, times(1)).sendRedirect("fail.jsp");
    }

}
