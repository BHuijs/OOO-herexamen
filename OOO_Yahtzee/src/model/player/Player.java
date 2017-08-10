package model.player;

import java.util.Map;

import model.game.Dice;

public class Player {
	private String id;
	private String name;
	private Dice dice;
	private int score;
	private int rolls;
	
	public Player(String id, String name)
	{
		setName(name);
		setID(id);
		dice = new Dice();
		setScore(0);
	}
	
	public void rollDice()
	{
		dice.rollDice();
		rolls -= 1;
	}
	
	public void setRolls()
	{
		this.rolls = 3;
	}
	
	public int getRolls()
	{
		return this.rolls;
	}
	
	public void lockDie(int index)
	{
		dice.lockDie(index);
	}
	
	public Map<Integer, Integer> getLockedDice()
	{
		return dice.getLockedDice();
	}
	
	public Map<Integer, Integer> getDice()
	{
		return this.dice.getDice();
	}
	
	private void setID(String id)
	{
		if(id.equals(null) || id.isEmpty())
		{
			throw new IllegalArgumentException("ID cannot be emtpy");
		}else
		{
			this.id = id;
		}
	}
	
	public String getID()
	{
		return this.id;
	}
	
	private void setName(String name)
	{
		if(name.equals(null) || name.isEmpty())
		{
			throw new IllegalArgumentException("Name cannot be emtpy");
		}else if(name.length() < 2)
		{
			throw new IllegalArgumentException("Name cannot be shorter than 2 letters");
		}else
		{
			this.name = name;
		}
	}
	
	public String getName()
	{
		return this.name;
	}
	
	private void setScore(int score)
	{
		if(score != 0)
		{
			throw new IllegalArgumentException("Game cannot start with a score different than 0");
		}else
		{
			this.score = score;
		}
	}
	
	public int getScore()
	{
		return this.score;
	}
	
	public void addToScore(int points)
	{
		if(points < 0)
		{
			throw new IllegalArgumentException("Cannot add negative points");
		}else
		{
			this.score += points;
		}
	}
}
