package week5.class_problems;

class AccessRuleEngine {
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

    public static String summarizeBatch(String[][] attempts) {
        int allowed = 0;
        int denied = 0;
        if (attempts != null) {
            for (String[] attempt : attempts) {
                if (attempt != null && attempt.length >= 2) {
                    if ("ALLOWED".equals(classifyAccess(attempt[0], attempt[1]))) {
                        allowed++;
                    } else {
                        denied++;
                    }
                }
            }
        }
        return "Allowed: " + allowed + " | Denied: " + denied;
    }
}

class PatientRecord {
    private String patientId;
    String wardCode;
    protected double vitalsScore;
    public String facilityName;

    public PatientRecord(String patientId, String wardCode, double vitalsScore, String facilityName) {
        if (patientId == null || patientId.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid patientId");
        }
        this.patientId = patientId.trim();
        this.wardCode = wardCode;
        this.vitalsScore = vitalsScore;
        this.facilityName = facilityName;
    }
}

public class FieldVisibilityIntakeValidator {
    public static void main(String[] args) {
        System.out.println(AccessRuleEngine.classifyAccess("private", "SAME_CLASS"));
        System.out.println(AccessRuleEngine.classifyAccess("default", "DIFFERENT_PACKAGE"));

        String[][] batch = {
            {"protected", "SAME_PACKAGE"},
            {"protected", "DIFFERENT_PACKAGE"},
            {"public", "DIFFERENT_PACKAGE"}
        };
        System.out.println(AccessRuleEngine.summarizeBatch(batch));

        try {
            new PatientRecord("MT9", "W3", 98.2, "MediTrack Central");
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }
    }
}
