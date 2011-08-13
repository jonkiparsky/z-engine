package zengine;

import java.util.*;
import zengine.rooms.Hall;
import zengine.Room;
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


	public Noun checkContext(Noun n)
	{	
				return inventory.get(n.toString());
			
	}



	/**
	* Primitive command to move the player from one point to a contiguous point. 
  * The Go.execute() method checks that this is a valid move. 
	*/
	public void go(Direction d)
	{	
		
		System.out.println(">> Go "+ d.toString());
		current_loc = current_loc.getExit(d);
		current_loc.enter();
		look();
	}

  /**
  *  Primitive command for "look". Returns the room's description of itself. 
  */
	public void look()
	{
		System.out.println(current_loc.description());
	}
	
   /**
   *  Primitive command to attempt to remove an item from the current locale's
   *  items list and add it to player's "pack". 
   */
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

	/**
	* Primitive command to remove an item from player's "pack" and add it to
	* current locale's items list.  
 	*/
	public void drop(Noun i)
	{
		Noun n = inventory.remove(i.toString());
		if (n == null)
		{
			System.out.println("You haven't got a "+i.toString());
		}
		else current_loc.drop(n);
	}
	
	// For Turn On/Off Flashlight
	public void turn (Preposition prep)
	{  
		Noun noun = checkContext(prep.noun);   
		
   	if (noun !=null)
		//if (inventory.containsKey(prep.noun.name))
		{
			noun = inventory.get(prep.noun.name);
			if (noun.state != prep.name)
			{
				noun.setState(prep);
			}
			else if (noun.state == prep.name)
				System.out.println(noun.name + " is already " + prep.name);
		}
		else
		{
			if (!noun.plural)
				System.out.println("You don't have a " + noun.name);
			else
				System.out.println("You don't have " + noun.name);
		}
	}

	
	//For Turn Flashlight On/Off
	public void turn(Noun n)
	{
		
		    Preposition p = n.prep;

			n = checkContext(n);
		    if (inventory.containsKey(n.name))
		    {
			    Noun noun = n;
			    if (noun.state != p.name)
				    noun.setState(p);
			    else if (noun.state == p.name)
				    System.out.print(noun.name + " is already " + p.name);
		    }
		    else
		    {
			    if (!n.plural)
				    System.out.println("You don't have a " + n.name);
			    else
				    System.out.println("You don't have " + n.name);
		    }
	}


	public boolean carryingLight()
	{
		System.out.println("carryingLight(): 1");
		for (String key: inventory.keySet())
		{
		System.out.println("carryingLight(): 2");
			System.out.println(key + ": "+ inventory.get(key).isLightSource());	
			System.out.println(key + ": "+ inventory.get(key).state);	
			if (inventory.get(key).isLightSource())
				return true;
		}		
		System.out.println("carryingLight(): 3");
		return false;
	}

  /**
  * Report the contents of the player's "pack". This is a primitive command; its
  * implementation is cannot be changed by the game designer without modifying
  * the Engine source code. 
	* To do: Re-implement as a hook, returning the data unformatted, to allow
	* game designers to override the implementation. This implementation should
	* be kept as a convenience.  
	* To do: externalize strings. 
  */
	public void inventory()
	{
		if (inventory.size()==0)
		{
		System.out.println("You are unburdened by material goods.");
		
		}
		for (String s: inventory.keySet())
		{ 
			if (!inventory.get(s).plural)
			{
				System.out.println("You have a " + s + ".");
				if (inventory.get(s).state != null)
				    System.out.println(inventory.get(s).itemDescription());
			}
			else
			{
	    		System.out.println("You have "+s+".");
		    	System.out.println(inventory.get(s).itemDescription());
			}
    	}
		System.out.println("Carrying light? "+carryingLight());
	}

	/**
	* Returns unformatted inventory HashMap for game designers who wish to
	* compose their own inventory command or check the inventory for other
	* reasons. 
	*/
	public HashMap<String,Noun> _getInventory()
	{
		return inventory;
	}

  /**
  * Perform a search of the current locale. This is ultimately implemented by
  * Room.search(); it can be overridden to provide custom effects for a specific
  * room. (ie, one might discover a trap, or disturb a monster). 
  */
	public void search()
	{	
		current_loc.search();
	}
	
	/**
	* Gracefully exit the game.
	*/
	public void quit()
	{
		
		System.out.println("Goodbye!");
		System.exit(0);
	}
	
	/**
	* Print information about the game.
	*/
	public void help()
	{
		System.out.println(zengine.grammar.Go.desc);
		System.out.println(zengine.grammar.Quit.desc);
		System.out.println(zengine.grammar.Take.desc);
	}

}
