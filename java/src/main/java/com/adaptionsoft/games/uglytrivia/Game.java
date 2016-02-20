package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.List;

public class Game {

    List players = new ArrayList();
    int[] places = new int[6];
    int[] purses = new int[6];
    boolean[] inPenaltyBox = new boolean[6];


    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;
    private final Questions questions;

    public Game() {
        questions = new Questions();
    }

    public boolean isPlayable() {
        return (howManyPlayers() >= 2);
    }

    public boolean add(String playerName) {


        players.add(playerName);
        initPlayerStats();

        logMessage(playerName + " was added");
        logMessage("They are player number " + players.size());
        return true;
    }

    private void initPlayerStats() {
        places[howManyPlayers()] = 0;
        purses[howManyPlayers()] = 0;
        inPenaltyBox[howManyPlayers()] = false;
    }

    public int howManyPlayers() {
        return players.size();
    }

    public void roll(int roll) {
        logMessageForCurrentPlayer(" is the current player");
        logMessage("They have rolled a " + roll);

        if (currentPlayerInPenaltyBox()) {
            if (notEven(roll)) {
                isGettingOutOfPenaltyBox = true;

                logMessageForCurrentPlayer(" is getting out of the penalty box");
                incrementCurrentPlayerPlace(roll);

                logMessageForCurrentPlayer("'s new location is " + places[currentPlayer]);
                logMessage("The category is " + currentCategory());
                askQuestion();
            } else {
                logMessageForCurrentPlayer(" is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
            }

        } else {
            incrementCurrentPlayerPlace(roll);

            String message = "'s new location is " + places[currentPlayer];
            logMessageForCurrentPlayer(message);
            logMessage("The category is " + currentCategory());
            askQuestion();
        }

    }

    private boolean currentPlayerInPenaltyBox() {
        return inPenaltyBox[currentPlayer];
    }

    private void logMessageForCurrentPlayer(String message) {
        logMessage(messageForCurrentPlayer(message));
    }

    private boolean notEven(int roll) {
        return roll % 2 != 0;
    }

    private String messageForCurrentPlayer(String message) {
        return currentPlayer() + message;
    }

    private void logMessage(String x) {
        System.out.println(x);
    }

    private void incrementCurrentPlayerPlace(int roll) {
        places[currentPlayer] = places[currentPlayer] + roll;
        if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;
    }

    private Object currentPlayer() {
        return players.get(currentPlayer);
    }

    private void askQuestion() {
        int place = places[currentPlayer];
        questions.askQuestions(place);
    }

    private String currentCategory() {
        return questions.getCurrentCategory(places[currentPlayer]);
    }


    public boolean wasCorrectlyAnswered() {
        if (currentPlayerInPenaltyBox() && !isGettingOutOfPenaltyBox) {
            incrementPlayer();
            return true;
        } else {

            logMessage("Answer was correct!!!!");
            purses[currentPlayer]++;
            logMessageForCurrentPlayer(" now has " + purses[currentPlayer] + " Gold Coins.");

            boolean winner = didPlayerWin();
            incrementPlayer();

            return winner;
        }

    }

    private void incrementPlayer() {
        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = 0;
    }

    public boolean wrongAnswer() {
        logMessage("Question was incorrectly answered");
        logMessageForCurrentPlayer(" was sent to the penalty box");
        inPenaltyBox[currentPlayer] = true;

        incrementPlayer();
        return true;
    }


    private boolean didPlayerWin() {
        return !(purses[currentPlayer] == 6);
    }
}
