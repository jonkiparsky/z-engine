package zengine;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import gamefiles.grammar.None;

public abstract class Noun extends Grammar
{
	protected String desc;
	protected String state;
        protected boolean fixture;
	protected boolean plural;
	protected Preposition prep;
        
        protected int weight = 1;

	public Noun(String name)
	{
//		super(name);
		this.name= name;
                fixture = false;
                acceptable.add(Preposition.class);
		this.acceptable.add(None.class);
		desc = "";
	}


	public boolean fixture()
	{
		return fixture;
	}

	/**
	* Override this when an object is emitting light. This should be true only
	* when the object is emitting light: a flashlight is not a light source when
	* it is turned off.
	*/
	public boolean isLightSource()
	{
		return false;
	}
	public boolean plural()
	{
		return plural;
	}

	public String getState()
	{
		return state;
	}
        
        public int getWeight()
        {
                return weight;
        }
	
	
	public void setState(Preposition prep)
	{
		System.out.println("You can't set " + name + " status to " + prep.name);
	}
	
	public String toString()
	{
		return name;
	}

	public String itemDescription()
	{
		if (state != null)
			desc = "The "+ name + " is " + state;
	
		return (desc);
	}

	public <V extends Verb> void execute(V  verb)
	{
		System.out.println(verb.getClass().getName());	
		try
		{
			Method ex = this.getClass().getMethod("execute", verb.getClass());
			ex.invoke(this,verb);
		}
		catch(NoSuchMethodException e)
		{

		System.out.println("You can't "+verb.toString()+" the "+this.toString());
		}
		catch(SecurityException se)
		{
			System.out.println("Noun.execute threw a securityException");
		}
		catch (IllegalAccessException iae)
		{
			System.out.println("Noun.execute threw a illegalAccessException");
		}
		catch (InvocationTargetException iae)
		{
			System.out.println("Noun.execute threw a InvocationTargetException");
		}
	}
}
