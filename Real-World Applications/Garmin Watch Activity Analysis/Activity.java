import java.util.*;
import java.time.*;

public class Activity {

	/**
	 * Represents an activity. Constructor class for ActivityDriver.java.
	 * 
	 * @author arsharma 
	 */
	
    //static fields to accumulate metrics across all Activity instances
    private static Duration totalTime = Duration.ZERO; //tracks total duration across all activities
    private static double totalKm = 0.0; //tracks total kilometers across all activities

    //instance variables for storing activity details
    private int[] date; //array to store date as [year, month, day]
    private String title; //activity title
    private double distance; //distance in kilometers
    private int calories; //calories burnt during the activity
    private Duration time; //duration of the activity
    private int avgHR; //average heart rate during activity
    private int maxHR; //maximum heart rate achieved during activity
    private double avgPace; //average pace in minutes per kilometer

    /**
     * Constructor to create an instance of Activity with all relevant details and update class-wide metrics.
     * 
     * @param date The date of the activity as an array [year, month, day].
     * @param title The title or name of the activity.
     * @param distance The distance covered during the activity in kilometers.
     * @param calories The total calories burnt during the activity.
     * @param time The total duration of the activity.
     * @param avgHR The average heart rate during the activity.
     * @param maxHR The maximum heart rate during the activity.
     * @param avgPace The average pace during the activity in minutes per kilometer.
     */
    public Activity(int[] date, String title, double distance, int calories, Duration time, int avgHR, int maxHR, double avgPace) {
        //initialize instance variables with provided parameters
        this.date = date; //set date
        this.title = title; //set title
        this.distance = distance; //set distance
        this.calories = calories; //set calories
        this.time = time; //set time
        this.avgHR = avgHR; //set average heart rate
        this.maxHR = maxHR; //set maximum heart rate
        this.avgPace = avgPace; //set average pace

        //update static totalTime and totalKm fields with this activity's metrics
        totalTime = totalTime.plus(this.time); //add this activity's duration to total
        totalKm += this.distance; //add this activity's distance to total
    }

    /**
     * Instance getters.
     *
     * @return Specified instances.
     */
    public static Duration getTotalTime() { return totalTime; } //returns total time across all activities
    public static double getTotalKm() { return Math.round(totalKm * 100.0) / 100.0; } //returns total km across all activities, rounded
    public int[] getDate() { return date; } //returns date
    public String getTitle() { return title; } //returns title
    public double getDistance() { return distance; } //returns distance
    public int getCalories() { return calories; } //returns calories
    public Duration getTime() { return time; } //returns duration
    public int getAvgHR() { return avgHR; } //returns average heart rate
    public int getMaxHR() { return maxHR; } //returns maximum heart rate
    public double getAvgPace() { return avgPace; } //returns average pace

    /**
     * Provides a string representation of the activity, formatted for readability.
     * 
     * @return A formatted string detailing the activity metrics.
     */
    @Override
    public String toString() {
        //formatting date for readability
        String formattedDate = String.format("%d-%02d-%02d", date[0], date[1], date[2]);
        //formatting time to mm:ss from Duration
        long totalMinutes = time.toMinutes();
        long secondsPart = time.getSeconds() % 60;
        String formattedTime = String.format("%02d:%02d", totalMinutes, secondsPart);
        //formatting pace to mm:ss per km
        int paceMinutes = (int) avgPace;
        int paceSeconds = (int) ((avgPace - paceMinutes) * 60);
        String formattedPace = String.format("%d:%02d min/km", paceMinutes, paceSeconds);

        //compiling all formatted strings into a single returnable string
        return "Activity Details:\n" +
               "Date: " + formattedDate + "\n" +
               "Title: " + title + "\n" +
               "Distance: " + String.format("%.2f km", distance) + "\n" +
               "Calories: " + calories + " kcal\n" +
               "Time: " + formattedTime + "\n" +
               "Average HR: " + avgHR + " bpm\n" +
               "Maximum HR: " + maxHR + " bpm\n" +
               "Average Pace: " + formattedPace;
    }

    /**
     * Parses a time string to double minutes.
     *
     * @param timeStr The time string in HH:MM:SS format.
     * @return The time in minutes.
     */
    public static double parseTime(String timeStr) { //parses hh:mm:ss to double minutes
        String[] parts = timeStr.split(":");
        double time = Integer.parseInt(parts[1]) + Integer.parseInt(parts[2]) / 60.0;
        if (parts[0] != null && !parts[0].isEmpty()) {
            time += Integer.parseInt(parts[0]) * 60; //convert hours to minutes
        }
        return time;
    }
    
    /**
     * Parses a date string into an array of integers.
     *
     * @param dateStr The date string in YYYY-MM-DD format.
     * @return The date as an array [year, month, day].
     */
    public static int[] parseDate(String dateStr) { //parses yyyy-mm-dd to int array
        String[] parts = dateStr.split("-");
        return new int[]{Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2])};
    }
    
    /**
     * Parses a pace string into double minutes.
     *
     * @param paceStr The pace string in MM:SS format.
     * @return The pace in minutes.
     */
    public static double parsePace(String paceStr) { //parses mm:ss to double minutes
        String[] parts = paceStr.split(":");
        return Integer.parseInt(parts[0]) + Integer.parseInt(parts[1]) / 60.0;
    }
    
    /**
     * Determines if the activity is considered tough.
     *
     * @return True if the activity meets certain criteria for being tough.
     */
    public boolean isTough() {
        //conditions to meet the tough criteria
        boolean isLongAndCaloric = this.distance > 4.0 && this.calories > 300; // arbitrary criteria to dictate distance and caloric criteria
        boolean hasHighHeartRate = this.maxHR > 170 || this.avgHR > 152;  // arbitrary criteria to dictate heart rate critera
        boolean hasFastPace = this.avgPace < 5.25; // arbitrary criteria to dictate pace critera

        //returns true if conditions are met
        return isLongAndCaloric && (hasHighHeartRate || hasFastPace);
    }

    /**
     * Compares this activity with another to determine which is tougher based on a set of criteria.
     * 
     * @param other The other activity to compare to.
     * @return 1 if this activity is tougher, -1 if the other is tougher, 0 if they are equally tough.
     */
    public int compareTo(Activity other) {
        //initializing point system for comparison
        int pointsThis = 0;
        int pointsOther = 0;

        //assigning points based on toughness criteria
        if (this.isTough()) pointsThis += 1;
        if (other.isTough()) pointsOther += 1;
        
        //give point to activity with highest distance
        if (this.distance > other.distance) pointsThis += 1;
        else if (other.distance > this.distance) pointsOther += 1;
        
        //give point to activity with highest calories
        if (this.calories > other.calories) pointsThis += 1;
        else if (other.calories > this.calories) pointsOther += 1;
        
        //give point to activity with highest heart rate
        if (this.maxHR > other.maxHR) pointsThis += 1;
        else if (other.maxHR > this.maxHR) pointsOther += 1;

        //returning comparison result based on the point tally based on the aforementioned factors
        return Integer.compare(pointsThis, pointsOther);
    }
}
