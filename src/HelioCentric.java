import java.util.Scanner;

/**
 * Created by Dante on 2014-10-20.
 */
public class HelioCentric {
    public static void main(String[] Args) {
        Scanner sc = new Scanner(System.in);
        int caseNum = 0;
        while(sc.hasNextInt()){
            caseNum++;
            int a = sc.nextInt();
            int b = sc.nextInt();
            int counter = 0;
            if (a!=0||b!=0) {
                a= (a+(687-b))%365;
                counter+=(687-b);
                if (a!=0) {
                    while (a != 0) {
                        a = (a+687) % 365;
                        counter+=687;
                    }
                }
            }
            System.out.println("Case "+caseNum+": "+counter);
        }
    }
}
