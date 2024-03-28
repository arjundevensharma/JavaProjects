package ISC4U;

import java.math.BigInteger;
import java.util.*;

public class OptimizedPerfectNums {

    /* Uses the Sieve of Atkin algorithm to generate a list of prime numbers
	* 
	* WHY??
	* 
	*  A Mersenne prime is a prime number that can also be expressed as 2^n - 1 for some integer n. If n is composite though, 
     * 2^n - 1 is also composite. Thus a Mersenne prime can also be defined as a prime number that can be expressed as
     *  2^p - 1 for some prime.
     * 
	* The generated numbers from this function are to be later searched through to see if they (as the p variable) in the form of 2^p - 1 result in a 
	* prime number (a Mersenne Prime) through the lucas lehmer primality test. (If they are, the corresponding perfect number 
	* of the form 2^(p−1)·(2^p−1) is outputted). This saves time to the regular method of just searching all odds
	* for if they are mersenne primes, because all iterations which the odd numbers that are being searched are
	* not prime are unnecessary.
	* 
	* Since this algorithm may be running for 3 weeks if it is picked, lots of research was done to find the 
	* fastest algorithm to generate a list of prime numbers up to 82,589,933, the highest p value to satisfy Mersenne prime in 2^p - 1
	* number. That way this program has the potential to find all 51 perfect numbers.
	* This Sieve of Atkin algorithm was found to be the fastest to generate this list of primes (time complexity: O(n)) after thorough research.
	* */
    public static ArrayList<Integer> generatePrimes(int limit) {
    	
    	//Sieve of Atkin algorithm, implemented with help from https://en.wikipedia.org/wiki/Sieve_of_Atkin
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

    /*
    *This function uses the Lucas-Lehmer primality test to test if a given prime number, as p, 
    *in 2^p - 1 results in another prime (thus a Mersenne prime) faster than the regular method. 
    *
    * THIS IS ALSO THE REASON 6 is outputted separately- this test doesn't work when p <= 2.
    * */
    public static boolean isMersennePrime(int p) {
        BigInteger initVal = BigInteger.valueOf(4);
        BigInteger mersennePrimeTypeFormula = BigInteger.TWO.pow(p).subtract(BigInteger.ONE); //2^p - 1

        for (int i = 0; i < p - 2; i++) {//repeat p - 2 times
        	// Formula: s = (s^2 - 2) mod m
            initVal = initVal.multiply(initVal).subtract(BigInteger.TWO).mod(mersennePrimeTypeFormula);
        }
        
        //if the test results in a 0 value, 2^p - 1 is prime and is thus a mersenne prime, else, it's not
        return initVal.equals(BigInteger.ZERO);
    }

    public static void main(String[] args) {
        int count = 1;
        
        ArrayList<Integer> primes = generatePrimes(82589933);  // Highest p value that 2^p - 1 is a Mersenne prime (meaning 2^(p−1)·(2^p−1) is the 51st and most recently discovered perfect number, 82,589,933]
        System.out.println("Perfect Number 1: 6"); //LL test can't work in this case, where in 2^p - 1, p = 2 [6 = 2^(2−1)·(2^2−1)]
        
        for (int p : primes) {//searches list of prime numbers for p values in which 2^p - 1  is prime (Mersenne prime), resulting in a perfect number in the form of 2^(p−1)·(2^p−1) 
            if (isMersennePrime(p)) {//checks if p value satisfies 2^p - 1 making Mersenne prime using LL test
                BigInteger perfectNumber = BigInteger.TWO.pow(p - 1).multiply(BigInteger.TWO.pow(p).subtract(BigInteger.ONE)); //if Mersenne prime found with p value, output 2^(p−1)·(2^p−1) which is a perfect number as per Euclid-Euler theorem
                count++;
                System.out.println("Perfect number " + count + ": " + perfectNumber);
            }
        }
    }
}
