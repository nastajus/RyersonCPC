import java.util.Scanner;

/**
 * Created by Dante on 2014-10-09.
 */
public class Aaah {
    public static void main(String[] Args) {
        Scanner sc = new Scanner(System.in);
        String jon = sc.nextLine();
        String doc = sc.nextLine();

        if (doc.length()>jon.length()) System.out.println("no");
        else System.out.println("go");
    }
}
