package com.adaptionsoft.games.uglytrivia;

import java.util.LinkedList;

public class Questions {

    LinkedList popQuestions = new LinkedList();
    LinkedList scienceQuestions = new LinkedList();
    LinkedList sportsQuestions = new LinkedList();
    LinkedList rockQuestions = new LinkedList();


    public Questions() {
        loadQuestions();
    }

    private void loadQuestions() {
        for (int i = 0; i < 50; i++) {
            logMessage("Adding game question");
            popQuestions.addLast("Pop Question " + i);
            scienceQuestions.addLast(("Science Question " + i));
            sportsQuestions.addLast(("Sports Question " + i));
            rockQuestions.addLast("Rock Question " + i);
        }
    }

    public String getCurrentCategory(int currentPlayerPlace) {
        if (currentPlayerPlace == 0) return "Pop";
        if (currentPlayerPlace == 4) return "Pop";
        if (currentPlayerPlace == 8) return "Pop";
        if (currentPlayerPlace == 1) return "Science";
        if (currentPlayerPlace == 5) return "Science";
        if (currentPlayerPlace == 9) return "Science";
        if (currentPlayerPlace == 2) return "Sports";
        if (currentPlayerPlace == 6) return "Sports";
        if (currentPlayerPlace == 10) return "Sports";
        return "Rock";
    }


    public void askQuestions(String currentCategory) {
        if (currentCategory == "Pop")
            System.out.println(popQuestions.removeFirst());
        if (currentCategory == "Science")
            System.out.println(scienceQuestions.removeFirst());
        if (currentCategory == "Sports")
            System.out.println(sportsQuestions.removeFirst());
        if (currentCategory == "Rock")
            System.out.println(rockQuestions.removeFirst());
    }


    private void logMessage(String x) {
        System.out.println(x);
    }



}
