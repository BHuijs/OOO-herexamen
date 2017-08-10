package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import javax.swing.*;

import controller.Controller;
import controller.PlayerController;

public class GameBoard extends JFrame implements View {
	private static final long serialVersionUID = 1L;
	private PlayerController controller;
	private String name;
	private String playing;
	
	private JLabel titleLabel;
	private JLabel nameLabel;
	private DicePanel diceBoard;

	public GameBoard(String name, PlayerController controller) {
		super();
        this.setSize(700, 600);
        this.setResizable(false);
        this.setLayout(null);
        
        this.name = "";
		setPlayerName(name);
		setController(controller);
        // create layout
        setupLayout();			
	}
	
	 private void setupLayout() {
		titleLabel = new JLabel("Yahtzee");
		titleLabel.setSize(new Dimension(50,25));
		titleLabel.setLocation(325, 0);
		this.add(titleLabel);
		 
		nameLabel = new JLabel(this.name);
		nameLabel.setSize(new Dimension(50, 25));
		nameLabel.setLocation(25, 0);
		this.add(nameLabel);
		
		diceBoard = new DicePanel(getController(), name);
		diceBoard.setBackground(Color.GRAY);
		diceBoard.setLocation(0, 30);
		diceBoard.setSize(new Dimension(700, 500));
		this.add(diceBoard);
	 }

	private void setPlayerName(String name) {
		if (!name.trim().isEmpty()) {
			this.name = name;
		} else {
			throw new IllegalArgumentException("invalid name");
		}
	}
	
	public void setCurrentPlayerName(String name)
	{
		if(name.isEmpty() || name.equals(null)) {
			throw new IllegalArgumentException("invalid name");
		} else {
			this.playing = name + " is playing";
		}
		
		JLabel currentPlayer = new JLabel(this.playing);
		currentPlayer.setSize(new Dimension(150, 25));
		currentPlayer.setLocation(325, 525);
		this.add(currentPlayer);
	}
	
	private PlayerController getController() {
        return this.controller;
    }
	
	public DicePanel getDiceBoard()
	{
		return this.diceBoard;
	}
	
	@Override
    public void setController(Controller controller) {
        this.controller = (PlayerController) controller;
    }

    @Override
    public void addIsListener(KeyListener isListener) {
        // TODO Auto-generated method stub
    }

    @Override
    public void start() {
        setVisible(true);
        setCurrentPlayerName(name);
        diceBoard.start();
    }
}
