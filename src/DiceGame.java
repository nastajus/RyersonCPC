import java.util.Scanner;

/**
 * Created by Dante on 2014-10-09.
 */
public class DiceGame {
    public static void main(String[] Args) {
        Scanner sc = new Scanner(System.in);
        double g = AverageRoll(sc.nextInt(),sc.nextInt(),sc.nextInt(),sc.nextInt());
        double e = AverageRoll(sc.nextInt(),sc.nextInt(),sc.nextInt(),sc.nextInt());

        if(g > e) System.out.println("Gunnar");
        else if(e>g) System.out.println("Emma");
        else System.out.println("Tie");
    }

    private static double AverageRoll(int d1min, int d1max, int d2min, int d2max) {
        int total = 0;
        int count = 0;
        for (int i = d1min; i <= d1max; i++) {
            for (int j = d2min; j <= d2max; j++) {
                total += (i+j); count++;
            }
        }
        return (double)total / (double)count;
    }
}
