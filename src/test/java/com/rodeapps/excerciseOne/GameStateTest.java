package com.rodeapps.excerciseOne;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameStateTest {

    @Test
    void getCurrentPlayerIndex() {
        GameState gameState = new GameState(1, true);
        assertEquals(1, gameState.getCurrentPlayerIndex());
    }

    @Test
    void setCurrentPlayerIndex() {
        GameState gameState = new GameState(0, true);
        gameState.setCurrentPlayerIndex(2);
        assertEquals(2, gameState.getCurrentPlayerIndex());
    }

    @Test
    void isRunning() {
        GameState gameState = new GameState(0, true);
        assertTrue(gameState.isRunning());
    }

    @Test
    void setRunning() {
        GameState gameState = new GameState(0, true);
        gameState.setRunning(false);
        assertFalse(gameState.isRunning());
    }

    @Test
    void getResource_shouldNotBeNull() {
        GameState gameState = new GameState(0, true);
        assertNotNull(gameState.getResource());
    }

    @Test
    void shouldCycleToNextPlayer() {
        GameState gameState = new GameState(0, true);
        int next = (gameState.getCurrentPlayerIndex() + 1) % 2;
        gameState.setCurrentPlayerIndex(next);
        assertEquals(1, gameState.getCurrentPlayerIndex());
    }
}