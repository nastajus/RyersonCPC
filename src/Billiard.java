import java.util.Scanner;

/**
 * Created by Dante on 2014-10-20.
 */
public class Billiard {
    public static void main(String[] Args) {
        Scanner sc = new Scanner(System.in);
        while(true){
            int a = sc.nextInt();
            int b = sc.nextInt();
            int s = sc.nextInt();
            int m = sc.nextInt();
            int n = sc.nextInt();

            if((a|b|s|m|n) == 0) return;

            double sideh = a*m;
            double sidev = b*n;
            double sidec = Math.sqrt(sideh*sideh + sidev*sidev);
            double angle;

            angle = Math.toDegrees(Math.acos(sideh/sidec));

            System.out.printf("%.2f ", angle);
            System.out.printf("%.2f", sidec/s);
            System.out.println();

        }
    }
}
