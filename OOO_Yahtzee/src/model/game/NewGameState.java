package model.game;

import java.util.Map;

import controller.Controller;
import model.main.Facade;
import model.player.Player;

public class NewGameState implements GameState{
	private Facade model;
	
	
	public NewGameState(Facade model)
	{
		this.model = model;
	}

	@Override
	public void newGame(Map<String, String> players, Controller context) {
		//model.newGame(players);
		startGame(context);
	}
	
	@Override
	public void startGame(Controller context) {
		context.setState(new TurnState(model, model.getPlayer("player1")));
		model.startGame(context.getCurrentState());
	}

	@Override
	public void turn(Controller context, Player player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endGame(Controller context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Player getCurrentPlayer() {
		// TODO Auto-generated method stub
		return null;
	}
}
