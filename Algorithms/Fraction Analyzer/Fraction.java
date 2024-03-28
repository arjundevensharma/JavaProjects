package ISC4U;

public class Fraction {
	
	private int num;
	private int denom;
	
	public Fraction (int n) {
		num = n;
		denom = 1;
	}
	public Fraction () {
		num = 0;
		denom = 1;
	}
	
	public Fraction (Fraction a) {
		num = a.num;
		denom = a.denom;
	}
	
	public String toString() {
	    return num + "/" + denom;
	}

	
	public Fraction (int n, int d) {
		if (d!= 0) {
			num = n;
			denom = d;
			reduce();
		}
		else {
			throw new IllegalArgumentException("Fraction construction error: denominator is 0");
		}
	}
	// Returns the sum of this fraction and other
	  public Fraction add(Fraction other)
	  {
	    int newNum = num * other.denom + denom * other.num;
	    int newDenom = denom * other.denom;
	    return new Fraction(newNum, newDenom);
	  }

	  // Returns the sum of this fraction and m
	  public Fraction subtract(int m)
	  {
		Fraction f = new Fraction(num - m * denom, denom);
	    return f;  // or just return new Fraction(num + m * denom, denom);
	  }
	  
	  public Fraction subtract(Fraction other)
	  {
	    int newNum = num * other.denom - denom * other.num;
	    int newDenom = denom * other.denom;
	    return new Fraction(newNum, newDenom);
	  }

	  // Returns the sum of this fraction and m
	  public Fraction add(int m)
	  {
		Fraction f = new Fraction(num + m * denom, denom);
	    return f;  // or just return new Fraction(num + m * denom, denom);
	  }

	  // Returns the product of this fraction and other
	  public Fraction multiply(Fraction other)
	  {
	    int newNum = num * other.num;
	    int newDenom = denom * other.denom;
	    return new Fraction(newNum, newDenom);
	  }

	  // Returns the product of this fraction and m
	  public Fraction multiply(int m)
	  {
	    return new Fraction(num * m, denom);
	  }
	  
	  // Returns the product of this fraction and other
	  public Fraction divide(Fraction other)
	  {
	    int newNum = num * other.denom;
	    int newDenom = denom * other.num;
	    return new Fraction(newNum, newDenom);
	  }

	  // Returns the product of this fraction and m
	  public Fraction divide(int m)
	  {
	    return new Fraction(num, denom * m);
	  }
	  
	 private void reduce()
	  {
	    if (num == 0)
	    {
	      denom = 1;
	      return;
	    }

	    if (denom < 0)
	    {
	      num = -num;
	      denom = -denom;
	    }

	    int q = gcf(Math.abs(num), denom);
	    num /= q;
	    denom /= q;    
	  }

	  //  Returns the greatest common factor of two positive integers
	  private int gcf(int n, int d)
	  {
	    if (n <= 0 || d <= 0)
	    {
	      throw new IllegalArgumentException(
	                  "gcf precondition failed: " + n + ", " + d);
	    }

	    if (n % d == 0)
	      return d;
	    else if (d % n == 0)
	      return n;
	    else
	      return gcf(n % d, d % n); 
	  }
	  
	  
}
