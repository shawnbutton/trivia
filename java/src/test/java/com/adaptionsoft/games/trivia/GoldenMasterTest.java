package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.uglytrivia.Game;
import org.junit.Ignore;
import org.junit.Test;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class GoldenMasterTest {

    public static final String GOLDEN_RECEIVED_FILENAME = "golden.received.txt";
    private static final String GOLDEN_APPROVED_FILENAME = "golden.approved.txt";

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

        URL path = this.getClass().getResource(GOLDEN_APPROVED_FILENAME);
        File file = new File(path.getFile());

        System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream(file)), true));

        Game game = new Game();

        game.add("player 1");
        game.add("player 2");
        game.add("player 3");

        game.roll(1);


//        URL path = this.getClass().getResource(GOLDEN_APPROVED_FILENAME);
//        Reader reader = new BufferedReader(new FileReader(file));



        byte[] approved = Files.readAllBytes(Paths.get(GOLDEN_RECEIVED_FILENAME));
        byte[] received = Files.readAllBytes(Paths.get(GOLDEN_APPROVED_FILENAME));

        assertArrayEquals(approved, received);


    }


}
