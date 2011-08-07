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
	protected ArrayList<Direction> hiddenExits;
	
	public Room (String name)
	{
		this.name = name;
		exits=new HashMap<String, Room>();
		items = new HashMap<String, Noun>();
		this.hiddenObjects = new ArrayList<Noun>();
		this.hiddenExits = new ArrayList<Direction>();
	}

	public Room getExit(String direction)
	{
			return exits.get(direction);
	}
	public Room getExit(Direction direction)
	{
			return exits.get(direction.toString());
	}
		
	public  void setExit(String direction, Room room)
	{
		exits.put(direction, room);
	}
	
	public String description()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(this.description+"\n");
                if (exits.size() == 1)
                    sb.append("You can see an exit to the " + this.listExits() + "\n");
                else
                    sb.append("You can see exits to the "+ this.listExits() + "\n");	
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
	
	public static Room getRoom(String name)
	{
		System.out.println("called getRoom: "+name);
		return Model.getRoom(name);
	}
		
	public Noun take(Noun n)
	{
			
		n  = items.remove(n.toString());		
		return n;
	}

	public void drop(Noun n)
	{
		items.put(n.toString(), n);
	}

	public String toString()
	{
		return this.name;
	}
	
	public void search()
	{
		if (hiddenObjects.size() == 0)
		{
			System.out.println("A careful search of the room reveals nothing...");
		}
		for (Direction d: hiddenExits)
		{
			exits.put(d.toString(), getExit(d));
			System.out.println("You find an exit to the  "+d.toString()+"!");
		}
		hiddenExits.clear();
		for (Noun n: hiddenObjects)
		{
			items.put(n.toString(), n);
			System.out.println("You find a "+n.toString()+"!");
		}
		hiddenObjects.clear();
		
	}
	
}



