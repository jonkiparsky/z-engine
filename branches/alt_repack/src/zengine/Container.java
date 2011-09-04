package zengine;

import java.util.HashMap;
import java.util.Set;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;

public class Container 
{
	String name;
	HashMap<String, Noun> container;
	 int size = 100;
	int weightLimit = 100;
	int currentWeight = 0;
	
	/**
	 * Default Constructor. Has no weight or size limits.
	 * @param name 
	 * The name of the container.
	 */
	public Container(String name)
	{
		this.name = name;
		container = new HashMap<String, Noun>(size);
	}
	
	/**
	 * Creates a new container with a weight and size limit.
	 * @param name
	 * The name of the container.
	 * @param size
	 * The size limit.
	 * @param weightLimit
	 * The weight limit.
	 */
	public Container(String name, int size, int weightLimit)
	{
		this.name = name;
		this.size = size;
		this.weightLimit = weightLimit;
		container = new HashMap<String, Noun>(this.size);
	}
	
	/**
	 * Returns the container.
	 */
	public HashMap<String, Noun> getContainer()
	{
		return container;
	}



	/**
	 * Removes an item from the container.
	 * @param name
	 * The name of the item to be removed.
	 * @return 
	 * The item that has been removed.
	 */
	public Noun removeItem(String name)
	{
		return container.remove(name);
	}
	
	/**
	 * Remove an item from the container.
	 * @param item
	 * The item to be removed.
	 * @return 
	 * The item that has been removed.
	 */
	public Noun removeItem(Noun item)
	{
		return container.remove(item.toString());
	}
	
	/**
	 * Returns a copy of an item given a specified name.
	 * @param name
	 * The name of the item you want.
	 * @return 
	 * A copy of the item you want.
	 */
	public Noun getItem(String name)
	{
		return container.get(name);
	}
	
	/**
	 * Returns a copy of a specified item.
	 * @param item
	 * The item you want.
	 * @return 
	 * A copy of the item you want.
	 */
	public Noun getItem(Noun item)
	{
		return container.get(item.toString());
	}
	
	/**
	 * Adds an item to the container.
	 * @param item 
	 * The item to be added.
	 */
	public boolean addItem(Noun item)
	{
		if (this.allowAdd(item))
		{
			container.put(item.name, item);
			return true;
		}	 
		else
		{
			return false;
		}
	}
	
	/**
	 * Returns a Set<String> of the keys within the container.
	 */
	public Set<String> keySet()
	{
		return container.keySet();
	}
	
	/**
	 * Returns a ArrayList<Noun> of the values within the container.
	 * @return 
	 */
	public ArrayList<Noun> itemList()
	{
				ArrayList<Noun> list = new ArrayList<Noun>();
				for (Noun noun: container.values())
				{
					list.add(noun);
				}
				return list;	
	}
	
	/**
	 * Return true if the container contains the item.
	 */
	public boolean containsItem(Noun item)
	{
		return container.containsKey(item);
	}


	public boolean allowAdd(Noun item)
	{
		if (containerFull())
			return false;

		if (! checkNewWeight(item))
			return false;
		return true;
	}
	
	/**
	 * Returns true if the container is full and false if it isn't.
	 */
	public boolean containerFull()
	{
		if (container.size() >= size)
			return true;
		else
			return false;
	}
	
	/**
	 * Checks whether adding an item will raise the current weight above
	 * the weightLimit of the container. Returns true if the item can be
	 * added.
	 * @param n
	 * The item to check.
	 */
	public boolean checkNewWeight(Noun n)
	{
		if ((currentWeight += n.getWeight()) > weightLimit)
			return false;
		return true;
	}
	
	/**
	 * Returns true if the container is empty.
	 */
	public boolean containerEmpty()
	{
		if (container.size() == 0)
			return true;
		else
			return false;
	}
}
