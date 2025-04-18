package com.rodeapps.excerciseOne;

public class GameState {
    private final Object resource = new Object();
    private volatile int currentPlayerIndex;
    private volatile boolean isRunning;

    public GameState(int playerIndex, boolean running) {
        this.currentPlayerIndex = playerIndex;
        this.isRunning = running;
    }


    public Object getResource() {
        return resource;
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }
    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        this.currentPlayerIndex = currentPlayerIndex;
    }
    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

}
