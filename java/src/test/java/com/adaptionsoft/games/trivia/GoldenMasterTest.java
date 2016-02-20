package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.uglytrivia.Game;
import org.approvaltests.Approvals;
import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

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

        assertTrue(game.wrongAnswer());
        assertTrue(game.wasCorrectlyAnswered());


    }

    @Test
    public void large_run_should_match_golden_master() throws Exception {


        OutputStream outputStream = redirectSystemOutToStream();

        Game game = new Game();

        game.add("player 1");
        game.add("player 2");
        game.add("player 3");
        game.add("player 4");
        game.add("player 5");

        int roll = 0;

        for (int i = 0; i < 300; i++) {

            roll++;
            if (roll > 6) {
                roll = 1;
            }
            game.roll(roll);

            if (i%3 == 0) {
                game.wrongAnswer();
            } else {
                game.wasCorrectlyAnswered();
            }

        }

        System.out.flush();

        Approvals.verify(outputStream.toString());

    }

    private OutputStream redirectSystemOutToStream() {
        OutputStream outputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(outputStream);
        System.setOut(ps);
        return outputStream;
    }


}
