package com.adaptionsoft.games.trivia;

public class Player {
    private final String name;
    private int purse;
    private int place;

    private boolean inPenaltyBox;

    public Player(String name) {
        this.name = name;
        purse = 0;
        place = 0;
        inPenaltyBox = false;
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

    public boolean isInPenaltyBox() {
        return inPenaltyBox;
    }

    public void putInPenaltyBox() {
        this.inPenaltyBox = true;
    }

    public int getPlace() {
        return place;
    }

    public void addRollToPlace(int roll) {
        place += roll;
        if (place > 11) {
            place -= 12;
        }

    }
}
