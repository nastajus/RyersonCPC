import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Created by Dante on 2014-09-11.
 */
public class Redundancy {

    public static void main(String[] Args){
        Scanner s = new Scanner(System.in);
        TreeMap<Integer, Integer> occurrences = new TreeMap<>();
        ArrayList<Integer> order = new ArrayList<>();

        while (s.hasNextInt()) {
            int a = s.nextInt();
            if (occurrences.containsKey(a)) {
                occurrences.put(a, occurrences.get(a) + 1);
            } else {
                occurrences.put(a, 1);
                order.add(a);
            }
        }
        for (int i :order){
            System.out.println(i + " "+occurrences.get(i));

        }
    }
}
