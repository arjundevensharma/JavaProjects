package ISC4U;

public class FractionDriver {

	public static void main(String[] args) {
		Fraction f1 = new Fraction(3, 4); 
        Fraction f2 = new Fraction(2, 5); 
        Fraction f3 = new Fraction(6); 

        System.out.println(f1.add(f2));
        System.out.println(f1.subtract(f2));
        System.out.println(f1.multiply(f3));
        System.out.println(f1.divide(f2));
	}

}
