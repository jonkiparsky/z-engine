package zengine;


import gamefiles.grammar.Put;

public class ContainerObject extends Noun
{
	Container interior;
	

	public ContainerObject(String name)
	{
		super(name);
		
	}
	

	public void execute(Put put)
	{
		System.out.println("ContainerObject "+this.getClass()+".put() called.\n"+
			"Object = "+put.what());
		
	}
	

	



}
