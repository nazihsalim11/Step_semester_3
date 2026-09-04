package week3.class_problems;

class BrokenSrmStudent {
    // Flaw: Marking these static ties them to the class template rather than the instance.
    // Every new instance creation unconditionally clobbers the previous student's fields.
    static String name;
    static String regNo;
    static int attendance;

    public BrokenSrmStudent(String name, String regNo, int attendance) {
        BrokenSrmStudent.name = name;
        BrokenSrmStudent.regNo = regNo;
        BrokenSrmStudent.attendance = attendance;
    }
}

class FixedSrmStudent {
    String name;
    String regNo;
    int attendance;

    static String university = "SRM Institute of Science and Technology";
    static int admissionCount = 1010;

    public FixedSrmStudent(String name, int attendance) {
        this.name = name;
        this.attendance = attendance;
        admissionCount++;
        this.regNo = "RA2311003010" + admissionCount;
    }

    public void printIdCard() {
        System.out.println(name + " | " + regNo);
    }

    public static void printTotalAdmissions() {
        System.out.println("Students admitted so far: " + (admissionCount - 1010));
    }
}

public class CollegeStaticBoundary {
    public static void main(String[] args) {
        // Demonstrate broken static misuse
        BrokenSrmStudent b1 = new BrokenSrmStudent("Ravi", "RA01", 80);
        BrokenSrmStudent b2 = new BrokenSrmStudent("Meera", "RA02", 90);
        System.out.println(BrokenSrmStudent.name);
        System.out.println(BrokenSrmStudent.name);

        // Demonstrate clean split
        FixedSrmStudent s1 = new FixedSrmStudent("Ravi", 80);
        FixedSrmStudent s2 = new FixedSrmStudent("Meera", 90);
        s1.printIdCard();
        s2.printIdCard();
        FixedSrmStudent.printTotalAdmissions();
    }
}
