import java.util.Scanner;

/**
 * Created by IAN on 24/11/14.
 */
public class Fizzbuzz {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        for(int i=1; i<=100; i++){
            System.out.print(" ");

            if ( i % 3 == 0 ){
                System.out.print("fizz");
            }
            if ( i % 5 == 0 ){
                System.out.print("buzz");
            }

            if ( i % 3 != 0 && i % 5 != 0){
                System.out.print(i);
            }

            if ( i % 30 == 0){
                System.out.println("");
            }
        }

    }
}
