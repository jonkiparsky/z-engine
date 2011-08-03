package zengine;


import java.util.ArrayList;

/**
* Base class for grammar items
*/
public abstract class Grammar
{
	protected boolean error;
	protected String name;
	protected ArrayList<Grammar> frame;
	protected Class acceptable;	

	public Grammar()
	{
		error = false;	
		this.frame = new ArrayList<Grammar>();
	}
	public  boolean accept(Grammar g)
	{
		return  acceptable.isAssignableFrom(g.getClass());			
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

