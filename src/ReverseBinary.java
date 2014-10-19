import java.util.Scanner;
import java.util.Stack;

/**
 * Created by Dante on 2014-10-09.
 */
public class ReverseBinary {

    public static void main(String[] Args) {
        Stack<Boolean> stack = new Stack<Boolean>();
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        while (i!=0){
            stack.push(i%2 == 1);
            i = i/2;
        }
        int res = 0;
        for(Boolean b : stack){
            res = res<<1;
            if(b){
                res+=1;
            }
        }
        System.out.print(res);
    }
}
