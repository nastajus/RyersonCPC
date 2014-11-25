import java.util.Scanner;

/**
 * Created by IAN on 24/11/14.
 */
public class Aaah {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String line1 = sc.nextLine();
        String line2 = sc.nextLine();
        if ( line2.length() <= line1.length() ) System.out.println("go");
        else System.out.println("no");
        //System.out.println(sc.nextLine());


    }
}
