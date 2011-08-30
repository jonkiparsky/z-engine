package zengine;

import java.util.*;
import gamefiles.rooms.Hall;
import gamefiles.time.*;
import zengine.Room;
import java.text.MessageFormat;
import java.text.FieldPosition;

public class State
{
	private  Room current_loc;
	
	private String message;
	//private HashMap<String,Noun> inventory;
	private Container inventory;
	private Confirmable confirm;
	Room tester;
	private int time;
	private Time timer;

	/**
	 * Creates a new state. This holds the players current location,
	 * inventory, as well as access primitive commands that the player can use.
	 */
	public State()
	{
		current_loc = Room.getRoom("Hall");
		current_loc.enter();
		look();
		//inventory = new HashMap<String, Noun>();
		// Inventory of size 25, weightLimit 50.
		inventory = new Container("Inventory", 25, 50);
		confirm = null;
	}
	
	/**
	 * Checks to see if an object is in the players inventory.
	 * @param n
	 * The object to check.
	 * @return 
	 * Returns the object if it is in the inventory, otherwise returns null.
	 */
	public Noun checkContext(Noun n)
	{	
				return inventory.getItem(n);			
	}
	
	/**
	 * Returns the current location of the player.
	 */
	public Room currentRoom()
	{
		return current_loc;
	}

	/**
	* Primitive command to move the player from one point to a contiguous point. 
  * The Go.execute() method checks that this is a valid move. 
	*/
	public void go(Direction d)
	{	
		
		moveToRoom(current_loc.getExit(d));
		current_loc.enter();
		look();
	}
	
	/**
	 * Changes the current location of the player to the desired Room.
	 */
	public void moveToRoom(Room room)
	{
		current_loc = Model.getRoom(room.name);
	}
	/**
	 * Changes the current location of the player to the desired Room.
	 */
	public void moveToRoom(String room)
	{
		current_loc = Model.getRoom(room);
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
			inventory.addItem(i);

			inventory();
		}		
		else 
		{
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
		return inventory.removeItem(item);
	}

	/**
	* Provides direct delete access to the inventory. Use for actions and events
	* which remove items from the player's pack. Note that if items are not
	* caught by the calling class, they are effectively destroyed in the game.
	* Convenience class allowing delete by name as well as by object reference. 
	*/
	public Noun inventory_destroy(String itemName)
	{
		return inventory.removeItem(itemName);
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
			Noun n = inventory_destroy(i);
			if (n == null)
			{
				if (i.plural())
				{	
					article = (String)ZEngineMain.strings.get("GEN_Multiple_Indef_Article");
				}
				else 
				{
					article = (String)ZEngineMain.strings.get("GEN_Singular_Indef_Article");
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
	
	/**
	 * Changes the state of a noun.
	 * @param prep 
	 * The state to change the noun to.
	 */
	public void turn (Preposition prep)
	{  
		Noun noun = checkContext(prep.noun);   
		
		if (noun !=null)
		//if (inventory.containsKey(prep.noun.name))
		{
			
			noun = inventory.getItem(prep.noun.name);
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
			String s = (String)ZEngineMain.strings.get("INV_No_Item");
			String article; 
			if (!prep.noun.plural)
			{
				article = (String) ZEngineMain.strings.get("GEN_Singular_Indef_Article");
				System.out.printf(s, article, prep.noun.name);
			}
			else
			{
				article = (String) ZEngineMain.strings.get("GEN_Multiple_Indef_Article");
				System.out.printf(s, article, prep.noun.name);
			}
		}
	}

	
	/**
	 * Changes the state of a noun.
	 * @param n 
	 * The noun whose state is to be changed.
	 */
	public void turn(Noun n)
	{
		
		    Preposition p = n.prep;

			n = checkContext(n);
		    if (inventory.containsItem(n))
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

	/**
	 * Checks to see if there is a light source in the players inventory.
	 * @return 
	 * Returns true if a light source is found, false if not.
	 */
	public boolean carryingLight()
	{
		for (String key: inventory.keySet())
		{
			if (inventory.getItem(key).isLightSource())
				return true;
		}		
		return false;
	}

  /**
  * Report the contents of the player's "pack". This is a primitive command; its
  * implementation is cannot be changed by the game designer without modifying
  * the Engine source code. 
	* To do: externalize strings. 
  */
	public void inventory()
	{
		if (inventory.containerEmpty())
		{
		System.out.println("You are unburdened by material goods.");
		
		}
		
		/*
		 * Purely to demonstrate usage of both string and nouns to
		 * do the same thing.
		 
		for (String s: inventory.keySet())
		{ 
			if (!inventory.getItem(s).plural)
			{
				System.out.println("You have a " + s + ".");
				if (inventory.getItem(s).state != null)
				    System.out.println(inventory.getItem(s).itemDescription());
			}
			else
			{
	    		System.out.println("You have "+s+".");
		    	System.out.println(inventory.getItem(s).itemDescription());
			}
		}*/
		
		for (Noun n: inventory.itemSet())
		{
			if (!inventory.getItem(n).plural)
			{
				System.out.println("You have a " + n.toString()
					+ ".");
				if (inventory.getItem(n).state != null)
					System.out.println(inventory.getItem(n).itemDescription());
			}
			else
			{
			    System.out.println("You have " + n.toString() + ".");
			    System.out.println(inventory.getItem(n).itemDescription());
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
		return inventory.getContainer();
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

	    public void updateTime(int duration)
    {
        time += duration;
    }
       
    public int getCurrentTime()
    {
        return time;
    }

    public String now()
    {
        return timer.now(time);
    } 
        
    public String dateAsString()
    {
        return timer.dateAsString(time);
    } 
    


	
	public boolean yes()
	{
		return true;
	}
	
	public boolean no()
	{
		return false;
	}

	public String toString()
	{
		return "STATE";
	}
}
