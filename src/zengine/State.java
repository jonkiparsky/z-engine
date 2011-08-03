package zengine;

import java.util.*;
import zengine.grammar.Hall;
public class State
{
	 Room current_loc;
	private HashMap<String,Noun> inventory;
	Room tester;

	public State()
	{
		current_loc = Room.getRoom("Hall");
		inventory = new HashMap<String, Noun>();
	}

	public void test()
	{	
//		go("NORTH");
//		go("SOUTH");
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

/*	
	public void inventory()
	{
		for (String s: inventory.keySet())
		{
			System.out.println("You have "+s+". "+inventory.get(s).itemDescription());
		}
	}
*/
    public void inventory()
    {
        for (String s: inventory.keySet())
        {
                    if (!inventory.get(s).plural)
                        System.out.println("You have a " + s + ".\n");
                    else
            System.out.println("You have "+s+".");
                    System.out.println(inventory.get(s).itemDescription());
        }
    }

}
