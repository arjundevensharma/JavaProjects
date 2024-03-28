package ISC4U;

import java.util.*;
import java.io.IOException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;

public class PerfectNumberFinder {

    //checks if prime numbers form Mersenne primes, outputs corresponding perfect number
    public static void main(String[] args) throws IOException {
    	
    	Date currentDate = new Date();

        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
        sdf.setTimeZone(TimeZone.getDefault()); // Ensure we're using the default timezone
    	
    	System.out.print("A perfect number is a number whose factors add up to itself. 6 is the smallest perfect number: 6 - 1 + 2 + 3.\nAs of now, there are ONLY 51 perfect numbers found. TCS has decided to join the grand hunt.\n\nPress ENTER to start the 2022 Great TCS Perfect Number Search...");
        
    	System.in.read();
    	
    	System.out.println("\nStart time: " + sdf.format(currentDate));
    	
    	System.out.println("#\t\t\tPerfectNumber\t\t\t# of Digits\t\t\tSeconds Taken\t\t\tDays Taken\t\t\t\t");
        
        long startTime = System.nanoTime();
        
        double elapsedTimeInSeconds = (System.nanoTime() - startTime) / 1_000_000_000.0;

            // Print to 4 decimal places
        System.out.printf("%.4f seconds since program started.%n", elapsedTimeInSeconds);

            
    	ArrayList<Integer> primes = primesWithSieveOfAtkin(82589933); //set this to 82589933 if this program is actually going to be used
    	System.out.println("#1: t\t\t6\t\t\t1\t\t\t" + elapsedTimeInSeconds + "\t\t\t" + "\t\t\t\t" + sdf.format(currentDate));

        int count = 1;
        
        for (int p : primes) {
            if (isMersennePrime(p)) {
                BigInteger perfectNumber = BigInteger.TWO.pow(p - 1).multiply(BigInteger.TWO.pow(p).subtract(BigInteger.ONE));
            	System.out.println("#\t\t\tPerfectNumber\t\t\t# of Digits\t\t\tSeconds Taken\t\t\tDays Taken\t\t\t\t");
            }
        }
    }
    
    //Lucas Lehmer test checks if prime numbers as p in 2^p - 1 result in prime
    //if so, perfect number = 2^(p−1)·(2^p−1)
    public static boolean isMersennePrime(int p) {
        BigInteger initVal = BigInteger.valueOf(4);
        BigInteger mersennePrimeTypeFormula = BigInteger.TWO.pow(p).subtract(BigInteger.ONE); 

        for (int i = 0; i < p - 2; i++) {
        	initVal = initVal.multiply(initVal).subtract(BigInteger.TWO).mod(mersennePrimeTypeFormula);
        }
        
        return initVal.equals(BigInteger.ZERO);
    }
    
    //algorithm called the sieve of atkin algorithm quickly generates prime numbers
    //generated primes are later searched to see if they form mersenne primes
    public static ArrayList<Integer> primesWithSieveOfAtkin(int limit) {
    	
        boolean[] sieve = new boolean[limit + 1];
        Arrays.fill(sieve, false);
        
        sieve[2] = true;
        sieve[3] = true;
        
        int root = (int) Math.ceil(Math.sqrt(limit));

        for (int x = 1; x <= root; x++) {
            for (int y = 1; y <= root; y++) {
                int n = (4 * x * x) + (y * y);
                if (n <= limit && (n % 12 == 1 || n % 12 == 5)) {
                    sieve[n] ^= true;
                }
                
                n = (3 * x * x) + (y * y);
                if (n <= limit && n % 12 == 7) {
                    sieve[n] ^= true;
                }
                
                n = (3 * x * x) - (y * y);
                if (x > y && n <= limit && n % 12 == 11) {
                    sieve[n] ^= true;
                }
            }
        }

        for (int n = 5; n <= root; n++) {
            if (sieve[n]) {
                for (int k = n * n; k <= limit; k += n * n) {
                    sieve[k] = false;
                }
            }
        }

        ArrayList<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= limit; i++) {
            if (sieve[i]) {
                primes.add(i);
            }
        }

        return primes;
    }
    
}