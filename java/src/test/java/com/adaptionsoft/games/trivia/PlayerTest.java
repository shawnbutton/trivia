package com.adaptionsoft.games.trivia;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PlayerTest {


    @Test
    public void player_should_have_name() {
        Player player = new Player("Bob");

        assertThat(player.getName(), is("Bob"));

    }


}
