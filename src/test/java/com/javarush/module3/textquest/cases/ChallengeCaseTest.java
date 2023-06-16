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

public class ChallengeCaseTest {
    private ChallengeCase challengeCase = new ChallengeCase();


    @Test
    @DisplayName("should return experctedNextCase")
    public void setNextCaseRetrunsCorrectNextCase() {
        AbstractCase expectedNextCase = mock(AbstractCase.class);
        AbstractCase unexpectedNextCase = mock(AbstractCase.class);
        AbstractCase nextCase = challengeCase.setNextCase(expectedNextCase);
        nextCase = challengeCase.setNextCase(unexpectedNextCase);
        assertEquals(expectedNextCase, nextCase);
    }

    @Test
    @DisplayName("should retrun true on answerA, false — answerB")
    public void getAnswerResultTest() {
        assertTrue(challengeCase.getAnswerResult("answerA"));
        assertFalse(challengeCase.getAnswerResult("answerB"));
    }

    @Test
    @DisplayName("should retrun illegalArgumentException on blank answer")
    @ValueSource(strings = {"", " ", "       "})
    public void getBlankAnswerResultTest() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> challengeCase.getAnswerResult(" "));
        assertEquals("answer can not be blank", illegalArgumentException.getMessage());
    }

    @Test
    @DisplayName("should retrun NullPointerException on null answer")
    public void getNullAnswerResultTest() {
        NullPointerException nullPointerException = assertThrows(NullPointerException.class, () -> challengeCase.getAnswerResult(null));
        assertEquals("answer can not be null", nullPointerException.getMessage());
    }

    @Test
    @DisplayName("should check checkStep method calls redirectToJSP")
    public void checkStepTest() throws ServletException, IOException {
        HttpServletResponse response = mock(HttpServletResponse.class);
        ChallengeCase challengeCaseMock = mock(ChallengeCase.class);

        challengeCaseMock.step = Step.Challenge;
        challengeCaseMock.nextCase = new CaptainBridgeCase();
        challengeCaseMock.answers = new String[]{"answerA", "answerB"};


        doCallRealMethod().when(challengeCaseMock).setNextCase(new CaptainBridgeCase());
        doCallRealMethod().when(challengeCaseMock).checkStep(Step.Challenge, "answerA", response);
        doCallRealMethod().when(challengeCaseMock).getAnswerResult("answerA");

        challengeCaseMock.checkStep(Step.Challenge, "answerA", response);
        verify(challengeCaseMock, atLeastOnce()).redirectToJSP(true, response);

        doCallRealMethod().when(challengeCaseMock).checkStep(Step.Challenge, "answerB", response);

        challengeCaseMock.checkStep(Step.Challenge, "answerB", response);
        verify(challengeCaseMock, atLeastOnce()).redirectToJSP(false, response);
    }

    @Test
    @DisplayName("should check redirectToJSP method calls sendRedirect")
    public void redirectToJSPTest() throws IOException {
        HttpServletResponse response = mock(HttpServletResponse.class);
        ChallengeCase challengeCaseMock = mock(ChallengeCase.class);

        doCallRealMethod().when(challengeCaseMock).redirectToJSP(true, response);
        challengeCaseMock.redirectToJSP(true, response);
        verify(response, times(1)).sendRedirect("question.jsp");

        doCallRealMethod().when(challengeCaseMock).redirectToJSP(false, response);
        challengeCaseMock.redirectToJSP(false, response);
        verify(response, times(1)).sendRedirect("fail.jsp");
    }
}
