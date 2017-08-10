package model.game;

import java.util.HashMap;
import java.util.Map;

public class Dice {
	private Map<Integer, Integer> dice;
	private Map<Integer, Integer> lockedDice;
	
	public Dice()
	{
		this.dice = new HashMap<>();
		this.lockedDice = new HashMap<>();
		for(int i = 0; i < 5; i++)
		{
			dice.put(i, 0);
			lockedDice.put(i, 0);
		}
	}
	
	public void lockDie(int index)
	{
		lockedDice.put(index, dice.get(index));
		dice.put(index, -1);
	}
	
	public Map<Integer, Integer> getLockedDice()
	{
		return this.lockedDice;
	}
	
	public void rollDice()
	{
		for(int i = 0; i < dice.size(); i++)
		{
			if(dice.get(i) != -1)
			{
				dice.put(i, (int) (Math.random() * 6) + 1);
			}
		}
	}
	
	public Map<Integer, Integer> getDice()
	{
		return this.dice;
	}
	
	public void returnLockedDie(int indexKey)
	{
		dice.put(indexKey, lockedDice.get(indexKey));
	}
}
