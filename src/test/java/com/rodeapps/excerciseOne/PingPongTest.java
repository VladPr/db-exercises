package com.rodeapps.excerciseOne;

import com.rodeapps.excerciseOne.enums.PlayerType;
import com.rodeapps.excerciseOne.players.Player;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

public class PingPongTest {

    @Test
    void pingPongShouldRunAndStopGracefully() throws InterruptedException {
        AtomicInteger counter = new AtomicInteger(0);
        GameState gameState = new GameState(0, true);

        int totalPlayers = PlayerType.values().length;
        List<Thread> players = new ArrayList<>();

        for (int i = 0; i < totalPlayers; i++) {
            Thread thread = new Thread(
                    new Player(PlayerType.values()[i].name(), counter, gameState, totalPlayers, i),
                    "Player-" + PlayerType.values()[i].name()
            );
            players.add(thread);
            thread.start();
        }

        // Let them run briefly
        Thread.sleep(1000);

        synchronized (gameState.getResource()) {
            gameState.setRunning(false);
            gameState.getResource().notifyAll();
        }

        for (Thread thread : players) {
            thread.join(1000); // Max 1s per thread
            assertFalse(thread.isAlive(), thread.getName() + " should have stopped");
        }

        assertTrue(counter.get() > 0, "At least one player should have printed");
    }

    @Test
    void pingPongWithSinglePlayerShouldWork() throws InterruptedException {
        AtomicInteger counter = new AtomicInteger(0);
        GameState gameState = new GameState(0, true);
        int totalPlayers = 1;

        Thread thread = new Thread(
                new Player("Solo", counter, gameState, totalPlayers, 0),
                "Player-Solo"
        );
        thread.start();

        Thread.sleep(500);

        synchronized (gameState.getResource()) {
            gameState.setRunning(false);
            gameState.getResource().notifyAll();
        }

        thread.join(1000);
        assertFalse(thread.isAlive(), "Single player should have stopped");
        assertTrue(counter.get() > 0, "Single player should have incremented the counter");
    }
}
