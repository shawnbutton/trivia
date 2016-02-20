package com.adaptionsoft.games.uglytrivia;

import com.adaptionsoft.games.trivia.Player;

import java.util.ArrayList;
import java.util.List;

public class Game {

    List<Player> players = new ArrayList<Player>();

    int[] places = new int[6];

    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;
    private final Questions questions;

    public Game() {
        questions = new Questions();
    }

    public boolean isPlayable() {
        return (howManyPlayers() >= 2);
    }

    public void add(String playerName) {


        Player player = new Player(playerName);
        players.add(player);
        places[howManyPlayers()] = 0;

        logMessage(playerName + " was added");
        logMessage("They are player number " + players.size());
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
        return players.get(currentPlayer).isInPenaltyBox();
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
            players.get(currentPlayer).incrementPurse();
            logMessageForCurrentPlayer(" now has " + players.get(currentPlayer).getPurse() + " Gold Coins.");

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
        players.get(currentPlayer).putInPenaltyBox();

        incrementPlayer();
        return true;
    }


    private boolean didPlayerWin() {
        return !(players.get(currentPlayer).getPurse() == 6);
    }
}
