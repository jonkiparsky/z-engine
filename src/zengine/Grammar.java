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
	protected ArrayList<ArrayList<Grammar>>frames;
	protected HashMap<String, Grammar> complements;
 
        /**
         * Acceptable grammar objects.
         */
	protected ArrayList<Class> acceptable;
        
        /**
         * Associated grammar object.
         */

	public Grammar()
	{
		this("Unnamed Grammar Object");
	}

        /**
         * Constructor that should be used for all derived classes.
         * @param name 
         * The name of the Grammar object.
         */
	public Grammar(String name)
	{
		error = false;	
		this.frames = new ArrayList<ArrayList<Grammar>>();
		this.acceptable = new ArrayList<Class>();
		this.name = name;
		this.complements = new HashMap<String, Grammar>();
	}
       
	void putFrame(Grammar... frameItems)
	{
		ArrayList<Grammar> frame = new ArrayList<Grammar>();
		for (Grammar g: frameItems)
		{
			frame.add(g);
		}

		frames.add(frame);
		
	}

 
        /**
         * Returns true if this Grammar object accepts the specified Grammar
         * object.
         * @param g
         * @return 
         */
	public abstract  Grammar accept(ArrayList<String> input);

		
        /**
         * Default execute() method. Should only be reached as an error, this 
         * should be overridden in all derived classes which execute.
         */
	protected void execute()
	{
		System.out.println("Can't execute "+ this.name);
	}

	/**
	* 	Convenient wrapper class for Utils.debug()
	*/
	protected void debug(String s)
	{
		Utils.debug(this, s);
	}
}

