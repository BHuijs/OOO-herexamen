package model.main;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import controller.GameController;
import model.game.GameState;
import model.player.Player;

public class Facade {
	private GameController controller;
	private Map<String, Player> players;

	public Facade(GameController controller) {
		this.controller = controller;
		players = new HashMap<String, Player>();
	}
	
	
	public void newGame(Map<String, String> playerlist)
	{
		Player player;
		for(int i = 1; i < playerlist.size()+1; i++)
		{
			player = new Player("player"+i, playerlist.get("player"+i));
			this.players.put("player"+i, player);
		}
		
	}
	
	public void startGame(GameState currentState)
	{
		controller.turns(currentState);
	}
	
	public Player getPlayer(String id)
	{
		return players.get(id);
	}
	
	public Map<Integer, Integer> getPlayerDice(String id)
	{
		return players.get(id).getDice();
	}
	
	public Player getPlayerByName(String name)
	{
		Player player = null;
		Set<String> keys = players.keySet();
		for (String key : keys) {
			if(players.get(key).getName().equals(name))
			{
				player = players.get(key);
			}
		}
		return player;
	}
	
	public void rollDice(String id)
	{
		getPlayer(id).rollDice();
	}
	
	public void lockDie(String id, int dieIndex)
	{
		getPlayer(id).lockDie(dieIndex);
	}
}
