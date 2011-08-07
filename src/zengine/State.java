package zengine;

import java.util.*;
import zengine.grammar.Hall;
public class State
{
	public  Room current_loc;
	private HashMap<String,Noun> inventory;
	Room tester;

	public State()
	{
		current_loc = Room.getRoom("Hall");
		inventory = new HashMap<String, Noun>();
	}


	/**
	* The Go.execute method checks that this is a valid move. 
	*/
	public void go(Direction d)
	{	
		
		System.out.println(">> Go "+ d.toString());
		current_loc = current_loc.getExit(d);
		look();
	}

	public void look()
	{
		System.out.println(current_loc.description());
	}
	
	public void take(Noun i)
	{
		Noun n = current_loc.take(i);
		if (n != null)
		{
			inventory.put(i.toString(),i);

			inventory();
		}		
		else {
			System.out.println("You can't see a "+i.toString()+" here!");
		}
	}

	public void drop(Noun i)
	{
		Noun n = inventory.remove(i.toString());
		if (n == null)
		{
			System.out.println("You haven't got a "+i.toString());
		}
		else current_loc.drop(n);
	}
	
	public void turn (Noun i, Preposition p)
	{
		if (inventory.containsKey(i.name))
		{
			// Can only be turned on or off
			if ((p.getClass() == zengine.grammar.On.class) || 
			    (p.getClass() == zengine.grammar.Off.class))
			{
				if (!i.state.equals(p.toString()))
				{
					i.state = p.s;
					System.out.println(i.itemDescription());
					return;
				}
				System.out.println(i + " is already " + p.toString());
			}
			else
			{
			    System.out.println("You can't do that to " + i);
			}
		}
		else
		{
			if (!i.plural)
			{
				System.out.println("You don't have a " + i + " in your inventory.");
			}
			else
			{
				System.out.println("You don't have " + i + " in your inventory.");
			}
		}
	}

	public void inventory()
	{
		for (String s: inventory.keySet())
		{ 
			if (!inventory.get(s).plural)
			{
				System.out.println("You have a " + s + ".");
			}
			else
			{
	    		System.out.println("You have "+s+".");
		    	System.out.println(inventory.get(s).itemDescription());
			}
    	}
	}

}
