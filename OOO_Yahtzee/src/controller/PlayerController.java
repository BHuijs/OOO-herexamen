package controller;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;

import model.game.GameState;
import model.game.TurnState;
import model.main.Facade;
import model.player.Player;
import view.UI;

public class PlayerController extends ControllerCommon{
	private Player player;
    private MouseClickHandler clickHandler;
    private GameController game;
	
	public PlayerController(GameController controller, UI view, Facade model, String id) throws ControllerException {
		super(view);		
		setModel(model);
		setPlayer(id);
		game = controller;
		clickHandler = new MouseClickHandler();
	}
	
	private void setPlayer(String id)
	{
		if(id.equals(null) || id.isEmpty())
		{
			throw new IllegalArgumentException("invalid ID");
		}else
		{
			this.player = model.getPlayer(id);
		}
	}
	
	public void startTurn(GameState currentState)
	{
		if(currentState.getCurrentPlayer().equals(getPlayer()))
		{
			setState(currentState);
		}else
		{
			setState(new TurnState(model, getPlayer()));
		}
		startTurn(getPlayer());
		view.getPlayerPanels().get(getPlayer().getID()).getDiceBoard().addMouseClickListener(clickHandler);

	}
	
	public Player getPlayer()
	{
		return player;
	}
	
	public String getPlayerName()
	{
		return model.getPlayer(player.getID()).getName();
	}
	
	public void rollDice()
	{		
		model.rollDice(getPlayer().getID());
		
		//model.getPlayer(getPlayer().getID()).rollDice();
		//System.out.println(model.getPlayer(getPlayer().getID()).getDice());
	}
	
	public Map<Integer, Integer> getDice()
	{
		return model.getPlayerDice(getPlayer().getID());
	}
	
	public void lockDie(int index)
	{
		model.lockDie(getPlayer().getID(), index);
		view.update();
	}
	
	public Map<Integer, Integer> getLockedDice()
	{
		return model.getPlayer(getPlayer().getID()).getLockedDice();
	}
	
	@Override
	public void startView() {
		getView().start();
	}
	
	private class MouseClickHandler extends MouseAdapter {
		public void mouseClicked(MouseEvent event)
		{
			if(getPlayer().getRolls() == 0)
			{
				view.getPlayerPanels().get(getPlayer().getID()).getDiceBoard().removeMouseListener(clickHandler);
			}else
			{
				rollDice();
			}
			view.update();
		}
	}

}
