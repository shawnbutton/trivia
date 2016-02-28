package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.List;

public class Players {

    List<Player> players = new ArrayList<Player>();

    int currentPlayer = 0;

    public void add(Player player) {
        players.add(player);

        logMessage(player.getName() + " was added");
        logMessage("They are player number " + players.size());

    }

    private void logMessage(String x) {
        System.out.println(x);
    }


    public int size() {
        return players.size();
    }

    public Player get(int currentPlayer) {
        return players.get(currentPlayer);
    }

    public void incrementPlayer() {
        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = 0;
    }


    public Player getCurrentPlayer() {
        return players.get(currentPlayer);
    }
}
