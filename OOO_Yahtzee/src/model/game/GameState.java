package model.game;

import java.util.Map;

import controller.Controller;
import model.player.Player;

public interface GameState {
	public void newGame(Map<String, String> players, Controller context);
	public void startGame(Controller context);
	public void turn(Controller context, Player player);
	public void endGame(Controller context);
	public Player getCurrentPlayer();
	
}
