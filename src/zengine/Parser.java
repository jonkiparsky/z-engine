package zengine;

import java.lang.reflect.*;
import java.util.HashMap;
import java.lang.Class;
import java.util.Scanner;
import zengine.rooms.*;
import zengine.verbs.*;

public class Parser
{
	private boolean error;
	Scanner scan;
	Scanner in;

	public Parser()
	{
		error = false;
		in = new Scanner(System.in);
	}	

	public Parser(String command)
	{
		error = false;
		scan = new Scanner(command);
	}

	public void test()
	{
		System.out.println("---------");
		System.out.println("Parse Test");
		System.out.println("---------");
	
		Grammar g = tokenise("inventory");
		g.execute();

		System.out.println(">>Go north");	
		Grammar gram = tokenise("Go");
		g = tokenise("north");
		if (gram.accept(g))
			gram.execute();

		System.out.println(">>take flash");	
gram = tokenise("take");
		g = tokenise("flashlight");
		if (gram.accept(g))
			gram.execute();
	
		System.out.println(">>take north");	
gram = tokenise("take");
		g = tokenise("north");
		if (gram.accept(g))
			gram.execute();

			

		System.out.println(">>Go south");	
		gram = tokenise("Go");
		g = tokenise("south");
		if (gram.accept(g))
			gram.execute();
		
	
		System.out.println(">>take flash");	
		gram = tokenise("take");
		g = tokenise("flashlight");
		if (gram.accept(g))
			gram.execute();

		System.out.println(">>drop flash");	
		gram = tokenise("drop");
		g = tokenise("flashlight");
		if (gram.accept(g))
			gram.execute();

		System.out.println(">>Go north");	
		gram = tokenise("Go");
		g = tokenise("north");
		if (gram.accept(g))
			gram.execute();

		System.out.println(">>Go south");	
		gram = tokenise("Go");
		g = tokenise("south");
		if (gram.accept(g))
			gram.execute();

		System.out.println(">>drop flash");	
		gram = tokenise("drop");
		g = tokenise("flashlight");
		if (gram.accept(g))
			gram.execute();


/*
		System.out.println(">>entertest flash");	
		gram = tokenise("entertest");
		g = tokenise("flashlight");
		if (gram.accept(g))
			gram.execute();
*/
		g = tokenise("inventory");
		g.execute();
}


	public void move()
	{
		
		System.out.print(">> ");
		String[] move = in.nextLine().split(" ");
		Grammar g = tokenise(move[0]);
		if (g.accept(tokenise(move[1])))
		{
			g.execute();
		}
	}


	public Verb parse(String verb, String complement)
	{
		Verb ret=null;
		Grammar v = tokenise(verb);
		Grammar c = tokenise(complement);
		try{
		 ret = (Verb)v.getClass().getConstructor(c.getClass().
						getSuperclass()).newInstance(c);
		}

		catch (Exception e)
		{
			System.out.println("Parse fuckup!");
			e.printStackTrace();

	
		}

		return ret;
	}

	private <T> T  forceType(T word)
	{
		return word;
	}


	public void parseFail(Grammar g)
	{
		System.out.println("Cannot understand "+g.toString()+" here");
	}

	public Grammar tokenise( String input)
	{
		input = correctCase(input);
		Class c; 
		Grammar g=null;
		try{
		c = Class.forName("zengine."+input);
		g = (Grammar)c.newInstance();
		}
		catch (Exception e)
		{
			e.printStackTrace();
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

	public boolean endOfString()
	{
		return !scan.hasNext();
	}

	
}
