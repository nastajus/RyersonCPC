import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by Dante on 2014-09-27.
 */
public class FlipFive {
    public static HashMap<Integer, Integer> dynamic = new HashMap<>();
    public static Queue<Integer> problems = new LinkedList<>();
    static {
        problems.add(0);
        dynamic.put(0,0);
    }
    public static int[] Step(int old){
        int[] ret = new int[9];
        ret[0] =  old ^ 0b110100000;
        ret[1] =  old ^ 0b111010000;
        ret[2] =  old ^ 0b011001000;
        ret[3] =  old ^ 0b100110100;
        ret[4] =  old ^ 0b010111010;
        ret[5] =  old ^ 0b001011001;
        ret[6] =  old ^ 0b000100110;
        ret[7] =  old ^ 0b000010111;
        ret[8] =  old ^ 0b000001011;
        return ret;
    }

    public static int Search(int find){
        int current = problems.remove();
        int currentIndex = dynamic.get(current);
        int[] subset = Step(current);
        for(int i : subset){
            if(!dynamic.containsKey(i)){
                dynamic.put(i, currentIndex+1);
                problems.add(i);
                if(i == find) return currentIndex+1;
            }
        }
        return Search(find);
    }

    public static void main(String[] Args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();sc.nextLine();
        for (int i = 0; i <n; i++) {
            String puzzle = sc.nextLine()+sc.nextLine()+sc.nextLine();
            int x = 0;
            for(char c : puzzle.toCharArray()){
                if(c == '*') x = (x << 1) + 1;
                else x = (x << 1);
            }
            int answer;
            if (dynamic.containsKey(x)) answer = dynamic.get(x);
            else answer = Search(x);
            System.out.println(answer);

        }
    }
}
