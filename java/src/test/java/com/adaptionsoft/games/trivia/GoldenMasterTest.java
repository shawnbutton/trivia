package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.uglytrivia.Game;
import org.junit.Ignore;
import org.junit.Test;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class GoldenMasterTest {

    @Test
    public void test_can_set_up_game() {

        Game game = new Game();

        game.add("player 1");
        assertFalse(game.isPlayable());

        game.add("player 2");
        assertTrue(game.isPlayable());

        game.add("player 3");
        assertThat(game.howManyPlayers(), is(3));

    }

    @Test
    public void large_run_should_match_golden_master() throws Exception {

        System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream("output.txt")), true));

        Game game = new Game();

        game.add("player 1");
        game.add("player 2");
        game.add("player 3");

        game.roll(1);






    }


}
