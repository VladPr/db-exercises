package com.rodeapps.excerciseOne.players;

import com.rodeapps.excerciseOne.GameState;

import java.util.concurrent.atomic.AtomicInteger;

public class Player implements Runnable {

    private final String message;
    private final AtomicInteger counter;
    private final GameState gameState;
    private final int totalPlayers;
    private final int playerIndex;

    public Player(String message, AtomicInteger counter, GameState gameState, int totalPlayers, int playerIndex) {
        this.message = message;
        this.counter = counter;
        this.gameState = gameState;
        this.totalPlayers = totalPlayers;
        this.playerIndex = playerIndex;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (gameState.getResource()) {
                while (gameState.isRunning() && gameState.getCurrentPlayerIndex() != playerIndex) {
                    try {
                        gameState.getResource().wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }

                if (!gameState.isRunning()) {
                    return;
                }

                System.out.println(message);
                counter.incrementAndGet();
                gameState.setCurrentPlayerIndex((gameState.getCurrentPlayerIndex() + 1) % totalPlayers);
                gameState.getResource().notifyAll();
            }
        }
    }
}

