import java.util.Scanner;

/**
 * Created by Dante on 2014-10-20.
 */
public class NumberTree {
    public static void main(String[] Args) {
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        long l = (1l << i+1);
        long diff = 1;
        if(sc.hasNext()){
            String path = sc.next();
            for(char c : path.toCharArray()){
                if(c=='L')diff*=2;
                if(c=='R')diff=diff*2+1;
            }
        }
        l-=diff;
        System.out.println(l);
    }
}
