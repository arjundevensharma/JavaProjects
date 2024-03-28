package ISC4U;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.io.*;
import java.time.*;
import java.time.format.TextStyle;

public class ActivityDriver {
	
	/** Analyze and search activity file running data sourced from Mr. Vojnov's Garmin Watch!
	 *  
	 *  @author arsharma 
	 **/
	
    public static void main(String[] args) throws InterruptedException {
        //welcome message
        System.out.println("Welcome to Arjun's Activity Tracker!");
        System.out.println("This program allows you to import activity data from a specific year, view statistics, and search through activities.\n");

        Scanner in = new Scanner(System.in);
         //declare an integer for menu choice
        int userMenuChoice;

        //prompt year
        System.out.print("Please enter the year to import data from (2018, 2019, 2020, 2021): ");
        //read year as string
        String yearInput = in.nextLine();

        //construct filename from user input
        String fileName = yearInput + ".txt";
        //declare arraylist for activities
        ArrayList<Activity> activitiesList = new ArrayList<>();

        //try block for file reading
        try {
            //call readActivities method
            activitiesList = readActivitiesFromDatabase(fileName);
            //inform user of success
            System.out.println("Data successfully imported for the year " + yearInput + ".\n");
            //pause for effect
            TimeUnit.SECONDS.sleep(1);
        } catch (FileNotFoundException e) {
            //handle file not found
            System.err.println("The file for the year " + yearInput + " was not found. Please ensure the file exists and try again.");
            //exit if file not found
            return;
        } catch (InterruptedException e) {
            //handle interrupted exception
            Thread.currentThread().interrupt(); //good practice
        }

        //main loop for menu
        do {
            //display menu options
            System.out.print("---Activities Main Menu---\n" +
                               "Select one of the three following actions by entering a number (1-3):" +
                               "\n1. Generate Stats" +
                               "\n2. Search Activities" +
                               "\n3. Exit" +
                               "\nYour choice:");
            //validate input is an int
            while (!in.hasNextInt()) {
                //inform user of invalid input
                System.out.println("That's not a valid input. Please enter a number between 1 and 3.");
                in.next(); //consume non-integer input in the case of in-valid non-integer input
            }
            //read choice
            userMenuChoice = in.nextInt();

            //switch based on the user-inputted choice
            switch (userMenuChoice) {
                case 1: //generate stats case
                    System.out.println();
                    generateStats(in, activitiesList);
                    break;
                case 2: //search activities case
                    System.out.println();
                    searchActivities(in, activitiesList);
                    break;
                case 3: //exit case
                    System.out.println("\nThank you for using Arjun's Activity Tracker. Goodbye!");
                    break;
                default: //invalid choice default case
                    System.out.println("Invalid choice. Please enter a number between 1 and 3.");
            }
        } while (userMenuChoice != 3); //loop until exit
    }

    /**
     * Reads activities from a specified database file.
     *
     * @param filename The name of the file to read activities from.
     * @return ArrayList of Activity objects read from the file.
     * @throws FileNotFoundException If the specified file is not found.
     */
    public static ArrayList<Activity> readActivitiesFromDatabase(String filename) throws FileNotFoundException {
        //scanner for file reading
        Scanner fileScanner = new Scanner(new File(filename));
        //list to store activities
        ArrayList<Activity> activitiesFromFile = new ArrayList<>();

        //loop through file lines
        while (fileScanner.hasNextLine()) {
            //read line
            String line = fileScanner.nextLine();
            try {
                //split line into parts
                String[] lineParts = line.split("\t");
                //split date and time
                String[] dateTimeParts = lineParts[0].split(" ");
                //parse date
                int[] activityDate = Activity.parseDate(dateTimeParts[0].trim());
                //trim title
                String activityTitle = lineParts[1].trim();
                //parse distance
                double activityDistance = Double.parseDouble(lineParts[2].trim());
                //parse calories
                int activityCalories = Integer.parseInt(lineParts[3].trim());
                //parse duration
                Duration activityDuration = Duration.ofMinutes((long) Activity.parseTime(lineParts[4].trim()));
                //parse avgHR
                int activityAvgHR = Integer.parseInt(lineParts[5].trim());
                //parse maxHR
                int activityMaxHR = Integer.parseInt(lineParts[6].trim());
                //parse avgPace
                double activityAvgPace = Activity.parsePace(lineParts[7].trim());

                //create and add new activity
                Activity newActivity = new Activity(activityDate, activityTitle, activityDistance, activityCalories, activityDuration, activityAvgHR, activityMaxHR, activityAvgPace);
                activitiesFromFile.add(newActivity);
            } catch (Exception e) {
                //log error on parsing failure
                System.err.println("Error parsing line from file: " + line + "\nError message: " + e.getMessage());
            }
        }
        //close scanner
        fileScanner.close();

        //return list of activities
        return activitiesFromFile;
    }

    /**
     * Generates statistics from a list of activities and interacts with the user via a submenu.
     *
     * @param in Scanner object for reading user input.
     * @param activities ArrayList of Activity objects to generate statistics from.
     * @throws InterruptedException If thread sleep is interrupted.
     */
    private static void generateStats(Scanner in, ArrayList<Activity> activities) throws InterruptedException {
        //declare variable for submenu choice
        int statsMenuChoice;
        //submenu loop
        do {
            //display submenu options
            System.out.println("---Stats Submenu---\n" +
                               "Select one of the four following actions by entering a number (1-4):" +
                               "\n1. Output total km run\n" +
                               "2. Output total time run\n" +
                               "3. Totals for month\n" +
                               "4. Exit to main menu");
            System.out.print("Your choice: ");
            //ensure input is an int
            while (!in.hasNextInt()) {
                //inform of invalid input
                System.out.println("Invalid input. Please enter a number (1-5).");
                in.next(); //clear incorrect input
            }
            //read choice
            statsMenuChoice = in.nextInt();

            //process submenu choice
            switch (statsMenuChoice) {
                case 1: //total km
                    double totalKilometers = Activity.getTotalKm();
                    System.out.println("\nTotal KM run this year: " + totalKilometers + " km");
                    //marathon comparison
                    if (totalKilometers > 42) {
                        System.out.println("That's the equivalent of " + (int)(totalKilometers / 42) + " marathons!");
                    }
                    //great wall goal
                    System.out.println("Just " + (21196 - totalKilometers) + " more kilometres to go and you could you have ran the entire Great Wall of China (21,196 km)...!\n");
                    break;
                case 2: //total time
                    long totalMinutesRun = Activity.getTotalTime().toMinutes();
                    System.out.println("\nTotal time run this year: " + totalMinutesRun + " minutes");
                    //days running comparison
                    if (totalMinutesRun > 1440) {
                        System.out.println("That's over " + totalMinutesRun / 1440 + " days spent running.");
                    }
                    //harry potter marathon calculation
                    System.out.println("You could have watched all the Harry Potter movies back to back " + (totalMinutesRun/1180) + " times...\n");
                    break;
                case 3: //totals for month
                    System.out.print("\nPlease enter month (Jan=1, Feb=2, etc): ");
                    int monthInput = in.nextInt();
                    printTotalsForMonth(monthInput, activities);
                    System.out.println();
                    break;
                case 4: //exit submenu
                    System.out.println("\nReturning to main menu...");
                    //pause before returning
                    TimeUnit.SECONDS.sleep(1);
                    return; //exit submenu
                default: //invalid choice handling
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (statsMenuChoice != 4); //loop until exit chosen
    }

    /**
     * Searches activities based on user criteria and interacts with the user via a submenu.
     *
     * @param in Scanner object for reading user input.
     * @param activities ArrayList of Activity objects to search through.
     * @throws InterruptedException If thread sleep is interrupted.
     */
    private static void searchActivities(Scanner in, ArrayList<Activity> activities) {
        //declare variable for submenu choice
        int searchMenuChoice;
        //search submenu loop
        do {
            //display search submenu options
            System.out.println("\n---Search Activities Submenu---\n" +
                    "Select one of the five following actions by entering a number (1-5):" +
                    "\n1. Search by name\n" +
                    "2. Search by Distance\n" +
                    "3. Output tough activities\n" +
                    "4. Output toughest activity\n" +
                    "5. Exit to main menu");
            System.out.print("Your choice: ");
            //read choice
            searchMenuChoice = in.nextInt();
            in.nextLine(); //clear buffer after int input

            //try block for interrupted exception
            try {
                //process search submenu choice
                switch (searchMenuChoice) {
                    case 1: //search by name
                        System.out.print("Enter activity name to search for: ");
                        String name = in.nextLine();
                        int nameCount = searchByName(name, activities);
                        System.out.println("Found " + nameCount + " activity records matching \"" + name + "\", as listed above.");
                        TimeUnit.SECONDS.sleep(1);
                        break;
                    case 2: //search by distance
                        System.out.print("\nEnter minimum distance in kilometers: ");
                        double minDistance = in.nextDouble();
                        System.out.print("Enter maximum distance in kilometers: ");
                        double maxDistance = in.nextDouble();
                        int distanceCount = searchByDistance(minDistance, maxDistance, activities);
                        System.out.printf("Found %d activity records within the distance range %.2f km to %.2f km, as listed above.\n", distanceCount, minDistance, maxDistance);
                        TimeUnit.SECONDS.sleep(1);
                        break;
                    case 3: //output tough activities
                        int toughCount = outputToughActivities(activities);
                        System.out.println("Found " + toughCount + " 'tough' activity records based on specified criteria of calorie burn, heart rate, pace, and distance, as listed above.");
                        TimeUnit.SECONDS.sleep(1);
                        break;
                    case 4: //output toughest activity
                        boolean found = outputToughestActivity(activities);
                        if (!found) {
                            System.out.println("\nNo 'tough' activities found.");
                        }
                        TimeUnit.SECONDS.sleep(1);
                        break;
                    case 5: //exit to main menu
                        System.out.println("\nReturning to main menu...");
                        TimeUnit.SECONDS.sleep(1);
                        break;
                    default: //invalid choice handling
                        System.out.println("Invalid choice. Please select a number between 1 and 5.");
                }
            } catch (InterruptedException e) {
                //handle interrupted exception
                Thread.currentThread().interrupt();
            }
        } while (searchMenuChoice != 5); //loop until exit is chosen
    }

    /**
     * Prints totals for a specified month from the list of activities.
     *
     * @param month The month for which to print totals.
     * @param activities ArrayList of Activity objects to calculate totals from.
     */
    private static void printTotalsForMonth(int month, ArrayList<Activity> activities) {
        //initialize totals
        double totalKm = 0.0;
        Duration totalTime = Duration.ZERO;
        int totalCalories = 0;
        double totalPace = 0.0;
        int count = 0;
        String monthName = "";
        int year = 0;

        //loop through activities
        for (Activity activity : activities) {
            //create date object from activity date
            LocalDate date = LocalDate.of(activity.getDate()[0], activity.getDate()[1], activity.getDate()[2]);
            //check if activity is in specified month
            if (date.getMonthValue() == month) {
                //set month name and year on first match
                if (count == 0) {
                    //this line converts the number inputted (1, 2, 3, etc.) into into a localized month name string.
                	//'FULL' is used to specify that the full text name of the month (e.g., "January", "February") should be returned
                	//in contrast to other styles like SHORT (e.g., "Jan", "Feb") and NARROW (single letter).
                    monthName = date.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault());
                    year = date.getYear();//get current year
                }
                //accumulate totals
                totalKm += activity.getDistance();
                totalTime = totalTime.plus(activity.getTime());
                totalCalories += activity.getCalories();
                totalPace += activity.getAvgPace();
                count++;
            }
        }

        //if activities found, print totals
        if (count > 0) {
            double averageCalories = (double) totalCalories / count;
            double averagePace = totalPace / count;
            System.out.printf("\nFor %s %d: Total KM = %.2f, Total Time = %d minutes, Average Calories = %.2f, Average Pace = %.2f min/km\n",
                    monthName, year, totalKm, totalTime.toMinutes(), averageCalories, averagePace);
        } else {
            //inform if no activities found
            System.out.println("No activities found for the specified month.");
        }
    }

    /**
     * Searches activities by distance range and returns the count of matching activities.
     *
     * @param minDistance The minimum distance of activities to include.
     * @param maxDistance The maximum distance of activities to include.
     * @param activities ArrayList of Activity objects to search through.
     * @return The count of activities within the specified distance range.
     */
    private static int searchByDistance(double minDistance, double maxDistance, ArrayList<Activity> activities) {
        //initialize counter
        int count = 0;
        //loop through activities to find matches
        for (Activity activity : activities) {
            //check if activity distance is within range
            if (activity.getDistance() >= minDistance && activity.getDistance() <= maxDistance) {
                //increment counter for each match
                count++;
            }
        }

        //print count before listing activities
        System.out.printf("\nFound %d activity records within %.2f km to %.2f km distance range:\n\n", count, minDistance, maxDistance);

        //if matches found, list them
        if (count > 0) {
            for (Activity activity : activities) {
                //check again for matches to list
                if (activity.getDistance() >= minDistance && activity.getDistance() <= maxDistance) {
                    //print each matching activity
                    System.out.println(activity + "\n");
                }
            }
        }

        //return count of matches
        return count;
    }

    /**
     * Searches activities by name and returns the count of matching activities.
     *
     * @param name The name to search for in activity titles.
     * @param activities ArrayList of Activity objects to search through.
     * @return The count of activities with titles containing the specified name.
     */
    private static int searchByName(String name, ArrayList<Activity> activities) {
        //initialize counter
        int count = 0;
        //loop through activities to find matches by name
        for (Activity activity : activities) {
            //case insensitive check if activity title contains the name
            if (activity.getTitle().toLowerCase().contains(name.toLowerCase())) {
                //print each matching activity
                System.out.println(activity + "\n");
                //increment counter for each match
                count++;
            }
        }
        //return count of matches
        return count;
    }

    /**
     * Outputs all 'tough' activities and returns the count.
     *
     * @param activities ArrayList of Activity objects to check for 'tough' activities.
     * @return The count of 'tough' activities.
     */
    private static int outputToughActivities(ArrayList<Activity> activities) {
        //initialize counter
        int count = 0;
        //loop through activities to find 'tough' ones
        for (Activity activity : activities) {
            //check if activity is marked as 'tough'
            if (activity.isTough()) {
                //print each 'tough' activity
                System.out.println(activity + "\n");
                //increment counter for each 'tough' activity
                count++;
            }
        }
        //return count of 'tough' activities
        return count;
    }

    /**
     * Finds and outputs the toughest activity, if any, and returns a boolean indicating if found.
     *
     * @param activities ArrayList of Activity objects to determine the toughest activity from.
     * @return True if the toughest activity was found and outputted, false otherwise.
     */
    private static boolean outputToughestActivity(ArrayList<Activity> activities) {
        //initialize variable for toughest activity
        Activity toughestActivity = null;
        //loop through activities to find the toughest one
        for (Activity activity : activities) {
            //check if current activity is tougher than the toughest found so far
            if (toughestActivity == null || activity.compareTo(toughestActivity) > 0) {
                //update toughest activity
                toughestActivity = activity;
            }
        }

        //if a toughest activity was found
        if (toughestActivity != null) {
            //print the toughest activity
            System.out.println("\nToughest activity: " + toughestActivity);
            //return true indicating a toughest activity was found
            return true;
        }
        //return false if no toughest activity was found
        return false;
    }

}