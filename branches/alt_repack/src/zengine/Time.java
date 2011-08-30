package zengine;

import gamefiles.time.*;
public interface Time
{


	public int getStartTime();


	/**
	* Returns date as array of ints: number of weeks, days, hours, and minutes
	* that have passed in game time since start.
	*/
	public  int[] parseDate(int time);

	public  String dateAsString(int time);
	
	/**
	* String representation of "now" - probably "time", but up to the game
	*/
	public  String now(int time);

	public String time(int time);

}
