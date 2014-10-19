import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
@ProblemName(name = "8queens")
public class EightQueens {

    static boolean[] cols = new boolean[8];
    static boolean[] minus = new boolean[16];
    static boolean[] plus= new boolean[16];

    public static void main(String[] Args) {
        Scanner sc = new Scanner(System.in);
        for (int y = 0; y < 8; y++) {
            String line = sc.nextLine();
            int x = -1;
            for (int i = 0; i < 8; i++) {
                if (line.charAt(i) == '*') {
                    if (x == -1) {
                        x = i;
                    } else {
                        System.out.println("invalid");
                        return;
                    }
                }
            }
            if (x==-1){
                System.out.println("invalid");
                return;
            }

                int plusIndex = x+y;
                int minusIndex = x-y + 8;

                if(cols[x] || plus[plusIndex]||minus[minusIndex]){
                    System.out.println("invalid");
                    return;
                }else{
                    cols[x]=true;
                    plus[plusIndex]=true;
                    minus[minusIndex]=true;
                }
        }
        System.out.println("valid");

    }
}