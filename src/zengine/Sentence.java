package zengine;

/*
 * Change so that it holds Phrases instead?
 */
import gamefiles.grammar.*;
import java.util.ArrayList;


/**
* Represents a Sentence, ie, a complete command, parsed and ready to execute. 
*/
public class Sentence extends Phrase
{
	
	private Grammar current;
	/**
	*	Returns a Sentence, ready to accept objects for parsing.
	*/
	public Sentence()
	{
		super ("SENTENCE");
		this.putFrame(new VPhrase());
//		this.putFrame(new DirectionPhrase());		
	}
	

	
	/**
	*	Perform the action represented by this sentence. execute simply passes an
	*	execute() message to the head of this sentence; it is assumed that that
	*	object knows what to do. 
	*/	
	protected void execute()
	{
		System.out.println("Printing complements");
		for (String s: complements.keySet())
		{
			System.out.println(s);
		}
		if (complements.get(Verb.class.getName())!=null)
		{
			System.out.println("verb complement not null");
			complements.get(Verb.class.getName());
		}
		else
			System.out.println("verb complement  null");
		if (complements.get(Direction.class.getName())!=null)
		{
			System.out.println("direction complement not null");
			complements.get(Direction.class.getName());
		}
		else
			System.out.println("direction complement  null");

		for (String s: complements.keySet())
		{
			System.out.println(s);
			complements.get(s).execute();
		}
	}

	public String toString()
	{
		return name;
	}


}
