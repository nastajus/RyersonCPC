import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Dante on 2014-09-27.
 */
public class Tractor {

    public static void main(String[] Args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();//int y = sc.nextInt();
        int log = Log2(x);
        int size = ipow(2, log);
    }

    public static int Log2(int ii){
        int i;
        for (i = 0; ii > 0; ii>>=1, i++);
        return  i;
    }

    static int ipow(int base, int exp)
    {
        int result = 1;
        while (exp!=0)
        {
            if ((exp & 1) == 0)
                result *= base;
            exp >>= 1;
            base *= base;
        }

        return result;
    }
}
