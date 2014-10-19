import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Scanner;

/**
 * Created by Dante on 2014-09-27.
 */
public class Units {


    static class Unit{
        public int scale;
        public String subUnit;
        public String unit;
        public Unit(String unit, int scale, String subUnit ){
            this.unit = unit;
            this.scale = scale;
            this.subUnit = subUnit;
            if (units.contains(subUnit)) units.remove(subUnit);
        }

    }

    public static HashSet<String> units;
    public static Hashtable<String, Unit> unitTable;

    public static void AddOrSwap(String unit, int scale, String subUnit){
        if(!unitTable.containsKey(unit)){
            for(Unit u : unitTable.values()){
                if (u.subUnit.equals(subUnit)){
                    if (u.scale >= scale){
                        unitTable.put(u.unit, new Unit(u.unit, u.scale/scale, unit));
                    }
                    else{
                        AddOrSwap(unit,scale/u.scale, u.unit);
                        return;
                    }
                }
            }
            unitTable.put(unit,new Unit(unit, scale,subUnit));
            return;

        }

        Unit oldUnit = unitTable.get(unit);

        if(scale >= oldUnit.scale){
            AddOrSwap(oldUnit.subUnit, scale/oldUnit.scale, subUnit);
            return;
        }

        Unit newUnit = new Unit(unit, scale, subUnit);
        unitTable.put(unit, newUnit);
        AddOrSwap(subUnit, oldUnit.scale / scale, oldUnit.subUnit);

    }

    public static String PrintUnits(String unit, int i){
        Unit u = unitTable.get(unit);
        if (u == null) return i + unit;
        else return i+ unit + " = " + PrintUnits(u.subUnit, i*u.scale);
    }

    public static void main(String[] Args) {
        Scanner sc = new Scanner(System.in);
        int n;
        while ((n = Integer.parseInt(sc.nextLine())) != 0) {
            unitTable = new Hashtable<>();
            units = new HashSet<>(Arrays.asList(sc.nextLine().split(" ")));
            for (int i = 0; i < n - 1; i++) {
                String[] tokens = sc.nextLine().split(" ");
                AddOrSwap(tokens[0], Integer.parseInt(tokens[2]), tokens[3]);
            }

            int i = 0;
            for (String s : units) {
                if (i++ > 0) System.out.println("fuck");
                System.out.println(PrintUnits(s, 1));
            }
        }
    }

}
