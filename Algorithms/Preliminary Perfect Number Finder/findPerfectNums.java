package ISC4U;
import java.io.*;
import java.util.*;

public class findPerfectNums {

	public static int iterations = 0;

	public static void main(String[] args) {
	    Scanner in = new Scanner(System.in);
	    
	    long n = 2;
	    if (isPrime(Math.pow(2, n) - 1) == true)
	    {
	    	double t = Math.pow(2, n-1) * ((long)Math.pow(2, n) - 1);
	    	System.out.println(t);
	    }
	    n++;
	    while (true)
	    {	
		    iterations++;
		    if (isPrime(Math.pow(2, n) - 1) == true)
		    {
		    	double t = Math.pow(2, n-1) * ((long)Math.pow(2, n) - 1);
		    	System.out.println(t + "[Iterations: " + iterations + "]");
		    }
		    n+=2;
	    }
	    
	}
	
	public static boolean isPrime (double num)
	  {
	    for (int i = 2; i <= num - 1; i++)
	    {
	      if (num%i == 0) 
	      {
	        return false;
	      }
	    }
	    return true;
	  }
	

}
