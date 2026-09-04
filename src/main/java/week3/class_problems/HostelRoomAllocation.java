package week3.class_problems;

class HostelRoom {
    String roomNo;
    int beds;
    int occupied;

    public HostelRoom(String roomNo, int beds, int occupied) {
        this.roomNo = roomNo;
        this.beds = beds;
        this.occupied = occupied;
    }

    public boolean allot(String name) {
        if (occupied < beds) {
            occupied++;
            return true;
        }
        return false;
    }
}

public class HostelRoomAllocation {
    // Explanation: In Java, arrays and objects are passed by reference value.
    // Passing the HostelRoom[] array passes a copy of the reference pointing to
    // the same array on the heap; no elements or array structures are cloned.
    public static HostelRoom findAvailableRoom(HostelRoom[] rooms) {
        if (rooms == null) return null;
        for (HostelRoom room : rooms) {
            if (room != null && room.occupied < room.beds) {
                return room;
            }
        }
        return null;
    }

    public static void safeAllot(HostelRoom[] rooms, String studentName) {
        HostelRoom target = findAvailableRoom(rooms);
        if (target != null) {
            target.allot(studentName);
            System.out.println(studentName + " allotted to room " + target.roomNo);
        } else {
            System.out.println("No rooms available for " + studentName);
        }
    }

    public static void main(String[] args) {
        HostelRoom[] batch1 = new HostelRoom[] {
            new HostelRoom("C-214", 3, 2),
            new HostelRoom("C-507", 2, 2)
        };
        safeAllot(batch1, "Divya");

        HostelRoom[] batch2 = new HostelRoom[] {
            new HostelRoom("C-214", 3, 3),
            new HostelRoom("C-507", 2, 2)
        };
        safeAllot(batch2, "Divya");
    }
}
