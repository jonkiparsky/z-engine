package zengine;

/*
 * Change so that it holds Phrases instead?
 */
import zengine.grammar.*;
import java.util.ArrayList;

public class Sentence extends Grammar
{
	private Grammar current;

	private Sentence(Class c, Grammar g)
	{
		super();
		acceptable.add(Verb.class);
		acceptable.add(Direction.class);
		current = g;
		complements.put(c.getName(), g);
		System.out.println("initiated Sentence with "+g.toString());
	}


	protected static Sentence getSentence(Grammar g)
	{
			if (Verb.class.isAssignableFrom(g.getClass()))
			{
				return new Sentence(Verb.class,g);
			}
			if (Direction.class.isAssignableFrom(g.getClass()))
			{
				return new Sentence(Direction.class, g);
			}

		return null;
	}

	public boolean accept(Grammar g)
	{
		StringBuffer sb= new StringBuffer();
                
                boolean accepted = current.accept(g);
		for(String s: complements.keySet())
		{
                        
			sb.append(complements.get(s).toString());
		}
	
		System.out.println("Sentence.accept("+g.toString()+") complements = "+
			sb.toString()+ " current = " +current);
			
		if (current == null) return false;

		//boolean accepted = current.accept(g); 

		

	 	if (accepted)
		{
			System.out.println("accepted " + g.toString());
			System.out.println(g.getClass().toString());
			current=g;

			sb= new StringBuffer();
      	for(String s: complements.keySet())
      	{
        		 sb.append(complements.get(s).toString());
      	}

			System.out.println("Result = true. Complements = "+ sb.toString());
			return true;
			

		}
		else 
		{	
			System.out.println("Result = false");
			return false;
		}

	}
		
	protected void execute()
	{
		for (String s: complements.keySet())
		{
			complements.get(s).execute();
		}
	}


}
