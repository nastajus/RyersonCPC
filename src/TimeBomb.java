import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Dante on 2014-10-20.
 */
public class TimeBomb {

    //static final String[][] elements = {
    //    {"***", "  *", "***", "***", "* *", "***", "***", "***", "***", "***"},
    //    {"* *", "  *", "  *", "  *", "* *", "*  ", "*  ", "  *", "* *", "* *"},
    //    {"* *", "  *", "***", "***", "***", "***", "***", "  *", "***", "***"},
    //    {"* *", "  *", "*  ", "  *", "  *", "  *", "* *", "  *", "* *", "  *"},
    //    {"***", "  *", "***", "***", "  *", "***", "***", "  *", "***", "***"}
    //};
    //static{
    //    for (int i = 0; i < 10; i++) {
    //        for (int j = 0; j < 5; j++) {
    //            System.out.print(elements[j][i]);
    //        }
    //        System.out.println();
    //    }
    //}
    static List<String> codes = Arrays.asList(
    "**** ** ** ****",
    "  *  *  *  *  *",
    "***  *****  ***",
    "***  ****  ****",
    "* ** ****  *  *",
    "****  ***  ****",
    "****  **** ****",
    "***  *  *  *  *",
    "**** ***** ****",
    "**** ****  ****");


    public static void main(String[] Args) {
        Scanner sc = new Scanner(System.in);
        String[] lines = {sc.nextLine(),sc.nextLine(),sc.nextLine(),sc.nextLine(),sc.nextLine()};
        String[] numbers = new String[lines[1].length()/4 +1];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i]="";
        }
        for(String line :lines) {
            for (int i = 0; i < line.length(); i++) {
                if (i%4!=3) numbers[i/4]+=line.charAt(i);
            }
        }
        if(codes.indexOf(numbers[numbers.length-1])%2!=0) {
            System.out.println("BOOM!!");
            return;
        }

        int counter=0;
        for (String num : numbers) {
            int x = codes.indexOf(num);
            if (x <0) {
                System.out.println("BOOM!!");
                return;
            }
            counter+=x;
        }

        System.out.println(counter%3==0?"BEER!!":"BOOM!!");



    }
}
