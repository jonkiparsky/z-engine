package zengine;
import gamefiles.grammar.None;
import gamefiles.grammar.Go;

/**
* Abstract class representing the grammatical class of directions
*/
public class Direction extends Word
{
	String name;

	public Direction()
	{
		super("Direction");
		this.name = "Direction object created for parsing";
	}


        /**
         * Constructor to be used.
         * @param name 
         * Name of the direction.
         */
	public Direction(String name)
	{
		super(name);
		this.name = name;
		this.acceptable.add(None.class);
	}
	
	public String toString()
	{
		return name;
	}

	
	/**
	* A user may enter a direction name to go in that direction; this execute()
	* method allows that functionality.
	*/
	public void execute()
	{
		System.out.println("Executing " +this.getClass().getName());
		Go go = new Go((Direction)this);
		go.execute();
	}

}

