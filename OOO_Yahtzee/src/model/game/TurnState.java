package model.game;

import java.util.Map;

import controller.Controller;
import model.main.Facade;
import model.player.Player;

public class TurnState implements GameState{
	private Facade model;
	private Player playing;
	
	public TurnState(Facade model, Player player)
	{
		this.playing = player;
		this.model = model;
	}

	@Override
	public void newGame(Map<String, String> players, Controller context) {
		// cannot start new game while current game is running
		
	}
	
	@Override
	public void startGame(Controller context) {
		// game is running, cannot initialize new game
		
	}

	@Override
	public void turn(Controller context, Player player) {
		System.out.println(playing.getName() + ", " + player.getName());
		player.setRolls();
	}

	@Override
	public void endGame(Controller context) {
		// TODO Auto-generated method stub
		
	}
	
	public Player getCurrentPlayer()
	{
		return this.playing;
	}


}
