package week3.assigment_problems;

class BrokenLibraryMember {
    // Fault: Static values belong to the class definition.
    // Modifying them changes the property for every instance sharing the classloader.
    static String name;
    static String memberId;
    static int booksIssued;

    public BrokenLibraryMember(String name, String memberId, int booksIssued) {
        BrokenLibraryMember.name = name;
        BrokenLibraryMember.memberId = memberId;
        BrokenLibraryMember.booksIssued = booksIssued;
    }
}

class FixedLibraryMember {
    String name;
    String memberId;
    int booksIssued;

    static String libraryName = "City Central Library";
    static int memberCount = 1000;

    public FixedLibraryMember(String name, int booksIssued) {
        this.name = name;
        this.booksIssued = booksIssued;
        memberCount++;
        this.memberId = "LM-" + memberCount;
    }

    public void printMemberCard() {
        System.out.println(name + " " + memberId);
    }

    public static void printTotalMembers() {
        System.out.println("Total members: " + (memberCount - 1000));
    }
}

public class LibraryMembershipBoundary {
    public static void main(String[] args) {
        BrokenLibraryMember m1 = new BrokenLibraryMember("Aditi", "LM-01", 1);
        BrokenLibraryMember m2 = new BrokenLibraryMember("Rohan", "LM-02", 2);
        System.out.println(BrokenLibraryMember.name);
        System.out.println(BrokenLibraryMember.name);

        FixedLibraryMember f1 = new FixedLibraryMember("Aditi", 1);
        FixedLibraryMember f2 = new FixedLibraryMember("Rohan", 2);
        f1.printMemberCard();
        f2.printMemberCard();
        FixedLibraryMember.printTotalMembers();
    }
}
