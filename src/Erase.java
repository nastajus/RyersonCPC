import java.util.Scanner;

/**
 * Created by Dante on 2014-10-20.
 */
public class Erase {
    public static void main(String[] Args) {
        Scanner sc = new Scanner(System.in);
        boolean flipped =sc.nextInt()%2==1;
        sc.nextLine();
        String before = sc.nextLine();
        String after = sc.nextLine();
        for (int i = 0; i < before.length(); i++) {
            if(!(flipped^before.charAt(i)==after.charAt(i))){
                System.out.println("Deletion failed");
                return;
            }
        }
        System.out.println("Deletion succeeded");
    }
}
