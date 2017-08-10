package controller;

import java.util.HashMap;
import java.util.Map;

import model.game.GameState;
import model.game.NewGameState;
import model.main.Facade;
import view.GameBoard;
import view.UI;

public class GameController extends ControllerCommon{
    private Map<String,PlayerController> playerControllers;
	
	public GameController(UI view) throws ControllerException {
        super(view);
        view.setController(this);
        Facade model = new Facade(this);
        setModel(model);
        playerControllers = new HashMap<>();

    }
	
	@Override
    public void startView() {
        getView().start();
        
    }
	
	@Override
	public void newGame(Map<String, String> players) {
    	setState(new NewGameState(model));
    	model.newGame(players);
    	for(int i = 1; i < players.size() + 1; i++)
    	{
    		String id = "player"+i;
    		System.out.println(id);
    		newPlayer(view, id);
    		view.getPlayerPanels().put(id, new GameBoard(players.get(id), this.getPlayerController(id)));
    		view.getPlayerPanels().get(id).start();

    	}
    	getCurrentState().newGame(players, this);
	}
	
	public void turns(GameState currentState)
	{
		System.out.println(currentState.getCurrentPlayer().getName() +", " + getCurrentPlayer().getPlayer().getName());
		//String nextPlayerID = getPlayer().getID().substring(0, 6) + (Integer.parseInt(getPlayer().getID().substring(6)) + 1);
		for(int i = 1; i < playerControllers.size() + 1; i++)
		{
			System.out.println(currentState.getCurrentPlayer().getRolls());
			if(currentState.getCurrentPlayer().getRolls() == 0)
			{
				getPlayerController("player"+i).startTurn(currentState);
			}
		}
	}
	
	public PlayerController getCurrentPlayer()
	{
		if(getCurrentState().getCurrentPlayer() != null)
		{
			return playerControllers.get(getCurrentState().getCurrentPlayer().getID());
		}
		else return null;
	}
	
	public void startGame()
	{
		startGame(this);
		view.update();
	}
	
	public void newPlayer(UI view, String id)
	{
		try {
			playerControllers.put(id, new PlayerController(this, view, this.model, id));
		} catch (ControllerException e) {
			e.printStackTrace();
		}
	}
	
    public PlayerController getPlayerController(String id)
    {
    	return this.playerControllers.get(id);
    }
    
    public Map<String,PlayerController> getPlayerControllers()
    {
    	return this.playerControllers;
    }
}
