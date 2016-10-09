import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by IAN on 09/10/2016.
 */
public class VirtualFriends {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        Map<String, Boolean> names = new HashMap<>();

        String str = sc.nextLine();
        int numTestCases = Integer.parseInt(str);

        for (int tc = 0; tc < numTestCases; tc++){
            str = sc.nextLine();
            int numFriendshipsFormed = Integer.parseInt(str);

            for(int f = 0; f < numFriendshipsFormed; f++){
                str = sc.nextLine();

                String regex = "\\s";    //space character, escaped once for regex, second for java
                String[] parts = str.split(regex);

                for (String part : parts){
                    names.put(part, true);
                }

                System.out.println(names.size());
            }
        }
    }
}
