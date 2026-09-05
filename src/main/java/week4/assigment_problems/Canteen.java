package week4.assigment_problems;
public class Canteen {
    private String canteenCode;
    private String canteenName;
    private int trustScore;

    public Canteen(String canteenCode, String canteenName, int trustScore) {
        this.canteenCode = canteenCode;
        this.canteenName = canteenName;
        this.trustScore = trustScore;
    }

    public Canteen(String canteenCode, String canteenName) {
        this(canteenCode, canteenName, 3);
    }

    int compareTo(Canteen other) {
        if (this.trustScore != other.trustScore) {
            return other.trustScore - this.trustScore;
        }

        int result =
                this.canteenCode.compareToIgnoreCase(other.canteenCode);

        if (result != 0) {
            return result;
        }

        return this.canteenName.length() - other.canteenName.length();
    }

    static Canteen[] rankCanteens(Canteen[] canteens) {
        Canteen[] result = canteens.clone();

        for (int i = 1; i < result.length; i++) {
            Canteen current = result[i];
            int j = i - 1;

            while (j >= 0 && current.compareTo(result[j]) < 0) {
                result[j + 1] = result[j];
                j--;
            }

            result[j + 1] = current;
        }

        return result;
    }

    public static void main(String[] args) {
        Canteen[] canteens = {
                new Canteen("HB3-C", "Spice Junction", 3),
                new Canteen("hb1-c", "Grand Mess", 5),
                new Canteen("HB2-C", "Southern Treats")
        };

        Canteen[] ranked = rankCanteens(canteens);

        for (Canteen canteen : ranked) {
            System.out.println(canteen.canteenCode);
        }
    }
}
