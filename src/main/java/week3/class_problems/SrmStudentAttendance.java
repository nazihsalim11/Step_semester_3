package week3.class_problems;

class SrmStudent {
    String name;
    String regNo;
    int attendance;

    public SrmStudent(String name, String regNo, int attendance) {
        this.name = name;
        this.regNo = regNo;
        this.attendance = attendance;
    }

    public void addAttendanceUpdate(int newAttendance) {
        this.attendance = newAttendance;
    }

    public boolean isEligible() {
        return this.attendance >= 75;
    }

    // Justification: classAverage operates across an aggregation of multiple students
    // and represents state/behavior of the entire cohort, not an individual student.
    // Conversely, isEligible evaluates the specific attendance state of a single student instance.
    public static double classAverage(SrmStudent[] students) {
        if (students == null || students.length == 0) {
            return 0.0;
        }
        double total = 0.0;
        for (SrmStudent s : students) {
            total += s.attendance;
        }
        return total / students.length;
    }
}

public class SrmStudentAttendance {
    public static void main(String[] args) {
        SrmStudent[] students = new SrmStudent[] {
            new SrmStudent("Ravi", "RA01", 82),
            new SrmStudent("Anitha", "RA02", 68),
            new SrmStudent("Karthik", "RA03", 91),
            new SrmStudent("Meera", "RA04", 74),
            new SrmStudent("Suresh", "RA05", 60)
        };

        for (SrmStudent s : students) {
            String status = s.isEligible() ? "Eligible" : "Detained";
            System.out.println(s.name + " " + s.attendance + "% " + status);
        }

        System.out.printf("Class average: %.1f%%\n", SrmStudent.classAverage(students));
    }
}
