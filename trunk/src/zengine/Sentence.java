package zengine;


import zengine.grammar.*;
import java.util.ArrayList;

public class Sentence extends Grammar
{
	private Grammar current;

	public Sentence()
	{
		super();
		acceptable.add(Verb.class);
		acceptable.add(Direction.class);		
		current = this;
	}


	public boolean accept(Grammar g)
	{
		if (current == this)
		{
			for (Class c : acceptable)
	      {
   	      if  (c.isAssignableFrom(g.getClass()))
      	   {
         	   complements.put(c.getName(),g);
					current = g;
            	return true;
    	    	}
      	}
      return false;

		}
		else 
		{
		 	if (current.accept(g))
			{
				current=g;
				return true;
			}
			else 
			{	
				return false;
			}
		}	
	
	}
		
	protected void execute()
	{

		//there's got to be a better way to do this

		for (String s: complements.keySet())
		{
			complements.get(s).execute();
		}
	}


}
