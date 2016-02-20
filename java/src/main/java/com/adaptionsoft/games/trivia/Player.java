package com.adaptionsoft.games.trivia;

public class Player {
    private final String name;

    private int purse;

    public Player(String name) {
        this.name = name;
        purse = 0;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    public void incrementPurse() {
        purse++;
    }

    public int getPurse() {
        return purse;
    }
}
