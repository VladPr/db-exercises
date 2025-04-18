package com.rodeapps.excerciseOne;

import com.rodeapps.excerciseOne.enums.PlayerType;
import com.rodeapps.excerciseOne.players.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class PingPong {


    public static void main(String[] args) {
        AtomicInteger counter = new AtomicInteger(0);
        GameState gameState = new GameState(0, true);

        int totalPlayers = PlayerType.values().length;

        List<Thread> players = new ArrayList<>();
        for (int i = 0; i < totalPlayers; i++) {
            Thread playerThread = new Thread(new Player(PlayerType.values()[i].name(), counter, gameState, totalPlayers, i));
            players.add(playerThread);
            playerThread.start();
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            synchronized (gameState.getResource()) {
                gameState.setRunning(false);
                gameState.getResource().notifyAll();
            }
        }

        for (Thread player : players) {
            try {
                player.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("Final count: " + counter.get());
    }

}
