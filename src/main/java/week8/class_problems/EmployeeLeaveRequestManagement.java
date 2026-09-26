import java.time.LocalDate;

enum LeaveStatus {
    PENDING,
    APPROVED,
    REJECTED
}

abstract class Employee {

    protected String name;

    public Employee(String name) {
        this.name = name;
    }

    public abstract boolean isLeaveAllowed(
            LocalDate start,
            LocalDate end);

    public String getName() {
        return name;
    }
}

class FullTimeEmployee extends Employee {

    public FullTimeEmployee(String name) {
        super(name);
    }

    @Override
    public boolean isLeaveAllowed(
            LocalDate start,
            LocalDate end) {

        return true;
    }
}

class PartTimeEmployee extends Employee {

    public PartTimeEmployee(String name) {
        super(name);
    }

    @Override
    public boolean isLeaveAllowed(
            LocalDate start,
            LocalDate end) {

        long days =
                end.toEpochDay()
                - start.toEpochDay();

        return days <= 5;
    }
}

class ContractEmployee extends Employee {

    public ContractEmployee(String name) {
        super(name);
    }

    @Override
    public boolean isLeaveAllowed(
            LocalDate start,
            LocalDate end) {

        long days =
                end.toEpochDay()
                - start.toEpochDay();

        return days <= 3;
    }
}

class LeaveRequest {

    private Employee employee;
    private LocalDate startDate;
    private LocalDate endDate;

    private LeaveStatus status;

    public LeaveRequest(
            Employee employee,
            LocalDate startDate,
            LocalDate endDate) {

        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = LeaveStatus.PENDING;
    }

    public void approve() {

        if (status != LeaveStatus.PENDING) {

            System.out.println(
                    "Cannot approve request from status "
                    + status
            );

            return;
        }

        status = LeaveStatus.APPROVED;

        System.out.println(
                "Leave request for "
                + employee.getName()
                + " approved. Status: Approved."
        );
    }

    public void reject() {

        if (status != LeaveStatus.PENDING) {

            System.out.println(
                    "Cannot reject request from status "
                    + status
            );

            return;
        }

        status = LeaveStatus.REJECTED;

        System.out.println(
                "Leave request for "
                + employee.getName()
                + " rejected. Status: Rejected."
        );
    }

    public void setPending() {

        if (status != LeaveStatus.PENDING) {

            System.out.println(
                    "Cannot change status: "
                    + status
                    + " request cannot revert to Pending."
            );

            return;
        }

        status = LeaveStatus.PENDING;
    }

    public LeaveStatus getStatus() {
        return status;
    }

    public Employee getEmployee() {
        return employee;
    }
}

class LeaveManager {

    public LeaveRequest submitRequest(
            Employee employee,
            LocalDate start,
            LocalDate end) {

        if (!employee.isLeaveAllowed(
                start,
                end)) {

            System.out.println(
                    "Leave request rejected by employee policy."
            );

            return null;
        }

        LeaveRequest request =
                new LeaveRequest(
                        employee,
                        start,
                        end
                );

        System.out.println(
                "Leave request submitted by "
                + employee.getName()
                + " for "
                + start
                + " to "
                + end
                + ". Status: Pending."
        );

        return request;
    }

    public void approve(
            LeaveRequest request) {

        if (request != null) {
            request.approve();
        }
    }

    public void reject(
            LeaveRequest request) {

        if (request != null) {
            request.reject();
        }
    }
}

public class EmployeeLeaveRequestManagement {

    public static void main(String[] args) {

        LeaveManager manager =
                new LeaveManager();

        Employee john =
                new FullTimeEmployee(
                        "John Doe"
                );

        LeaveRequest johnRequest =
                manager.submitRequest(
                        john,
                        LocalDate.of(2024, 10, 10),
                        LocalDate.of(2024, 10, 12)
                );

        manager.approve(johnRequest);

        Employee jane =
                new PartTimeEmployee(
                        "Jane Smith"
                );

        manager.submitRequest(
                jane,
                LocalDate.of(2024, 11, 1),
                LocalDate.of(2024, 11, 5)
        );

        // Attempt to move approved request
        // back to pending.
        johnRequest.setPending();
    }
}
