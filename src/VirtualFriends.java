import java.util.*;

/**
 * Created by IAN on 09/10/2016.
 */
public class VirtualFriends {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        String str = sc.nextLine();
        int numTestCases = Integer.parseInt(str);

        for (int tc = 0; tc < numTestCases; tc++){

            List<Map<String,Boolean>> networks = new ArrayList<>();

            str = sc.nextLine();
            int numFriendshipsFormed = Integer.parseInt(str);

            for(int f = 0; f < numFriendshipsFormed; f++){
                str = sc.nextLine();

                String regex = "\\s";    //space character, escaped once for regex, second for java
                String[] parts = str.split(regex);

                boolean personsExistsAlready = false;

                //check existing networks for any matches
                for (Map<String, Boolean> network : networks){

                    //name exists in pre-existing network
                    if (network.containsKey(parts[0]) || network.containsKey(parts[1])) {

                        //at this point it doesn't matter, set both to true in said network
                        network.put(parts[0], true);
                        network.put(parts[1], true);
                        personsExistsAlready = true;
                        System.out.println(network.size());

                        break;
                    }
                }

                if (!personsExistsAlready){
                    Map<String, Boolean> friendships = new HashMap<>();
                    friendships.put(parts[0], true);
                    friendships.put(parts[1], true);
                    networks.add(friendships);
                    System.out.println(friendships.size());

                }
            }
        }
    }
}
