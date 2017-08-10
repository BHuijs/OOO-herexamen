package controller;

import java.util.Map;

import model.game.GameState;
import model.main.Facade;
import model.player.Player;
import view.UI;
import view.View;

public abstract class ControllerCommon implements Controller {

    protected UI view;
    protected Facade model;
	private GameState gameState;


    public ControllerCommon(UI view) throws ControllerException {
        setView(view);
    }


    protected Facade getModel() {
        return model;
    }


    protected void setModel(Facade model) throws ControllerException {
        if (model == null) {
            throw new ControllerException("Invalid Model");
        }
        this.model = model;
    }


    protected View getView() {
        return view;
    }
    

    private void setView(UI view) throws ControllerException {
        if (view == null) {
            throw new ControllerException("Invalid View");
        }
        this.view = view;
    }
    
    @Override
    public void setState(GameState state)
    {
		this.gameState = state;
    }
    
    @Override
    public GameState getCurrentState()
    {
    	return this.gameState;
    }
    
    @Override
	public void newGame(Map<String, String> players) {
    	//setState(new NewGameState(model));
    	gameState.newGame(players, this);
	}
    
    public void startGame(Controller context)
	{
		gameState.startGame(context);
	}
    
    public void startTurn(Player currentPlayer)
    {
    	gameState.turn(this, currentPlayer);
    }
}
