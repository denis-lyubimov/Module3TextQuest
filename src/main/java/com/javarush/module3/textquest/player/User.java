package com.javarush.module3.textquest.player;

import lombok.Getter;

@Getter
public class User {


    private String name ;
    private int gameCounter;

    public User(String name) {
        this.name = name;
        gameCounter = 0;
    }

    public void incrementGameCounter(){
        gameCounter++;
    }
}
