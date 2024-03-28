package ISC4U;

public class PersonDriver {
    public static void main(String[] args) {
        Person[] persons = {
            new Person("John Doe", 20, 9.5),
            new Person("Jane Smith", 25, 8.0),
            new Student("Mike Johnson", 22, 10.0, 3.5, 1200),
            new Student("Emily Davis", 18, 7.5, 3.8, 1300)
        };

        double averageAge = calculateAverageAge(persons);

        System.out.println("The average age is: " + averageAge);
        
        for (int i = 0; i < persons.length; i++) {
        	System.out.println("Person " + (i + 1) + " 's name is " + persons[i].getName() + " and their mystery number is " + persons[i].calculateMysteryNum() + "." );
        }
        
        System.out.println("Person 1 stats: " + persons[0].toString() + "\nPerson 2 stats: " + persons[1].toString());
        persons[0].bindFoot(persons[1]);
        
        System.out.println("New Person 1 stats: " + persons[0].toString() + "\nNew Person 2 stats: " + persons[1].toString());
        
    }

    public static double calculateAverageAge(Person[] persons) {
        if (persons == null || persons.length == 0) {
            return 0;
        }
        
        int sumOfAges = 0;
        for (Person person : persons) {
            sumOfAges += person.getAge();
        }
        
        return (double) sumOfAges / persons.length;
    }
    
    public static int outputVal(Person p) {
        if (p instanceof Student) {
        	return (int) ((Student) p).getStudentVal();
        } else {
        	return p.calculateMysteryNum();
        }
    }
}