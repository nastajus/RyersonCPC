import java.util.Scanner;

/**
 * Created by Dante on 2014-10-09.
 */
public class Easiest {
    public static void main(String[] Args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()){
            int n = sc.nextInt();
            if (n == 0) break;
            int nsum = DigitSum(n);
            for (int p = 11; p < Integer.MAX_VALUE; p++) {
                if(nsum == DigitSum(n*p)){
                    System.out.println(p);
                    break;
                }
            }
        }
    }
    public static int DigitSum(int i){
        int total = 0;
        while(i!=0){
            total+=i%10;
            i /= 10;
        }
        return total;
    }
}
