package zengine;

import java.lang.reflect.*;
import java.util.HashMap;
import java.lang.Class;
import java.util.Scanner;
import java.util.Properties;
import java.util.ArrayList;
import gamefiles.grammar.*;

public class Parser
{
	private boolean error;
	Scanner scan;
	Scanner in;
	Scanner inputScanner;
	private Properties macros;

       
	/**
	* The no-arg constructor returns a Parser prepared to accept 
	* input from STDIN
	*/
	public Parser()
	{
		error = false;
		in = new Scanner(System.in);
		loadProperties();
	}	

	/**
	* Creates a parser on a particular String. This may be a candidate for
	* deletion, although it could be useful for testing.
	*/
	public Parser(String command)
	{
		error = false;
		scan = new Scanner(command);
	
		loadProperties();
	}

	public void makeMove()
	{
		String input;
		do
		{
	//		System.out.println(ZEngineMain.state.dateAsString());
			System.out.print(">> ");
			input = in.nextLine();
		} while (input.length() == 0);
	
		
		
		doMove(input);
	}	

	public void doMove(String input)
	{

//		String[] inputWords = input.split(" ");	
		ArrayList<String> inputWords = new ArrayList<String>();
		for (String s: input.split(" "))
			inputWords.add(s);
		Sentence sentence = new Sentence();
		if (sentence.accept(inputWords) !=null)
		{
			System.out.println("doMove says sentence accepted");
			sentence.execute();
		}
		else

			System.out.println("doMove says sentence not accepted");

	}	


	/**
	*	Implemented for politeness only. If this is ever needed, please explain
	*	why in this javadoc string. 
	*/

	public String toString()
	{
		return "PARSER";
	}


	/**
	* Respond to a failure to parse a String
	*/
	private void parseFail(String s)
	{
		error("I don't know what "+s+" means(pfs)");
	}


	/**
	* Respond to a failure to parse a grammar object
	*/
	private void parseFail(Grammar g)
	{
		error("Cannot understand "+g.toString()+" here(pfg)");
	}


	/**
	*	Attempts to create and return a grammar object for the class represented
	*	by the String "input"
	*	
	*/
	public Grammar tokenise( String input)
	{
		input = correctCase(input);
		Class c; 
		Grammar g = null;
		try 
		{
			c = Class.forName("gamefiles.grammar." + input);
			g = (Grammar) c.newInstance();
			/*if (g == null)
			{
				c = Class.forName("zengine." + input);
				g = (Grammar) c.newInstance();
			}*/
		}
		catch (ClassNotFoundException cnfe)
		{
			    if ((g = tokeniseRoom(input)) == null)
			    {
				    System.out.println("I don't know what "+input+" means! (cnfe)");
				    error = true;
			    }
		}
		catch (InstantiationException ie)
		{	
			if ((g = tokeniseRoom(input)) == null)
			{
				error = true;
				System.out.println("error: "+ ie.getMessage());
			}
		}
		catch (IllegalAccessException iae)
		{
			g = tokeniseRoom(input); 
			if (g == null)
			{
				error = true;
				System.out.println("error: "+ iae.getMessage());
			}
		}
		
		return g;
	}

	/**
	*	Return a Room object for the current input. This will eat input until it
	*	matches a room, which may end up being incorrect.
	*/	
	public Grammar tokeniseRoom(String input)
	{
		return null;	
	
	}

	private String correctCase(String s)
	{
		StringBuilder sb= new StringBuilder(s.toLowerCase());
		sb.replace(0,1, sb.substring(0,1).toUpperCase());
		return sb.toString();	
	}
	
	public Parser parser()
	{
		return this;
	}

	private void error(String s)
	{
		Utils.debug(this, s);
	}	

	private void loadProperties()
	{
			// hidden literal string buried in the code
			// "macros" is the partial name of the file containing abbreviated
			// command names.

		macros = PropertyLoader.getProperties("macros");

	}


	public void test()
	{
		System.out.println("---------");
		System.out.println("Parse Test");
		System.out.println("---------");	
	}

}
