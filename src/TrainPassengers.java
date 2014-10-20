import java.util.Scanner;

/**
 * Created by Dante on 2014-10-20.
 */
public class TrainPassengers {
    public static void main(String[] Args) {
        Scanner sc = new Scanner(System.in);
        int capacity = sc.nextInt();
        int stations = sc.nextInt();
        int current = 0;
        for (int i = 0; i < stations; i++) {
            int leaving = sc.nextInt();
            int boarding = sc.nextInt();
            int staying = sc.nextInt();

            current -= leaving;
            if (current<0){
                System.out.println("impossible");
                return;
            }
            current += boarding;
            if (current > capacity){
                System.out.println("impossible");
                return;
            }
            if (staying > 0 && current!=capacity){
                System.out.println("impossible");
                return;
            }
        }
        if(current!=0){
            System.out.println("impossible");
            return;
        }
        System.out.println("possible");
    }
}
