package ISC4U;

public class Person {
    // Fields
    private String name;
    private int age;
    private double shoeSize;

    // Constructor
    public Person(String name, int age, double shoeSize) {
        this.name = name;
        this.age = age;
        this.shoeSize = shoeSize;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getShoeSize() {
        return shoeSize;
    }

    public void setShoeSize(double shoeSize) {
        this.shoeSize = shoeSize;
    }

    // toString Method
    @Override
    public String toString() {
        return "name=" + name + " age=" + age + " shoe size=" + shoeSize + " mysteryNum=" + calculateMysteryNum();
    }

    // Method to calculate mystery number
    public int calculateMysteryNum() {
        return age * (int)shoeSize;
    }
    
    public void bindFoot(Person p) {
    	p.setShoeSize(p.getShoeSize() - 1);
    	this.setAge(this.getAge() + 1);
    	
    	if (p instanceof Student && this instanceof Student) {
    		((Student) p).setGpa(((Student) p).getGpa() * 0.9);
    		((Student) p).setGpa(((Student) p).getSat() * 0.9);
    		
    		((Student) p).setGpa(((Student) p).getGpa() * 1.1);
    		((Student) p).setGpa(((Student) p).getSat() * 1.1);
    	}
    }
}