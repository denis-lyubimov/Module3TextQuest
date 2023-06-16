package com.javarush.module3.textquest.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    @Test
    @DisplayName("should return correct username")
        public void getUserNameTest(){
        String expected = "userName";
        User user = new User(expected);
        assertEquals(expected, user.getName());
    }

    @Test
    @DisplayName("should return gamecounter = 0")
    public void getUserGameCounterTest(){
        int expectedGameCounter = 0;
        String userName = "userName";
        User user = new User(userName);
        assertEquals(expectedGameCounter, user.getGameCounter());
    }

    @Test
    @DisplayName("should return incremented gamecounter")
    public void incrementUserGameCounterTest(){
        int expectedGameCounter = 1;
        String userName = "userName";
        User user = new User(userName);
        user.incrementGameCounter();
        assertEquals(expectedGameCounter, user.getGameCounter());
    }

}
