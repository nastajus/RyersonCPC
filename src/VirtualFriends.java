import java.util.*;

/**
 * Created by IAN on 09/10/2016.
 */
public class VirtualFriends {
    public static void main(String args[]) {

        long tStart = System.currentTimeMillis();

        Scanner sc = new Scanner(System.in);

        String str = sc.nextLine();
        int numTestCases = Integer.parseInt(str);

        for (int tc = 0; tc < numTestCases; tc++) {

            List<Map<String, Boolean>> networks = new ArrayList<>();

            str = sc.nextLine();
            int numFriendshipsFormed = Integer.parseInt(str);

            for (int f = 0; f < numFriendshipsFormed; f++) {
                str = sc.nextLine();

                String regex = "\\s";    //space character, escaped once for regex, second for java
                String[] person = str.split(regex);

                NetworkPerson np1 = null;
                NetworkPerson np2 = null;

                outerLoop:
                //check existing networks for any matches
                for (Map<String, Boolean> network : networks) {

                    //both exist in same network
                    if (network.containsKey(person[0]) && network.containsKey(person[1])){
                        np1 = new VirtualFriends().new NetworkPerson(network, person[0]);
                        np2 = new VirtualFriends().new NetworkPerson(network, person[1]);
                        break;
                    }

                    //one exists in one network
                    if (network.containsKey(person[0])){
                        np1 = new VirtualFriends().new NetworkPerson(network, person[0]);

                        for(Map<String, Boolean> otherNetwork : networks){
                            //another exists in another network
                            if (otherNetwork != network && otherNetwork.containsKey(person[1])){
                                np2 = new VirtualFriends().new NetworkPerson(otherNetwork, person[1]);
                                break outerLoop;
                            }
                        }
                        break;
                    }
                    else if (network.containsKey(person[1])){
                        np1 = new VirtualFriends().new NetworkPerson(network, person[1]);

                        for(Map<String, Boolean> otherNetwork : networks){
                            if (otherNetwork != network && otherNetwork.containsKey(person[0])){
                                np2 = new VirtualFriends().new NetworkPerson(otherNetwork, person[0]);
                                break outerLoop;
                            }
                        }
                        break;
                    }
                }


                //exists in same network
                if (np1 != null && np2 != null && np1.network == np2.network){
                    //do nothing
                    System.out.println(np1.network.size());
                }
                //exists in one network only
                else if (np1 != null && np2 == null){
                    np1.network.put(person[1], true); //hoping by references works here... have my doubts.
                    System.out.println(np1.network.size());
                }
                //exists in two separate networks
                else if (np1 != null && np2 != null && np1.network != np2.network){
                    np1.network.putAll(np2.network); //hoping by references works here... have my doubts.
                    //networks.get(np1.network)   //doesn't seem to work
                    networks.remove(np2.network);
                    System.out.println(np1.network.size());
                }
                //exists nowhere
                else if (np1 == null && np2 == null){
                    Map<String, Boolean> newNetwork = new HashMap<>();
                    newNetwork.put(person[0],true);
                    newNetwork.put(person[1],true);
                    networks.add(newNetwork);
                    System.out.println(newNetwork.size());
                }
            }
        }

        long tEnd = System.currentTimeMillis();
        long tDelta = tEnd - tStart;
        double elapsedSeconds = tDelta / 1000.0;

        //System.out.println(elapsedSeconds);

    }

    public class NetworkPerson {
        public Map<String, Boolean> network;
        public String person;

        NetworkPerson(Map<String, Boolean> network, String person) {
            this.network = network;
            this.person = person;
        }

    }
}