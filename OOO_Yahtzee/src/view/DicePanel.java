package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.Map;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JPanel;

import controller.PlayerController;

public class DicePanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private PlayerController controller;
	private String name;
	private JPanel diceList;
	private JButton rollDice;
	private JPanel lockedDice;
	
	ActionListener lockActionListener = actionEvent -> {
        AbstractButton aButton = (AbstractButton) actionEvent.getSource();
        System.out.println("clicked " + aButton.getName() + " value:" + aButton.getText());
        controller.lockDie(Integer.parseInt(aButton.getName()));
    };
	
	DicePanel(PlayerController controller, String name) {
		Dimension size = getPreferredSize();
		size.width = 700;
		size.height = 500;
		this.setPreferredSize(size);
		this.setLayout(null);
		
		setPlayerName(name);
		this.controller = controller;
		
	}
	
	public void start()
	{
		getDice();
		
		rollDice = new JButton("Roll dice");
		rollDice.setLocation(50, 220);
		rollDice.setSize(new Dimension(100, 25));
		this.add(rollDice);
		
		diceList = new JPanel();
		diceList.setBackground(Color.GRAY);
		diceList.setLocation(0, 250);
		diceList.setSize(new Dimension(300, 50));
		for(int i = 0; i < getDice().size(); i++)
		{
			JButton die = new JButton(getDice().get(i).toString());
			die.setLocation(0 + i * 50 + 50, 0);
			die.setSize(new Dimension(45, 50));
			diceList.add(die);
		}
		this.add(diceList);
		
		lockedDice = new JPanel();
		lockedDice.setBackground(Color.GRAY);
		lockedDice.setSize(new Dimension(300, 50));
		lockedDice.setLocation(0, 50);
		this.add(lockedDice);
	}
	
	public void setLockedDice(Map<Integer, Integer> locked)
	{
		lockedDice.removeAll();
		for(int i = 0; i < locked.size(); i++)
		{
			JButton lockedDie = new JButton(locked.get(i).toString());
			lockedDie.setLocation(0 + i * 50 + 50, 0);
			lockedDie.setSize(new Dimension(45, 50));
			if(lockedDie.getText().equals(""+0))
			{
				lockedDie.setVisible(false);
			}
			lockedDice.add(lockedDie, i);
		}
		if(!locked.containsValue(0))
		{
			rollDice.setEnabled(false);
		}
		lockedDice.repaint();
		diceList.repaint();
	}
	
	public void setDice(Map<Integer, Integer> rolled)
	{
		diceList.removeAll();
		for (int i = 0; i < rolled.size(); i++) {
			this.getDice().put(i, rolled.get(i));
			JButton die = new JButton(getDice().get(i).toString());
			die.setLocation(0 + i * 50 + 50, 0);
			die.setSize(new Dimension(45, 50));
			die.setName(""+i);
			if(die.getText().equals(""+0) || die.getText().equals(""+-1))
			{
				die.setVisible(false);
			}
			die.addActionListener(lockActionListener);
			this.diceList.add(die);			

		}
		diceList.repaint();

	}
	
	public Map<Integer, Integer> getDice()
	{
		return controller.getPlayer().getDice();
	}
	
	public Map<Integer, Integer> getLockedDice()
	{
		return controller.getPlayer().getLockedDice();
	}
	
	private void setPlayerName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public void addMouseClickListener(MouseListener listener){
		rollDice.addMouseListener(listener);
	}
}
