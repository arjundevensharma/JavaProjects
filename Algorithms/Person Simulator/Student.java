package ISC4U;

public class Student extends Person {
    private double gpa;
    private int sat;

    public Student(String name, int age, double shoeSize, double gpa, int sat) {
        super(name, age, shoeSize);
        this.gpa = gpa;
        this.sat = sat;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public int getSat() {
        return sat;
    }

    public void setSat(int sat) {
        this.sat = sat;
    }
    
    public double getStudentVal() {
        return gpa * sat;
    }
   
    public String toString() {
        return super.toString() + " gpa=" + gpa + " sat=" + sat;
    }

    public int calculateMysteryNum() {
        return (int)(super.calculateMysteryNum() + getStudentVal() );
    }

    public double calculateMysterySum() {
        return calculateMysteryNum();
    }
}
