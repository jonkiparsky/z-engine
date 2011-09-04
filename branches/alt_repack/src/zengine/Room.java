package zengine;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.Class;
import zengine.Container;

public abstract class Room extends Grammar
{
	/**
	* Used to determine what the player has done in the room. 
	*/
	enum PlayerInteractionState { NOT_ENTERED, NEW_ENTERED, PREV_ENTERED}
    
	protected String name;
	protected String teleportName;
	protected HashMap<String, Room> exits;
	//public HashMap<String, Noun> items;
	protected Container items;
	protected State state;		
	protected String description;
	protected String longDescription;
	protected PlayerInteractionState interactState;
	protected Room startRoom;
	protected boolean isDark;	
	
	protected ArrayList<Noun> hiddenObjects;
	protected HashMap<String,Room> hiddenExits;
	
	/**
	 * Creates a new room and instantiates properties to default.
	 * @param name 
	 * The name of the room.
	 */
	public Room (String name)
	{
		this.name = name;
		this.teleportName = name;
		interactState = PlayerInteractionState.NOT_ENTERED;
		exits=new HashMap<String, Room>();
		items = new Container("Items");
		this.hiddenObjects = new ArrayList<Noun>();
		this.hiddenExits = new HashMap<String, Room>();
		isDark = false;
	}
	
	/**
	 * Returns a HashMap<String, Room> of all exits.
	 */
	public HashMap<String, Room> getExits()
	{
		return exits;
	}

	/**
	*  Called when entering a Room. Always call super.enter() when overriding, or
	*  Bad Things will happen. 
	*/
	public void enter()
	{

		switch (interactState)
		{
			case NOT_ENTERED:
			    interactState = PlayerInteractionState.NEW_ENTERED;
			    break;
			case NEW_ENTERED:
			    interactState = PlayerInteractionState.PREV_ENTERED;
			    break;
			default:
			    break;
		}
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
	public void setExit(String direction, Room room)
	{
		exits.put(direction, room);
	}
	
	/**
	* Returns the name of a teleport room from this room. Returns null
	 * if there is no teleport room.
	*/	
	public String teleportRoom()
	{
		return teleportName;
	}
	
	/**
	* Constructs and returns a user-facing (formatted) String describing the
	* room, including any objects present and visible. Description varies
	* based on interactState.
	*/
	public String description()
	{
		if (isDark && ! ZEngineMain.state.carryingLight())
		{
			return "It is dark; you are likely to stub your toe. ";
		}
		StringBuilder sb = new StringBuilder();
		switch (interactState)
		{
		    case NOT_ENTERED:
				if (longDescription != null)
					sb.append(this.longDescription + "\n");
				else
					sb.append(description + "\n");
				break;
		    case NEW_ENTERED:
		    case PREV_ENTERED:
				sb.append(this.description + "\n");
				break;
		}
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
		StringBuilder sb = new StringBuilder("There is ");
		ArrayList<Noun> toRemove = new ArrayList<Noun>();
		if (!items.containerEmpty())
		{
			ArrayList<Noun> itemList = items.itemList();
			for (Noun n : itemList)
			{
				if (n.plural())
					sb.append("a ");
				
				if (itemList.size() == 1)
					sb.append(n.name);
				else if (itemList.size() == 2)
					sb.append(n.name + " and ");
				else
					sb.append(n.name + ", ");
				toRemove.add(n);	
			}
			for (Noun n: toRemove)
			{
				itemList.remove(n); // Reduces size.
			}
			sb.append(" here.");
		}
		return sb.toString();
		/*
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
				// counts down as items are appended, so last 
				//item is always prefixed by and.
				itemCount--;
			}
		}
		sb.append(" here");
		return sb.toString();
		 */
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
	
	/**
	 * Used for room creation. Override to include room.setExits().
	 */
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
		n  = items.removeItem(n);		
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
		items.addItem(n);
	}

	/**
	* Returns the name of this room. 
	*/
	public String toString()
	{
		return this.name;
	}
	
	/**
	* Executes a search in this room. Default behavior checks the hiddenExits
	* and hiddenObjects fields and returns a stock answer if there is 
	* nothing to find. 
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
      		setExit(s, hiddenExits.get(s));
				System.out.println("You find an exit leading "+s.toString()+"!");
			hiddenExits.clear();
			}
	
		}
		if (hiddenObjects.size() >0)
		{
			for (Noun n: hiddenObjects)
			{
				items.addItem(n);
				System.out.println("You find a "+n.toString()+"!");
			}
			hiddenObjects.clear();
		}
	}
}
