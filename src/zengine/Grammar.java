package zengine;


import java.util.ArrayList;
import java.util.HashMap;
import gamefiles.grammar.None;

/**
* Base class for grammar items
*/
public abstract class Grammar
{    
	protected boolean error;
	protected String name;
	protected ArrayList<Grammar> frame;
	protected ArrayList<Class> acceptable;
	protected HashMap<String, Grammar> complements;
		//complements might not be the right word...

	public Grammar()
	{
		this(" ");
	}

	public Grammar(String name)
	{
		error = false;	
		this.frame = new ArrayList<Grammar>();
		this.acceptable = new ArrayList<Class>();
		this.complements = new HashMap<String, Grammar>();
                acceptable.add(None.class);
		this.name = name;
	}
	public  boolean accept(Grammar g)
	{
		for (Class c : this.acceptable)
		{
			if  (c.isAssignableFrom(g.getClass()))
			{
				this.complements.put (c.getName(), g);
				return true;
			}			
		}	
		return false;		
	}

	protected void execute()
	{
		System.out.println("Can't execute "+ this.name);
	}

	/**

	public String toString()
	{
		return name;
	}
	
	/**
	* 	Convenient wrapper class for Utils.debug()
	*/
	protected void debug(String s)
	{
		Utils.debug(this, s);
	}
}

