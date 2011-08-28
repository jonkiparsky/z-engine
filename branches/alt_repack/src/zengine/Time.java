package zengine;

public class Time 
{
        private static String[] days = {
                "Monday",
                "Tuesday",
                "Wednesday",
                "Thursday",
                "Friday",
                "Saturday",
                "Sunday" };
        
        private static int mins;
        private static int hours;
        private static String currentDay;
        private static int time;            // In timeSteps. 
        private static int minsInHour;
        private static int hoursInDay;
        private static int timeStep;
        private static int dayIndex;
        
        static
        {
                resetTime();
        }
        
        public static void resetTime()
        {
                mins = 0;
                hours = 0;
                dayIndex = 0;
                currentDay = days[dayIndex];
                timeStep = 1;
                minsInHour = 60;
                hoursInDay = 24; 
        }
        public static void increaseTime(int timeIncrease)
        {
                if (mins < minsInHour)
                {
                        int hourIncrease = (timeIncrease * timeStep) / minsInHour;
                        // if Mins != 0 after calculation.
                        mins += ((timeIncrease * timeStep) % minsInHour);
                        hours += mins / minsInHour;
                        mins = mins % minsInHour;                                
                        hours += hourIncrease;
                        
                        if (hours > hoursInDay)
                        {
                                dayIndex++;
                                if (dayIndex > days.length)
                                        dayIndex = dayIndex - days.length;
                                currentDay = days[dayIndex];
                                hours = hours % hoursInDay;
                        }
                }
                
                time += (timeIncrease / timeStep);
                System.out.println("Time Steps: " + time);
        }
        
        public static void setTimeStep(int newTimeStep)
        {
                timeStep = newTimeStep;
        }
        
        public static void showTime()
        {
                String showMins, showHours;
                if (mins < 10)
                        showMins = "0" + String.valueOf(mins);
                else
                        showMins = String.valueOf(mins);
                
                if (hours < 10)
                        showHours = "0" + String.valueOf(hours);
                else
                        showHours = String.valueOf(hours);
                
                System.out.println("Day: " + currentDay +
                        "\nTime: " + showHours + ":" + showMins);
        }
                /*
                if (currentTime < 24f)
                {
                        if ((currentTime += (stepIncrement * timeStep) % hoursInDay) != 0)
                        {
                                float dayIncrement =  stepIncrement / hoursInDay;
                                currentTime = currentTime % hoursInDay;
                                dayIndex += dayIncrement;
                                if (dayIndex >= daysList.length)
                                        dayIndex = (dayIndex - daysList.length);
                                currentDay = daysList[dayIndex];
                        }
                }*/
}
