package zengine;


import gamefiles.grammar.None;
import java.util.ArrayList;
import java.util.HashMap;

/**
* Base class for grammar items
*/
public abstract class Grammar
{    
	protected boolean error;
	protected String name;
	protected ArrayList<ArrayList<Class>>frames;
        
        /**
         * Acceptable grammar objects.
         */
	protected ArrayList<Class> acceptable;
        
        /**
         * Associated grammar object.
         */
	protected HashMap<String, Grammar> complements;
		//complements might not be the right word...

	public Grammar()
	{
		this(" ");
	}

        /**
         * Constructor that should be used for all derived classes.
         * @param name 
         * The name of the Grammar object.
         */
	public Grammar(String name)
	{
		error = false;	
		this.frames = new ArrayList<ArrayList<Class>>();
		this.acceptable = new ArrayList<Class>();
		this.complements = new HashMap<String, Grammar>();
   //             acceptable.add(None.class);
		this.name = name;
	}
       
	void putFrame(Class... frameItems)
	{
		ArrayList<Class> frame = new ArrayList<Class>();
		for (Class c: frameItems)
		{
			frame.add(c);
		}

		frames.add(frame);
		
	}

 
        /**
         * Returns true if this Grammar object accepts the specified Grammar
         * object.
         * @param g
         * @return 
         */
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

        /**
         * Default execute() method. Should only be reached as an error, this 
         * should be overridden in all derived classes which execute.
         */
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

