import java.util.*;

/**
 * Created by Dante on 2014-10-20.
 */
public class Flexible {
    public static void main(String[] Args) {
        Scanner sc = new Scanner(System.in);
        int last = sc.nextInt();
        List<Integer> list = new ArrayList<>();
        TreeSet<Integer> result = new TreeSet<>();
        list.add(0);
        int max = sc.nextInt();
        for (int i = 0; i < max; i++) {
            list.add(sc.nextInt());
        }
        list.add(last);

        for (int i = 0; i < list.size(); i++) {
            for (int j = i+1; j < list.size(); j++) {
                result.add(list.get(j)-list.get(i));
            }
        }
        for(Integer i:result){
            System.out.print(i + " ");
        }
        System.out.println();

    }
}
