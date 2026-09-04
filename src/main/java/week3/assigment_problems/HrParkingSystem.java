package week3.assigment_problems;

class CompanyEmployeeRecord {
    String name;
    String empId;
    Employee employee;
    ParkingSlot slot;

    static int totalRecords = 0;

    public CompanyEmployeeRecord(String name, String empId, Employee employee, ParkingSlot slot) {
        this.name = name;
        this.empId = empId;
        this.employee = employee;
        this.slot = slot;
        totalRecords++;
    }

    public String fullProfile() {
        double pay = 0.0;
        if (employee instanceof ManagerEmployee) {
            pay = ((ManagerEmployee) employee).effectiveSalary();
        } else if (employee instanceof InternEmployee) {
            pay = ((InternEmployee) employee).effectiveSalary();
        } else if (employee != null) {
            pay = employee.getSalary();
        }

        String slotStr = (slot != null) ? slot.slotNo : "no parking assigned";
        return name + " | Pay: Rs " + pay + " | Slot: " + slotStr;
    }
}

public class HrParkingSystem {
    public static void main(String[] args) {
        ParkingSlot s1 = new ParkingSlot("A1", 2, 1);
        ParkingSlot s2 = new ParkingSlot("A2", 2, 1);

        ManagerEmployee m = new ManagerEmployee("M01", "Divya", 70000, 8000);
        Employee p = new Employee("E02", "Karan", 40000);
        InternEmployee i = new InternEmployee("I03", "Meera", 12000, 10000);

        CompanyEmployeeRecord r1 = new CompanyEmployeeRecord("Divya", "M01", m, s1);
        CompanyEmployeeRecord r2 = new CompanyEmployeeRecord("Karan", "E02", p, s2);
        CompanyEmployeeRecord r3 = new CompanyEmployeeRecord("Meera", "I03", i, null);

        System.out.println(r1.fullProfile());
        System.out.println(r2.fullProfile());
        System.out.println(r3.fullProfile());
        System.out.println("Total records: " + CompanyEmployeeRecord.totalRecords);
    }
}
