package zengine;

import java.lang.reflect.*;
import java.util.HashMap;
import java.lang.Class;
import java.util.Scanner;
import java.util.Properties;
import zengine.grammar.*;
import zengine.properties.PropertyLoader;

public class Parser
{
	private boolean error;
	Scanner scan;
	Scanner in;
	private Properties macros;

        private static String verbPackage;
        private static String prepPackage;
       
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
        
        public Parser(String verbPackage, String prepPackage)
        {
                error = false;
                loadProperties();
                in = new Scanner(System.in);
                this.verbPackage = verbPackage + ".";
                this.prepPackage = prepPackage + ".";
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

	/**
	*	There are currently three methods for accepting a move. Make the 
	*	appropriate change in ZEngineMain
	*	
	*	This one recognizes good words used wrongly and non-words, and reacts
	*	correctly to those. It does not what to do with the end of a sentence,
	*	though.
	*/
	public void makeMove()
	{
		String input;
		
		StringBuffer tokens = new StringBuffer();
		do
		{
			System.out.print(">> ");
			input = in.nextLine();
		} while (input.length() == 0);
		
		String[] move = input.split("\\W");
		
		String first = move[0];
		if (macros.get(first) != null)
			first =(String) macros.get(first);
				
		Grammar token = tokenise(first);
		if (token==null) 
		{	
			
			error ("I don't recognize that word - see parser.makeMove");
			return;
		}
		
		Sentence sentence = Sentence.getSentence(token);
		
		if (sentence == null)
		{
			error ("error 1");
			return;
		}

		if (move.length <2) 
		{
			sentence.execute();
			return;
		}
	
		String[] m = new String[move.length - 1];		
		System.arraycopy(move, 1, m, 0, m.length);
				
		for (String word: m)
		{
			Grammar g = tokenise(word);
			if (error) 
			{
				error = false;
				break;
			}
			tokens.append(g.toString());
			tokens.append(", ");
			if (g == null) 
			{
				parseFail(word);
				break;
			}		
	
			if (!sentence.accept(g))
			{
				parseFail(g);
				break;
			}
		}
		
		if (error)
		{
			error("I don't know what that word means: see parser.makeMove");
			error= false;
			return;
		}
		
		if (sentence.accept(tokenise("None")))
		{
			System.out.println(tokens.toString());
			
			sentence.execute();
		}
		else
		{
			error("premature end of input: " + tokens.toString());
		}

	}

        
	public void parseFail(String s)
	{
		error("I don't know what "+s+" means");
	}

	public void parseFail(Grammar g)
	{
		error("Cannot understand "+g.toString()+" here");
	}

	public Grammar tokenise(String input)
	{
		input = correctCase(input);
		Class c; 
		Grammar g = null;
		try 
                {
                        c = Class.forName("zengine.grammar." + input);
                        g = (Grammar) c.newInstance();
                }
		catch (ClassNotFoundException cnfe)
		{
				//System.out.println("I don't know what "+input+" means!");
                                return externTokenise(input, Model.getItemPackage());			
		}
		catch (InstantiationException ie)
		{	
			error = true;
			System.out.println("error: "+ ie.getMessage());
		}
		catch (IllegalAccessException iae)
		{
			error = true;
			System.out.println("error: "+ iae.getMessage());
		}
		
		return g;
	}
        
        private Grammar externTokenise(String input, String tryPackage)
        {
                input = correctCase(input);
		Class c; 
		Grammar g = null;
                String path = tryPackage;
		try 
                {
                        c = Class.forName(path + input);
                        g = (Grammar) c.newInstance();
                } catch (ClassNotFoundException cnfe)
		{
                        if (path == Model.getItemPackage())
                                return externTokenise(input, verbPackage);
                        else if (path == verbPackage)
                                return externTokenise(input, prepPackage);
                        // If we reach here, we've tried all possible grammar packages.
			System.out.println("I don't know what "+input+" means!");
			
		}
		catch (InstantiationException ie)
		{	
			error = true;
			System.out.println("error: "+ ie.getMessage());
		}
		catch (IllegalAccessException iae)
		{
			error = true;
			System.out.println("error: "+ iae.getMessage());
		}		
		return g;
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
		System.out.println(s);
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
