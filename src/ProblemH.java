import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Dante on 2014-09-25.
 */
public class ProblemH {

    public static void main(String[] Args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        while (n-- > 0) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            if (a > b) System.out.println(">");
            else if (a<b) System.out.println("<");
            else System.out.println("=");
        }
    }
}
