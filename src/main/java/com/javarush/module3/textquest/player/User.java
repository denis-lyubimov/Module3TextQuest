package com.javarush.module3.textquest.player;

import lombok.Getter;

public class User {

    @Getter
    private String name ;
    @Getter
    private int gameCounter;

    public User(String name) {
        this.name = name;
        gameCounter = 0;
    }

    public void incrementGameCounter(){
        gameCounter++;
    }
}
