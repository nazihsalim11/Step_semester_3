package week3.class_problems;

class FeeAccount {
    private String regNo;
    private double totalFee;
    private double amountPaid;

    public FeeAccount(String regNo, double totalFee, double amountPaid) {
        this.regNo = regNo;
        this.totalFee = totalFee;
        this.amountPaid = amountPaid;
    }

    public void pay(double amount) {
        if (amount > 0) {
            this.amountPaid += amount;
        }
    }

    public double getDue() {
        return Math.max(0.0, totalFee - amountPaid);
    }

    public String getRegNo() {
        return regNo;
    }
}

class HostelFeeAccount extends FeeAccount {
    public HostelFeeAccount(String regNo, double totalFee, double amountPaid) {
        super(regNo, totalFee, amountPaid);
    }

    public void payInTwoInstallments(double amount) {
        if (amount > 0) {
            pay(amount / 2.0);
            pay(amount / 2.0);
        }
    }
}

class ScholarshipFeeAccount extends FeeAccount {
    private double scholarshipPercent;

    public ScholarshipFeeAccount(String regNo, double totalFee, double amountPaid, double scholarshipPercent) {
        super(regNo, totalFee, amountPaid);
        this.scholarshipPercent = Math.max(0, Math.min(100, scholarshipPercent));
    }

    public double effectiveDue() {
        double due = getDue();
        return due - (due * (scholarshipPercent / 100.0));
    }
}

public class FeeAccountManager {
    public static void main(String[] args) {
        FeeAccount plain = new FeeAccount("RA101", 150000, 150000);
        HostelFeeAccount hostel = new HostelFeeAccount("RA102", 200000, 60000);
        ScholarshipFeeAccount scholarship = new ScholarshipFeeAccount("RA103", 180000, 0, 20);

        FeeAccount[] accounts = new FeeAccount[] { plain, hostel, scholarship };

        for (FeeAccount acc : accounts) {
            if (acc instanceof ScholarshipFeeAccount) {
                ScholarshipFeeAccount sfa = (ScholarshipFeeAccount) acc;
                System.out.println("Scholarship account effective due: Rs " + sfa.effectiveDue());
            } else if (acc instanceof HostelFeeAccount) {
                System.out.println("Hostel account due: Rs " + acc.getDue());
            } else {
                System.out.println("Plain account due: Rs " + acc.getDue());
            }
        }
    }
}
