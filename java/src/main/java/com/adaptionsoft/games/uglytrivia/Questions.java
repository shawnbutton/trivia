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

    public String getCurrentCategory(int place) {
        if ((place == 0) || (place == 4) || (place == 8)) return "Pop";
        if ((place == 1) || (place == 5)  || (place == 9)) return "Science";
        if ((place == 2)  || (place == 6)  || (place == 10)) return "Sports";
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
