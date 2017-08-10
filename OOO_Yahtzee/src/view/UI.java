package view;

import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controller.Controller;
import controller.GameController;
import controller.PlayerController;

public class UI extends JFrame implements View {

    private static final long serialVersionUID = 1L;
    private String name;
    private Map<String,String> playerNames;
    private GameController controller;
    private Map<String, GameBoard> playerPanels;
    private int players;
    //super controller voor UI die per speler een Gamecontroller initialiseert?
    
    public UI() {
    	super();
        this.setResizable(false);
        this.setLayout(null);        
        String players = "";
        int amount = 0;
        
    	while(amount < 2){
    		amount = setPlayerAmount(players);
    	}
    	this.players = amount;

        playerNames = new HashMap<String,String>();
        playerPanels = new HashMap<>();
    }
    
    
    
    //checks if input for amount of players is a number
    private int setPlayerAmount(String players)
    {
		players = JOptionPane.showInputDialog("Please enter the amount of players");
        while(players.isEmpty() || players == null)
        {
            players = JOptionPane.showInputDialog("Please enter the amount of players");
        }
        
        try
        {
        	return Integer.parseInt(players);
        }catch (NumberFormatException e) {
        	return 0;
        }
    }
    
    //add player and create window
    private void setupLayout() 
    {     
    	int size = playerNames.size() + 1;
    	String id = "player" + size;
    	name = JOptionPane.showInputDialog(id + ", enter your name: ");
    	while(name.isEmpty() || name == null || playerNames.containsValue(name) || name.length() < 2)
    	{
        	name = JOptionPane.showInputDialog(id +", enter your name: ");
    	}
    	playerNames.put(id, name);
    	//controller.newGame(getPlayerIDAndNames());
    	//controller.newPlayer(this, id);
        //playerPanels.put(id, new GameBoard(name, controller.getPlayerController(id)));
    }
    
    public void update() 
    {
    	Map<String, PlayerController> playerControllers = this.getController().getPlayerControllers();
    	
    	for(int i = 1; i < playerControllers.size(); i++)
    	{
    		if(controller.getCurrentPlayer().getPlayer().getRolls() == 0)
        	{
        		
        		int playerNum = Integer.parseInt(controller.getCurrentPlayer().getPlayer().getID().substring(6));
        		System.out.println(playerNum);
        		if(playerNum == playerControllers.size())
        		{
        			playerNum = 1;
        		}
        		playerPanels.get("player" + (playerNum +1)).toFront();
        		playerPanels.get("player" + i).getDiceBoard().setEnabled(false);
        		playerControllers.get("player" + (playerNum + 1)).startTurn(controller.getCurrentState());
        	}
    		
    		//playerPanels.get("player"+i).getDiceBoard().setDice(playerControllers.get("player" + i).getDice());
    		//playerPanels.get("player"+i).getDiceBoard().setLockedDice(playerControllers.get("player" + i).getLockedDice());
    		playerPanels.get("player"+i).getDiceBoard().setDice(playerControllers.get(controller.getCurrentPlayer().getPlayer().getID()).getDice());
    		playerPanels.get("player"+i).getDiceBoard().setLockedDice(playerControllers.get(controller.getCurrentPlayer().getPlayer().getID()).getLockedDice());   			
			playerPanels.get("player"+i).setCurrentPlayerName(this.controller.getCurrentPlayer().getPlayerName());
    	}
    	
    }
    
    public Map<String, String> getPlayerIDAndNames()
    {
    	return this.playerNames;
    }
    
    public Map<String, GameBoard> getPlayerPanels()
    {
    	return playerPanels;
    }
    
    public String getName() 
    {
        return this.name;
    }
    
    private GameController getController() 
    {
        return this.controller;
    }

    @Override
    public void setController(Controller controller) {
    	this.controller = (GameController) controller;    
	}

    @Override
    public void addIsListener(KeyListener isListener) {
        // TODO Auto-generated method stub
    }

    @Override
    public void start() {
    	// create layout
        for(int i = 0; i < this.players; i++){
        	
        	setupLayout();
        }
        controller.newGame(playerNames);
    	for(int i = playerPanels.size(); i > 0; i--)
    	{
    		playerPanels.get("player" + i).setVisible(true);
    	}
    	//playerPanels.get("")
        //playerPanels.get("player1").start();
        //controller.startGame();
    }
}
