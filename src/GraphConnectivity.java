import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Dante on 2014-09-25.
 */
public class GraphConnectivity {

    static ArrayList<ArrayList<Integer>> graph;
    static boolean[] visited;

    public static void main(String[] Args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); sc.nextLine();sc.nextLine();
        for (int i = 0; i<n ; i++) {
            if (i!=0)
                System.out.println();
            String line = sc.nextLine();
            int max = line.charAt(0)-'A';
            graph  = new ArrayList<>(max+1);
            visited = new boolean[max+1];
            for (int j = 0; j <= max; j++){
                graph.add(new ArrayList<Integer>());
            }
            int count = 0;
            while(sc.hasNextLine())
            {
                line = sc.nextLine();
                if (line.isEmpty()) break;
                int a = line.charAt(0)-'A';
                int b = line.charAt(1)-'A';

                graph.get(a).add(b);
                graph.get(b).add(a);
            }

            //for (ArrayList<Integer> a : graph){
            //    for (Integer in : a){
            //        System.out.print(in + " ");
            //    }
            //    System.out.println(":");
            //}

            int result=0;
            for(int j = max; j>=0 ;j--){
                if (!visited[j]){
                    result++;
                    DPS(j);
                }
            }
            System.out.println(result);
        }
    }

    static void DPS(int index){
        visited[index] = true;
        //System.out.println((char)(index+'A'));
        for(Integer i : graph.get(index)){
            if(visited[i]) continue;
            else DPS(i);
        }
    }
}
