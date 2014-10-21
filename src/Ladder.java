import java.util.Scanner;

/**
 * Created by Dante on 2014-10-20.
 */
public class Ladder {
    public static void main(String[] Args) {
        Scanner sc = new Scanner(System.in);
        System.out.println((int)Math.ceil(sc.nextInt()/Math.sin(Math.toRadians(sc.nextInt()))));
    }
}
