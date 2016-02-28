package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.List;

public class Game {

//    List<Player> players = new ArrayList<Player>();

    Players players_t = new Players();

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
//        players.add(player);

        players_t.add(player);

    }

    public int howManyPlayers() {
        return players_t.size();
    }

    public void roll(int roll) {
        logMessageForCurrentPlayer(" is the current player");
        logMessage("They have rolled a " + roll);

        if (currentPlayerInPenaltyBox()) {
            if (notEven(roll)) {
                isGettingOutOfPenaltyBox = true;

                logMessageForCurrentPlayer(" is getting out of the penalty box");
                incrementCurrentPlayerPlace(roll);

                logMessageForCurrentPlayer("'s new location is " + getCurrentPlayer().getPlace());
                logMessage("The category is " + currentCategory());
                askQuestion();
            } else {
                logMessageForCurrentPlayer(" is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
            }

        } else {
            incrementCurrentPlayerPlace(roll);

            logMessageForCurrentPlayer("'s new location is " + getCurrentPlayer().getPlace());
            logMessage("The category is " + currentCategory());
            askQuestion();
        }

    }

    private boolean currentPlayerInPenaltyBox() {
        return getCurrentPlayer().isInPenaltyBox();
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
        getCurrentPlayer().addRollToPlace(roll);
    }

    private Player currentPlayer() {
        return getCurrentPlayer();
    }

    private void askQuestion() {
        int place = getCurrentPlayer().getPlace();
        questions.askQuestions(place);
    }

    private String currentCategory() {
        return questions.getCurrentCategory(getCurrentPlayer().getPlace());
    }


    public boolean wasCorrectlyAnswered() {
        if (currentPlayerInPenaltyBox() && !isGettingOutOfPenaltyBox) {
            incrementPlayer();
            return true;
        } else {

            logMessage("Answer was correct!!!!");
            getCurrentPlayer().incrementPurse();
            logMessageForCurrentPlayer(" now has " + getCurrentPlayer().getPurse() + " Gold Coins.");

            boolean winner = didPlayerWin();
            incrementPlayer();

            return winner;
        }

    }

    private void incrementPlayer() {
        players_t.incrementPlayer();
    }



    public boolean wrongAnswer() {
        logMessage("Question was incorrectly answered");
        logMessageForCurrentPlayer(" was sent to the penalty box");
        getCurrentPlayer().putInPenaltyBox();

        incrementPlayer();
        return true;
    }

    private Player getCurrentPlayer() {
        return players_t.getCurrentPlayer();
    }


    private boolean didPlayerWin() {
        return !(getCurrentPlayer().getPurse() == 6);
    }
}
