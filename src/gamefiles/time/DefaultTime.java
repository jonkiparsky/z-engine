package gamefiles.time;
import java.util.Formatter;
//import java.util.StringBuffer;
import zengine.Time;
public class DefaultTime implements Time
{

private  String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday",
			"Saturday", "Sunday"};
private    int weekLength;
private   int dayLength;
private   int hourLength;
private   int minuteLength;
private   int startTime;
private Formatter formatter;  

	public DefaultTime()
	{
		StringBuffer sb = new StringBuffer();
		formatter = new Formatter(sb);
		minuteLength = 60;
		hourLength = 60* minuteLength;
		dayLength = 24 * hourLength;
		weekLength = days.length * dayLength;

		startTime= hourLength *12;  //Monday at noon

	}

	public int getStartTime()
	{
		return startTime;
	}


	/**
	* Returns date as array of ints: number of weeks, days, hours, and minutes
	* that have passed in game time since start.
	*/
	public  int[] parseDate(int time)
	{
		int week = time / weekLength;
		time %= weekLength;
		int day = time / dayLength;
		time %= dayLength;
		int hour =  time / hourLength;
		time %= hourLength;
		int minute = time / minuteLength;
		time %= minuteLength;
		int[] returnVals = 	{week, day, hour, minute};
		return returnVals;
	}	

	public  String dateAsString(int time)
	{
		int[] now = parseDate(time);
		String returnString = formatter.format("%s, %d:%d.2",days[now[1]],
			now[2],now[3]).toString(); 
		return returnString;
	}
	
	/**
	* String representation of "now" - probably "time", but up to the game
	*/
	public  String now(int time)
	{
		return time(time);	
	}

	public  String time(int time)
	{
		int [] now = parseDate(time);
		return (""+now[2]+":"+now[3]);
	}
	
		

}
