package ISC4U;

import java.math.BigInteger;
import java.util.*;

public class PerfectNumbers {

    public static void main(String[] args) {
        BigInteger mersenneExponent = BigInteger.valueOf(2); // Start with 2^2 - 1 (M2)
        int counter = 0;

        while (true) {
            if (isMersennePrime(mersenneExponent)) {
                BigInteger perfectNumber = BigInteger.ONE.shiftLeft(mersenneExponent.intValueExact()).subtract(BigInteger.ONE);
                System.out.println("Mersenne perfect number found: " + perfectNumber);
                counter++;
            }

            mersenneExponent = mersenneExponent.add(BigInteger.ONE);
        }
    }

    public static boolean isMersennePrime(BigInteger exponent) {
        if (!exponent.isProbablePrime(50)) {
            return false;
        }

        BigInteger two = BigInteger.valueOf(2);
        BigInteger mersenneNumber = two.pow(exponent.intValueExact()).subtract(BigInteger.ONE);

        return mersenneNumber.isProbablePrime(50);
    }
}
