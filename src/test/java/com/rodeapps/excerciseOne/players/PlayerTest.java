package com.rodeapps.excerciseOne.players;

import com.rodeapps.excerciseOne.GameState;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void playerShouldIncrementCounterOnItsTurn() throws InterruptedException {
        AtomicInteger counter = new AtomicInteger(0);
        GameState state = new GameState(0, true); // Player 0 goes first

        Player player = new Player("Ping", counter, state, 2, 0);
        Thread playerThread = new Thread(player);
        playerThread.start();

        // Let it run briefly
        Thread.sleep(200);

        synchronized (state.getResource()) {
            state.setRunning(false); // stop the loop
            state.getResource().notifyAll();
        }

        playerThread.join();

        assertTrue(counter.get() > 0, "Player should have incremented the counter");
    }

    @Test
    void playerShouldNotIncrementCounterIfNotTheirTurn() throws InterruptedException {
        AtomicInteger counter = new AtomicInteger(0);
        GameState state = new GameState(1, true); // Player 1's turn, but we run Player 0

        Player player = new Player("Ping", counter, state, 2, 0);
        Thread playerThread = new Thread(player);
        playerThread.start();

        // Give it time to wait
        Thread.sleep(200);

        synchronized (state.getResource()) {
            state.setRunning(false);
            state.getResource().notifyAll();
        }

        playerThread.join();

        assertEquals(0, counter.get(), "Player should not have incremented the counter if it wasn't their turn");
    }

    @Test
    void playerShouldExitCleanlyWhenStopped() throws InterruptedException {
        AtomicInteger counter = new AtomicInteger(0);
        GameState state = new GameState(0, true);

        Player player = new Player("Ping", counter, state, 2, 0);
        Thread playerThread = new Thread(player);
        playerThread.start();

        Thread.sleep(50); // short wait to let it possibly enter loop

        synchronized (state.getResource()) {
            state.setRunning(false);
            state.getResource().notifyAll();
        }

        playerThread.join(1000); // wait max 1s

        assertFalse(playerThread.isAlive(), "Player thread should have exited cleanly");
    }
}
