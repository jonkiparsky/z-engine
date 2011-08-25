package zengine;

/*
 * Attempt at Timer class. Time is currently 1f = 1 hour.
 * Minutes are not supported at the moment. The timer is also not
 * customisable (should the designer want 36 hours in a day, or 9 days
 * in a week, for example. Possible use of Hour/Minute/Day classes instead
 * of string/floats may be better, but will have to think about it more.
 */
public class Timer 
{
        private static String[] Days = 
        {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        private static float currentTime = 0f;
        private static String currentDay = Days[0];
        private static int dayIndex = 0;
        
        public static void increaseTime(float timeIncrement)
        {
                // Increases time, rolling over at 24 (hours)
                // Increases day once time is over 24 hours.
                if (currentTime < 24f)
                {
                        if ((currentTime += timeIncrement) % 24f != 0)
                        {
                                int dayIncrement = (int) timeIncrement / 24;
                                currentTime = currentTime % 24f;
                                dayIndex += dayIncrement;
                                if (dayIndex >= 7)
                                        dayIndex = (dayIndex - 7);
                                currentDay = Days[dayIndex];
                        }
                }
        }
        
        public static void showTime()
        {
                System.out.println("Day: " + currentDay + "\nTime: " + (int)currentTime + ":00");
        }
        
        public static float getTime()
        {
                return currentTime;
        }
}
