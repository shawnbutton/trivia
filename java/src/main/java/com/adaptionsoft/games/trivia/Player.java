package com.adaptionsoft.games.trivia;

/**
 * Created by shawnbutton on 2016-02-20.
 */
public class Player {
    private final String name;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
