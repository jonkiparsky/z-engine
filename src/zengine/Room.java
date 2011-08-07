
package zengine;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.Class;

public abstract class Room
{
	
	protected String name;
	public HashMap<String, Room> exits;
	public HashMap<String, Noun> items;
	protected State state;		
	protected String description;
	Room startRoom;
	
	protected ArrayList<Noun> hiddenObjects;
	protected HashMap<String,Room> hiddenExits;
	
	public Room (String name)
	{
		this.name = name;
		exits=new HashMap<String, Room>();
		items = new HashMap<String, Noun>();
		this.hiddenObjects = new ArrayList<Noun>();
		this.hiddenExits = new HashMap<String, Room>();
	}


	/**
	* Asks this room if it has an exit in the named direction
	* Returns null if no exit exists or if you misspell the direction or get the
	* capitalization wrong. 
	*/
	public Room getExit(String direction)
	{
			return exits.get(direction);
	}

	/**
	*	Asks this room if it has an exit in the provided Direction
	*  Returns null if there is no visible exit in that direction.
	*/
	public Room getExit(Direction direction)
	{
		return exits.get(direction.toString());
	}
	

	/**
	* Informs this room that it has an exit in the named direction.
	*/	
	public  void setExit(String direction, Room room)
	{
		exits.put(direction, room);
	}

	
	/**
	* Constructs and returns a user-facing (formatted) String describing the
	* room, including any objects present and visible.
	*/
	public String description()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(this.description+"\n");
		if (exits.size() == 0)
		{
			sb.append("You can't see any way out.");
		}
		else if (exits.size() == 1)
		{
		  sb.append("You can see an exit to the " + this.listExits() + "\n");
		}
		else
		{
		    sb.append("You can see exits to the "+ this.listExits() + "\n");	
		}
		sb.append(listItems());
	
		return sb.toString();
	}
	
    public String listItems()
    {
	    int itemCount = items.size();
	    StringBuilder sb = new StringBuilder("There is ");
		    if (items.isEmpty())
			    sb.append("nothing");
	    for (String item: items.keySet())
	    {
		    if (!items.get(item).plural())
			    sb.append("a ");
		    if (itemCount == 1)
			    sb.append(item);    
		    else if (itemCount == 2)
		    {
			    sb.append(item + " and ");
			    itemCount--;
		    }
		    else
		    {
			    sb.append(item + ", ");
	 // counts down as items are appended, so last item is always prefixed by and.
			    itemCount--;
		    }
	    }
	    sb.append(" here");
	    return sb.toString();
    }

	/**
	* Returns a user-facing (formatted) list of visible exits from the room
	*/
	public String listExits()
	{
		StringBuilder sb = new StringBuilder();
		int roomCount = exits.size();
		for (String s : exits.keySet()) 
		{
			if (roomCount == 1)
			
				sb.append(s);
			else if (roomCount == 2)
			{
				sb.append(s + " and ");
				roomCount--;
			}
			else
			{
				sb.append(s + ", ");
				// Same use as for listItems()
				roomCount--;
			}
		}
		
		return sb.toString();
	}
	
	
	public void setExits()
	{
	}
	

	/**
	* Returns the single instance of the Room with this name.
	*
	* This is a wrapper method for Model.getRoom
	*/
	
	public static Room getRoom(String name)
	{
		return Model.getRoom(name);
	}
		

	/**
	* Takes an item from the current Room and returns it, removing it from the
	* Room's inventory. You must override this method to deal with items which
	* exist in quantities greater than one, including infinite objects (pools of
	* water, for example, might be infinite in quantity). 
	* Returns the object retrieved, or null if it is not in the room's inventory.
	* You must override this method if you want something to happen when you take
	* an item in a particular room. (think Indiana Jones in the opening sequence
	* of Raiders...)
	*/
	public Noun take(Noun n)
	{			
		n  = items.remove(n.toString());		
		return n;
	}

	/**
	* Places an object in the room's items list. This method does not affect the
	* player's inventory; that is handled by state. This method only affect's the
	* room's items list.
	* You must override this method if you want something to happen when you drop
	* an item. 
	*/
	public void drop(Noun n)
	{
		items.put(n.toString(), n);
	}

	/**
	* Returns the name of this room. 
	*/
	public String toString()
	{
		return this.name;
	}
	
	/**
	* Executes a search in this room. Default behavior checks the hiddenExits and
	* hiddenObjects fields and returns a stock answer if there is nothing to
	* find. 
	*/
	public void search()
	{
		if ((hiddenObjects.size() == 0) && (hiddenExits.size() == 0))
		{
			System.out.println("A careful search of the room reveals nothing...");
			return;
		}


		if (hiddenExits.size() > 0)
		{
			for (String s: hiddenExits.keySet())
			{
				System.out.println("*");
				exits.put(s, hiddenExits.get(s));
				System.out.println("You find an exit leading "+s.toString()+"!");
				System.out.println(exits.get(s));
			hiddenExits.clear();
			}
	
		}
		if (hiddenObjects.size() >0)
		{
			for (Noun n: hiddenObjects)
			{
				items.put(n.toString(), n);
				System.out.println("You find a "+n.toString()+"!");
			}
			hiddenObjects.clear();
		}
		
		System.out.println("Exits:\n-----");
		for (String s: exits.keySet())
		{
			System.out.println(s);
			System.out.println(exits.get(s));
		}
		
	}
	
}



