package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.uglytrivia.Player;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PlayerTest {


    @Test
    public void player_should_have_name() {
        Player player = new Player("Bob");
        assertThat(player.getName(), is("Bob"));
    }

    @Test
    public void player_should_have_purse() {
        Player player = new Player("Bob");
        assertThat(player.getPurse(), is(0));

        player.incrementPurse();
        assertThat(player.getPurse(), is(1));
    }

    @Test
    public void player_should_be_able_to_be_in_penalty_box() {
        Player player = new Player("Bob");
        assertThat(player.isInPenaltyBox(), is(false));

        player.putInPenaltyBox();
        assertThat(player.isInPenaltyBox(), is(true));
    }

}
