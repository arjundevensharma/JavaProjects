import java.util.Scanner;

public class readingTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int level;
        int difficulty = 0;
        String[] passages = {
                "The cat sat on the mat\\.The cat was cool.", // Kindergarten
                "The quick brown fox jumps over the lazy dog.\\.The cat was cool.", // Elementary School
                "It was the best of times, it was the worst of times.\\.The cat was cool.", // Middle School
                "To be, or not to be, that is the question.\\.The cat was cool.", // High School
                "It is a truth universally acknowledged, that a single man in possession of a good fortune, must be in want of a wife.\\.The cat was cool." // University/College
        };
        System.out.println("Welcome to the reading test! Which level of schooling do you belong to? Enter a number:");
        System.out.println("1. Kindergarten");
        System.out.println("2. Elementary School");
        System.out.println("3. Middle School");
        System.out.println("4. High School");
        System.out.println("5. University/College");
        level = sc.nextInt();
        switch(level) {
            case 1:
                difficulty = 0;
                break;
            case 2:
                difficulty = 1;
                break;
            case 3:
                difficulty = 2;
                break;
            case 4:
                difficulty = 3;
                break;
            case 5:
                difficulty = 4;
                break;
            default:
                System.out.println("Invalid input, please enter a number between 1 and 5.");
                System.exit(0);
        }
        String passage = passages[difficulty];
        System.out.println("Here's your passage:");
        String[] lines = passage.split("\\.");
        long startTime = System.currentTimeMillis(); // Start time measurement
        long elapsedTime = 0;
        int numWords = 0;
        double readingSpeed = 0;
        for(String line: lines) {
            System.out.println(line.trim());
            sc.nextLine(); // Wait for user to press enter
            long endTime = System.currentTimeMillis(); // End time measurement
            elapsedTime = endTime - startTime;
            readingSpeed = line.trim().length() / (elapsedTime / 1000.0 / 60.0); // Calculate reading speed in words per minute
            System.out.println("How many words did you not understand?");
            numWords = sc.nextInt();
            // Store numWords and readingSpeed for use in the book recommendation engine
            startTime = System.currentTimeMillis(); // Reset start time for next line
        }
        
        // Book recommendation engine code
        double avgReadingSpeed = lines.length / elapsedTime;
  // Calculate average reading speed in words per minute assuming 150 words per minute per line
        int totalMisunderstoodWords = numWords;
        /*for(String line: lines) {
            System.out.println("What is the meaning of the following words in the context of the passage?");
            String[] words = line.trim().split("\\s+");
            for(String word: words) {
                System.out.print(word + ": ");
                String answer = sc.nextLine();
                if(!word.equals(answer)) {
                    totalMisunderstoodWords++;
                }
            }
        }
        */
        
            double recommendedLength = (400 / avgReadingSpeed); // Calculate recommended book length in minutes
            int recommendedDifficulty = difficulty + (int) Math.ceil(totalMisunderstoodWords / (double) lines.length); // Calculate recommended book difficulty level
            switch(recommendedDifficulty) {
                case 4:
                    System.out.println("Based on your reading level and test results, we recommend the following book:");
                    System.out.println("Title: Goodnight Moon");
                    System.out.println("Author: Margaret Wise Brown");
                    System.out.println("Length: 32 pages");
                    System.out.println("Difficulty: Kindergarten");
                    break;
                case 3:
                    System.out.println("Based on your reading level and test results, we recommend the following book:");
                    System.out.println("Title: Charlotte's Web");
                    System.out.println("Author: E.B. White");
                    System.out.println("Length: 184 pages");
                    System.out.println("Difficulty: Elementary School");
                    break;
                case 2:
                    System.out.println("Based on your reading level and test results, we recommend the following book:");
                    System.out.println("Title: The Giver");
                    System.out.println("Author: Lois Lowry");
                    System.out.println("Length: 240 pages");
                    System.out.println("Difficulty: Middle School");
                    break;
                case 1:
                    System.out.println("Based on your reading level and test results, we recommend the following book:");
                    System.out.println("Title: To Kill a Mockingbird");
                    System.out.println("Author: Harper Lee");
                    System.out.println("Length: 336 pages");
                    System.out.println("Difficulty: High School");
                    break;
                case 0:
                    System.out.println("Based on your reading level and test results, we recommend the following book:");
                    System.out.println("Title: 1984");
                    System.out.println("Author: George Orwell");
                    System.out.println("Length: 328 pages");
                    System.out.println("Difficulty: University/College");
                    break;
                default:
                    System.out.println("Error: could not recommend a book.");
                    break;
            }
            System.out.println("We recommend a book that is approximately " + recommendedLength + " minutes long.");
        
    }
}