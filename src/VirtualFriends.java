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

        while(sc.hasNextLine()){
            String str = sc.nextLine();

            //blatantly disregard counting per test case, only requires to reset when new numbers detected
            //assumes only valid data provided
            if (isNumeric(str)) {
                names = new HashMap<>();
                continue;
            }

            String regex = "\\s";    //space character, escaped once for regex, second for java
            String[] parts = str.split(regex);

            for (String part : parts){
                names.put(part, true);
            }

            System.out.println(names.size());

        }
    }

    public static boolean isNumeric(String str)
    {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }

}
