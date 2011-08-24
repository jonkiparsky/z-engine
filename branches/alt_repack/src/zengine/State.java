package zengine;

import java.util.*;
import gamefiles.rooms.Hall;
import zengine.Room;
import java.text.MessageFormat;
import java.text.FieldPosition;

public class State
{
	public  Room current_loc;
	
	private String message;
	private HashMap<String,Noun> inventory;
	private Confirmable confirm;
	Room tester;


	public State()
	{
		current_loc = Room.getRoom("Hall");
		inventory = new HashMap<String, Noun>();
		confirm = null;
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
		//	System.out.println("You can't see a "+i.toString()+" here!");
			

		String formatString = (String)ZEngineMain.strings.get("TAKE_No_Such_Item");

     System.out.printf(formatString, i.toString());
       System.out.println();
			}
	}

	/**
	* Provides direct delete access to the inventory. Use for actions and events
	* which remove items from the player's pack. Note that if items are not
	* caught by the calling class, they are effectively destroyed in the game.
	*/
	public Noun inventory_destroy(Noun item)
	{
		return inventory.remove(item.toString());
	}

	/**
	* Provides direct delete access to the inventory. Use for actions and events
	* which remove items from the player's pack. Note that if items are not
	* caught by the calling class, they are effectively destroyed in the game.
	* Convenience class allowing delete by name as well as by object reference. 
	*/
	public Noun inventory_destroy(String itemName)
	{
		return inventory.remove(itemName);
	}

	/**
	* Provides direct add access to the inventory. Use this to deposit items in
	* the player's pack when "take" is inconvenient.
	* To do: if weight or space limits are imposed, this method should address
	* them.
	public void inventory_put(Noun item)
	{	
		inventory.put(item.toString(), item);
	}

	/**
	* Primitive command to remove an item from player's "pack" and add it to
	* current locale's items list.  
 	*/
	public void drop(Noun i)
	{
                if (i != null)
                {
                        String article;
                        Noun n = inventory.remove(i.toString());
                        if (n == null)
                        {
                                if (i.plural())
                                {	
                                        article = "any";
                                }
                                else 
                                {
                                        article = "a";
                                }
                                String s = (String)ZEngineMain.strings.get("DROP_No_Such_Item");
			
                                System.out.printf(s, article, i.toString() );
                        }
                        else current_loc.drop(n);
                }
                else
                {
                        System.out.println("Drop what?");
                }
	}
	
	// For Turn On/Off Flashlight
	public void turn (Preposition prep)
	{  
		Noun noun = checkContext(prep.noun);   
		
                if (noun !=null)
		//if (inventory.containsKey(prep.noun.name))
		{
			
			noun = inventory.get(prep.noun.name);
			if (! noun.state.equals(prep.name))
			{
				noun.setState(prep);
			}
			else if (noun.state.equals (prep.name))
			{
				String s = (String)ZEngineMain.strings.get("TURN_Already_In_State");
				System.out.printf(s, noun.name, prep.name);

			}
		}
                else
		{
			if (!prep.noun.plural)
				System.out.println("You don't have a " + prep.noun.name);
			else
				System.out.println("You don't have " + prep.noun.name);
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
		for (String key: inventory.keySet())
		{
			if (inventory.get(key).isLightSource())
				return true;
		}		
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

	public void answer(String s)
	{
		if (confirm == null)
		{
			System.out.println(s + " what?");
			return;
		}
		
		confirm.confirm(s);

	}

	public void setConfirm(Confirmable command)
	{
		this.confirm = command;
	}
	
	public void clearConfirm()
	{
		confirm = null;
	}
	
	/**
	* Gracefully exit the game.
	*/
	public void quit(String quitString)
	{
		
		System.out.println(quitString);
		System.exit(0);
	}
	
	/**
	* Print information about the game.
	*/
	public void help()
	{
		System.out.println(gamefiles.grammar.Go.desc);
		System.out.println(gamefiles.grammar.Quit.desc);
		System.out.println(gamefiles.grammar.Take.desc);
	}


	public String toString()
	{
		return "STATE";
	}
}
