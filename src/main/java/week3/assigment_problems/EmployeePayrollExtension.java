package week3.assigment_problems;

class Employee {
    private String empId;
    private String empName;
    private double salary;

    public Employee(String empId, String empName, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public String getEmpId() {
        return empId;
    }

    public String getEmpName() {
        return empName;
    }
}

class ManagerEmployee extends Employee {
    private double teamBonus;

    public ManagerEmployee(String empId, String empName, double salary, double teamBonus) {
        super(empId, empName, salary);
        this.teamBonus = teamBonus;
    }

    public double effectiveSalary() {
        return getSalary() + teamBonus;
    }
}

class InternEmployee extends Employee {
    private double stipendCap;

    public InternEmployee(String empId, String empName, double salary, double stipendCap) {
        super(empId, empName, salary);
        this.stipendCap = stipendCap;
    }

    public double effectiveSalary() {
        return Math.min(getSalary(), stipendCap);
    }
}

public class EmployeePayrollExtension {
    public static void main(String[] args) {
        Employee plain = new Employee("E101", "Plain", 40000);
        ManagerEmployee manager = new ManagerEmployee("M102", "Manager", 70000, 8000);
        InternEmployee intern = new InternEmployee("I103", "Intern", 12000, 10000);

        Employee[] staff = new Employee[] { plain, manager, intern };

        for (Employee e : staff) {
            if (e instanceof ManagerEmployee) {
                ManagerEmployee m = (ManagerEmployee) e;
                System.out.println("Manager effective pay: Rs " + m.effectiveSalary());
            } else if (e instanceof InternEmployee) {
                InternEmployee in = (InternEmployee) e;
                System.out.println("Intern effective pay: Rs " + in.effectiveSalary());
            } else {
                System.out.println("Plain employee pay: Rs " + e.getSalary());
            }
        }
    }
}
