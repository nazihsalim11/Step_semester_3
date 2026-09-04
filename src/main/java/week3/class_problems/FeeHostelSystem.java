package week3.class_problems;

class SrmStudentRecord {
    String name;
    String regNo;
    HostelFeeAccount feeAccount;
    HostelRoom room;

    static int totalStudents = 0;

    public SrmStudentRecord(String name, String regNo, HostelFeeAccount feeAccount, HostelRoom room) {
        this.name = name;
        this.regNo = regNo;
        this.feeAccount = feeAccount;
        this.room = room;
        totalStudents++;
    }

    public String fullStatus() {
        String roomStr = (room != null) ? room.roomNo : "unallotted";
        double due = (feeAccount != null) ? feeAccount.getDue() : 0.0;
        return name + " | Due: Rs " + due + " | Room: " + roomStr;
    }
}

public class FeeHostelSystem {
    public static void main(String[] args) {
        HostelRoom r1 = new HostelRoom("C-214", 3, 2);
        HostelRoom r2 = new HostelRoom("C-507", 2, 1);

        HostelFeeAccount f1 = new HostelFeeAccount("RA01", 200000, 60000);
        HostelFeeAccount f2 = new HostelFeeAccount("RA02", 180000, 0);
        HostelFeeAccount f3 = new HostelFeeAccount("RA03", 200000, 0);

        f3.pay(-5000); // rejected payment

        SrmStudentRecord s1 = new SrmStudentRecord("Ravi", "RA01", f1, r1);
        SrmStudentRecord s2 = new SrmStudentRecord("Anitha", "RA02", f2, r2);
        SrmStudentRecord s3 = new SrmStudentRecord("Karthik", "RA03", f3, null);

        System.out.println(s1.fullStatus());
        System.out.println(s2.fullStatus());
        System.out.println(s3.fullStatus());
        System.out.println("Total students: " + SrmStudentRecord.totalStudents);
    }
}
