import java.util.Scanner;
/**
 * Created by Dante on 2014-09- 27. //
 */
public class MixedFractions {

    public static void main(String[] Args) {
        Scanner sc = new Scanner(System.in);

        while(true){
            int a = sc.nextInt();
            int b = sc.nextInt();
            if (a==0&& b==0) return;
            System.out.println((a/b) + " " + (a%b) + " / " + b );
        }
    }
}
