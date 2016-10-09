import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

/**
 * Created by IAN on 08/10/2016.
 */
public class Cetvrta {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        Map<String, Integer> xCoordinateCount = new HashMap<>();
        Map<String, Integer> yCoordinateCount = new HashMap<>();

        while (sc.hasNextLine()){
            String str = sc.nextLine();
            String regex = "\\s";    //space character, escaped once for regex, second for java
            String[] parts = str.split(regex);

            //not as readable or scalable as I'd like.
            if (!xCoordinateCount.containsKey(parts[0])) xCoordinateCount.put(parts[0], 1);
            else xCoordinateCount.put(parts[0], xCoordinateCount.get(parts[0]) + 1);

            if (!yCoordinateCount.containsKey(parts[1])) yCoordinateCount.put(parts[1], 1);
            else yCoordinateCount.put(parts[1], yCoordinateCount.get(parts[1]) + 1);

        }
        String xDesired = getKeyByValue(xCoordinateCount, 1);
        String yDesired = getKeyByValue(yCoordinateCount, 1);

        System.out.print(xDesired + " " + yDesired);
    }

    //returns on first match, since only hit is always expected for given data set
    public static <T, E> T getKeyByValue(Map<T, E> map, E value){
        for (Map.Entry<T, E> entry : map.entrySet()){
            if (Objects.equals(value, entry.getValue())){
                return entry.getKey();
            }
        }
        return null;
    }
}
