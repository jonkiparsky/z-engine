package zengine;


import java.util.ArrayList;
import java.util.HashMap;

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
		error = false;	
		this.frame = new ArrayList<Grammar>();
		this.acceptable = new ArrayList<Class>();
		this.complements = new HashMap<String, Grammar>();
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
/*	
	protected Grammar expect (Grammar parent, Grammar expected)
	{
		return null;
	}
*/		
/*	public void put(Grammar g)
	{
		System.out.println("called Grammar.put(): "+g.toString());
		ZEngineMain.parser.parseFail(g);
	}
*/
/*
	protected boolean endOfString()
	{
		return true;
	}
*/
	
}

