package week5.assigment_problems;

class AccessChecker {
    public static String classifyAccess(String fieldModifier, String accessorContext) {
        if (fieldModifier == null || accessorContext == null) return "DENIED";
        switch (fieldModifier) {
            case "public":
                return "ALLOWED";
            case "protected":
            case "default":
                if ("SAME_CLASS".equals(accessorContext) || "SAME_PACKAGE".equals(accessorContext)) {
                    return "ALLOWED";
                }
                return "DENIED";
            case "private":
                return "SAME_CLASS".equals(accessorContext) ? "ALLOWED" : "DENIED";
            default:
                return "DENIED";
        }
    }

    public static String summarizeByModifier(String[][] attempts) {
        int privA = 0, privD = 0;
        int defA = 0, defD = 0;
        int protA = 0, protD = 0;
        int pubA = 0, pubD = 0;

        if (attempts != null) {
            for (String[] row : attempts) {
                if (row == null || row.length < 2) continue;
                String res = classifyAccess(row[0], row[1]);
                boolean allowed = "ALLOWED".equals(res);

                switch (row[0]) {
                    case "private":
                        if (allowed) privA++; else privD++;
                        break;
                    case "default":
                        if (allowed) defA++; else defD++;
                        break;
                    case "protected":
                        if (allowed) protA++; else protD++;
                        break;
                    case "public":
                        if (allowed) pubA++; else pubD++;
                        break;
                }
            }
        }

        return "private: " + privA + " allowed / " + privD + " denied | " +
               "default: " + defA + " allowed / " + defD + " denied | " +
               "protected: " + protA + " allowed / " + protD + " denied | " +
               "public: " + pubA + " allowed / " + pubD + " denied";
    }
}

class LibraryMemberRecord {
    private String membershipId;
    String branchCode;
    protected double finesOwed;
    public String displayName;

    public LibraryMemberRecord(String membershipId, String branchCode, double finesOwed, String displayName) {
        if (membershipId == null || membershipId.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid membershipId");
        }
        this.membershipId = membershipId.trim();
        this.branchCode = branchCode;
        this.finesOwed = finesOwed;
        this.displayName = displayName;
    }
}

public class MembershipFieldReachChecker {
    public static void main(String[] args) {
        System.out.println(AccessChecker.classifyAccess("private", "SAME_CLASS"));
        System.out.println(AccessChecker.classifyAccess("protected", "DIFFERENT_PACKAGE"));

        String[][] attempts = {
            {"private", "SAME_CLASS"},
            {"private", "SAME_PACKAGE"},
            {"default", "SAME_PACKAGE"},
            {"default", "DIFFERENT_PACKAGE"},
            {"protected", "SAME_PACKAGE"},
            {"protected", "SAME_CLASS"},
            {"public", "DIFFERENT_PACKAGE"}
        };
        System.out.println(AccessChecker.summarizeByModifier(attempts));

        try {
            new LibraryMemberRecord("LB9", "BR1", 0, "Priya Nair");
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }
    }
}
