import java.util.Scanner;

/**
 * Created by IAN on 08/10/2016.
 */
public class Timeloop {
    public static void main(String args[]){

        Scanner sc = new Scanner(System.in);


        while( sc.hasNext() ) {

            String str = sc.next();
            int chantTimes = Integer.parseInt(str);
            String abracadabra = "Abracadabra";

            for ( int chantInstance = 0; chantInstance < chantTimes; chantInstance++) {
                System.out.println(chantInstance + 1 + " " + abracadabra);
            }
        }
    }
}
