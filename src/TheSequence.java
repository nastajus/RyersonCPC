import java.util.Scanner;

/**
 * Created by Dante on 2014-10-09.
 */
public class TheSequence {

    public static void main(String[] Args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();

        for (int i = Math.min(a, b); i <= Math.max(a, b); i++) {
            System.out.println(ThreeNTraverse(i));
        }
    }
    private static int ThreeNTraverse(int n) {
        if(n ==1) return 0;
        if(n%2 == 0) return 1+ ThreeNTraverse(n/2);
        else return 1+ ThreeNTraverse(3*n + 1);
    }
}
