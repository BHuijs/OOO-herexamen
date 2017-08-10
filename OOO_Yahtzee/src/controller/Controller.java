package controller;

import java.util.Map;

import model.game.GameState;

public interface Controller {

    void startView();
    void newGame(Map<String, String> players);
    void setState(GameState state);
    GameState getCurrentState();
}
