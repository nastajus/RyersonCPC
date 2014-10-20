import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Created by Dante on 2014-10-20.
 */
public class BusNumbers {
    public static void main(String[] Args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<Integer> numbers = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            numbers.add(sc.nextInt());
        }
        Collections.sort(numbers);

        int last = -1;
        int chain = 0;
        for (Integer bus :numbers){
            if (bus-last == 1){
                chain++;
            }else if(chain > 1){
                System.out.print("-"+last + " "+bus);
                chain = 0;
            }else if(chain == 1){
                System.out.print(" "+last + " "+bus);
                chain = 0;
            }else{
                System.out.print((last == -1?"":" ")+bus);
            }
            last = bus;
        }

        if(chain > 1){
            System.out.print("-"+last);
        }else if(chain == 1){
            System.out.print(" "+last);
        }
        System.out.println();
    }
}
